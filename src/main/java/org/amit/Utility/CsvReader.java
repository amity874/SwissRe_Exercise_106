package org.amit.Utility;

import org.amit.Entity.Employee;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvReader {
    /*
    Helper method to normalize header names
     */
    private static String normalize(String s) {
        return s == null ? null : s.trim().toLowerCase().replaceAll("[ _\\-]", "");
    }

    /**
     * Reads a CSV file containing employee data and returns a list of {@link Employee} objects.
     *
     * <p>This method processes the CSV file as follows:
     * <ul>
     *   <li>Reads the header row to determine the column indices for "id", "firstname", "lastname", "salary", and "managerid".</li>
     *   <li>Parses each subsequent row to create an {@link Employee} object using the extracted column values.</li>
     *   <li>Handles cases where the "managerid" column is missing or empty by setting it to {@code null}.</li>
     *   <li>Handles cases where the "salary" column is empty by setting the salary to {@code 0.0}.</li>
     * </ul>
     *
     * <p>If the header row is missing required columns, an {@link IllegalArgumentException} is thrown.
     * If the file cannot be read, an {@link IOException} is caught, and an error message is printed.
     *
     * @param filePath The path to the CSV file to be read.
     * @return A list of {@link Employee} objects parsed from the CSV file.
     * @throws IllegalArgumentException If the header row is missing required columns.
     */
    public static List<Employee> readCsv(String filePath){
        System.out.printf("Entering into readCsv with filePath: %s%n", filePath);
        List<Employee> employeeList=new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
            System.out.println("Total lines read from file: " + lines.size());
            if (lines.isEmpty()) return employeeList;
            /*
            in future csv structure column sequence can change so we can use header
            row to identify column index
             */
            String headerLine=lines.get(0);
            String []headers=headerLine.split(",");
            Map<String,Integer> indexOf=new HashMap<>();
            for(int i=0;i<headers.length;i++){
                indexOf.put(normalize(headers[i]),i);
            }
            int idIdx     = indexOf.getOrDefault("id", -1);
            int fnIdx     = indexOf.getOrDefault("firstname", -1);
            int lnIdx     = indexOf.getOrDefault("lastname", -1);
            int salaryIdx = indexOf.getOrDefault("salary", -1);
            int mgrIdx    = indexOf.getOrDefault("managerid", -1);
            System.out.println("Column Indices - ID: " + idIdx + ", First Name: " + fnIdx + ", Last Name: " + lnIdx + ", Salary: " + salaryIdx + ", Manager ID: " + mgrIdx);
            if (idIdx < 0 || fnIdx < 0 || lnIdx < 0 || salaryIdx < 0) {
                throw new IllegalArgumentException("Missing required column(s) in header");
            }

            for(int i=1;i<lines.size();i++){
                String []values=lines.get(i).split(",");
                String id=(values[idIdx].trim());
                String firstName=values[fnIdx].trim();
                String lastName=values[lnIdx].trim();
                double salary=values[salaryIdx].trim().isEmpty() ? 0.0 : Double.parseDouble(values[salaryIdx].trim());
                String managerId =(mgrIdx >= 0 && mgrIdx < values.length && !values[mgrIdx].trim().isEmpty())
                        ? values[mgrIdx].trim() : null;
                employeeList.add(new Employee(id,firstName,lastName,salary,managerId));
            }
        }
        catch (IOException e){
            e.printStackTrace();
            System.out.println("Error reading file: " + e.getMessage());
        }
        finally {
            System.out.println("Total Employees read: " + employeeList.size());
        }
        return employeeList;
    }
}
