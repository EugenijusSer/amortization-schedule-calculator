package lt.ba.challenge;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Loan {
    private LocalDate startDate;
    private BigDecimal presentValue;
    private BigDecimal interestRate;
    private int numberOfPeriods;

    Loan(LocalDate startDate, BigDecimal presentValue, BigDecimal interestRate, int numberOfPeriods) {
        this.startDate = startDate;
        this.presentValue = presentValue;
        this.interestRate = interestRate;
        this.numberOfPeriods = numberOfPeriods;
    }

    public LocalDate getStartDate() { return startDate; }

    public void setStartDateDate(LocalDate startDate) { this.startDate = startDate; }

    public BigDecimal getPresentValue() { return presentValue; }

    public void setPresentValue(BigDecimal presentValue) { this.presentValue = presentValue; }

    public BigDecimal getInterestRate() { return interestRate; }

    public void setInterestRate(BigDecimal interestRate) { this.interestRate = interestRate; }

    public int getNumberOfPeriods() { return numberOfPeriods; }

    public void setNumberOfPeriods(int numberOfPeriods) { this.numberOfPeriods = numberOfPeriods; }
}
