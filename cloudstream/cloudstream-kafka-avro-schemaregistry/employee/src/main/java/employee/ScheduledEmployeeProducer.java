package employee;

import employee.avro.schema.Employee;
import employee.producer.EmployeeProducer;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Random;

@EnableScheduling
@Configuration
public class ScheduledEmployeeProducer {
    private final EmployeeProducer producer;

    public ScheduledEmployeeProducer(final EmployeeProducer producer) {
        this.producer = producer;
    }

    @Scheduled(fixedRate = 1000)
    public void publishWild() {
        final Employee employee = createEmployee("Paul", "McCartney", "IT");
        producer.publish(employee);
    }

    private Employee createEmployee(final String firstName, final String lastName, final String department) {
        return new Employee(new Random().nextInt(),
                firstName, lastName, department);
    }
}
