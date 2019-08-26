import domain.Employee;
import domain.Payslip;
import file.EmployeeFileReader;
import file.PayslipFileWriter;
import service.PaySlipService;

import java.util.List;

public class PayslipCLI {

    private static final EmployeeFileReader employeeFileReader = EmployeeFileReader.getInstance();
    private static final PayslipFileWriter payslipFileWriter = PayslipFileWriter.getInstance();
    private static PaySlipService paySlipService = PaySlipService.getInstance();

    public static void main(String... args) {

        List<Employee> employees = employeeFileReader.load(args[0]);
        List<Payslip> payslips = paySlipService.calculatePayslips(employees);
        payslipFileWriter.writePayslips(args[1],payslips);
    }
}
