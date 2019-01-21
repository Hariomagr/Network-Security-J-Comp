package com.example.hario.networksecurity;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.widget.ImageView;
import android.widget.TextView;



@TargetApi(Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

    private ImageView tv;
    private  Context mcontext;
    Integer amount;
    String id,password;
    String number;

    public FingerprintHandler(Context mcontext,ImageView tv,Integer amount,String id,String password,String number) {
        this.tv = tv;
        this.mcontext = mcontext;
        this.amount = amount;
        this.id = id;
        this.password = password;
        this.number = number;
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        super.onAuthenticationError(errorCode, errString);
        tv.setImageResource(R.drawable.ic_fingerprint_black1_24dp);
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        super.onAuthenticationHelp(helpCode, helpString);

    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);
        tv.setImageResource(R.drawable.ic_fingerprint_black3_24dp);
        Intent intent = new Intent(mcontext,Otp.class);
        intent.putExtra("amount",String.valueOf(amount));
        intent.putExtra("id",id);
        intent.putExtra("password",password);
        intent.putExtra("number",number);
        mcontext.startActivity(intent);
    }

    @Override
    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();
    }

    public void doAuth(FingerprintManager manager, FingerprintManager.CryptoObject obj) {
        CancellationSignal signal = new CancellationSignal();

        try {
            manager.authenticate(obj, signal, 0, this, null);
        }
        catch(SecurityException sce) {}
    }
}
