package file;

import com.opencsv.CSVWriter;
import domain.Payslip;

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
        File payslipFile = new File(ClassLoader.getSystemResource(fileName).getFile());
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(payslipFile));
            for (Payslip payslip : payslips) {
                writer.writeNext(new String[] {String.valueOf(payslip.getIncomeTax()),"","",""});
            }
            writer.close();
        } catch (IOException e) {


        }
    }
}
