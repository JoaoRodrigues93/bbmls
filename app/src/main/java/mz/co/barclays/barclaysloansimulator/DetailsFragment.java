package mz.co.barclays.barclaysloansimulator;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import mz.co.barclays.barclaysloansimulator.formulas.FormatValues;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link DetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public DetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment DetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailsFragment newInstance() {
        DetailsFragment fragment = new DetailsFragment();
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
        View root = inflater.inflate(R.layout.home_section_one,container,false);
        //preencheDetalhes(root);
        return root;
    }

    private void preencheDetalhes(View view){
        //DecimalFormat format = new DecimalFormat("#0.00");
        FormatValues formatValues = new FormatValues();
        TextView loan_amount = (TextView) view.findViewById(R.id.loan_amount_detail);
        TextView interest_rate = (TextView) view.findViewById(R.id.interest_rate_detail);
        TextView loan_term = (TextView) view.findViewById(R.id.loan_term_detail);
//            TextView contribution = (TextView) view.findViewById(R.id.contribution_detail);

         EditText  monthly_payment = (EditText) view.findViewById(R.id.monthly_payment_detail);
        EditText   total_interest = (EditText) view.findViewById(R.id.total_interest_detail);
        EditText   total_amount = (EditText) view.findViewById(R.id.total_amount_detail);


        monthly_payment.setText(formatValues.formatAmount(MainActivity.loanData.getMonthlyPayment()));
        total_interest.setText(formatValues.formatAmount(MainActivity.loanData.getTotalInterest()));
        total_amount.setText(formatValues.formatAmount(MainActivity.loanData.getTotalAmount()));

        loan_amount.setText(formatValues.formatAmount(MainActivity.loanData.getLoanAmount())+" MZN");
        interest_rate.setText(formatValues.formatInterest(MainActivity.loanData.getAnnualInterest()) + "%");
        loan_term.setText(formatValues.formatMonth(MainActivity.loanData.getLoanTerm()) + " " + getString(R.string.months));
//            contribution.setText(format.format(MainActivity.loanData.getContributionAmount()) + " MZN");

    }

    @Override
    public void onResume() {
        super.onResume();
        preencheDetalhes(getView());
    }
}
