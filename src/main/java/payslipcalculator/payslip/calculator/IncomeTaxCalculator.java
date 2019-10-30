package payslipcalculator.payslip.calculator;

import payslipcalculator.payslip.domain.TaxBracket;
import payslipcalculator.payslip.domain.TaxTable;
import payslipcalculator.payslip.repository.TaxTableRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class IncomeTaxCalculator {

  private TaxTableRepository taxTableHardcodedRepository;

  public IncomeTaxCalculator(TaxTableRepository taxTableHardcodedRepository) {
    this.taxTableHardcodedRepository = taxTableHardcodedRepository;
  }

  public int calculateTax(double annualIncome) {
    TaxTable taxTable = taxTableHardcodedRepository.getTaxTable(2018);
    TaxBracket taxBracket = taxTable.getTaxBracket(annualIncome);

    return new BigDecimal(annualIncome)
      .subtract(new BigDecimal(taxBracket.getLowerBounds()-1))
      .multiply(new BigDecimal(taxBracket.getTaxAmountPercent()))
      .add(new BigDecimal(taxBracket.getAccumulatedTax()))
      .divide(BigDecimal.valueOf(12), RoundingMode.HALF_UP)
      .setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
  }
}
