package com.example.hario.networksecurity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mukesh.OnOtpCompletionListener;
import com.mukesh.OtpView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Otp extends AppCompatActivity {
    TextView confirmation,count;
    private OtpView otpView;
    String ss="xxxxxx";
    Integer amount=0;
    String id="",pass="";
    String number;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        id=getIntent().getStringExtra("id");
        number=getIntent().getStringExtra("number");
        pass=getIntent().getStringExtra("password");
        amount = Integer.parseInt(getIntent().getStringExtra("amount"));
        confirmation=(TextView)findViewById(R.id.confirmation);
        count=(TextView)findViewById(R.id.count);
        otpView = findViewById(R.id.otp_view);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://networksecurity.herokuapp.com/api/?number="+number+"&chk=a&amount=12")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final RequestInterface request = retrofit.create(RequestInterface.class);
        Call<SMS_API_POJO> call = request.getOtp("");
        call.enqueue(new Callback<SMS_API_POJO>() {
            @Override
            public void onResponse(Call<SMS_API_POJO> call, Response<SMS_API_POJO> response) {
                progressDialog.dismiss();
                if(response.code()==200){
                    SMS_API_POJO sms_api_pojo = response.body();
                    ss=sms_api_pojo.getOtp();
                    Log.d("dsdffff",ss);
                    new CountDownTimer(60000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            count.setText("Waiting for OTP... 00:"+ millisUntilFinished / 1000);
                        }

                        public void onFinish() {
                            count.setVisibility(View.GONE);
                            otpView.setEnabled(false);
                            otpView.setCursorVisible(false);
                        }

                    }.start();
                }
                else {
                    Toast.makeText(getApplicationContext(),String.valueOf(response.code()),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SMS_API_POJO> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        confirmation.setText("We've sent an OTP to "+number);
        otpView.setOtpCompletionListener(new OnOtpCompletionListener() {
            @Override
            public void onOtpCompleted(String s) {
                progressDialog.show();
                Retrofit retrofit1 = new Retrofit.Builder().baseUrl("http://networksecurity.herokuapp.com/api/validate/?enc_otp="+ss+"&otp="+s)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                final RequestInterface request1 = retrofit1.create(RequestInterface.class);
                Call<SMS_API_POJO> call1 = request1.getOtp("");
                call1.enqueue(new Callback<SMS_API_POJO>() {
                    @Override
                    public void onResponse(Call<SMS_API_POJO> call, Response<SMS_API_POJO> response) {
                        progressDialog.dismiss();
                        if(response.code()==200){
                            if(response.body().getOtp().equals("true")){
                                final ProgressDialog progressDialog1 = new ProgressDialog(Otp.this);
                                progressDialog1.setMessage("Verifying Payment...");
                                progressDialog1.setCancelable(false);
                                progressDialog1.show();
                                Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();
                                ViewDialog alert = new ViewDialog();
                                alert.showDialog(Otp.this);
                                Retrofit retrofit = new Retrofit.Builder().baseUrl("http://leaarningapps99.000webhostapp.com/network_security/")
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();
                                final RequestInterface request = retrofit.create(RequestInterface.class);
                                Call<SMS_API_POJO> call1 = request.change(id,pass,amount);
                                call1.enqueue(new Callback<SMS_API_POJO>() {
                                    @Override
                                    public void onResponse(Call<SMS_API_POJO> call, Response<SMS_API_POJO> response) {
                                        progressDialog1.dismiss();
                                        if(response.code()==200){
                                            SMS_API_POJO sms_api_pojo = response.body();
                                            Toast.makeText(getApplicationContext(),"Payment Successful",Toast.LENGTH_SHORT).show();
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Retrofit retrofit = new Retrofit.Builder().baseUrl("http://networksecurity.herokuapp.com/api/?number="+number+"&chk=b&amount="+String.valueOf(amount))
                                                            .addConverterFactory(GsonConverterFactory.create())
                                                            .build();
                                                    final RequestInterface request = retrofit.create(RequestInterface.class);
                                                    Call<SMS_API_POJO> call = request.getOtp("");
                                                    call.enqueue(new Callback<SMS_API_POJO>() {
                                                        @Override
                                                        public void onResponse(Call<SMS_API_POJO> call, Response<SMS_API_POJO> response) {
                                                            if(response.code()==200){
                                                                SMS_API_POJO sms_api_pojo = response.body();
                                                                ss=sms_api_pojo.getOtp();
                                                                Log.d("dsdf",ss);
                                                            }
                                                            else {
                                                                Toast.makeText(getApplicationContext(),String.valueOf(response.code()),Toast.LENGTH_SHORT).show();
                                                            }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<SMS_API_POJO> call, Throwable t) {
                                                            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                                    startActivity(new Intent(Otp.this,MainActivity.class));
                                                }
                                            },10000);



                                        }
                                        else {
                                            Toast.makeText(getApplicationContext(),String.valueOf(response.code()),Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<SMS_API_POJO> call, Throwable t) {
                                        progressDialog1.dismiss();
                                        Toast.makeText(getApplicationContext(),t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Wrong OTP",Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),String.valueOf(response.code()),Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<SMS_API_POJO> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
    public class ViewDialog {

        public void showDialog(Activity activity){
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.auth_custom_dialog);
            ImageView auth = (ImageView) dialog.findViewById(R.id.image);
            Glide.with(Otp.this).asGif().load(R.drawable.succ).into(auth);
            dialog.show();

        }
    }
}
