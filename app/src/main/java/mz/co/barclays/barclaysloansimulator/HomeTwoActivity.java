package mz.co.barclays.barclaysloansimulator;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Editable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import mz.co.barclays.barclaysloansimulator.formulas.FormatValues;
import mz.co.barclays.barclaysloansimulator.formulas.ScheduleTableFormulas;
import mz.co.barclays.barclaysloansimulator.models.Loan;
import mz.co.barclays.barclaysloansimulator.models.LoanTableAcess;
import mz.co.barclays.barclaysloansimulator.models.ScheduleTable;

public class HomeTwoActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    public static Context context;
    private static List<Loan> loans;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    protected static ViewPager mViewPager;
    protected static View tableFragmentView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_two);

        context = getApplicationContext();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }

    public void saveLoan(View view){
        long id;
        LoanTableAcess loanTableAcess = new LoanTableAcess(getApplicationContext());
        Loan loan = MainActivity.loanData;
        id = loanTableAcess.insert(loan);

        Toast.makeText(view.getContext(),"Loan Saved",Toast.LENGTH_LONG).show();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            //return PlaceholderFragment.newInstance(position + 1);



            switch (position){
                case 0: return DetailsFragment.newInstance();
                case 1: return TableFragment.newInstance();
                case 2: return MyLoansFragment.newInstance();
            }

            return  null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.section_1);
                case 1:
                    return getString(R.string.section_2);
                case 2:
                    return getString(R.string.section_3);
            }
            return null;
        }
    }

    public static List<Loan> readLoan ( ) {
        Cursor cursor;
        LoanTableAcess loanTableAcess = new LoanTableAcess(context);
        cursor = loanTableAcess.getAll();

        List<Loan> loans = new ArrayList<Loan>();

        Loan loan;

        if(cursor.moveToFirst()) {
            cursor.moveToFirst();
            do {
                Calendar date;
                loan = new Loan();
                loan.setLoanAmount(cursor.getDouble(1));
                loan.setAnnualInterest(cursor.getDouble(2));
                loan.setMonthlyPayment(cursor.getDouble(3));
                loan.setLoanTerm(cursor.getInt(4));
                loan.setTotalAmount(cursor.getDouble(5));
                loan.setTotalInterest(cursor.getDouble(6));
                date = Calendar.getInstance();
                date.setTimeInMillis(cursor.getLong(7));
                loan.setDateSaved(date);
                loans.add(loan);
            } while (cursor.moveToNext());

        }

        return loans;
    }
}
