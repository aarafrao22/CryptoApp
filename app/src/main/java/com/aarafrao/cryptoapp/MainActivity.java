package com.aarafrao.cryptoapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView btnLeverage, txtSizeUsdt, txtPriceA, txtPrice, txtMargin, txtPercentage, txtProfit;
    private CardView btnDown, btnUp;
    int leverage = 1;
    EditText edMargin, edProfit, edPercent;
    double size, margin, markPrice, entryPrice, unRealizedPNL, percentage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLeverage = findViewById(R.id.btnLeverage);
        btnUp = findViewById(R.id.btnUp);
        edProfit = findViewById(R.id.edProfit);
        edPercent = findViewById(R.id.edPercent);
        txtPrice = findViewById(R.id.txtPrice);
        edMargin = findViewById(R.id.edMargin);
        txtMargin = findViewById(R.id.txtMargin);
        btnDown = findViewById(R.id.btnDown);
        txtPercentage = findViewById(R.id.txtPercentage);
        txtProfit = findViewById(R.id.txtProfit);
        txtSizeUsdt = findViewById(R.id.txtSizeUsdt);
        txtPriceA = findViewById(R.id.txtPriceA);

        markPrice = Double.valueOf(txtPrice.getText().toString());
        margin = Double.valueOf(txtMargin.getText().toString());
        entryPrice = Double.valueOf(txtPriceA.getText().toString());

        size = margin * leverage;
        txtSizeUsdt.setText(String.valueOf(size));

        clickListeners();

        edMargin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    margin = Double.valueOf(edMargin.getText().toString());
                    size = margin * leverage;
                    txtMargin.setText(String.valueOf(margin));
                }
            }
        });
        edPercent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    percentage = Double.valueOf(edPercent.getText().toString());
                    txtPercentage.setText(String.valueOf(Math.round(percentage)));
                }
            }
        });
        edProfit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    unRealizedPNL = Double.valueOf(edProfit.getText().toString());
                    txtProfit.setText(String.valueOf(unRealizedPNL));
                }
            }
        });

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

                txtPrice.setText(String.valueOf(markPrice));
                unRealizedPNL = (markPrice - entryPrice) * leverage;
                txtProfit.setText(String.valueOf(unRealizedPNL));


                if (markPrice > entryPrice) {
                    txtProfit.setTextColor(Color.parseColor("#009688"));
                    txtPercentage.setTextColor(Color.parseColor("#009688"));
                } else if (markPrice < entryPrice) {
                    txtProfit.setTextColor(Color.parseColor("#BA0202"));
                    txtPercentage.setTextColor(Color.parseColor("#BA0202"));
                }
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
                markPrice = markPrice + 0.0001;
                txtPrice.setText(String.valueOf(markPrice));
                unRealizedPNL = (markPrice - entryPrice) * leverage * 10;
                txtProfit.setText(String.valueOf(unRealizedPNL));

                percentage = (markPrice / entryPrice) * leverage * 100;
                txtPercentage.setText(String.valueOf(Math.round(percentage * 100.0) / 100.0));
                if (markPrice > entryPrice) {
                    txtProfit.setTextColor(Color.parseColor("#009688"));
                    txtPercentage.setTextColor(Color.parseColor("#009688"));
                } else if (markPrice < entryPrice) {
                    txtProfit.setTextColor(Color.parseColor("#BA0202"));
                    txtPercentage.setText("-" + String.valueOf(Math.round(percentage * 100.0) / 100.0).concat("%"));
                    txtPercentage.setTextColor(Color.parseColor("#BA0202"));
                }


                break;

            case R.id.btnDown:
                markPrice = markPrice - 0.0001;
                txtPrice.setText(String.valueOf(markPrice));
                unRealizedPNL = (markPrice - entryPrice) * leverage * 10;
                txtProfit.setText(String.valueOf(unRealizedPNL));
                percentage = (markPrice / entryPrice) * leverage * 100;
                txtPercentage.setText(String.valueOf(Math.round(percentage * 100.0) / 100.0).concat("%"));
                if (markPrice > entryPrice) {
                    txtProfit.setTextColor(Color.parseColor("#009688"));
                    txtPercentage.setTextColor(Color.parseColor("#009688"));
                } else if (markPrice < entryPrice) {
                    txtProfit.setTextColor(Color.parseColor("#BA0202"));
                    txtPercentage.setText("-" + String.valueOf(Math.round(percentage * 100.0) / 100.0).concat("%"));
                    txtPercentage.setTextColor(Color.parseColor("#BA0202"));
                }
                break;
        }
    }
}