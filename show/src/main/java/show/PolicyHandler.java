package show;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;
import show.config.kafka.KafkaProcessor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHandler{

    @Autowired
    ShowRepository showRepository;

    @StreamListener(KafkaProcessor.INPUT_1)
    public void wheneverBooked_TicketQtyChange(@Payload Booked booked){
        String resultCode = "S";
        if ("Booked".equals(booked.getEventType())) {
            if(booked.isMe()){
                try {
                    Optional<Show> showOption = showRepository.findById(booked.getShowId());

                    if (showOption.isPresent()) {
                        if (showOption.get().getRemainCount() >= booked.getQty()){
                            showOption.get().setRemainCount(showOption.get().getRemainCount() - booked.getQty());
                            showRepository.save(showOption.get());
                        } else {
                            resultCode = "F";
                        }
                    } else {
                        resultCode = "F";
                    }
                } catch (Exception e) {
                    resultCode = "F";
                } finally {
                    if ("F".equals(resultCode)) {
                       TicketQtyChanged ticketQtyChanged = new TicketQtyChanged();
                       ticketQtyChanged.setBookId(booked.getId());
                       ticketQtyChanged.setResultCode(resultCode);

                       ObjectMapper objectMapper = new ObjectMapper();
                       String json = null;

                       try {
                           json = objectMapper.writeValueAsString(ticketQtyChanged);
                       } catch (JsonProcessingException e) {
                            e.printStackTrace();
                       }

                       KafkaProcessor kafkaProcessor = Application.applicationContext.getBean(KafkaProcessor.class);
                       MessageChannel outputChannel = kafkaProcessor.outboundTopic1();

                       outputChannel.send(MessageBuilder
                               .withPayload(json)
                               .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                               .build()
                       );
                    }
                }
            }
        }
    }
    @StreamListener(KafkaProcessor.INPUT_2)
    public void wheneverBookingCancelled_TicketQtyChange(@Payload BookingCancelled bookingCancelled){
        String resultCode = "S";
        if ("BookingCancelled".equals(bookingCancelled.getEventType())) {
            if(bookingCancelled.isMe()){
                try {
                    Optional<Show> showOption = showRepository.findById(bookingCancelled.getShowId());

                    if (showOption.isPresent()) {
                        showOption.get().setRemainCount(showOption.get().getRemainCount() + bookingCancelled.getQty());
                        showRepository.save(showOption.get());
                    } else {
                        resultCode = "F";
                    }
                } catch (Exception e) {
                    resultCode = "F";
                } finally {
                    if ("F".equals(resultCode)) {
                        TicketQtyChanged ticketQtyChanged = new TicketQtyChanged();
                        ticketQtyChanged.setBookId(bookingCancelled.getId());
                        ticketQtyChanged.setResultCode(resultCode);

                        ObjectMapper objectMapper = new ObjectMapper();
                        String json = null;

                        try {
                            json = objectMapper.writeValueAsString(ticketQtyChanged);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }

                        KafkaProcessor kafkaProcessor = Application.applicationContext.getBean(KafkaProcessor.class);
                        MessageChannel outputChannel = kafkaProcessor.outboundTopic1();

                        outputChannel.send(MessageBuilder
                                .withPayload(json)
                                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                                .build()
                        );
                    }
                }
            }
        }
    }

}
