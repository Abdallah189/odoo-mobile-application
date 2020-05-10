package com.example.proodoo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import oogbox.api.odoo.OdooClient;
import oogbox.api.odoo.OdooUser;
import oogbox.api.odoo.client.AuthError;
import oogbox.api.odoo.client.OdooVersion;
import oogbox.api.odoo.client.helper.OdooErrorException;
import oogbox.api.odoo.client.helper.data.OdooRecord;
import oogbox.api.odoo.client.helper.data.OdooResult;
import oogbox.api.odoo.client.helper.utils.ODomain;
import oogbox.api.odoo.client.helper.utils.OdooFields;
import oogbox.api.odoo.client.helper.utils.OdooValues;
import oogbox.api.odoo.client.listeners.AuthenticateListener;
import oogbox.api.odoo.client.listeners.IOdooResponse;
import oogbox.api.odoo.client.listeners.OdooConnectListener;
import oogbox.api.odoo.client.listeners.OdooErrorListener;

public class MainActivity extends AppCompatActivity {
    Button buttonLogin;
    OdooClient client;
    EditText login,psw,db;
    Spinner sp;
    List<String> databases;
    String bd;
    TextView reg;
    String strAdresse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonLogin=(Button)findViewById(R.id.buttonLogin);
        getIdInptut();
        reg=(TextView)findViewById(R.id.Registre);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registre();
            }
        });
         sp = (Spinner) findViewById(R.id.spinner);
        RempList();
//        ArrayAdapter<String> adapter =new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,ar);
//        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                bd=databases.get(i).toString();
//                Toast.makeText(MainActivity.this,bd,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // NotificationManager NM;
//                NM=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//                Notification notify = new Notification(android.R.drawable.stat_notify_more,"title",System.currentTimeMillis());
//                PendingIntent  pending = PendingIntent.getActivity(getApplicationContext(), 0, new Intent(),0);
//                notify.setLatestEventInfo(getApplicationContext(), "sujet", "body",pending);
//                NM.notify(0, notify);
                //send email
//                String[] TO = {"lahbib.3abdallah98@gmail.com"};
//                String[] CC = {"lahbib.3abdallah98@gmail.com"};
//                Intent emailIntent = new Intent(Intent.ACTION_SEND);
//
//                emailIntent.setData(Uri.parse("mailto:"));
//                emailIntent.setType("text/plain");
//                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
//                emailIntent.putExtra(Intent.EXTRA_CC, CC);
//                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
//                emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");
//
//                try {
//                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
//                    finish();
//                } catch (android.content.ActivityNotFoundException ex) {
//                    Toast.makeText(MainActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
//                }
                //notfication
//                NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//                Notification notify=new Notification.Builder
//                        (getApplicationContext()).setContentTitle("tit").setContentText("bodu").
//                        setContentTitle("sub").setSmallIcon(R.drawable.err).build();
//
//                notify.flags |= Notification.FLAG_AUTO_CANCEL;
//                notif.notify(0, notify);

                logi();
            }
        });
    }

    private void getIdInptut() {
        login=(EditText)findViewById(R.id.login);
        psw=(EditText)findViewById(R.id.psw);
    }

    public void logi(){
//        SendMail email=new SendMail(this,"lahbib.3abdallah98@gmail.com","sfsdf","sdfs");
//        email.execute();
        //    Toast.makeText(MainActivity.this,"user"+psw.getText().toString()+" "+db.getText().toString(),Toast.LENGTH_LONG).show();
       final AuthenticateListener loginCallback=new AuthenticateListener() {

           private int partnerId;

           @Override
           public void onLoginSuccess(OdooUser user) {
           //    Toast.makeText(MainActivity.this,"yes user :"+user.account.toString(),Toast.LENGTH_LONG).show();
//               List<Integer> ids =Arrays.asList(1, 2, 3);
//               List<String> fields = Arrays.asList("id", "name");
//               client.read("res.partner",ids,fields, new IOdooResponse() {
//                   @Override
//                   public void onResult(OdooResult result) {
//                       OdooRecord[] rec=result.getRecords();
//                       for (OdooRecord record:rec){
//                           Toast.makeText(MainActivity.this,record.getString("name"),Toast.LENGTH_LONG).show();
//                           Log.v("Name ",record.getString("name"));
//                           Log.v("id ",record.getString("id"));
//
//                       }
//                   }
//               });

               //Géolocalisation
               GPSTracker gps = new GPSTracker(MainActivity.this);
               double latitude = gps.getLatitude();
               double longitude = gps.getLongitude();
               LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
               Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());
               List<Address> adresses = null;
               try
               {
                   adresses = geocoder.getFromLocation(latitude, longitude, 1);
               }
               catch (IOException ioException)
               {
//            Log.e("GPS", "erreur", ioException);
               } catch (IllegalArgumentException illegalArgumentException)
               {
//            Log.e("GPS", "erreur " + coordonnees, illegalArgumentException);
               }

               if (adresses == null || adresses.size()  == 0)
               {
                   //     Log.e("GPS", "erreur aucune adresse !");
               } else {
                   Address adresse = adresses.get(0);
                   ArrayList<String> addressFragments = new ArrayList<String>();

                   strAdresse = adresse.getAddressLine(0) + ", " + adresse.getLocality();
               }
               String subj="ODOO Client ";
               String msg="latitude :"+latitude+"\n longitude :"+longitude+"\n adresse :"+strAdresse+"\n Code de vérification :"+5893;
               //correct
               SendMail email=new SendMail(MainActivity.this,login.getText().toString(),subj,msg);
               email.execute();

//               List<Integer> ids = Arrays.asList(user.uid);
//               List<String> fields = Arrays.asList("id", "name","price");

//               client.read("product.template", ids, fields, new IOdooResponse() {
//                   @Override
//                   public void onResult(OdooResult result) {
//
//                       OdooRecord[] records = result.getRecords();
//
//
//                       for(OdooRecord record: records) {
//                           Toast.makeText(MainActivity.this,record.getString("price"),Toast.LENGTH_LONG).show();
//                           Log.v("Name:", record.getString("name"));
//                       }
//                   }
//               });

               //  OdooFields odooFields=user.;
              // Toast.makeText(MainActivity.this," "+user.partnerId,Toast.LENGTH_LONG).show();

               Intent loged=new Intent(MainActivity.this,emailVerifaction.class);
               loged.putExtra("bd",bd);
               loged.putExtra("login",login.getText().toString());
               loged.putExtra("psw",psw.getText().toString());
               loged.putExtra("loc",strAdresse);
               loged.putExtra("code","5893");
               startActivity(loged);
             //  Toast.makeText(MainActivity.this,)



           }

           @Override
           public void onLoginFail(AuthError error) {
              // Toast.makeText(MainActivity.this,"No",Toast.LENGTH_LONG).show();
               AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
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
                       Toast.makeText(MainActivity.this,"fe"+error,Toast.LENGTH_LONG).show();
                   }
               })
               .setConnectListener(new OdooConnectListener() {
                   @Override
                   public void onConnected(OdooVersion version) {
                 //      Toast.makeText(MainActivity.this,"yes"+version.server_version,Toast.LENGTH_LONG).show();
                      client.authenticate(login.getText().toString(),psw.getText().toString(), bd, loginCallback);
                  //     client.authenticate("lahbib.3abdallah98@gmail.com","123456", "Pro", loginCallback);
                   }

               }).build();



   }

    public void RempList(){
        client = new OdooClient.Builder(getApplicationContext())
                .setHost("http://10.0.2.2:8069")
                .setErrorListener(new OdooErrorListener() {
                    @Override
                    public void onError(OdooErrorException error) {
                        Toast.makeText(MainActivity.this,"fe"+error,Toast.LENGTH_LONG).show();
                    }
                })
                .setConnectListener(new OdooConnectListener() {
                    @Override
                    public void onConnected(OdooVersion version) {
                        databases = client.getDatabases();
                        ArrayAdapter<String> adapter =new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,databases);
                        sp.setAdapter(adapter);
                    }

                }).build();
    }
     public void registre(){
         OdooClient clientt = new OdooClient.Builder(getApplicationContext())
                 .setHost("http://10.0.2.2:8069")
                 .setErrorListener(new OdooErrorListener() {
                     @Override
                     public void onError(OdooErrorException error) {
                         // Error exception with detail of errors
                     }
                 })
                 .setConnectListener(new OdooConnectListener() {
                     @Override
                     public void onConnected(OdooVersion version) {
                         // Success connection
                     }
                 }).build();
         List<Integer> ids = Arrays.asList(1, 2, 3);
         List<String> fields = Arrays.asList("id", "name");

         clientt.read("res.partner", ids, fields, new IOdooResponse() {
             @Override
             public void onResult(OdooResult result) {
                 OdooRecord[] records = result.getRecords();

                 for(OdooRecord record: records) {
                     Log.v("Name:", record.getString("name"));
                 }
             }
         });
    }
}
