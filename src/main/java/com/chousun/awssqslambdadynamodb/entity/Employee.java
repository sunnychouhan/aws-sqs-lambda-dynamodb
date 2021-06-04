package com.chousun.awssqslambdadynamodb.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "Employee")
public class Employee implements Serializable {
    @DynamoDBHashKey  (attributeName = "EmployeeId")
    @DynamoDBAutoGeneratedKey
    private String employeeId;
    @DynamoDBAttribute
    private String name;
    @DynamoDBAttribute
    private int age;
    @DynamoDBAttribute
    private String email;
    @DynamoDBAttribute
    private Address address;
}