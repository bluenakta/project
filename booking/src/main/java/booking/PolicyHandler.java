package booking;

import booking.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHandler{

    @Autowired
    BookingRepository bookingRepository;

    @StreamListener(KafkaProcessor.INPUT1)
    public void wheneverTicketQtyChanged_BookingStatusChange(@Payload TicketQtyChanged ticketQtyChanged){

        if(ticketQtyChanged.isMe()){
            if ( "F".equals(ticketQtyChanged.getResultCode()) ) {
                Optional<Booking> bookingOptional = bookingRepository.findById(ticketQtyChanged.getBookId());
                bookingOptional.get().setBookStatus("Failed");
                bookingRepository.save(bookingOptional.get());
            }
            System.out.println("##### listener BookingStatusChange : " + ticketQtyChanged.toJson());
        }
    }
}
