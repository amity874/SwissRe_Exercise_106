import org.amit.Entity.Employee;
import org.amit.Main;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class EmployeeTest {
    private Employee ceo;
    Map<String, Employee> empMap = new HashMap<>();
    private Employee notManager;
    @Before
    public void setUp() {
        // Arrange
        ceo   = new Employee("1", "Carol", "CEO",      150000, null);
        Employee emp1    = new Employee("2", "Martin","Manager1", 120000, "1");
        Employee emp2    = new Employee("3", "Laura", "Manager2", 110000, "2");
        Employee emp3    = new Employee("4", "Peter", "Manager3", 100000, "3");
        Employee emp4    = new Employee("5", "Susan", "Manager4",  90000, "4");
        Employee emp5    = new Employee("6", "Raj",   "Manager5",  80000, "5");
        Employee emp6 = new Employee("7", "Alice", "EmployeeX", 70000, "6");
        empMap.put(ceo.getId(), ceo);
        empMap.put(emp1.getId(), emp1);
        empMap.put(emp2.getId(), emp2);
        empMap.put(emp3.getId(), emp3);
        empMap.put(emp4.getId(), emp4);
        empMap.put(emp5.getId(), emp5);
        empMap.put(emp6.getId(), emp6);
      // Build hierarchy
        ceo.getSubordinates().add(emp1);
        emp1.getSubordinates().add(emp2);
        emp2.getSubordinates().add(emp3);
        emp3.getSubordinates().add(emp4);
        emp4.getSubordinates().add(emp5);
        emp5.getSubordinates().add(emp6);
        notManager=emp6;
    }
    @Test
    public void isManger() {
        boolean res=ceo.isManager();
        assertTrue("CEO should be a manager", res);
    }
    @Test
    public void notaManger() {
        boolean res=notManager.isManager();
        assertFalse(" should not be a manager", res);
    }
    @Test
    public void testAnalyzeCompanyStructure() {
        // Act & Assert
        try{
            Main.analyzeCompanyStructure(empMap, ceo, 0);
        }
        catch (Exception e) {
            fail("Unexpected exception: " + e);
        }
    }
    @Test
   public void analyzeCompanyStructure_outputsExpectedMessages() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));
        try {
            Main.analyzeCompanyStructure(empMap, ceo, 0);
        } finally {
            System.setOut(originalOut);
        }
        String output = out.toString();
        System.out.println(output); // For debugging purposes
        assertTrue(output.contains("Martin Manager1 earns too LITTLE by 12000.00"));
        assertTrue(output.contains("Laura Manager2 earns too LITTLE by 10000.00"));
        assertTrue(output.contains("Peter Manager3 earns too LITTLE by 8000.00"));
        assertTrue(output.contains("Susan Manager4 earns too LITTLE by 6000.00"));
        assertTrue(output.contains("Raj Manager5 earns too LITTLE by 4000.00"));
        assertTrue("Raj must be reported as exceeding by 1 manager",
                output.contains("Reporting line too long: Raj Manager5 exceeds by 1 managers"));
        assertFalse("Alice must be reported as exceeding by 1 manager",
                output.contains("Reporting line too long: Raj EmployeeX exceeds by 2 manager"));
        /*
        in this case actual output will be
        Reporting line too long: Alice EmployeeX exceeds by 2 managers
        but here i have given ->  Raj EmployeeX exceeds by 2 manager hence our test case will pass because i am doing assertFalse
         */
    }

}
