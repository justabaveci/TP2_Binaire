package com.example.tp2part2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<ToggleButton> tgBtnarray;
    LinearLayout ll;
    EditText edt;
    int n = 6;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Ref();

        Intent itt = getIntent();
        n = itt.getIntExtra(MainActivity2.NUMBER_OF_BUTTONS, 8);

        afficheNbtn(n, ll, tgBtnarray);
        ChangeNbtn(btn);
        EntiertoBinaire(edt, tgBtnarray);
        BinairetoEntier(edt, tgBtnarray);

    }

    void Ref () {
        tgBtnarray = new ArrayList<>();
        edt = (EditText) findViewById(R.id.edt);
        ll = (LinearLayout) findViewById(R.id.ll);
        btn = (Button) findViewById(R.id.btn);
    }

    int power2(int n) {
        if (n < 0) return -1;
        else if (n == 0) return 1;
        else return 2 * power2(n - 1);
    }

    void EntiertoBinaire(final EditText edt, final ArrayList<ToggleButton> tgBtnarray) {
        //Viet so roi auto chuyen sang dang binaire
        edt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (edt.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Ecrire un nombre", Toast.LENGTH_SHORT).show();
                } else {
                    int n = Integer.parseInt(edt.getText().toString());
                    if (n >= 0) {
                        for (int i = tgBtnarray.size() - 1; i >= 0; i--) {
                            ToggleButton tg = tgBtnarray.get(i);
                            if (n % 2 == 1) {
                                tg.setChecked(true);
                            } else {
                                tg.setChecked(false);
                            }
                            n = n / 2;
                        }
                    } else {
                        //inverse
                        n = (-1)*n;
                        for (int i = tgBtnarray.size() - 1; i >= 0; i--) {
                            ToggleButton tg = tgBtnarray.get(i);
                            if (n % 2 == 1) {
                                tg.setChecked(false);
                            } else {
                                tg.setChecked(true);
                            }
                            n = n / 2;
                        }
                     //complement a 2
                        check(tgBtnarray, tgBtnarray.size()-1);
                    }

                }
                return false;
            }
        });
    }

    void check (ArrayList<ToggleButton> tgBtnarray, int index) {
        ToggleButton tg = tgBtnarray.get(index);
        if (index == 0) {
            if(!tg.isChecked()){
                tg.setChecked(true);
            } else {
                tg.setChecked(false);
            }
        } else {
            if(!tg.isChecked()){
                tg.setChecked(true);
            } else {
                tg.setChecked(false);
                check(tgBtnarray, index-1);
            }
        }

    }

    void BinairetoEntier(final EditText edt, final ArrayList<ToggleButton> tgBtnarray) {
        //Thay doi champ de texte sau khi bam ToggleButton
        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s = 0;
                for (int i = 0; i < tgBtnarray.size(); i++) {
                    ToggleButton tg = tgBtnarray.get(i);
                    if (tg.isChecked()) { //true = 1
                        s = s + power2(tgBtnarray.size() - 1 - i);
                    }
                }
                edt.setText(s + "");
            }
        };

        for (int i = 0; i < tgBtnarray.size(); i++) {
            ToggleButton tg = tgBtnarray.get(i);
            tg.setOnClickListener(ocl);
        }
    }

    void ChangeNbtn (Button btn) {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }

    void afficheNbtn (int n, LinearLayout ll, ArrayList<ToggleButton> tgBtnarray) {
        for(int i=0 ; i< n;i++){
            ToggleButton tb = new ToggleButton(this );
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.width = 60;

            tb.setText("0");
            tb.setTextOff("0");
            tb.setTextOn("1");
            tb.setLayoutParams(params);
            ll.addView(tb);
            ll.setGravity(Gravity.CENTER);
            tgBtnarray.add(tb);
        }
    }

}