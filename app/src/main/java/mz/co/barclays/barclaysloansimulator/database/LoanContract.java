package mz.co.barclays.barclaysloansimulator.database;


import android.provider.BaseColumns;

/**
 * Created by João on 04/05/2016.
 */
public final class LoanContract  {

    public LoanContract (){

    }

    public static abstract class LoanEntry implements BaseColumns{
        public static final String TABLE_NAME = "loan";
        public static final String COLUMN_NAME_LOAN_ID = "loanid";
        public static final String COLUMN_NAME_LOAN_AMOUNT = "loanamount";
        public static final String COLUMN_NAME_TERM = "term";
        public static final String COLUMN_NAME_INTEREST = "interest";
        public static final String COLUMN_NAME_MONTHLY_PAYMENT = "monthlypayment";
        public static final String COLUMN_NAME_TOTAL_AMOUNT = "totalamount";
        public static final String COLUMN_NAME_TOTAL_INTEREST = "totalinterest";
        public static final String COLUMN_NAME_SAVED_DATE = "saveddate";
        public static final String COLUMN_NAME_NULLABLE ="nullable";

        private static final String TEXT_TYPE = " TEXT";
        private static final String DECIMAL_TYPE =" DECIMAL";
        private static final String INTEGER_TYPE = " INTEGER";
        private static final String DATE_TYPE = " DATE";
        private static final String COMMA_SEP = ",";

        private static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE "+LoanEntry.TABLE_NAME+ "( "+
                        LoanEntry._ID +" INTEGER PRIMAY KEY,"+
                        LoanEntry.COLUMN_NAME_LOAN_ID+TEXT_TYPE+COMMA_SEP+
                        LoanEntry.COLUMN_NAME_LOAN_AMOUNT+DECIMAL_TYPE+COMMA_SEP+
                        LoanEntry.COLUMN_NAME_INTEREST+DECIMAL_TYPE+COMMA_SEP+
                        LoanEntry.COLUMN_NAME_MONTHLY_PAYMENT+DECIMAL_TYPE+COMMA_SEP+
                        LoanEntry.COLUMN_NAME_TERM+INTEGER_TYPE+COMMA_SEP+
                        LoanEntry.COLUMN_NAME_TOTAL_AMOUNT+DECIMAL_TYPE+COMMA_SEP+
                        LoanEntry.COLUMN_NAME_TOTAL_INTEREST+DECIMAL_TYPE+COMMA_SEP+
                        LoanEntry.COLUMN_NAME_SAVED_DATE+DATE_TYPE+COMMA_SEP+
                        LoanEntry.COLUMN_NAME_NULLABLE+TEXT_TYPE+
                        " )";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS "+ LoanEntry.TABLE_NAME;
    }
}
