import org.amit.Entity.Employee;
import org.amit.Utility.CsvReader;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CsvReaderTest {
    /*
    This test case is for if i have valid csv file with proper header and data
    if i change the order of csv columns  it should work fine
     */
    @Test
    public void testReadCsv_ValidFile() throws Exception {
        // Arrange
        String testCsvPath = "src/test/java/Employee.csv";
        String csvContent = """
                id,firstname,lastname,salary,managerid
                1,John,Doe,100000,
                2,Jane,Smith,80000,1
                3,Bob,Brown,60000,1
                """;
        Files.writeString(Path.of(testCsvPath), csvContent);

        List<Employee> employees = CsvReader.readCsv(testCsvPath);

        assertEquals(3, employees.size());
        assertEquals("John", employees.get(0).getFirstName());
        assertEquals("Doe", employees.get(0).getLastName());
        assertEquals(100000.0, employees.get(0).getSalary(), 0.001);
        assertNull(employees.get(0).getManagerId());
    }
}
