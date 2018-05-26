package lt.ba.challenge;

import java.math.BigDecimal;
import java.util.Date;

public class Payment {
    private int number;
    private Date date;
    private BigDecimal remainingAmount;
    private BigDecimal principalPayment;
    private BigDecimal interestPayment;
    private BigDecimal totalPayment;
    private BigDecimal interestRate;

    public int getNumber() { return number; }

    public void setNumber(int number) { this.number = number; }

    public Date getDate() { return date; }

    public void setDate(Date date) { this.date = date; }

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
}
