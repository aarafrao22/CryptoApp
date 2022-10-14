package com.aarafrao.cryptoapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView btnLeverage, txtSizeUsdt;
    private CardView btnDown, btnUp;
    private String lev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLeverage = findViewById(R.id.btnLeverage);
        btnUp = findViewById(R.id.btnUp);
        btnDown = findViewById(R.id.btnDown);


    }

    private void showSpeedDialogue() {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(MainActivity.this);
        builderSingle.setTitle("Select Leverage");

        final ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(MainActivity.this, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("1x");
        arrayAdapter.add("2x");
        arrayAdapter.add("3x");
        arrayAdapter.add("4x");
        arrayAdapter.add("5x");
        arrayAdapter.add("6x");
        arrayAdapter.add("7x");
        arrayAdapter.add("8x");
        arrayAdapter.add("9x");
        arrayAdapter.add("10x");
        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                lev = arrayAdapter.getItem(which);

            }
        });
        builderSingle.show();



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLeverage:
                showSpeedDialogue();
                break;
        }
    }
}