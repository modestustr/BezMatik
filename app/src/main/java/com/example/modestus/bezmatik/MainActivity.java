package com.example.modestus.bezmatik;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.View;

import java.text.DecimalFormat;

import static android.view.View.*;

public class MainActivity extends AppCompatActivity {

    EditText adet, toplam, kargo;
    TextView birim;
    Float a = 0f;
    Float b = 0f;
    Float c = 0f;
    String strAdet;
    String strToplam;
    String strKargo;

    public static boolean isNullOrEmpty(String a) {
        return a == null || a.isEmpty();
    }

    public static boolean isNullOrWhiteSpace(String a) {
        return a == null || (a.length() > 0 && a.trim().length() <= 0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adet = (EditText) findViewById(R.id.txtAdet);
        toplam = (EditText) findViewById(R.id.txtToplam);
        kargo = (EditText) findViewById(R.id.txtKargo);
        birim = (TextView) findViewById(R.id.txtBirim);

        Button myButon = (Button) findViewById(R.id.btnHesapla);
        myButon.setOnClickListener(onClickListener);
        Button temizle = (Button) findViewById(R.id.imgBtnTemizle);
        temizle.setOnClickListener(onClickListener);
        Button temizleAdet = (Button) findViewById(R.id.imgBtnTemizleAdet);
        temizleAdet.setOnClickListener(onClickListener);

        Button temizleToplam = (Button) findViewById(R.id.imgBtnTemizleToplam);
        temizleToplam.setOnClickListener(onClickListener);

        Button temizleKargo = (Button) findViewById(R.id.imgBtnTemizleKargo);
        temizleKargo.setOnClickListener(onClickListener);


    }

    private OnClickListener onClickListener;

    {
        onClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.imgBtnTemizle:
                        adet.setText(getString(R.string.bos));
                        toplam.setText(getString(R.string.bos));
                        kargo.setText(getString(R.string.bos));
                        birim.setText(getString(R.string.birim));
                        slide_left();
                        adet.requestFocus();
                        break;
                    case R.id.btnHesapla:
                        try {

                            strAdet = strToplam = strKargo = "0f";

                            if (isNullOrEmpty(adet.getText().toString()) == false)
                                strAdet = adet.getText().toString();
                            a = Float.parseFloat(strAdet);

                            if (!isNullOrEmpty(toplam.getText().toString()))
                                strToplam = toplam.getText().toString();
                            b = Float.parseFloat(strToplam);

                            if (!isNullOrEmpty(kargo.getText().toString()))
                                strKargo = kargo.getText().toString();
                            c = Float.parseFloat(strKargo);


                            birim.setText(String.valueOf(Cevir(getHesapla(a, b, c))));

                        } catch (Exception ex) {
                            birim.setText("");
                        }

                        slide_left();
                        break;
                    case R.id.imgBtnTemizleAdet:
                        adet.setText("");
                        adet.requestFocus();
                        break;
                    case R.id.imgBtnTemizleToplam:
                        toplam.setText("");
                        toplam.requestFocus();
                        break;

                    case R.id.imgBtnTemizleKargo:
                        kargo.setText("");kargo.requestFocus();
                        break;
                }
            }

            public void slide_left() {
                birim.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.slide_out_right));
            }
        };
    }

    public float getHesapla(float adet, float toplam, float kargo) {
        float rtn = 0;


        if (adet == 0) {
            rtn = 0;
        }
        if (kargo == 0) {
            rtn = toplam / adet;
        }
        if (kargo > 0) {
            rtn = (toplam + kargo) / adet;
        }

        return rtn;
    }

    public String Cevir(float values) {

        Float x = Float.parseFloat(String.valueOf(values));
        DecimalFormat df = new DecimalFormat("#.##");
        df.setMaximumFractionDigits(2);
        return df.format(x);
    }
}