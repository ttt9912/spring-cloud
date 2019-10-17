package employee.producer;

import employee.avro.schema.Employee;
import employee.avro.schema.EmployeeKey;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeProducer {
    private final Processor processor;

    public EmployeeProducer(final Processor processor) {
        this.processor = processor;
    }

    public void produceEmployeeDetails(int empId, String firstName, String lastName) {

        // creating employee details
        Employee employee = new Employee();
        employee.setId(empId);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);

        // creating partition key for kafka topic
        EmployeeKey employeeKey = new EmployeeKey();
        employeeKey.setId(empId);
        employeeKey.setDepartmentName("IT");

        Message<Employee> message = MessageBuilder.withPayload(employee)
                .setHeader(KafkaHeaders.MESSAGE_KEY, employeeKey)
                .build();

        processor.output().send(message);
    }
}
