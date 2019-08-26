package service;

import calculator.IncomeTaxCalculator;
import domain.Employee;
import domain.Payslip;
import file.EmployeeFileReader;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class PaySlipService {
  private static PaySlipService instance = new PaySlipService();
  private final IncomeTaxCalculator taxCalculator;
  private final EmployeeFileReader fileReader;

  private PaySlipService() {
    fileReader = EmployeeFileReader.getInstance();
    taxCalculator = IncomeTaxCalculator.getInstance();
  }

  public static PaySlipService getInstance() {
    return instance;
  }

  public List<Payslip> calculatePayslips(List<Employee> employees) {
    List<Payslip> payslips = new ArrayList<>();
    for (Employee employee : employees) {
      payslips.add(calculatePayslip(employee));
    }
    return payslips;
  }

  private Payslip calculatePayslip(Employee employee) {
    int tax = taxCalculator.calculateTax(employee.getAnnualSalary());
    int grossIncome = BigDecimal.valueOf(employee.getAnnualSalary()).divide(BigDecimal.valueOf(12),0, RoundingMode.HALF_UP).intValue();
    int netIncome = grossIncome - tax;
    int superannuation = BigDecimal.valueOf(grossIncome * employee.getSuperPercentage()).divide(BigDecimal.valueOf(100), 0, RoundingMode.HALF_UP).intValue();

    return new Payslip(tax, grossIncome, netIncome, superannuation);
  }
}
