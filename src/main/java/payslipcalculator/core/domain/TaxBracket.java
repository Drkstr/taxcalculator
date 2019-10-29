package payslipcalculator.core.domain;

import lombok.Getter;

@Getter
public class TaxBracket {
  private final int lowerBounds;
  private final int upperBounds;
  private final int accumulatedTax;
  private final double taxAmountPercent;

  public TaxBracket(int lowerBounds, int upperBounds, int accumulatedTax, double taxAmountPercent) {

    this.lowerBounds = lowerBounds;
    this.upperBounds = upperBounds;
    this.accumulatedTax = accumulatedTax;
    this.taxAmountPercent = taxAmountPercent;
  }
}
