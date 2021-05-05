package sg.edu.rp.c346.id20047401.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    Button split;
    Button reset;
    EditText amount;
    EditText numOfPax;
    EditText discount;
    ToggleButton svs;
    ToggleButton gst;
    TextView totalBill;
    TextView eachPays;
    RadioGroup rgPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        split = findViewById(R.id.btnSplit);
        reset = findViewById(R.id.btnReset);
        amount = findViewById(R.id.editInputAmount);
        numOfPax = findViewById(R.id.editInputNumOfPax);
        discount = findViewById(R.id.editInputDiscount);
        svs = findViewById(R.id.toggleButtonSVS);
        gst = findViewById(R.id.toggleButtonGST);
        totalBill = findViewById(R.id.totalBillDisplay);
        eachPays = findViewById(R.id.eachPaysDisplay);
        rgPayment = findViewById(R.id.radioGroupPayment);

        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amount.getText().toString().trim().length() != 0 && numOfPax.getText().toString().trim().length() != 0) {
                    double originalAmount = Double.parseDouble(amount.getText().toString());
                    double newAmount = 0.0;
                    if (svs.isChecked() && gst.isChecked()){
                        newAmount = originalAmount * 1.17;
                    } else if (svs.isChecked() && !gst.isChecked()) {
                        newAmount = originalAmount * 1.1;
                    } else if (!svs.isChecked() && gst.isChecked()) {
                        newAmount = originalAmount * 1.07;
                    } else {
                        newAmount = originalAmount;
                    }
                    if (discount.getText().toString().trim().length() != 0 ){
                        double discountPercentage = Double.parseDouble(discount.getText().toString());
                        newAmount *= 1 - (discountPercentage/100);
                    }

                    totalBill.setText(" $" + String.format("%.2f", newAmount));
                    int totalNumPerson = Integer.parseInt(numOfPax.getText().toString());
                    String eachPaysFinal;
                    if (totalNumPerson > 1){
                        eachPaysFinal = " $" + String.format("%.2f", newAmount/totalNumPerson);
                    }else {
                        eachPaysFinal = " $" + String.format("%.2f", newAmount);
                    }

                    if (rgPayment.getCheckedRadioButtonId() == R.id.radioButtonCash){
                        eachPays.setText(eachPaysFinal + " in cash");
                    }else{
                        eachPays.setText(eachPaysFinal + " via PayNow to 87792785");
                    }
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText("");
                numOfPax.setText("");
                discount.setText("");
                svs.setChecked(false);
                gst.setChecked(false);

            }
        });

    }
}