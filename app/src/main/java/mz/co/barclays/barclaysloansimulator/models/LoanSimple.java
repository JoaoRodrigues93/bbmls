package mz.co.barclays.barclaysloansimulator.models;

import java.util.Calendar;

/**
 * Created by João on 26/04/2016.
 */
public class LoanSimple {

    private String loanAmount;
    private String loanTerm;
    private Calendar dateSaved;
    private String monthlyPayment;


    public LoanSimple(String loanAmount, String loanTerm, String monthlyPayment, Calendar dateSaved) {
        this.loanAmount = loanAmount;
        this.loanTerm = loanTerm;
        this.monthlyPayment = monthlyPayment;
        this.dateSaved = dateSaved;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(String loanTerm) {
        this.loanTerm = loanTerm;
    }

    public Calendar getDateSaved() {
        return dateSaved;
    }

    public void setDateSaved(Calendar dateSaved) {
        this.dateSaved = dateSaved;
    }

    public String getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(String monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }
}
