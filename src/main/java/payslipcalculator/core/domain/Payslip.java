package payslipcalculator.core.domain;

import lombok.Getter;

@Getter
public class Payslip {
  private int incomeTax;
  private int grossIncome;
  private int netIncome;
  private int superannuation;

  public Payslip(int incomeTax, int grossIncome, int netIncome, int superannuation) {
    this.incomeTax = incomeTax;
    this.grossIncome = grossIncome;
    this.netIncome = netIncome;
    this.superannuation = superannuation;
  }

}
