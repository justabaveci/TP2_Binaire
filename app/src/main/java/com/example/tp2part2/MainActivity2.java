package com.example.tp2part2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    public static final String NUMBER_OF_BUTTONS = "Number of buttons";
    Button btn;
    EditText edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Ref();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(v, edt);
            }
        });

    }

    void Ref () {
        btn = (Button) findViewById(R.id.btn);
        edt = (EditText) findViewById(R.id.edt);
    }

    public void sendMessage(View v, EditText edt) {
        if(edt.getText().toString().isEmpty()){
            Toast.makeText(this, "write a number", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(NUMBER_OF_BUTTONS, Integer.parseInt(edt.getText().toString()));
            startActivity(intent);
        }

    }
}