package employee.producer;

import employee.avro.schema.Employee;
import employee.avro.schema.EmployeeKey;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeProducer {
    private final Processor processor;

    public EmployeeProducer(final Processor processor) {
        this.processor = processor;
    }

    public void publish(final Employee employee) {
        final EmployeeKey employeeKey = createPartitionKey(employee);

        processor.output().send(MessageBuilder.withPayload(employee)
                .setHeader(KafkaHeaders.MESSAGE_KEY, employeeKey)
                .build());
    }

    private EmployeeKey createPartitionKey(final Employee employee) {
        return new EmployeeKey(employee.getId(), employee.getDepartment());
    }
}
