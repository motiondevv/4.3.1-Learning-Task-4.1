package com.demo.percentagecalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etPercent, etNumber, etNumber2;
    String strPercent, strNumber, strNumber2, resultMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCompute = (Button) findViewById(R.id.btnCompute);

        btnCompute.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Computing....", Toast.LENGTH_SHORT).show();
        ComputeResult();
    }

    public void ComputeResult(){
        etPercent = (EditText) findViewById(R.id.etPercent);
        etNumber = (EditText) findViewById(R.id.etNumber);
        etNumber2 = (EditText) findViewById(R.id.etNumber2);
        if(etPercent.getText().toString().isEmpty() || etNumber.getText().toString().isEmpty() || etNumber2.getText().toString().isEmpty()){
            strPercent = "0";
            strNumber = "0";
            strNumber2 = "0";
        }else{
            strPercent = etPercent.getText().toString();
            strNumber = etNumber.getText().toString();
            strNumber2 = etNumber2.getText().toString();
        }
        double rawPercent = Double.parseDouble(strPercent);
        double rawNumber = Double.parseDouble(strNumber);
        double rawNumber2 = Double.parseDouble(strNumber2);

        rawPercent = ToDecimal(rawPercent);

        double result = rawPercent * (rawNumber + rawNumber2);
        double sum = rawNumber + rawNumber2;
        // Casted result to int type, remove to show decimal value
        resultMessage = (double) result + " is " + strPercent +"% of " +sum;

        // Create Bundle instance, this will allow transfer of data from Activity to DialogFragment
        Bundle args = new Bundle();
        args.putString("result", resultMessage);

        // Create a dialog instance
        DialogFragmentCustom dialogFragmentImp = new DialogFragmentCustom();
        // Pass on dialog argument(args), the result
        dialogFragmentImp.setArguments(args);
        // Display the Dialog
        dialogFragmentImp.show(getSupportFragmentManager(),"Display Re  sult");
        // Reset EditTexts
        clearEditText();
    }
    public void clearEditText(){
        etNumber.getText().clear();
        etPercent.getText().clear();
        etPercent.requestFocus();
        etNumber2.getText().clear();
    }
    public double ToDecimal(double nbr){
        nbr = nbr/100;
        return nbr;
    }
}

