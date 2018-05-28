package lt.ba.challenge;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        ScheduleGenerator generator = new ScheduleGenerator();
        DataExporter exporter = new DataExporter();

        while(true) {
            LocalDate date = LocalDate.of(2017, 4, 15);
            Loan loan = new Loan(date, new BigDecimal(5000), new BigDecimal(12).movePointLeft(2), 24);

            printMenu();
            int num = in.nextInt();
            switch (num) {
                case 0:
                    System.exit(0);
                case 1:
                    break;
                case 2:
                    generator.dateOfChange = LocalDate.of(2017, 9, 2);
                    generator.newRate = new BigDecimal(9).movePointLeft(2);
                    break;
                case 3:
                    try {
                        System.out.println("Enter start date(e.g. 2017-05-21)");
                        Scanner scan = new Scanner(System.in);
                        String enteredDate = scan.nextLine();
                        LocalDate localDate = LocalDate.parse(enteredDate);
                        System.out.println("Enter present value of loan:");
                        double presentValue = in.nextDouble();
                        System.out.println("Enter interest rate:");
                        double interestRate = in.nextDouble();
                        System.out.println("Enter number of periods:");
                        int numberOfPeriods = in.nextInt();
                        loan = new Loan(localDate, new BigDecimal(presentValue),
                                new BigDecimal(interestRate).movePointLeft(2), numberOfPeriods);
                        break;
                    }
                    catch (Exception e){
                        System.out.println(e.toString());
                    }
            }

            List<Payment> schedule = generator.generateSchedule(loan);

            for(Payment payment : schedule)
                System.out.println(payment.toString());

            exporter.exportDataToCsv(schedule);
        }
    }

    private static void printMenu(){
        System.out.println();
        System.out.println("Choose action:");
        System.out.println("1. Main task");
        System.out.println("2. First bonus task (changed rate mid-schedule)");
        System.out.println("3. Second bonus task (custom input)");
        System.out.println("0. Exit program");
        System.out.println();
    }
}
