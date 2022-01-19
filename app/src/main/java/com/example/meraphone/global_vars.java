package com.example.meraphone;

import android.app.Application;

import java.util.ArrayList;

public class global_vars extends Application {
    private   String LoginUserID;
    public String act_naam_others;

    public String getAct_naam_others() {
        return act_naam_others;
    }

    public void setAct_naam_others(String act_naam_others) {
        this.act_naam_others = act_naam_others;
    }

    public int getIdofgenerate() {
        return idofgenerate;
    }

    public void setIdofgenerate(int idofgenerate) {
        this.idofgenerate = idofgenerate;
    }

    private int idofgenerate;
    public ArrayList myGlobalArray = null;

    public float getAmounttopay() {
        return amounttopay;
    }

    public void setAmounttopay(float amounttopay) {
        this.amounttopay = amounttopay;
    }

    public float amounttopay;

    public global_vars() {
        myGlobalArray = new ArrayList();
    }

    public String getLoginUserID() {
        return LoginUserID;
    }

    public void setLoginUserID(String LoginUserID) {
        this.LoginUserID = LoginUserID;
    }
}
