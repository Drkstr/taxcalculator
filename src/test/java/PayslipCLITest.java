import domain.Payslip;
import file.PayslipFileReader;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PayslipCLITest {

  private static String employeesFileName = ClassLoader.getSystemResource("employees.csv").getPath();
  private static String payslipsFileName = Double.valueOf(Math.random() * 10000) + "payslips.csv";
  private static PayslipFileReader payslipFileReader = new PayslipFileReader();

  @Test
  public void shouldWritePayslips() {
    PayslipCLI.main(employeesFileName, payslipsFileName);
    List<Payslip> payslips = payslipFileReader.load(payslipsFileName);

    assertEquals(5004, payslips.get(0).getGrossIncome());
  }

  @AfterAll
  public static void tearDown() {
    new File(payslipsFileName).delete();
  }

}