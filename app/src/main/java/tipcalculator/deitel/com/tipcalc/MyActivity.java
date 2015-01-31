package tipcalculator.deitel.com.tipcalc;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;






public class MyActivity extends Activity {



    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();



    private double billAmount = 0.0;
    private double customPercent = 0.18;
    private TextView amountDisplayTextView;
    private TextView percentCustomTextView;
    private TextView tip15TextView;
    private TextView total15TextView;
    private TextView tipCustomTextView;
    private TextView totalCustomTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);




        amountDisplayTextView = (TextView) findViewById(R.id.amountDisplayTextView);
        percentCustomTextView = (TextView) findViewById(R.id.percentCustomTextView);
        tip15TextView = (TextView) findViewById(R.id.tip15TextView);
        total15TextView = (TextView) findViewById(R.id.total15TextView);
        tipCustomTextView = (TextView) findViewById(R.id.tipCustomTextView);
        totalCustomTextView = (TextView) findViewById(R.id.totalCustomTextView);





        amountDisplayTextView.setText(currencyFormat.format(billAmount));
        updateStandard();
        updateCustom();


        EditText amountEditText = (EditText)findViewById(R.id.amountEditText);
        amountEditText.addTextChangedListener(amountEditTextWatcher);


        SeekBar customTipSeekBar = (SeekBar) findViewById(R.id.customTipSeekBar);
        customTipSeekBar.setOnSeekBarChangeListener(customSeekBarChangeListener);
    }



    private void updateStandard(){


        double fifteenPercentTip = billAmount * 0.15;
        double fifteenPercentTotal = billAmount + fifteenPercentTip;


        tip15TextView.setText(currencyFormat.format(fifteenPercentTip));
        total15TextView.setText(currencyFormat.format(fifteenPercentTotal));

    }

    private void updateCustom(){


        percentCustomTextView.setText(percentFormat.format(customPercent));


        double customTip = billAmount * customPercent;
        double customTotal = billAmount + customTip;


        tipCustomTextView.setText(currencyFormat.format(customTip));
        totalCustomTextView.setText(currencyFormat.format(customTotal));

    }

    private SeekBar.OnSeekBarChangeListener customSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {






            customPercent = progress / 100.0;
            updateCustom();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };


    private TextWatcher amountEditTextWatcher = new TextWatcher() {


        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            try{
                billAmount = Double.parseDouble(s.toString()) / 100.0;
            }
            catch (NumberFormatException e){
                billAmount = 0.0;
            }
            amountDisplayTextView.setText(currencyFormat.format(billAmount));
            updateStandard();
            updateCustom();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        }
    };


}
