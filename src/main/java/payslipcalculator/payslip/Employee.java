package payslipcalculator.payslip;

import lombok.Getter;

@Getter

public class Employee {
  private String firstName;
  private String lastName;
  private double annualSalary;
  private double superPercentage;
  private String payPeriod;

  public Employee(String firstName, String lastName, double annualSalary, double superPercentage, String payPeriod) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.annualSalary = annualSalary;
    this.superPercentage = superPercentage;
    this.payPeriod = payPeriod;
  }
}
