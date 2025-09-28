# SwissRe_Exercise_106

This repository contains a code exercise solution for **SwissRe – Company Structure Analyzer**.

---

## Problem Statement
BIG COMPANY wants to analyze its organizational structure to:

1. **Salary Compliance**  
   Ensure every manager earns at least **20% more** than the average salary of their direct subordinates, and **not more than 50% more** than that average.

2. **Reporting Line Depth**  
   Identify employees who have **more than 4 managers** between them and the CEO.

### Input Format
A CSV file in the following format:<br/>
Id,firstName,lastName,salary,managerId <br/>
123,Joe,Doe,60000,<br/>
124,Martin,Chekov,45000,123 <br/>
125,Bob,Ronstad,47000,123 <br/>
300,Alice,Hasacat,50000,124 <br/>
305,Brett,Hardleaf,34000,300 <br/>

### Output Format
Martin Manager1 earns too LITTLE by 12000.00 <br/>
Laura Manager2 earns too LITTLE by 10000.00 <br/>
Peter Manager3 earns too LITTLE by 8000.00 <br/>
Susan Manager4 earns too LITTLE by 6000.00 <br/>
Raj Manager5 earns too LITTLE by 4000.00 <br/>
Reporting line too long: Raj Manager5 exceeds by 1 managers <br/>
Reporting line too long: Alice EmployeeX exceeds by 2 managers <br/>

### Below is my assumption of this problem
1. CEO Identification <br/>
The CEO has no manager (managerId is empty or null).<br/>

2. Salary Compliance <br/>
A manager’s direct subordinates are only the employees who list that manager’s Id as their managerId.<br/>
Salary rules:<br/>
Minimum: 20% more than the average salary of direct subordinates.<br/>
Maximum: 50% more than the average salary of direct subordinates.<br/>
Only direct subordinates are used to calculate the average, not indirect reports.<br/>

3.Edge cases<br/>
Assuming there is  no circular references (i.e., no manager is indirectly their own subordinate).<br/> 
All IDs are integers. <br/>

Additional Resources

### Detailed Assumptions: See src/main/resources/Assumption.txt for comprehensive test scenarios
### Large Scale Testing: 1000-line test case included for performance validation
