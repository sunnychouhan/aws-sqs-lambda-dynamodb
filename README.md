# ASW SQS Event Trigger Lambda which in return insert Record In Dynamodb
Pre requisit:
*  Create SQS Queue, Dynamodb and Lambda Function in AWS Console
*  Add SQS Event to trigger Lambda
*  Create SQS Role to trigger Lambda

Implementation :
 * Go to Root of the Project 
 * Run mvn package
 * Upload the jar created in Target Folder to lambda
 * Under Handler code section add the below lines "com.chousun.awssqslambdadynamodb.SqsEventHandler::handleRequest"

Test :
* go to SQS and publish db record in json format
* verify Table in Dynamodb


