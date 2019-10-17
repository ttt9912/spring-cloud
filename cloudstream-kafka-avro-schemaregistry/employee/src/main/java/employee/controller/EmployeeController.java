package employee.controller;

import employee.avro.schema.Employee;
import employee.producer.EmployeeProducer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * curl -X POST localhost:8080/employees/1001/Harry/Potter
 */
@RestController
public class EmployeeController {
    private final EmployeeProducer avroProducer;

    public EmployeeController(final EmployeeProducer avroProducer) {
        this.avroProducer = avroProducer;
    }

    @PostMapping("/employees/{id}/{firstName}/{lastName}")
    public void producerAvroMessage(@PathVariable int id, @PathVariable String firstName,
                                    @PathVariable String lastName, @PathVariable String department) {
        avroProducer.publish(new Employee(id, firstName, lastName, department));
    }
}
