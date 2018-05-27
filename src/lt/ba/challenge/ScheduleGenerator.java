package lt.ba.challenge;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ScheduleGenerator {
    private final RoundingMode mode = RoundingMode.HALF_UP;
    private final int scale = 100;    //BigInteger fields scaling value

    public LocalDate dateOfChange; //Field for first bonus task
    public BigDecimal newRate;  //Field for first bonus task

    //Method to generate amortization schedule (returns list of payments)
    public List<Payment> generateSchedule(Loan loan){
        List<Payment> schedule = new ArrayList<>();
        BigDecimal monthlyInterest = loan.getInterestRate().divide(new BigDecimal(12),scale,mode);
        BigDecimal paymentAmount = calculatePaymentAmount(loan).setScale(2,RoundingMode.FLOOR);
        int loopTimes = loan.getNumberOfPeriods() - 2; //minus 2 to exclude first and last payment

        //First payment
        Payment payment = new Payment();
        payment.setNumber(1);
        payment.setDate(loan.getStartDate());
        payment.setRemainingAmount(loan.getPresentValue());
        payment.setTotalPayment(paymentAmount);
        payment.setInterestPayment(payment.getRemainingAmount().multiply(monthlyInterest).setScale(2, mode));
        payment.setPrincipalPayment(payment.getTotalPayment().subtract(payment.getInterestPayment()));
        payment.setInterestRate(loan.getInterestRate().movePointRight(2));
        schedule.add(payment);

        //Remaining payments (excluding last one)
        for (int i = 0; i < loopTimes; i++) {
            payment = fillCommonInfo(schedule.get(i));
            //Condition to check if there was a change in interest rate before estimated payoff date
            if(dateOfChange != null && payment.getDate().isAfter(dateOfChange)){
                //If there was a change, change loan values and recalculate monthly payment amounts after that date
                loan.setPresentValue(payment.getRemainingAmount());
                loan.setNumberOfPeriods(loan.getNumberOfPeriods() - i - 1);
                loan.setInterestRate(newRate);
                paymentAmount = calculatePaymentAmount(loan).setScale(2,RoundingMode.FLOOR);
                monthlyInterest = loan.getInterestRate().divide(new BigDecimal(12),scale,mode);
                dateOfChange = null;
            }
            payment.setTotalPayment(paymentAmount);
            payment.setInterestPayment(payment.getRemainingAmount().multiply(monthlyInterest).setScale(2, mode));
            payment.setPrincipalPayment(payment.getTotalPayment().subtract(payment.getInterestPayment()));
            payment.setInterestRate(loan.getInterestRate().movePointRight(2));
            schedule.add(payment);
        }

        if(loan.getNumberOfPeriods() > 1) {
            //Last payment
            payment = fillCommonInfo(schedule.get(schedule.size()-1));
            payment.setInterestPayment(payment.getRemainingAmount().multiply(monthlyInterest).setScale(2, mode));
            payment.setPrincipalPayment(payment.getRemainingAmount());
            payment.setTotalPayment(payment.getPrincipalPayment().add(payment.getInterestPayment()));
            payment.setInterestRate(loan.getInterestRate().movePointRight(2));
            schedule.add(payment);
        }

        return schedule;
    }

    private BigDecimal calculatePaymentAmount(Loan loan){
        //Used formula: presentValue * ((interestRate/12) / (1-(1+interestRate/12)^-numberOfPeriods))
        return loan.getPresentValue()
                .multiply(loan.getInterestRate().divide(new BigDecimal(12),scale, mode)
                        .divide(BigDecimal.ONE.subtract(
                                power(BigDecimal.ONE.add(loan.getInterestRate()
                                        .divide(new BigDecimal(12),scale,mode)), -loan.getNumberOfPeriods()))
                                ,scale, mode));
    }

    //Created this method to power BigDecimal number by negative number as there is no built-in function for this
    private BigDecimal power(BigDecimal basis, int exponent) {
        if (exponent > 0)
            return (basis.multiply(power(basis, exponent -1)));
        else if(exponent < 0)
            return (BigDecimal.ONE.divide(power(basis, -exponent),scale, mode));
        else
            return BigDecimal.ONE;
    }

    private Payment fillCommonInfo(Payment previous){
        Payment payment = new Payment();
        payment.setNumber(previous.getNumber() + 1);
        payment.setDate(previous.getDate().plusMonths(1));
        payment.setRemainingAmount(previous.getRemainingAmount().subtract(previous.getPrincipalPayment()));
        return payment;
    }
}
