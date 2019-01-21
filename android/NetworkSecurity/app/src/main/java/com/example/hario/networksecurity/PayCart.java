package com.example.hario.networksecurity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class PayCart extends AppCompatActivity {
    ListView listView;
    TextView total;
    Button pay;
    Integer total_price=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_cart);
        listView=(ListView) findViewById(R.id.listview);
        total=(TextView)findViewById(R.id.total);
        pay=(Button)findViewById(R.id.pay);
        ArrayList<POJO> arrayList = new ArrayList<>();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("prefs",0);
        if(sharedPreferences.getInt("1",0)==1){
            POJO pojo = new POJO(R.drawable.mac,"$800","Apple laptop","1",1);
            total_price+=800;
            arrayList.add(pojo);
        }
        if(sharedPreferences.getInt("2",0)==1){
            POJO pojo = new POJO(R.drawable.travelbag,"$40","Travel Bag","2",1);
            total_price+=40;
            arrayList.add(pojo);
        }
        if(sharedPreferences.getInt("3",0)==1){
            POJO pojo=new POJO(R.drawable.tennis,"$300","Tennis Racket","3",1);
            total_price+=300;
            arrayList.add(pojo);
        }
        if(sharedPreferences.getInt("4",0)==1){
            POJO pojo=new POJO(R.drawable.speaker,"$120","Speakers","4",1);
            total_price+=120;
            arrayList.add(pojo);
        }
        if(sharedPreferences.getInt("4",0)==1){
            POJO pojo=new POJO(R.drawable.shoe,"$80","Nike Shoes","5",1);
            total_price+=80;
            arrayList.add(pojo);
        }
        if(sharedPreferences.getInt("4",0)==1){
            POJO pojo=new POJO(R.drawable.oneplus,"$600","OnePlus 6","6",1);
            total_price+=600;
            arrayList.add(pojo);
        }
        adapter adapter = new adapter(this,R.layout.adapter,arrayList);
        listView.setAdapter(adapter);
        total.setText("$"+String.valueOf(total_price));
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PayCart.this,Pay.class);
                intent.putExtra("amount",String.valueOf(total_price));
                startActivity(intent);
            }
        });
    }
}
