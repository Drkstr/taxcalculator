package file;

import domain.Payslip;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PayslipFileWriterTest {

  private static PayslipFileWriter writer;
  private static List<Payslip> payslips = new ArrayList<>();
  private static Payslip payslip1 = new Payslip(1000, 10000, 9000, 500);;
  private static String payslipsFileName = Double.valueOf(Math.random() * 10000) + "payslips.csv";
  private static final PayslipFileReader payslipFileReader = new PayslipFileReader();
  private static List<Payslip> csvData;

  @BeforeAll
  public static void setup() {
    writer = PayslipFileWriter.getInstance();
    payslips.add(payslip1);
    writer.writePayslips(payslipsFileName, payslips);
    csvData = payslipFileReader.load(payslipsFileName);
  }

  @Test
  public void shouldWriteIncomeTax() {
    assertEquals(1000, csvData.get(0).getIncomeTax());
  }

  @Test
  public void shouldWriteGrossIncome() {
    assertEquals(10000, csvData.get(0).getGrossIncome());
  }

  @Test
  public void shouldWriteNetIncome() {
    assertEquals(9000, csvData.get(0).getNetIncome());
  }

  @Test
  public void shouldWriteSuperannuation() {
    assertEquals(500, csvData.get(0).getSuperannuation());
  }

  @Test
  public void shouldThrowExceptionWhenInvalidFile()
  {
    RuntimeException exception = assertThrows(RuntimeException.class, () -> writer.writePayslips("/", payslips));
    assertEquals("Problem writing file:/", exception.getMessage());
  }

  @AfterAll
  public static void tearDown() {
    new File(payslipsFileName).delete();
  }

}