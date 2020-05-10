package com.example.proodoo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import oogbox.api.odoo.OdooClient;
import oogbox.api.odoo.OdooUser;
import oogbox.api.odoo.client.AuthError;
import oogbox.api.odoo.client.OdooVersion;
import oogbox.api.odoo.client.helper.OdooErrorException;
import oogbox.api.odoo.client.helper.data.OdooRecord;
import oogbox.api.odoo.client.helper.data.OdooResult;
import oogbox.api.odoo.client.listeners.AuthenticateListener;
import oogbox.api.odoo.client.listeners.IOdooResponse;
import oogbox.api.odoo.client.listeners.OdooConnectListener;
import oogbox.api.odoo.client.listeners.OdooErrorListener;

public class profile extends AppCompatActivity {
    OdooClient client;
     TextView tx,nameDB,email,location;
     String information;
     Button btnStat,btnArt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        tx=(TextView)findViewById(R.id.information);
        nameDB=(TextView)findViewById(R.id.username);
        email=(TextView)findViewById(R.id.email);
        location=(TextView)findViewById(R.id.Localisation);
        btnStat=(Button)findViewById(R.id.btnStat);
        btnArt=(Button)findViewById(R.id.btnArt);
        getInformation();

        btnStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent st=new Intent(profile.this,statistiques.class);
                startActivity(st);
            }
        });
        btnArt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loged=new Intent(profile.this,activty_dashboard.class);
                loged.putExtra("bd",getIntent().getStringExtra("bd"));
                loged.putExtra("login",getIntent().getStringExtra("login"));
                loged.putExtra("psw",getIntent().getStringExtra("psw"));
                loged.putExtra("loc",getIntent().getStringExtra("loc"));
                startActivity(loged);

            }
        });
    }

    public void getInformation(){
        final AuthenticateListener loginCallback=new AuthenticateListener() {


            @Override
            public void onLoginSuccess(OdooUser user) {
//               SendMail email=new SendMail(profile.this,"lahbib.3abdallah98@gmail.com","Odoo Client","login with seccues");
//               email.execute();
                nameDB.setText(user.database);
                email.setText(user.username);
                location.setText(getIntent().getStringExtra("loc"));
                information=" Odoo version : "+user.odooVersion+" \n name"+user.name+" \n"+user.host;
                tx.setText(information);
                List<Integer> ids =Arrays.asList(user.uid);
                List<String> fields = Arrays.asList("id", "name");
                client.read("res.partner", ids, fields, new IOdooResponse() {
                    @Override
                    public void onResult(OdooResult result) {

                        OdooRecord[] records = result.getRecords();
        //                Toast.makeText(profile.this," "+result.lastKey(),Toast.LENGTH_LONG).show();

                        for(OdooRecord record: records) {
                            information+="\n "+record.getString("name");
                            //   Log.v("Name:", record.getString("name"));
                        }
                    }
                });
            }

            @Override
            public void onLoginFail(AuthError error) {
                // Toast.makeText(MainActivity.this,"No",Toast.LENGTH_LONG).show();
                AlertDialog alertDialog = new AlertDialog.Builder(profile.this).create();
                alertDialog.setTitle("Attention");
                alertDialog.setMessage("Données Erronées");
                alertDialog.setIcon(R.drawable.erro);
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.show();
            }
        };

        client = new OdooClient.Builder(getApplicationContext())
                .setHost("http://10.0.2.2:8069")
                .setErrorListener(new OdooErrorListener() {
                    @Override
                    public void onError(OdooErrorException error) {
                        Toast.makeText(profile.this,"fe"+error,Toast.LENGTH_LONG).show();
                    }
                })
                .setConnectListener(new OdooConnectListener() {
                    @Override
                    public void onConnected(OdooVersion version) {
                        //      Toast.makeText(MainActivity.this,"yes"+version.server_version,Toast.LENGTH_LONG).show();
                        client.authenticate(getIntent().getStringExtra("login"),getIntent().getStringExtra("psw"), getIntent().getStringExtra("bd"), loginCallback);
                        //   client.authenticate("lahbib.3abdallah98@gmail.com","123456", "Pro", loginCallback);
                    }

                }).build();
    }
}
