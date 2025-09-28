# SwissRe_Exercise_106
This Repository contains the Code exercise solution given by SwissRe
Company Structure Analyzer 
 Problem Statement
BIG COMPANY wants to analyze its organizational structure to:
Salary Compliance
Ensure every manager earns at least 20% more than the average salary of their direct subordinates,
and not more than 50% more than that average.
Reporting Line Depth
Identify employees who have more than 4 managers between them and the CEO.
Input is provided as a CSV file with the following format:
Id,firstName,lastName,salary,managerId
123,Joe,Doe,60000,
124,Martin,Chekov,45000,123
125,Bob,Ronstad,47000,123
300,Alice,Hasacat,50000,124
305,Brett,Hardleaf,34000,300
CEO has no managerId.
 Features
Reads employee data from a CSV file (up to 1000 rows).
Builds an in-memory organization graph from the CSV.
Reports:
Managers earning too little or too much (and by how much).
Employees whose reporting line is too long, and by how many managers.
 Technology Stack
Java SE (pure Java, no frameworks)
JUnit 4(for unit testing)
Maven (for project structure and build)
📂 Project Structure
src/
├─ main/
│   ├─ java/
│   │    └─ org/amit/
│   │         ├─ Main.java          
│   │         ├─ Entity/Employee.java
│   │         └─ Utility/CsvReader.java
│   └─ resources/
│        └─ Employee.csv            # Input CSV file
└─ test/
└─ java/…                      # JUnit tests (if added)
⚙️ How It Works
1️ CsvReader
Reads the CSV file and converts each line into an Employee object.
Column order is not hardcoded—the header row is used to detect columns.
2️Organization Graph
Employees are stored in a Map<id, Employee>.
The hierarchy is built by linking each employee to their manager.
CEO is identified as the employee with no managerId.
3️ Analysis (Main.analyzeCompanyStructure)
Salary check:
Calculates direct subordinates’ average salary.
Verifies manager salary is between 120% and 150% of that average.
Reporting depth check:
Recursively traverses the hierarchy (DFS).
Flags employees with >4 managers above them.
▶️ Running the Application
Prerequisites
Java 8 or later
Maven 3.x
Build & Run
# Compile and package
mvn clean package

# Run the program
java -cp target/company-structure-analyzer-1.0-SNAPSHOT.jar org.amit.Main
Ensure src/main/resources/Employee.csv contains the input data
or pass your own file path in Main.java.
🧪 Running Tests
mvn test
🖥️ Sample Output
For the sample CSV provided:
Employee ceo     = new Employee("1", "Carol", "CEO",      150000, null);
Employee emp1    = new Employee("2", "Martin","Manager1", 120000, "1");
Employee emp2    = new Employee("3", "Laura", "Manager2", 110000, "2");
Employee emp3    = new Employee("4", "Peter", "Manager3", 100000, "3");
Employee emp4    = new Employee("5", "Susan", "Manager4",  90000, "4");
Employee emp5    = new Employee("6", "Raj",   "Manager5",  80000, "5");
Employee emp6    =    new Employee("7", "Alice", "EmployeeX", 70000, "6");

Output will be:
Martin Manager1 earns too LITTLE by 12000.00
Laura Manager2 earns too LITTLE by 10000.00
Peter Manager3 earns too LITTLE by 8000.00
Susan Manager4 earns too LITTLE by 6000.00
Raj Manager5 earns too LITTLE by 4000.00
Reporting line too long: Raj Manager5 exceeds by 1 managers
Reporting line too long: Alice EmployeeX exceeds by 2 managers

Further Test case i have attached in Attachment file
 Assumptions
CSV may have columns in any order, but header names must exist.
Empty salary is treated as 0.0.
Empty or missing managerId means the employee is the CEO.
If no CEO is found, the application throws an exception.
 Key Points
Uses Depth-First Search (DFS) for hierarchy traversal.
Clean, readable code with JavaDocs for key methods.
Fully Mavenized—easy to build, run, and test.

Author: Amit Pandey