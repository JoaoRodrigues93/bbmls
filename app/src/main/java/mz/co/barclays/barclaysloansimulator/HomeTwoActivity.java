package mz.co.barclays.barclaysloansimulator;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.CursorLoader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import mz.co.barclays.barclaysloansimulator.formulas.ScheduleTableFormulas;
import mz.co.barclays.barclaysloansimulator.models.LoanSimple;
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

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_two);

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


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    }

    public void saveLoan(View view){
        Toast.makeText(view.getContext(),"Saving a loan",Toast.LENGTH_LONG);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home_two, menu);
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
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            int section = getArguments().getInt(ARG_SECTION_NUMBER);

            List<LoanSimple> loans = new ArrayList<LoanSimple>();

            Calendar date = Calendar.getInstance();

            loans.add(new LoanSimple("20000.00 MZN", "12 Months","1699.00 MZN",date));
            loans.add(new LoanSimple("4000.00 MZN", "18 Months","3400.00 MZN",date));
            loans.add(new LoanSimple("50000.00 MZN","24 Months","3500.00 MZN",date));
            loans.add(new LoanSimple("20000.00 MZN", "12 Months","1699.00 MZN",date));
            loans.add(new LoanSimple("4000.00 MZN", "18 Months","3400.00 MZN",date));
            loans.add(new LoanSimple("50000.00 MZN","24 Months","3500.00 MZN",date));
            loans.add(new LoanSimple("20000.00 MZN", "12 Months","1699.00 MZN",date));
            loans.add(new LoanSimple("4000.00 MZN", "18 Months","3400.00 MZN",date));
            loans.add(new LoanSimple("50000.00 MZN","24 Months","3500.00 MZN",date));

            View rootView = null;
            switch (section){
                case 1: rootView = inflater.inflate(R.layout.home_section_one, container, false);
                    preencheDetalhes(rootView);
                    break;
                case 2: rootView = inflater.inflate(R.layout.home_section_two, container, false);
                    preencheTabela(rootView);
                    break;
                case 3: rootView = inflater.inflate(R.layout.home_section_three, container, false);

                    String [] myStringArray = {"Loan Amount","Loan Term"};

                    /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                            android.R.layout.simple_list_item_1, myStringArray);*/

                    String[] fromColumns = {"loanAmount",
                            "loanTerm","monthlyPayment","Data"};

                    int[] toViews = {R.id.loan_amount_item, R.id.loan_term_item,R.id.monthly_payment_item,R.id.date_item};
                    ArrayList<HashMap<String,String>> loansMap;
                    loansMap = toMap(loans);
                    ListAdapter adapter = new SimpleAdapter(
                           getContext(), loansMap,
                            R.layout.list_item, fromColumns,
                            toViews);


                    ListView listView = (ListView) rootView.findViewById(R.id.listView);
                    listView.setAdapter(adapter);

                    break;
            }

            /*TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));*/
            return rootView;
        }

        private void preencheDetalhes(View view){
            DecimalFormat format = new DecimalFormat("#0.00");
            TextView loan_amount = (TextView) view.findViewById(R.id.loan_amount_detail);
            TextView interest_rate = (TextView) view.findViewById(R.id.interest_rate_detail);
            TextView loan_term = (TextView) view.findViewById(R.id.loan_term_detail);
//            TextView contribution = (TextView) view.findViewById(R.id.contribution_detail);
            EditText monthly_payment = (EditText) view.findViewById(R.id.monthly_payment);
            EditText efective_interest = (EditText) view.findViewById(R.id.efective_interest);
            EditText total_interest = (EditText) view.findViewById(R.id.total_interest);
            EditText total_amount = (EditText) view.findViewById(R.id.total_amount);

            loan_amount.setText(format.format(MainActivity.loanData.getLoanAmount())+"MZN");
            interest_rate.setText(format.format(MainActivity.loanData.getAnnualInterest()) + "%");
            loan_term.setText(String.valueOf(MainActivity.loanData.getLoanTerm()) + " " + getString(R.string.months));
//            contribution.setText(format.format(MainActivity.loanData.getContributionAmount()) + " MZN");

            monthly_payment.setText(format.format(MainActivity.loanData.getMonthlyPayment()));
            efective_interest.setText(format.format(MainActivity.loanData.getEfectiveInterestRate()));
            total_interest.setText(format.format(MainActivity.loanData.getTotalInterest()));
            total_amount.setText(format.format(MainActivity.loanData.getTotalAmount()));

        }

        private void preencheTabela(View view){

            int rows = MainActivity.loanData.getLoanTerm();
            DecimalFormat format = new DecimalFormat("#0.00");
            double endOfMonth,interest, beginOfMonth,instalment;
            int loan_term;

            TableRow.LayoutParams paramsCell = (TableRow.LayoutParams) view.findViewById(R.id.table_header_1).getLayoutParams();
            TableLayout.LayoutParams paramsRow = (TableLayout.LayoutParams) view.findViewById(R.id.header_row).getLayoutParams();

            TableLayout table = (TableLayout) view.findViewById(R.id.schedule_table);
            TableRow row = new TableRow(view.getContext());
            ScheduleTable st;

            View line = new View(getContext());
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            int dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,1,getResources().getDisplayMetrics());
            layoutParams.height = dp;
            line.setLayoutParams(layoutParams);
            line.setBackgroundColor(Color.parseColor("#2196F3"));


            ScheduleTableFormulas.init();
            if(MainActivity.loanData.getContributionAmount()>=0)
                ScheduleTableFormulas.beginOfMonth = MainActivity.loanData.getLoanAmount() - MainActivity.loanData.getContributionAmount();
            else
                ScheduleTableFormulas.beginOfMonth = MainActivity.loanData.getLoanAmount() - MainActivity.loanData.getContributionAmount();
            ScheduleTableFormulas.loanTerm = MainActivity.loanData.getLoanTerm();
            ScheduleTableFormulas.interestRate = MainActivity.loanData.getAnnualInterest();
            ScheduleTableFormulas.installment = MainActivity.loanData.getMonthlyPayment();

            ScheduleTableFormulas.firstMonth();
            endOfMonth = ScheduleTableFormulas.endOfMonth;
            beginOfMonth = ScheduleTableFormulas.beginOfMonth;
            interest = ScheduleTableFormulas.interestOfMonth;
            instalment = ScheduleTableFormulas.installment;
            loan_term = ScheduleTableFormulas.noOfMonth;




            TextView loan_termText = new TextView(getContext());
            TextView instalmentText = new TextView(getContext());
            TextView interestText = new TextView(getContext());
            TextView beginOfMonthText = new TextView(getContext());
            TextView endOfMonthText = new TextView(getContext());

            loan_termText.setLayoutParams(paramsCell);
            loan_termText.setText(String.valueOf(loan_term));
            instalmentText.setText(format.format(instalment));
            interestText.setText(format.format(interest));
            beginOfMonthText.setText(format.format(beginOfMonth));
            endOfMonthText.setText(format.format(endOfMonth));


            loan_termText.setGravity(Gravity.CENTER_VERTICAL);
            instalmentText.setGravity(Gravity.CENTER_VERTICAL);
            interestText.setGravity(Gravity.CENTER_VERTICAL);
            beginOfMonthText.setGravity(Gravity.CENTER_VERTICAL);
            endOfMonthText.setGravity(Gravity.CENTER_VERTICAL);
            //row.setLayoutParams(paramsRow);
            row.addView(loan_termText);
            row.addView(beginOfMonthText);
            row.addView(interestText);
            row.addView(instalmentText);
            row.addView(endOfMonthText);


            table.addView(row);
            table.addView(line);

            while (ScheduleTableFormulas.hasNext())
            {
                View line1 = new View(getContext());
                ViewGroup.LayoutParams layoutParams1 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                int dp1 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,1,getResources().getDisplayMetrics());
                layoutParams1.height = dp1;
                line1.setLayoutParams(layoutParams1);
                line1.setBackgroundColor(Color.parseColor("#2196F3"));

                TableRow row1 = new TableRow(getContext());
                loan_termText = new TextView(getContext());
                instalmentText = new TextView(getContext());
                interestText = new TextView(getContext());
                beginOfMonthText = new TextView(getContext());
                endOfMonthText = new TextView(getContext());

                ScheduleTableFormulas.nextMonth();
                endOfMonth = ScheduleTableFormulas.endOfMonth;
                beginOfMonth = ScheduleTableFormulas.beginOfMonth;
                interest = ScheduleTableFormulas.interestOfMonth;
                instalment = ScheduleTableFormulas.installment;
                loan_term = ScheduleTableFormulas.noOfMonth;

                loan_termText.setLayoutParams(paramsCell);
                loan_termText.setText(String.valueOf(loan_term));
                instalmentText.setText(format.format(instalment));
                interestText.setText(format.format(interest));
                beginOfMonthText.setText(format.format(beginOfMonth));
                endOfMonthText.setText(format.format(endOfMonth));

                loan_termText.setGravity(Gravity.CENTER_VERTICAL);
                instalmentText.setGravity(Gravity.CENTER_VERTICAL);
                interestText.setGravity(Gravity.CENTER_VERTICAL);
                beginOfMonthText.setGravity(Gravity.CENTER_VERTICAL);
                endOfMonthText.setGravity(Gravity.CENTER_VERTICAL);

                row1.addView(loan_termText);
                row1.addView(beginOfMonthText);
                row1.addView(interestText);
                row1.addView(instalmentText);
                row1.addView(endOfMonthText);

                //row1.setLayoutParams(paramsRow);
                table.addView(row1);
                table.addView(line1);
            }
        }

        private ArrayList<HashMap<String, String>> toMap(List<LoanSimple> loans){
            ArrayList<HashMap<String, String>> loanList = new ArrayList<HashMap<String, String>>();

            for (int i = 0; i <loans.size(); i++) {
                HashMap<String,String> map = new HashMap<String,String>();
                LoanSimple loan = loans.get(i);

                map.put("loanAmount",loan.getLoanAmount());
                map.put("loanTerm",loan.getLoanTerm());
                map.put("monthlyPayment",loan.getMonthlyPayment());
                map.put("Data","26/04/2016");
                loanList.add(map);
            }

            return  loanList;
        }
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
            return PlaceholderFragment.newInstance(position + 1);
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
}
