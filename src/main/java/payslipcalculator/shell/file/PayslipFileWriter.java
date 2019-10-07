package payslipcalculator.shell.file;

import com.opencsv.CSVWriter;
import payslipcalculator.core.domain.Payslip;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PayslipFileWriter {
    private static PayslipFileWriter instance = new PayslipFileWriter();

    public static PayslipFileWriter getInstance() {
        return instance;
    }

    public void writePayslips(String fileName, List<Payslip> payslips) {
        File payslipFile = new File(fileName);
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(payslipFile));
            for (Payslip payslip : payslips) {
                writer.writeNext(buildLine(payslip));
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("Problem writing file:" + fileName);
        }
    }

    private String[] buildLine(Payslip payslip) {
        return new String[] {
          String.valueOf(payslip.getIncomeTax()),
          String.valueOf(payslip.getGrossIncome()),
          String.valueOf(payslip.getNetIncome()),
          String.valueOf(payslip.getSuperannuation())
        };
    }
}
