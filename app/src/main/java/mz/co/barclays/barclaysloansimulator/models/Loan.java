package mz.co.barclays.barclaysloansimulator.models;

import mz.co.barclays.barclaysloansimulator.formulas.Finance;

/**
 * Created by João on 23/04/2016.
 */
public class Loan {
    private long id;
    private double loanAmount;
    private double annualInterest;
    private int loanTerm;
    private double downPayment;
    private double contributionAmount;
    private double contributionPercent;
    private double monthlyPayment;
    private double efectiveInterestRate;
    private double totalInterest;
    private double totalAmount;

    public Loan() {
        this.contributionAmount =0;
    }

    public long getId() {
        return id;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public double getAnnualInterest() {
        return annualInterest;
    }

    public int getLoanTerm() {
        return loanTerm;
    }

    public double getDownPayment() {
        return downPayment;
    }

    public double getContributionAmount() {
        return contributionAmount;
    }

    public double getContributionPercent() {
        return contributionPercent;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public double getEfectiveInterestRate() {
        return efectiveInterestRate * 100;
    }

    public double getTotalInterest() {
        return this.totalInterest;
    }

    public double getTotalAmount() {
        return this.totalAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public void setAnnualInterest(double annualInterest) {
        this.annualInterest = annualInterest;
    }

    public void setDownPayment(double downPayment) {
        this.downPayment = downPayment;
    }

    public void setLoanTerm(int loanTerm) {
        this.loanTerm = loanTerm;
    }

    public void setContributionAmount(double contributionAmount) {
        this.contributionAmount = contributionAmount;
    }

    public void setContributionPercent(double contributionPercent) {
        this.contributionPercent = contributionPercent;
    }
    
    public void calculateLoan(){
        
        if (contributionAmount > 0)
        this.monthlyPayment = -Finance.pmt((this.annualInterest/100) /12,this.loanTerm,this.loanAmount - this.contributionAmount,-1,0);
        else
        this.monthlyPayment = -Finance.pmt((this.annualInterest/100) / 12,this.loanTerm,this.loanAmount,-1,0);

        this.totalAmount = this.monthlyPayment * this.loanTerm;
        this.efectiveInterestRate = Math.pow((1 + (annualInterest / 100) /12),12) -1;
        if (this.contributionAmount>0)
        this.totalInterest = this.totalAmount - (this.loanAmount - this.contributionAmount );
        else
        this.totalInterest = this.totalAmount - this.loanAmount;

    }
}
