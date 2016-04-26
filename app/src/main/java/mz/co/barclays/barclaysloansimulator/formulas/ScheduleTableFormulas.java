package mz.co.barclays.barclaysloansimulator.formulas;

/**
 * Created by João on 24/04/2016.
 */
public class ScheduleTableFormulas {

    public static double beginOfMonth=0;
    public static double interestOfMonth=0;
    public static int loanTerm=0;
    public static int currentMonth=1;
    public static double installment=0;
    public static double endOfMonth=0;
    public static double interestRate=0;
    public static int noOfMonth=0;

    public static void init(){
         beginOfMonth=0;
         interestOfMonth=0;
         loanTerm=0;
         currentMonth=1;
         installment=0;
         endOfMonth=0;
         interestRate=0;
         noOfMonth=0;
    }

    public static void firstMonth(){
        noOfMonth = currentMonth;
        calculateInterest();
        calculateEndOfMonth();
    }

    public static void nextMonth(){
        currentMonth++;
        noOfMonth = currentMonth;
        beginOfMonth = endOfMonth;
        calculateInterest();
        if (currentMonth == loanTerm)
            calculateFinalInstallment();
        calculateEndOfMonth();
    }

    public static boolean hasNext (){
        return currentMonth < loanTerm;
    }

    public static void calculateInterest (){
        interestOfMonth = beginOfMonth * (interestRate / 100 ) / 12;
    }

    public static void calculateEndOfMonth(){
        endOfMonth = beginOfMonth+interestOfMonth-installment;
    }

    public static void calculateFinalInstallment (){
        installment = beginOfMonth + interestOfMonth;
    }


}
