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
A CSV file in the following format:

Id,firstName,lastName,salary,managerId
123,Joe,Doe,60000,
124,Martin,Chekov,45000,123
125,Bob,Ronstad,47000,123
300,Alice,Hasacat,50000,124
305,Brett,Hardleaf,34000,300

### Output Format
Martin Manager1 earns too LITTLE by 12000.00
Laura Manager2 earns too LITTLE by 10000.00
Peter Manager3 earns too LITTLE by 8000.00
Susan Manager4 earns too LITTLE by 6000.00
Raj Manager5 earns too LITTLE by 4000.00
Reporting line too long: Raj Manager5 exceeds by 1 managers
Reporting line too long: Alice EmployeeX exceeds by 2 managers

Additional Resources

## Detailed Assumptions: See src/main/resources/Assumption.txt for comprehensive test scenarios
## Large Scale Testing: 1000-line test case included for performance validation
