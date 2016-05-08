package mz.co.barclays.barclaysloansimulator.formulas;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by João on 06/05/2016.
 */
public class FormatValues {

    private NumberFormat amountFormat;
    private NumberFormat interestFormat;
    private NumberFormat monthFormat;
    private NumberFormat yearFormat;
    private NumberFormat decimalFormat;
    private DateFormat dateFormat;
    private DecimalFormat formatToCalculate;

    public FormatValues (){
        this.amountFormat = new DecimalFormat("###,###,##0.00");
        this.interestFormat = new DecimalFormat("#0.00");
        this.monthFormat = NumberFormat.getIntegerInstance();
        this.yearFormat = new DecimalFormat("#0.0");
        this.dateFormat = DateFormat.getDateInstance();
        this.decimalFormat = new DecimalFormat("#0.00");



    }

    public String formatAmount(double amount){

        return this.amountFormat.format(amount);
    }

    public String formatInterest(double interest){
        return this.interestFormat.format(interest);
    }

    public String formatMonth(int months){
        return this.monthFormat.format(months);
    }

    public String formatYear(float years){
       return this.yearFormat.format(years);
    }

    public String formatDate (Calendar date){

        return this.dateFormat.format(date.getTime());
    }

    public String formatDecimal (Double number) {
        return this.decimalFormat.format(number);
    }

    public String formatToCalculate (Double number){
        return this.formatToCalculate.format(number);
    }


}
