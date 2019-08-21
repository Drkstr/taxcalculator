package calculator;

import domain.TaxBracket;
import domain.TaxTable;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class IncomeTaxCalculator {
  private static IncomeTaxCalculator instance = new IncomeTaxCalculator();
  private final TaxTable taxTable = TaxTable.getInstance();

  private IncomeTaxCalculator() {

  }

  public static IncomeTaxCalculator getInstance() {
    return instance;
  }

  public int calculateTax(double annualIncome) {
    TaxBracket taxBracket = taxTable.getTaxBracket(annualIncome);

    return new BigDecimal(annualIncome)
      .subtract(new BigDecimal(taxBracket.getLowerBounds()-1))
      .multiply(new BigDecimal(taxBracket.getTaxAmountPercent()))
      .add(new BigDecimal(taxBracket.getAccumulatedTax()))
      .setScale(2, RoundingMode.HALF_UP)
      .divide(BigDecimal.valueOf(12), RoundingMode.HALF_UP)
      .setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
  }
}
