package com.example.hario.networksecurity;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.KeyguardManager;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.hardware.fingerprint.FingerprintManager;
import android.net.Uri;
import android.os.Build;
import android.preference.Preference;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.juanlabrador.badgecounter.BadgeCounter;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.common.AccountPicker;
import com.scottyab.aescrypt.AESCrypt;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView message;
    private static final String KEY_NAME = "SwA";

    private KeyStore keyStore;
    private KeyGenerator keyGenerator;
    private FingerprintManager.CryptoObject cryptoObject;

    private FingerprintManager fingerprintManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("prefs",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("1",0);
        editor.putInt("2",0);
        editor.putInt("3",0);
        editor.putInt("4",0);
        editor.putInt("5",0);
        editor.putInt("6",0);
        editor.commit();
        GridView gridView = (GridView)findViewById(R.id.grid);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("Choose Product");
        toolbar.inflateMenu(R.menu.toolbar_menu);
        ArrayList<POJO> arrayList = new ArrayList<>();
        POJO pojo = new POJO(R.drawable.mac,"$800","Apple laptop","1",0);
        arrayList.add(pojo);
        pojo = new POJO(R.drawable.travelbag,"$40","Travel Bag","2",0);
        arrayList.add(pojo);
        pojo=new POJO(R.drawable.tennis,"$300","Tennis Racket","3",0);
        arrayList.add(pojo);
        pojo=new POJO(R.drawable.speaker,"$120","Speakers","4",0);
        arrayList.add(pojo);
        pojo=new POJO(R.drawable.shoe,"$80","Nike Shoes","5",0);
        arrayList.add(pojo);
        pojo=new POJO(R.drawable.oneplus,"$600","OnePlus 6","6",0);
        arrayList.add(pojo);
        adapter adapter = new adapter(this,R.layout.adapter,arrayList);
        gridView.setAdapter(adapter);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.cart){
                    startActivity(new Intent(MainActivity.this,PayCart.class));
                }
                return false;
            }
        });






    }


}
