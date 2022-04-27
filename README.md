# TIGER CARD FARE CALCULATOR
## Problem Statement
Build a payment system for public metro transport.
Given a list of travel zones, fares for peak/off-peak hours, dailycap and weeklycap rates,
calculate the total fare incurred by the customer based on his travel data.

Detailed problem statement can be found in TigerCard.pdf

## Assumptions
### Timestamp format
Travel time in the input records are in ISO 8601 format
### Input order
Ordered by timestamp in ascending order

## Technologies Used
Programming Language : Java 8  
Build Tool : maven

## Execution Steps
### Prerequisites
Java and maven already installed.

### Building the Jar
1. Download or clone the project - https://github.com/hariguru7777/SahajFareCalculator
2. Build the project. From project-root folder give  
#### Build command
  ```mvn install```  
    
FareCalculator-{version}.jar will be generated in project-root/target folder

### Executing the Jar 
Execution can be done in two ways.  
#### 1. With default input.
Sample input of journeys are present in project-root/src/main/resources/InputJourneyData.txt folder.  
To run with default input.
##### Run command
  ```java -jar project-root/target/FareCalculator-1.0.0.jar```  
  
#####  Sample Output:  
  ```Total Fare :690```  
  
#### 2. With file input.
This file will have a list of test cases that can be executed. Useful for system testing
##### File content format
1. First line denotes the number of test cases. Let say N
2. Next line denotes number of journey records for first/next test case. Let say M
3. Next M lines has the list od journeys with each line representing a single journey.
4. 2 and 3 repeated for N test cases.  

Sample file can be found in project-root/sampleInputs/input_file  

To run with the file input, filename is passed as argument.  
##### Run command
```java -jar project-root/target/FareCalculator-1.0.0.jar inputFilePath```  

##### Sample Output:  
  ```
     Testcase:1	Total Fare :35  
     Testcase:2	Total Fare :690
  ```
### Unit Testing 
Tests run: 40, Failures: 0, Errors: 0, Skipped: 0
### Code Coverage
|class|Method|Line|Branch|
|-----|------|----|------|
| 90% | 95%  |83% | 88%  |

Only main class Application.java is not covered in code coverage.
Detailed report can be found in the path project-root/target/jacoco.exec  

### Integration testing
Integration testing is done by giving inputs for various scenarios to the application.  
Sample inputs can be found in <project-root>/sampleInputs/input_file2
  
