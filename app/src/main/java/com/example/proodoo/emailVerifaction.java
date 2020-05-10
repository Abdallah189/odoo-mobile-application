package com.example.proodoo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class emailVerifaction extends AppCompatActivity {
    EditText code;
    ImageView img;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_verifaction);
        code=(EditText)findViewById(R.id.verif);
        img=(ImageView)findViewById(R.id.imgVerf);
        btn=(Button)findViewById(R.id.validBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Toast.makeText(emailVerifaction.this,getIntent().getStringExtra("code"),Toast.LENGTH_LONG).show();
                if(code.getText().toString().equals(getIntent().getStringExtra("code"))){
                    img.setImageResource(R.drawable.verifemail);
                    Intent loged=new Intent(emailVerifaction.this,profile.class);
                    loged.putExtra("bd",getIntent().getStringExtra("bd"));
                    loged.putExtra("login",getIntent().getStringExtra("login"));
                    loged.putExtra("psw",getIntent().getStringExtra("psw"));
                    loged.putExtra("loc",getIntent().getStringExtra("loc"));
                    startActivity(loged);
                }
                else {
                    img.setImageResource(R.drawable.erremail);
                }
            }
        });

    }
}
