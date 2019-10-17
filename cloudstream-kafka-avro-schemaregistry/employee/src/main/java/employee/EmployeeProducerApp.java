package employee;

import employee.avro.schema.Employee;
import employee.avro.schema.EmployeeKey;
import employee.producer.EmployeeProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.schema.client.EnableSchemaRegistryClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Random;

/*
 * ---------------------------------------------------------------------------------
 * Avro Maven Plugin
 * ---------------------------------------------------------------------------------
 * avro plugin generates schema classes under employee.avro.schema.*
 *
 * - employee schema: resources/employee-schema.avsc
 * - employee partition key: resources/employee-key-schema.avsc
 *
 * ---------------------------------------------------------------------------------
 * Schema changes
 * ---------------------------------------------------------------------------------
 * - topic must be cleared (most probably...)
 * - maybe... existing schemas have to be deleted from schema registry
 * -> is there no schema version increment??
 *
 * ---------------------------------------------------------------------------------
 * Modeling
 * ---------------------------------------------------------------------------------
 * - avro classes are not related
 * - EmployeeKey is not used by Employee
 * - maybe add another Entity layer?
 *
 */
@SpringBootApplication
@EnableBinding(Processor.class)
@EnableSchemaRegistryClient
@EnableScheduling
public class EmployeeProducerApp {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeProducerApp.class, args);
    }

    @Autowired
    EmployeeProducer producer;

    @Scheduled(fixedRate = 1000)
    public void modifyTodos() {
        final Employee employee = createEmployee("Paul", "McCartney");
        final EmployeeKey key = createEmployeeKey(employee.getId(), "IT");

        producer.produceEmployeeDetails(employee.getId(), "Paul", "McCartney");
    }

    private Employee createEmployee(final String firstName, final String lastName) {
        return new Employee(new Random().nextInt(),
                firstName, lastName);
    }

    private EmployeeKey createEmployeeKey(final Integer employeeId, final String department) {
        return new EmployeeKey(employeeId, department);
    }

}
