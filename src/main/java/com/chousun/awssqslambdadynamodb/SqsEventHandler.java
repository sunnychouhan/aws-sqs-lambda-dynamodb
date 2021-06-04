package com.chousun.awssqslambdadynamodb;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.chousun.awssqslambdadynamodb.entity.Employee;
import com.chousun.awssqslambdadynamodb.repository.EmployeeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqsEventHandler implements RequestHandler<SQSEvent, String> {
    private static final Logger logger = LoggerFactory.getLogger(SqsEventHandler.class);
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

//    @Autowired
    private EmployeeRepository repository = new EmployeeRepository();

    @Override
    public String handleRequest(SQSEvent event, Context context) {
        String response = new String("200 OK");

        ObjectMapper objectMapper = new ObjectMapper();

        for (SQSEvent.SQSMessage record : event.getRecords()) {
            logger.info(record.toString());
//            logger.info(record.getAwsRegion());
//            logger.info("message body :" + body);
//            logger.info("Event Source :" + record.getEventSource());
//            logger.info("Event Source Arn:" + record.getEventSourceArn());
//            logger.info("Receipt Handler :" + record.getReceiptHandle());
//            logger.info("Message Id :" + record.getMessageId());
//            logger.info("Attributes :" + record.getAttributes().toString());
//            logger.info("Md5OfMessageAttributes : " + record.getMd5OfMessageAttributes());
            String body = record.getBody();
            Employee persistDbynamodbRecord = null;
            try {
                Employee sqsMessageBody = objectMapper.readValue(body, Employee.class);
                persistDbynamodbRecord = buildEmployeeDbRecord(sqsMessageBody);
                repository.addPerson(persistDbynamodbRecord);
                logger.info("Successfully Persisted Employee : {}", sqsMessageBody);
            } catch (JsonProcessingException e) {
                logger.error("Object Mapper failed to Deserialize Object: {} Exception {} ", body, e);
            } catch (Exception e) {
                logger.error("Failed Persisting Record to Dynamodb : {} exception : {}", persistDbynamodbRecord, e);
            }
        }
        logger.info("Successfully processed " + event.getRecords().size() + " records.");
        Util.logEnvironment(event, context, gson);
        return response;
    }

    private Employee buildEmployeeDbRecord(Employee employee) {
        Employee empRecord = new Employee();
        empRecord.setAddress(employee.getAddress());
        empRecord.setEmail(employee.getEmail());
        empRecord.setName(employee.getName());
        empRecord.setAge(employee.getAge());
        return employee;
    }
}
