package employee.controller;

import employee.producer.EmployeeProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AvroController {

    @Autowired
    private EmployeeProducer avroProducer;

    @PostMapping("/employees/{id}/{firstName}/{lastName}")
    public String producerAvroMessage(@PathVariable int id, @PathVariable String firstName, @PathVariable String lastName) {
        avroProducer.produceEmployeeDetails(id, firstName, lastName);
        return "Sent employee details to consumer";
    }
}
