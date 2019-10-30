package payslipcalculator.cli;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import payslipcalculator.payslip.Payslip;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

  @Test
  public void shouldValidateInput() {
    RuntimeException exception = assertThrows(RuntimeException.class, () -> PayslipCLI.main());
    assertEquals("Input and output files must be defined.", exception.getMessage());
  }

  @AfterAll
  public static void tearDown() {
    new File(payslipsFileName).delete();
  }

}