package file;

import com.opencsv.CSVReader;
import domain.Employee;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class EmployeeFileReader {
  private static EmployeeFileReader instance = new EmployeeFileReader();

  private EmployeeFileReader() {

  }

  public static EmployeeFileReader getInstance() {
    return instance;
  }

  public List<Employee> load(String employeeFileName) {

    CSVReader csvReader = new CSVReader(getReader(employeeFileName));
    List<Employee> employees = new ArrayList<>();
    String[] line = null;
    try {
      while ((line = csvReader.readNext()) != null) {
        employees.add(new Employee(line[0], line[1], parseSalary(line[2]), parseSuperValue(line[3]), line[4]));
      }
    } catch (Exception e) {
      throw new RuntimeException("Problem CSV line. Line:" + line);
    }

    return employees;
  }

  private double parseSalary(String salary) {
    return Double.parseDouble(salary);
  }

  private double parseSuperValue(String s) {
    return Double.parseDouble(s.replaceAll("%",""));
  }

  private Reader getReader(String employeeFileName) {

    File employeeFile = new File(employeeFileName);
    try {
      return new FileReader(employeeFile);
    } catch (FileNotFoundException e) {
      throw new RuntimeException("Problem reading file. Check file path.");
    }
  }
}
