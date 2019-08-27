package calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IncomeTaxCalculatorTest {

  private IncomeTaxCalculator calculator = IncomeTaxCalculator.getInstance();


  @Test
  public void shouldHaveZeroTaxForLowestBracket() {
    int tax = calculator.calculateTax(1000d);
    assertEquals(0, tax);
  }

  @Test
  public void shouldHaveTaxAmountWhenAboveThreshold() {
    int tax = calculator.calculateTax(20000d);
    assertEquals(29, tax);
  }

  @Test
  public void shouldIncludeAccumulatedTax() {
    int tax = calculator.calculateTax(38000);
    assertEquals(325, tax);
  }

  @Test
  public void shouldSupportHighestBracket() {
    int tax = calculator.calculateTax(200000);
    assertEquals(5269, tax);
  }

  @Test
  public void shouldThrowExceptionWhenInvalidIncome() {
    RuntimeException exception = assertThrows(RuntimeException.class, () -> calculator.calculateTax(-1));
    assertEquals("No tax bracket found. Invalid income. Income:-1.0", exception.getMessage());
  }

}