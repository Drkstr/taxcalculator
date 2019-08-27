package domain;

import java.util.ArrayList;
import java.util.List;

public class TaxTable {
  private static TaxTable instance = new TaxTable();
  final List<TaxBracket> taxTable = new ArrayList();

  // TODO tax brackets set from DB
  private TaxTable() {
    taxTable.add(new TaxBracket(0,18200,0,0));
    taxTable.add(new TaxBracket(18201,37000,0,0.19));
    taxTable.add(new TaxBracket(37001,87000,3572,0.325));
    taxTable.add(new TaxBracket(87001,180000,3572,0.37));
    taxTable.add(new TaxBracket(180001 ,Integer.MAX_VALUE,54232,0.45));
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

  public static TaxTable getInstance() {
    return instance;
  }
}