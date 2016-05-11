package mz.co.barclays.barclaysloansimulator;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.math.RoundingMode;

import mz.co.barclays.barclaysloansimulator.formulas.Finance;
import mz.co.barclays.barclaysloansimulator.formulas.FormatValues;
import mz.co.barclays.barclaysloansimulator.models.Loan;
import mz.co.barclays.barclaysloansimulator.models.MainMenuActions;


public class MainActivity extends AppCompatActivity {


    public static double loanAmount;
    public static double annualInterest;
    public static int loanTerm;
    public static boolean onBack;
    public static double contribution;
    public static double percent;
    public static Loan loanData;

    private SeekBar.OnSeekBarChangeListener onSeekBarChangeAmount = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };



    private SeekBar.OnSeekBarChangeListener onSeekBarChangeInterest = new SeekBar.OnSeekBarChangeListener(){
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            EditText annualInterestText = (EditText) findViewById(R.id.annual_interest);
            float progressFloat = progress;
            float annualInterest = progressFloat/4;

            annualInterestText.setText(""+annualInterest);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private SeekBar.OnSeekBarChangeListener onSeekBarChangeTerm = new SeekBar.OnSeekBarChangeListener(){
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            EditText loanTermText = (EditText) findViewById(R.id.loan_term);

            loanTermText.setText(""+(progress*12));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //SeekBar seek_amount = (SeekBar)findViewById(R.id.seek_loan_amount);
        SeekBar seek_interest_rate = (SeekBar)findViewById(R.id.seek_interest_rate);
        SeekBar seek_loan_term = (SeekBar)findViewById(R.id.seek_loan_term);

        //seek_amount.setOnSeekBarChangeListener(onSeekBarChangeAmount);
        seek_interest_rate.setOnSeekBarChangeListener(onSeekBarChangeInterest);
        seek_loan_term.setOnSeekBarChangeListener(onSeekBarChangeTerm);

        EditText loanAmountText = (EditText)findViewById(R.id.loan_amount);
        EditText annualInterestText = (EditText) findViewById(R.id.annual_interest);
        EditText loanTermsText = (EditText) findViewById(R.id.loan_term);

        loanAmountText.setText(String.valueOf(2000));
        seek_interest_rate.setProgress(48);
        seek_loan_term.setProgress(2);

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

       switch (id ){
           case R.id.about:
               MainMenuActions.openAbout(this);
               return true;
           case R.id.version:
               MainMenuActions.openVersion(this);
               return true;
           case R.id.contact:
               MainMenuActions.openContact(this);
               return true;
       }

        return super.onOptionsItemSelected(item);
    }

    public void calculateLoan (View view){
        EditText loanAmountText = (EditText)findViewById(R.id.loan_amount);
        EditText annualInterestText = (EditText) findViewById(R.id.annual_interest);
        EditText loanTermsText = (EditText) findViewById(R.id.loan_term);
//        EditText contributionAmountText = (EditText)findViewById(R.id.contribution_amount);
//        EditText contributionPercentText = (EditText)findViewById(R.id.contribution_percent);




        loanAmount = Float.valueOf(loanAmountText.getText().toString());
        annualInterest = Float.valueOf(annualInterestText.getText().toString());
        loanTerm = Integer.valueOf(loanTermsText.getText().toString());
//        contribution = Float.valueOf(contributionAmountText.getText().toString());
//        percent = Float.valueOf(contributionPercentText.getText().toString());


        loanData = new Loan();

        loanData.setAnnualInterest(annualInterest);
        loanData.setLoanAmount(loanAmount);
        loanData.setLoanTerm(loanTerm);
        loanData.setContributionAmount(contribution);
        loanData.setContributionPercent(percent);
        loanData.calculateLoan();

        Intent intent = new Intent(this,HomeTwoActivity.class);
        if(onBack==false)
            onBack = true;
        startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();

        if(onBack){
            FormatValues formatValues = new FormatValues();
            EditText loanAmountText = (EditText)findViewById(R.id.loan_amount);
            EditText annualInterestText = (EditText) findViewById(R.id.annual_interest);
            EditText loanTermsText = (EditText) findViewById(R.id.loan_term);
//            EditText contributionAmountText = (EditText)findViewById(R.id.contribution_amount);
//            EditText contributionPercentText = (EditText)findViewById(R.id.contribution_percent);

            loanAmount = new BigDecimal(loanAmount).setScale(2, RoundingMode.UP).doubleValue();
            annualInterest = new BigDecimal(annualInterest).setScale(2, RoundingMode.UP).doubleValue();

            loanAmountText.setText(String.valueOf(loanAmount));
            annualInterestText.setText(String.valueOf(annualInterest));
            loanTermsText.setText(String.valueOf(loanTerm));
//            contributionAmountText.setText(String.valueOf(contribution));
//            contributionPercentText.setText(String.valueOf(percent));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
