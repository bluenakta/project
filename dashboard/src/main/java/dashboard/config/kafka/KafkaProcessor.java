package dashboard.config.kafka;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface KafkaProcessor {

    String INPUT_1 = "event-in1";
    String INPUT_2 = "event-in2";
    String INPUT_3 = "event-in3";
    String INPUT_4 = "event-in4";
    String INPUT_5 = "event-in5";

    String OUTPUT_1 = "event-out1";

    @Input(INPUT_1)
    SubscribableChannel inboundTopic1();
    @Input(INPUT_2)
    SubscribableChannel inboundTopic2();
    @Input(INPUT_3)
    SubscribableChannel inboundTopic3();
    @Input(INPUT_4)
    SubscribableChannel inboundTopic4();
    @Input(INPUT_5)
    SubscribableChannel inboundTopic5();

    @Output(OUTPUT_1)
    MessageChannel outboundTopic1();

}
