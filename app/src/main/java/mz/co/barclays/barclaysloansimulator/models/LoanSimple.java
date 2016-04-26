package mz.co.barclays.barclaysloansimulator.models;

/**
 * Created by João on 26/04/2016.
 */
public class LoanSimple {

    private String loanAmount;
    private String loanTerm;

    public LoanSimple(String loanAmount, String loanTerm) {
        this.loanAmount = loanAmount;
        this.loanTerm = loanTerm;
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
}
