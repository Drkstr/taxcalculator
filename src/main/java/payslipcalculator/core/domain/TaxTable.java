package payslipcalculator.core.domain;

import java.util.List;

public class TaxTable {

  private List<TaxBracket> taxTable;

  public TaxTable(List<TaxBracket> taxBrackets) {
    this.taxTable=taxBrackets;
  }

  private boolean matchesBracket(double annualIncome, TaxBracket taxBracket) {
    return annualIncome >= taxBracket.getLowerBounds() &&
      annualIncome <= taxBracket.getUpperBounds();
  }

  public TaxBracket getTaxBracket(double annualIncome) {

    return taxTable.stream()
      .filter(taxBracket -> matchesBracket(annualIncome, taxBracket))
      .findFirst()
      .orElseThrow(() -> new RuntimeException("No tax bracket found. Invalid income. Income:" + annualIncome));

  }
}