package lt.ba.challenge;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Payment {
    private int number;
    private LocalDate date;
    private BigDecimal remainingAmount;
    private BigDecimal principalPayment;
    private BigDecimal interestPayment;
    private BigDecimal totalPayment;
    private BigDecimal interestRate;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public int getNumber() { return number; }

    public void setNumber(int number) { this.number = number; }

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) { this.date = date; }

    public BigDecimal getRemainingAmount() { return remainingAmount; }

    public void setRemainingAmount(BigDecimal remainingAmount) { this.remainingAmount = remainingAmount; }

    public BigDecimal getPrincipalPayment() { return principalPayment; }

    public void setPrincipalPayment(BigDecimal principalPayment) { this.principalPayment = principalPayment; }

    public BigDecimal getInterestPayment() { return interestPayment; }

    public void setInterestPayment(BigDecimal interestPayment) { this.interestPayment = interestPayment; }

    public BigDecimal getTotalPayment() { return totalPayment; }

    public void setTotalPayment(BigDecimal totalPayment) { this.totalPayment = totalPayment; }

    public BigDecimal getInterestRate() { return interestRate; }

    public void setInterestRate(BigDecimal interestRate) { this.interestRate = interestRate; }

    public String toCsvRow() {
        return Stream.of(String.valueOf(number), date.format(formatter), remainingAmount.toString(), principalPayment.toString(),
                interestPayment.toString(), totalPayment.toString(), interestRate.toString())
                .collect(Collectors.joining(","));
    }

    @Override
    public String toString() {
        return "Payment{" +
                "number=" + number +
                ", date=" + date.format(formatter) +
                ", remainingAmount=" + remainingAmount +
                ", principalPayment=" + principalPayment +
                ", interestPayment=" + interestPayment +
                ", totalPayment=" + totalPayment +
                ", interestRate=" + interestRate +
                '}';
    }
}
