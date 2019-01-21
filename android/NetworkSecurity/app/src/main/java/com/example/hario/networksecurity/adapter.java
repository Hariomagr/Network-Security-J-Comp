package com.example.hario.networksecurity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class adapter extends ArrayAdapter<POJO> {
    private Context mcontext;
    int mresource;
    private static class ViewHolder {
        TextView name,price;
        Button button;
        ImageView image;
    }

    public adapter(Context context, int resource, ArrayList<POJO> objects) {
        super(context, resource, objects);
        mcontext = context;
        mresource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final String price = getItem(position).getPrice();
        final String name = getItem(position).getName();
        final int drawable = getItem(position).getDrawable();
        final String chk = getItem(position).getChk();
        Integer act = getItem(position).getAct();
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("prefs",0);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        final View result;
        final ViewHolder holder;
        if (convertView == null) {

            LayoutInflater inflater = LayoutInflater.from(mcontext);
            convertView = inflater.inflate(mresource, parent, false);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.price = (TextView) convertView.findViewById(R.id.price);
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.button = (Button) convertView.findViewById(R.id.button);
            result = convertView;
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(holder.button.getText().equals("ADD")) {
                    holder.button.setText("Remove");
                    editor.putInt(chk,1);
                    editor.commit();
                    Toast.makeText(mcontext, "Added", Toast.LENGTH_SHORT).show();
                }
                else {
                    holder.button.setText("ADD");
                    editor.putInt(chk,0);
                    editor.commit();
                    Toast.makeText(mcontext, "Removed", Toast.LENGTH_SHORT).show();
                }
            }
        });
        if(act==1)holder.button.setVisibility(View.GONE);
        holder.button.setText("ADD");
        holder.name.setText(name);
        holder.price.setText("Price: "+price);
        holder.image.setImageResource(drawable);
        return convertView;
    }

}

