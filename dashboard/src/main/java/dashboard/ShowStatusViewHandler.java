package dashboard;

import dashboard.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShowStatusViewHandler {
    @Autowired
    private ShowStatusRepository showStatusRepository;

    @StreamListener(KafkaProcessor.INPUT_1)
    public void whenBooked_then_CREATE_1 (@Payload Booked booked) {
        try {
            if (booked.isMe()) {
                ShowStatus showStatus = new ShowStatus();
                showStatus.setBookId(booked.getId());
                showStatus.setShowName(booked.getShowName());
                showStatus.setPayAmount(booked.getAmount() * booked.getQty());
                showStatusRepository.save(showStatus);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT_2)
    public void whenBookingCancelled_then_UPDATE_1(@Payload BookingCancelled bookingCancelled) {
        try {
            if (bookingCancelled.isMe()) {
                ShowStatus showStatus = showStatusRepository.findByBookId(bookingCancelled.getId());
                showStatus.setPayStatus("Cancelled");
                showStatusRepository.save(showStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT_3)
    public void whenPayed_then_UPDATE_2(@Payload Payed payed) {
        try {
            if (payed.isMe()) {
                ShowStatus showStatus = showStatusRepository.findByBookId(payed.getBookId());
                showStatus.setPayStatus(payed.getStatus());
                showStatusRepository.save(showStatus);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT_4)
    public void whenCancelled_then_UPDATE_3(@Payload Cancelled cancelled) {
        try {
            if (cancelled.isMe()) {
                ShowStatus showStatus = showStatusRepository.findByBookId(cancelled.getBookId());
                showStatus.setPayStatus(cancelled.getStatus());
                showStatusRepository.save(showStatus);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT_5)
    public void whenIssueStatusChanged_then_UPDATE_4(@Payload IssueStatusChanged issueStatusChanged) {
        try {
            if (issueStatusChanged.isMe()) {
                ShowStatus showStatus = showStatusRepository.findByBookId(issueStatusChanged.getBookId());
                showStatus.setIssueStatus(issueStatusChanged.getIssueStatus());
                showStatusRepository.save(showStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}