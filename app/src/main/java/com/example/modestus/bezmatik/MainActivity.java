package com.example.modestus.bezmatik;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

import static android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

    //Controls
    EditText adet, toplam, kargo, etNum1, etNum2;
    TextView birim, tvResult;
    Button hesapla, temizle, temizleAdet, temizleToplam, temizleKargo, btnAdd, btnSub, btnMult, btnDiv;

    //Variables
    String strAdet, strToplam, strKargo, oper = "";
    Float a, b, c, num1, num2, result = 0f;
    private OnClickListener onClickListener;

    {
        onClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                String etNumara1 = etNum1.getText().toString();
                String etNumara2 = etNum2.getText().toString();

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
                        kargo.setText("");
                        kargo.requestFocus();
                        break;

                    case R.id.btnAdd:
                        oper = "+";
                        if (Kontrol(etNumara1, etNumara2))
                            result = num1 + num2;
                        break;
                    case R.id.btnSub:
                        oper = "-";
                        if (Kontrol(etNumara1, etNumara2)) result = num1 - num2;
                        break;
                    case R.id.btnMult:
                        oper = "*";
                        if (Kontrol(etNumara1, etNumara2)) result = num1 * num2;
                        break;
                    case R.id.btnDiv:
                        oper = "/";
                        if (Kontrol(etNumara1, etNumara2)) result = num1 / num2;
                        break;

                }
                tvResult.setText(num1 + " " + oper + " " + num2 + " = " + result);
            }

            public void slide_left() {
                birim.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.slide_out_right));
            }

        };
    }

    //Public Functions
    public static boolean isNullOrEmpty(String a) {
        return a == null || a.isEmpty();
    }

    public static boolean isNullOrWhiteSpace(String a) {
        return a == null || (a.length() > 0 && a.trim().length() <= 0);
    }

    public boolean Kontrol(String etNum1, String etNum2) {

        boolean a = false;

        if (isNullOrEmpty(etNum1) || isNullOrEmpty(etNum2)) {
            a = false;
            num1 = num2 = result = 0f;
        } else a = true;
        if (a) {
            // read EditText and fill variables with numbers
            num1 = Float.parseFloat(etNum1);
            num2 = Float.parseFloat(etNum2);
        }

        return a;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adet = (EditText) findViewById(R.id.txtAdet);
        toplam = (EditText) findViewById(R.id.txtToplam);
        kargo = (EditText) findViewById(R.id.txtKargo);
        birim = (TextView) findViewById(R.id.txtBirim);


        // find the elements
        etNum1 = (EditText) findViewById(R.id.etNum1);
        etNum2 = (EditText) findViewById(R.id.etNum2);

        hesapla = (Button) findViewById(R.id.btnHesapla);
        temizle = (Button) findViewById(R.id.imgBtnTemizle);
        temizleAdet = (Button) findViewById(R.id.imgBtnTemizleAdet);
        temizleToplam = (Button) findViewById(R.id.imgBtnTemizleToplam);
        temizleKargo = (Button) findViewById(R.id.imgBtnTemizleKargo);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSub = (Button) findViewById(R.id.btnSub);
        btnMult = (Button) findViewById(R.id.btnMult);
        btnDiv = (Button) findViewById(R.id.btnDiv);

        tvResult = (TextView) findViewById(R.id.tvResult);


        btnAdd.setOnClickListener(onClickListener);
        btnSub.setOnClickListener(onClickListener);
        btnMult.setOnClickListener(onClickListener);
        btnDiv.setOnClickListener(onClickListener);
        hesapla.setOnClickListener(onClickListener);
        temizle.setOnClickListener(onClickListener);
        temizleAdet.setOnClickListener(onClickListener);
        temizleToplam.setOnClickListener(onClickListener);
        temizleKargo.setOnClickListener(onClickListener);


    }


}