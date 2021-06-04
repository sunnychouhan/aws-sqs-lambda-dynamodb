package com.chousun.awssqslambdadynamodb.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.chousun.awssqslambdadynamodb.dbconfig.DynamoDBConfig;
import com.chousun.awssqslambdadynamodb.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {

    DynamoDBConfig dynamoDBConfig = new DynamoDBConfig();
    private DynamoDBMapper mapper =  dynamoDBConfig.getMapper();


    public Employee addPerson(Employee employee) {
        mapper.save(employee);
        return employee;
    }

    public Employee findPersonByPersonId(String personId) {
        return mapper.load(Employee.class, personId);
    }

    public List<Employee> findAllRecords() {
        PaginatedScanList<Employee> scan = mapper.scan(Employee.class, new DynamoDBScanExpression());
        List<Employee> collect = scan.stream()
                .collect(Collectors.toList());
        return collect;
    }

    public String deletePerson(Employee employee) {
        mapper.delete(employee);
        return "person removed !!";
    }

    public String editPerson(Employee employee) {
        mapper.save(employee, buildExpression(employee));
        return "record updated ...";
    }

    private DynamoDBSaveExpression buildExpression(Employee employee) {
        DynamoDBSaveExpression dynamoDBSaveExpression = new DynamoDBSaveExpression();
        Map<String, ExpectedAttributeValue> expectedMap = new HashMap<>();
        expectedMap.put("EmployeeId", new ExpectedAttributeValue(new AttributeValue().withS(employee.getEmployeeId())));
        dynamoDBSaveExpression.setExpected(expectedMap);
        return dynamoDBSaveExpression;
    }


}
