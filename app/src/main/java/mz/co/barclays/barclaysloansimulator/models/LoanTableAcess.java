package mz.co.barclays.barclaysloansimulator.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Calendar;

import mz.co.barclays.barclaysloansimulator.database.LoanContract;
import mz.co.barclays.barclaysloansimulator.database.LoanDbHelper;

/**
 * Created by João on 04/05/2016.
 */
public class LoanTableAcess {

    private LoanDbHelper myDbHelper;
    private SQLiteDatabase db;

    public LoanTableAcess (Context context){
        myDbHelper = new LoanDbHelper(context);
        db = myDbHelper.getWritableDatabase();
    }

    public long insert(Loan loan){

        ContentValues values = new ContentValues();


        values.put(LoanContract.LoanEntry.COLUMN_NAME_LOAN_AMOUNT,loan.getLoanAmount());
        values.put(LoanContract.LoanEntry.COLUMN_NAME_INTEREST,loan.getAnnualInterest());
        values.put(LoanContract.LoanEntry.COLUMN_NAME_TERM,loan.getLoanTerm());
        values.put(LoanContract.LoanEntry.COLUMN_NAME_TOTAL_AMOUNT,loan.getTotalAmount());
        values.put(LoanContract.LoanEntry.COLUMN_NAME_MONTHLY_PAYMENT,loan.getMonthlyPayment());
        values.put(LoanContract.LoanEntry.COLUMN_NAME_TOTAL_INTEREST,loan.getTotalInterest());
        values.put(LoanContract.LoanEntry.COLUMN_NAME_SAVED_DATE, Calendar.getInstance().getTime().getTime());

        long id;

        id = this.db.insert(LoanContract.LoanEntry.TABLE_NAME, LoanContract.LoanEntry.COLUMN_NAME_NULLABLE, values);

        return id;
    }

    public Cursor getAll (){

        String [] projection = {
                LoanContract.LoanEntry.COLUMN_NAME_LOAN_ID,
                        LoanContract.LoanEntry.COLUMN_NAME_LOAN_AMOUNT,
                        LoanContract.LoanEntry.COLUMN_NAME_INTEREST,
                        LoanContract.LoanEntry.COLUMN_NAME_MONTHLY_PAYMENT,
                        LoanContract.LoanEntry.COLUMN_NAME_TERM,
                        LoanContract.LoanEntry.COLUMN_NAME_TOTAL_AMOUNT,
                        LoanContract.LoanEntry.COLUMN_NAME_TOTAL_INTEREST,
                        LoanContract.LoanEntry.COLUMN_NAME_SAVED_DATE};

        String sortOrder = LoanContract.LoanEntry.COLUMN_NAME_SAVED_DATE + " DESC";

        Cursor c = db.query(LoanContract.LoanEntry.TABLE_NAME, projection,null,null,null,null,sortOrder);

        return c;
    }
}
