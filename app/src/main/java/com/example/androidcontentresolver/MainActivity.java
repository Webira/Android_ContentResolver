package com.example.androidcontentresolver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED);
        //lireContacts();
         }

     public  void lireContacts(View arg) {
         if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED){
             ContentResolver contentResolver = getContentResolver();
         Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
             Cursor resultat = null;
             if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                 resultat = contentResolver.query(uri, null, null, null);
             }
             int nombreDeContacts = resultat.getCount(); //nombre de contacts recuperés
         Toast.makeText(this, "Nombre de contacts " + nombreDeContacts, Toast.LENGTH_SHORT).show();

         resultat.moveToFirst();
         int indexNumero = resultat.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
         int indiceNom = resultat.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
         String nom = resultat.getString(indiceNom);
         String numeroTelephone = resultat.getString(indexNumero);
         Log.e("Contact lus", "Nom du contacts : " + nom);
         Log.e("Telephone lus", "Numero de télephone : " + numeroTelephone);

         while (!resultat.isAfterLast()) { //ce bloc parcour la liste des contacts et affiche pour
             //chaque contact, le numero tel
             String noms = resultat.getString(indiceNom);
             String numero = resultat.getString(indexNumero);
             Log.e("Contact lus", "Nom du contacts :" + numero);
             Log.e("Telephone lus", "Numero de télephone : " + numeroTelephone);
             resultat.moveToNext(); //passer à l'iteration suivante
         }
         }else{
             Toast.makeText(this, "Donnez nous l'autorisation d'accés à vos contacts", Toast.LENGTH_LONG).show();
             ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 5);
         }
     }
}