package employee.consumer;

import employee.avro.schema.Employee;
import employee.avro.schema.EmployeeKey;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class EmployeeConsumer {

    @StreamListener(Processor.INPUT)
    public void consume(final Employee employee) {
        System.out.println(">> EmployeeConsumer value=" + employee);
    }

    @StreamListener(Processor.INPUT)
    public void consumeKey(@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) final EmployeeKey key,
                           @Payload final Employee employee) {
        System.out.println(">> EmployeeConsumer key=" + key);
    }

}
