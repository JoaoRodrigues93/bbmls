package mz.co.barclays.barclaysloansimulator.models;

/**
 * Created by João on 23/04/2016.
 */
public class ScheduleTable {
    private int monthNumber;
    private double beginMonth;
    private double interest;
    private double instalment;
    private double capitalEndOfMonth;
    private double interestRate;

    public ScheduleTable(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public void setMonthNumber(int monthNumber) {
        this.monthNumber = monthNumber;
    }

    public double getBeginMonth() {
        return beginMonth;
    }

    public void setBeginMonth(double beginMonnth) {
        this.beginMonth = beginMonnth;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getInstalment() {
        return instalment;
    }

    public void setInstalment(double instalment) {
        this.instalment = instalment;
    }

    public double getCapitalEndOfMonth() {
        return capitalEndOfMonth;
    }

    public void setCapitalEndOfMonth(double capitalEndOfMonth) {
        this.capitalEndOfMonth = capitalEndOfMonth;
    }
}
