package com.example.hario.networksecurity;

import android.graphics.drawable.Drawable;

public class POJO {
    int drawable;
    String price;
    String name;
    String chk;
    Integer act;

    public POJO(int drawable, String price, String name, String chk, Integer act) {
        this.drawable = drawable;
        this.price = price;
        this.name = name;
        this.chk = chk;
        this.act = act;
    }

    public Integer getAct() {
        return act;
    }

    public void setAct(Integer act) {
        this.act = act;
    }

    public String getChk() {
        return chk;
    }

    public void setChk(String chk) {
        this.chk = chk;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
