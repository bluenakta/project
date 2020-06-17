package pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import pay.config.kafka.KafkaProcessor;

@Service
public class PolicyHandler{
	
	@Autowired
	PaymentRepository paymentRepository;
    
    @StreamListener(KafkaProcessor.INPUT_1)
    public void wheneverBooked_PaymentRequest(@Payload Booked booked){

        if(booked.isMe()){
        	
        	Payment payment = new Payment();
        	payment.setBookId(booked.getId());
        	payment.setStatus("Payed");
        	
        	paymentRepository.save(payment);
            
        }
    }
    
    @StreamListener(KafkaProcessor.INPUT_2)
    public void wheneverBookingCancelled_PaymentCancel(@Payload BookingCancelled bookingCancelled){

        if(bookingCancelled.isMe()) {
        	Payment paymentCanceled = paymentRepository.findByBookId(bookingCancelled.getId());
        	paymentCanceled.setStatus("Cancelled");
        	paymentRepository.save(paymentCanceled);
        	
        }
    }

}
