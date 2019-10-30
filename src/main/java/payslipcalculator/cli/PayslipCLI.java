package payslipcalculator.cli;

import payslipcalculator.payslip.calculator.IncomeTaxCalculator;
import payslipcalculator.payslip.domain.Employee;
import payslipcalculator.payslip.domain.Payslip;
import payslipcalculator.cli.file.EmployeeFileReader;
import payslipcalculator.cli.file.PayslipFileWriter;
import payslipcalculator.payslip.service.PaySlipService;
import payslipcalculator.cli.repository.TaxTableHardcodedRepository;

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

    private static void validateInput(String... args) {
        if (args.length < 2) {
            throw new RuntimeException("Input and output files must be defined.");
        }
    }
}
