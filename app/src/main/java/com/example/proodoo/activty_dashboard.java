package com.example.proodoo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
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

public class activty_dashboard extends AppCompatActivity {
    OdooClient client;
    final String[] namerec={};
    final String[] decrec={};
    final String[] pricerec={};
     ArrayList<DataModel> dataModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RecyclerView rc;
         final RecyclerView.Adapter adapter;
         RecyclerView recyclerView;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activty_dashboard);
        final String[] nameArray={"Product1","Product2","Product3","Product4","Product1","Product2","Product3","Product4"};
        final String[] nameArray1={"Description1","Description2","Description3","Description4","Product1","Product2","Product3","Product4"};
        final String[] prix={"12 dinar","15 dinar","16 dinar","17 dinar","12 dinar","15 dinar","16 dinar","17 dinar"};
        dataModels=new ArrayList<DataModel> ();
       // Toast.makeText(this,namerec.length,Toast.LENGTH_LONG).show();
//        for(int i=0;i<nameArray.length;i++) {
//            dataModels.add(new DataModel(nameArray[i], nameArray[i],nameArray[i], R.drawable.box));
//        };
        recyclerView=findViewById(R.id.list);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new CustomAdapter(dataModels);

        final AuthenticateListener loginCallback=new AuthenticateListener() {


            @Override
            public void onLoginSuccess(OdooUser user) {
//                SendMail email=new SendMail(activty_dashboard.this,"lahbib.3abdallah98@gmail.com","Odoo Client","verify");
//                email.execute();
                List<Integer> ids = Arrays.asList(user.uid);
                List<String> fields = Arrays.asList("id", "name","price");

                client.read("product.template", ids, fields, new IOdooResponse() {
                    @Override
                    public void onResult(OdooResult result) {

                        OdooRecord[] records = result.getRecords();
                        Toast.makeText(activty_dashboard.this," "+result.lastKey(),Toast.LENGTH_LONG).show();

                        for(OdooRecord record: records) {
                            dataModels.add(new DataModel(record.getString("name"), record.getString("name"),record.getString("price"), R.drawable.box));
                            dataModels.add(new DataModel(record.getString("name")+"2", record.getString("name"),5.00+"dinar", R.drawable.box));
                            adapter.notifyDataSetChanged();
                         //   Log.v("Name:", record.getString("name"));
                        }
                    }
                });
            }

            @Override
            public void onLoginFail(AuthError error) {
                // Toast.makeText(MainActivity.this,"No",Toast.LENGTH_LONG).show();
                AlertDialog alertDialog = new AlertDialog.Builder(activty_dashboard.this).create();
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
                        Toast.makeText(activty_dashboard.this,"fe"+error,Toast.LENGTH_LONG).show();
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
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(activty_dashboard.this, Context.NOTIFICATION_SERVICE)
                        .setSmallIcon(R.drawable.err)
                        .setContentTitle("tdfsd")
                        .setContentText("dfgdfgdfgdf")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
            }
        });
    }

    public void rempFildes(){
        //    Toast.makeText(MainActivity.this,"user"+psw.getText().toString()+" "+db.getText().toString(),Toast.LENGTH_LONG).show();
        final AuthenticateListener loginCallback=new AuthenticateListener() {

            private int partnerId;

            @Override
            public void onLoginSuccess(OdooUser user) {

                List<Integer> ids = Arrays.asList(user.uid);
                List<String> fields = Arrays.asList("id", "name","price");

                client.read("product.template", ids, fields, new IOdooResponse() {
                    @Override
                    public void onResult(OdooResult result) {
                        OdooRecord[] records = result.getRecords();
                        for(OdooRecord record: records) {
                            dataModels.add(new DataModel(record.getString("name"), record.getString("name"),record.getString("name"), R.drawable.box));
                            Toast.makeText(activty_dashboard.this,record.getString("name"),Toast.LENGTH_LONG).show();
                            Log.v("Name:", record.getString("name"));
                        }
                    }
                });
            }

            @Override
            public void onLoginFail(AuthError error) {
                // Toast.makeText(MainActivity.this,"No",Toast.LENGTH_LONG).show();
                AlertDialog alertDialog = new AlertDialog.Builder(activty_dashboard.this).create();
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
                        Toast.makeText(activty_dashboard.this,"fe"+error,Toast.LENGTH_LONG).show();
                    }
                })
                .setConnectListener(new OdooConnectListener() {
                    @Override
                    public void onConnected(OdooVersion version) {
                        //      Toast.makeText(MainActivity.this,"yes"+version.server_version,Toast.LENGTH_LONG).show();
                      //  client.authenticate(getIntent().getStringExtra("login"),getIntent().getStringExtra("psw"), getIntent().getStringExtra("bd"), loginCallback);
                           client.authenticate("lahbib.3abdallah98@gmail.com","123456", "Pro", loginCallback);
                    }

                }).build();


    }
}
