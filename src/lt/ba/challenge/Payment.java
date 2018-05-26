package lt.ba.challenge;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Payment {
    private int number;
    private LocalDate date;
    private BigDecimal remainingAmount;
    private BigDecimal principalPayment;
    private BigDecimal interestPayment;
    private BigDecimal totalPayment;
    private BigDecimal interestRate;

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

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
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
