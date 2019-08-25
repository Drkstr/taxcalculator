package service;

import domain.Payslip;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaySlipServiceTest {

  private PaySlipService paySlipService = PaySlipService.getInstance();
  private String employeesFileName = "employees.csv";
  private String payslipsfileName = "payslips.csv";
  private List<Payslip> payslips  =  paySlipService.calculatePayslips(employeesFileName);

  @Test
  public void shouldHavePayslipWithGrossIncome() {
    assertEquals(5004, payslips.get(0).getGrossIncome());
  }

  @Test
  public void shouldHavePayslipWithTax() {
    assertEquals(922, payslips.get(0).getIncomeTax());
  }

  @Test
  public void shouldHavePayslipWithNetIncome() {
    assertEquals(4082, payslips.get(0).getNetIncome());
  }

  @Test
  public void shouldHavePayslipWithSuper() {
    assertEquals(450, payslips.get(0).getSuperannuation());
  }

}