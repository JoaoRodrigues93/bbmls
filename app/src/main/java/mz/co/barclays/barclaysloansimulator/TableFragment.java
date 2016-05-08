package mz.co.barclays.barclaysloansimulator;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import mz.co.barclays.barclaysloansimulator.formulas.FormatValues;
import mz.co.barclays.barclaysloansimulator.formulas.ScheduleTableFormulas;
import mz.co.barclays.barclaysloansimulator.models.ScheduleTable;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link TableFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TableFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private static int dp;

    public TableFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TableFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TableFragment newInstance() {
        TableFragment fragment = new TableFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
       dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_section_two,container,false);
        //preencheTabela(view);
        HomeTwoActivity.tableFragmentView = view;
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        preencheTabela(getView());
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    protected void preencheTabela(){
        preencheTabela(getView());
    }

    protected void preencheTabela(View view){
        int rows = MainActivity.loanData.getLoanTerm();
        FormatValues formatValues = new FormatValues();
        double endOfMonth,interest, beginOfMonth,instalment;
        int loan_term;

        TableRow.LayoutParams paramsCell = (TableRow.LayoutParams) view.findViewById(R.id.table_header_1).getLayoutParams();
        TableLayout.LayoutParams paramsRow = (TableLayout.LayoutParams) view.findViewById(R.id.header_row).getLayoutParams();

        TableLayout table = (TableLayout) view.findViewById(R.id.schedule_table);
        TableRow row = new TableRow(view.getContext());
        ScheduleTable st;
        TableRow tableRow = (TableRow)table.getChildAt(0);
        table.removeAllViews();
        table.addView(tableRow);


        View line = new View(view.getContext());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
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

        TextView loan_termText = new TextView(view.getContext());
        TextView instalmentText = new TextView(view.getContext());
        TextView interestText = new TextView(view.getContext());
        TextView beginOfMonthText = new TextView(view.getContext());
        TextView endOfMonthText = new TextView(view.getContext());

        loan_termText.setLayoutParams(paramsCell);
        loan_termText.setText(String.valueOf(loan_term));
        instalmentText.setText(formatValues.formatAmount(instalment));
        interestText.setText(formatValues.formatAmount(interest));
        beginOfMonthText.setText(formatValues.formatAmount(beginOfMonth));
        endOfMonthText.setText(formatValues.formatAmount(endOfMonth));

        setLayout(loan_termText);
        setLayout(instalmentText);
        setLayout(interestText);
        setLayout(beginOfMonthText);
        setLayout(endOfMonthText);
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
            View line1 = new View(view.getContext());
            ViewGroup.LayoutParams layoutParams1 = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            int dp1 = dp;
            layoutParams1.height = dp1;
            line1.setLayoutParams(layoutParams1);
            line1.setBackgroundColor(Color.parseColor("#2196F3"));

            TableRow row1 = new TableRow(view.getContext());
            loan_termText = new TextView(view.getContext());
            instalmentText = new TextView(view.getContext());
            interestText = new TextView(view.getContext());
            beginOfMonthText = new TextView(view.getContext());
            endOfMonthText = new TextView(view.getContext());

            ScheduleTableFormulas.nextMonth();
            endOfMonth = ScheduleTableFormulas.endOfMonth;
            beginOfMonth = ScheduleTableFormulas.beginOfMonth;
            interest = ScheduleTableFormulas.interestOfMonth;
            instalment = ScheduleTableFormulas.installment;
            loan_term = ScheduleTableFormulas.noOfMonth;

            loan_termText.setLayoutParams(paramsCell);
            loan_termText.setText(String.valueOf(loan_term));
            instalmentText.setText(formatValues.formatAmount(instalment));
            interestText.setText(formatValues.formatAmount(interest));
            beginOfMonthText.setText(formatValues.formatAmount(beginOfMonth));
            endOfMonthText.setText(formatValues.formatAmount(endOfMonth));

            setLayout(loan_termText);
            setLayout(instalmentText);
            setLayout(interestText);
            setLayout(beginOfMonthText);
            setLayout(endOfMonthText);

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

    private void setLayout (TextView textView){
        textView.setGravity(Gravity.LEFT);
        textView.setTextColor(Color.BLACK);
        textView.setPadding(2*dp,2*dp,2*dp,2*dp);
    }
}
