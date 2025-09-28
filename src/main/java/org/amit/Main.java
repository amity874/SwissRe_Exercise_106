package org.amit;


import org.amit.Entity.Employee;
import org.amit.Utility.CsvReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Main {
    /**
     * Analyzes the company structure starting from the given parent employee and prints warnings
     * related to salary discrepancies and reporting line length.
     *
     * <p>This method performs the following checks:
     * <ul>
     *   <li>If the parent employee is a manager, it calculates the average salary of their subordinates
     *       and checks if the parent's salary is within 20% to 50% of the average. If not, it prints
     *       warnings about the parent earning too much or too little.</li>
     *   <li>If the reporting line exceeds 4 levels, it prints a warning about the reporting line being
     *       too long.</li>
     * </ul>
     *
     * <p>The method is recursive and processes all subordinates of the given parent employee.
     *
     * @param EmpMap       A map of employee IDs to their corresponding {@link Employee} objects.
     * @param parent       The current employee being analyzed.
     * @param reportingLine The current reporting line depth (starting from 0 for the CEO).
     */
    public static void analyzeCompanyStructure(Map<String,Employee> EmpMap,Employee parent,Integer reportingLine){
        if(parent.isManager()){
            List<Employee> subordinates=EmpMap.get(parent.getId()).getSubordinates();
            double average=subordinates.stream().mapToDouble(Employee::getSalary).average().orElse(0.0);
            double lowerLimit = average * 1.2;
            double upperLimit = average * 1.5;
            if(parent.getSalary()<lowerLimit){
                System.out.printf("%s earns too LITTLE by %.2f%n",
                        parent.getFirstName()+" "+parent.getLastName(), (lowerLimit - parent.getSalary()));
            }
            else if(parent.getSalary()>upperLimit){
                System.out.printf("%s earns too MUCH by %.2f%n",parent.getFirstName()+" "+parent.getLastName(),(upperLimit-parent.getSalary()));
            }
        }
        if(reportingLine>4){
            System.out.printf("Reporting line too long: %s exceeds by %d managers%n",
                    parent.getFirstName()+" "+parent.getLastName(), (reportingLine - 4));
        }
        for(Employee emp:parent.getSubordinates()){
            analyzeCompanyStructure(EmpMap,emp,reportingLine+1);
        }

    }
    public static void main(String[] args) throws Exception {
        String csvFilePath="src/main/resources/Employee.csv";
        List<Employee> employeeList= CsvReader.readCsv(csvFilePath);
        for (Employee e:employeeList){
            System.out.println(e);
        }
        //to fetch manager id quickly adding into map this represent employee map
        Map<String,Employee> EmpMap=new HashMap<>();
        for(Employee emp:employeeList){
            EmpMap.put(emp.getId(),emp);
        }
        Employee ceo=null;
        for(Employee emp:employeeList){
            if(emp.getManagerId()==null){
                //it means CEO
                ceo=emp;
            }
            else{
                Employee employee=EmpMap.get(emp.getManagerId());
                if(employee!=null){
                    employee.getSubordinates().add(emp);
                }
            }
        }

       if(ceo==null){
           throw new Exception("Company can't exist without CEO");
       }
        analyzeCompanyStructure(EmpMap,ceo,0);
    }
}