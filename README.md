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

Reporting Line Rules <br/>
The reporting line is the chain of managers between an employee and the CEO. <br/>
Count the number of managers above the employee in this hierarchy.<br/>
Any employee with more than 4 managers between them and the CEO is considered to have a “too long” reporting line.<br/>
The number of exceeding managers is calculated as:<br/>
exceed_count = reporting_line_length - 4 <br/>
Id,firstName,lastName,salary,managerId <br/>
1,CEO,Boss,150000,<br/>
2,L1,M1,120000,1 <br/>
3,L2,M2,110000,2 <br/>
4,L3,M3,100000,3 <br/>
5,L4,M4,90000,4 <br/>
6,L5,M5,80000,5 <br/>
7,L6,M6,70000,6 <br/>
8,E1,Employee,60000,7<br/>

CEO Boss <br/>
 └─ L1 M1 <br/>
      └─ L2 M2 <br/>
           └─ L3 M3 <br/>
                └─ L4 M4 <br/>
                     └─ L5 M5 <br/>
                          └─ L6 M6 <br/>
                               └─ E1 Employee <br/>


Reporting Line Violations: <br/>
L5 M5 has 5 managers above → exceeds limit by 1.<br/>
L6 M6 has 6 managers above → exceeds limit by 2.<br/>
E1 Employee has 7 managers above → exceeds limit by 3.<br/>
Employees L1–L4 are within the limit → no violation reported.<br/>
Output Example for Reporting Line Violations:<br/>
Reporting line too long: L5 M5 exceeds by 1 managers<br/>
Reporting line too long: L6 M6 exceeds by 2 managers<br/>
Reporting line too long: E1 Employee exceeds by 3 managers<br/>

3.Edge cases<br/>
Assuming there is  no circular references (i.e., no manager is indirectly their own subordinate).<br/> 
All IDs are integers. <br/>

Additional Resources

### Detailed Assumptions: See src/main/resources/Assumption.txt for comprehensive test scenarios
### Large Scale Testing: 1000-line test case included for performance validation
