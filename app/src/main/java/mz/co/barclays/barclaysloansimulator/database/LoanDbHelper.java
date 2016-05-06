package mz.co.barclays.barclaysloansimulator.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by João on 04/05/2016.
 */
public class LoanDbHelper extends SQLiteOpenHelper {
    private static final String TEXT_TYPE = " TEXT";
    private static final String DECIMAL_TYPE =" DECIMAL";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String DATE_TYPE = " DATE";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE "+LoanContract.LoanEntry.TABLE_NAME+ "( "+
                    LoanContract.LoanEntry._ID +" INTEGER PRIMAY KEY,"+
                    LoanContract.LoanEntry.COLUMN_NAME_LOAN_ID+TEXT_TYPE+COMMA_SEP+
                    LoanContract.LoanEntry.COLUMN_NAME_LOAN_AMOUNT+DECIMAL_TYPE+COMMA_SEP+
                    LoanContract.LoanEntry.COLUMN_NAME_INTEREST+DECIMAL_TYPE+COMMA_SEP+
                    LoanContract.LoanEntry.COLUMN_NAME_MONTHLY_PAYMENT+DECIMAL_TYPE+COMMA_SEP+
                    LoanContract.LoanEntry.COLUMN_NAME_TERM+INTEGER_TYPE+COMMA_SEP+
                    LoanContract.LoanEntry.COLUMN_NAME_TOTAL_AMOUNT+DECIMAL_TYPE+COMMA_SEP+
                    LoanContract.LoanEntry.COLUMN_NAME_TOTAL_INTEREST+DECIMAL_TYPE+COMMA_SEP+
                    LoanContract.LoanEntry.COLUMN_NAME_SAVED_DATE+DATE_TYPE+COMMA_SEP+
                    LoanContract.LoanEntry.COLUMN_NAME_NULLABLE+TEXT_TYPE+
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS "+ LoanContract.LoanEntry.TABLE_NAME;


    public static final int DATABASE_VERSION =1;
    public static final String DATABASE_NAME = "Loan.db";

    public LoanDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
