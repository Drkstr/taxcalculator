package payslipcalculator.shell;

import payslipcalculator.core.calculator.IncomeTaxCalculator;
import payslipcalculator.core.domain.Employee;
import payslipcalculator.core.domain.Payslip;
import payslipcalculator.shell.file.EmployeeFileReader;
import payslipcalculator.shell.file.PayslipFileWriter;
import payslipcalculator.core.service.PaySlipService;
import payslipcalculator.shell.repository.TaxTableHardcodedRepository;

import java.util.List;

public class PayslipCLI {

    private static final EmployeeFileReader employeeFileReader = EmployeeFileReader.getInstance();
    private static final PayslipFileWriter payslipFileWriter = PayslipFileWriter.getInstance();
    private static IncomeTaxCalculator incomeTaxCalculator = new IncomeTaxCalculator(new TaxTableHardcodedRepository());
    private static PaySlipService paySlipService = new PaySlipService(incomeTaxCalculator);

    public static void main(String... args) {

        validateInput(args);
        String inputFileName = args[0];
        String outputFileName = args[1];

        List<Employee> employees = employeeFileReader.load(inputFileName);
        List<Payslip> payslips = paySlipService.calculatePayslips(employees);
        payslipFileWriter.writePayslips(outputFileName,payslips);
    }

    private static void validateInput(String[] args) {
        if (args.length < 2) {
            throw new RuntimeException("Input and output files must be defined.");
        }
    }
}
