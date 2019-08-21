package file;

import domain.Employee;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmployeeFileReaderTest {

  private EmployeeFileReader reader = EmployeeFileReader.getInstance();
  private List<Employee> employees = reader.load("employees.csv");;

  @Test
  public void shouldLoadEmployeeFirstName() {
    assertEquals("David", employees.get(0).getFirstName());
  }

  @Test
  public void shouldLoadEmployeeFirstLastName() {
    assertEquals("Rudd", employees.get(0).getLastName());
  }

  @Test
  public void shouldLoadEmployeeSalary() {
    assertEquals(60050, employees.get(0).getAnnualSalary());
  }

  @Test
  public void shouldLoadEmployeeSuper() {
    assertEquals(9, employees.get(0).getSuperPercentage());
  }

  @Test
  public void shouldLoadEmployeePayPeriod() {
    assertEquals("01 March – 31 March", employees.get(0).getPayPeriod());
  }

  @Test
  public void shouldExceptionWhenInvalidFile() {
    assertThrows(RuntimeException.class, () -> reader.load("123.csv"));
  }
}