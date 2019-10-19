package employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.cloud.stream.schema.client.EnableSchemaRegistryClient;

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
 * Schema Registry
 * ---------------------------------------------------------------------------------
 * - clean install generates classes & puts schema to Schema Registry
 * - schemas are also written to kafka (Topic: _schemas)
 *
 * ---------------------------------------------------------------------------------
 * Schema changes
 * ---------------------------------------------------------------------------------
 * - schema version is incremented on Schema Registry
 * - topic must be cleared
 * - schema must be deleted from Schema Registry (most probably...)
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
public class EmployeeAvroApp {

    public static void main(final String[] args) {
        SpringApplication.run(EmployeeAvroApp.class, args);
    }


}
