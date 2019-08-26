package file;

import com.opencsv.CSVReader;
import domain.Payslip;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class PayslipFileReader {

  public PayslipFileReader() {

  }

  public List<Payslip> load(String fileName) {

    CSVReader csvReader = new CSVReader(getReader(fileName));
    List<Payslip> payslips = new ArrayList<Payslip>();
    String[] line = null;
    try {
      while ((line = csvReader.readNext()) != null) {
        payslips.add(new Payslip(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3])));
      }
    } catch (Exception e) {
      throw new RuntimeException("Problem CSV line. Line:" + line);
    }

    return payslips;
  }
  private Reader getReader(String fileName) {
    File employeeFile = new File(fileName);
    try {
      return new FileReader(employeeFile);
    } catch (FileNotFoundException e) {
      throw new RuntimeException("File not found. Check file path.");
    }
  }
}