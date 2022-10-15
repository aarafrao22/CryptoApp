package com.aarafrao.cryptoapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView btnLeverage, txtSizeUsdt, txtPrice, txtMargin, txtPercentage, txtProfit;
    private CardView btnDown, btnUp;
    int leverage = 1;
    double size, margin, price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLeverage = findViewById(R.id.btnLeverage);
        btnUp = findViewById(R.id.btnUp);
        txtPrice = findViewById(R.id.txtPrice);
        txtMargin = findViewById(R.id.txtMargin);
        btnDown = findViewById(R.id.btnDown);
        txtPercentage = findViewById(R.id.txtPercentage);
        txtProfit = findViewById(R.id.txtProfit);
        txtSizeUsdt = findViewById(R.id.txtSizeUsdt);

        price = Double.valueOf(txtPrice.getText().toString());
        margin = Double.valueOf(txtMargin.getText().toString());

        size = margin * leverage;
        txtSizeUsdt.setText(String.valueOf(size));

        clickListeners();


    }

    private void clickListeners() {
        btnLeverage.setOnClickListener(this);
        btnUp.setOnClickListener(this);
        btnDown.setOnClickListener(this);
    }

    private void showSpeedDialogue() {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(MainActivity.this);
        final ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(MainActivity.this, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("1");
        arrayAdapter.add("2");
        arrayAdapter.add("3");
        arrayAdapter.add("4");
        arrayAdapter.add("5");
        arrayAdapter.add("6");
        arrayAdapter.add("7");
        arrayAdapter.add("8");
        arrayAdapter.add("9");
        arrayAdapter.add("10");
        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                leverage = which + 1;
                size = margin * leverage;
                txtSizeUsdt.setText(String.valueOf(size));
            }
        });
        builderSingle.show();


    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLeverage:
                showSpeedDialogue();
                break;

            case R.id.btnUp:
                price = price + 0.0001;
                txtPrice.setText(String.valueOf(price));
                txtProfit.setTextColor(R.color.green);
                txtPercentage.setTextColor(R.color.green);
                break;

            case R.id.btnDown:
                price = price - 0.0001;
                txtPrice.setText(String.valueOf(price));
                txtProfit.setTextColor(R.color.red);
                txtPercentage.setTextColor(R.color.red);
                break;
        }
    }
}