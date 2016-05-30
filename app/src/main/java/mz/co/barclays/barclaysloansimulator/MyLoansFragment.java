package mz.co.barclays.barclaysloansimulator;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import mz.co.barclays.barclaysloansimulator.formulas.FormatValues;
import mz.co.barclays.barclaysloansimulator.models.Loan;
import mz.co.barclays.barclaysloansimulator.models.LoanTableAcess;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MyLoansFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyLoansFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static List<Loan> loans;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyLoansFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MyLoansFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyLoansFragment newInstance() {
        MyLoansFragment fragment = new MyLoansFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_section_three,container,false);
        init(view);
        return view;
    }

    public void init (View view){
        loans = new ArrayList<Loan>();

        Calendar date = Calendar.getInstance();

        loans = HomeTwoActivity.readLoan();


                    /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                            android.R.layout.simple_list_item_1, myStringArray);*/

        String[] fromColumns = {"loanAmount",
                "loanTerm","data","tile"};

        int[] toViews = {R.id.loan_amount_item, R.id.loan_term_item,R.id.date_item,R.id.tile};
        ArrayList<HashMap<String,String>> loansMap;
        loansMap = toMap(loans);
        ListAdapter adapter = new SimpleAdapter(
                getContext(), loansMap,
                R.layout.list_item, fromColumns,
                toViews);

        ListView listView = (ListView) view.findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Loan loan = loans.get(position);
                MainActivity.loanData = loan;
                MainActivity.loanAmount = loan.getLoanAmount();
                MainActivity.loanTerm = loan.getLoanTerm();
                MainActivity.annualInterest = loan.getAnnualInterest();
                TableFragment tableFragment = new TableFragment();
                tableFragment.preencheTabela(HomeTwoActivity.tableFragmentView);
                HomeTwoActivity.mViewPager.setCurrentItem(0);

            }
        });
    }

    public List<Loan> readLoan (View view ) {
        Cursor cursor;
        LoanTableAcess loanTableAcess = new LoanTableAcess(view.getContext());
        cursor = loanTableAcess.getAll();

        List<Loan> loans = new ArrayList<Loan>();

        Loan loan;

        while (!cursor.isClosed()){
            Calendar date;
            cursor.moveToNext();
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
        }

        return loans;
    }

    private ArrayList<HashMap<String, String>> toMap(List<Loan> loans){
        ArrayList<HashMap<String, String>> loanList = new ArrayList<HashMap<String, String>>();
        FormatValues formatValues = new FormatValues();
        for (int i = 0; i <loans.size(); i++) {
            HashMap<String,String> map = new HashMap<String,String>();
            Loan loan = loans.get(i);

            Calendar data;
            data = loan.getDateSaved();
            map.put("loanAmount",formatValues.formatAmount(loan.getLoanAmount())+" MZN");
            map.put("loanTerm", formatValues.formatMonth(loan.getLoanTerm())+ " "+ getString(R.string.months));
            map.put("tile",String.valueOf(i + 1));
            map.put("data",formatValues.formatDate(data));
            loanList.add(map);
        }

        return  loanList;
    }

    public List<Loan> readLoan ( ) {
        Cursor cursor;
        LoanTableAcess loanTableAcess = new LoanTableAcess(getContext());
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
