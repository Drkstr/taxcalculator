package payslipcalculator.core.repository;

import payslipcalculator.core.domain.TaxTable;

public interface TaxTableRepository {
  TaxTable getTaxTable(int year);
}
