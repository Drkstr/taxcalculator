package payslipcalculator.payslip.repository;

import payslipcalculator.payslip.domain.TaxTable;

public interface TaxTableRepository {
  TaxTable getTaxTable(int year);
}
