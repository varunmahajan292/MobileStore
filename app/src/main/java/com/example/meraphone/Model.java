package com.example.meraphone;

import android.graphics.Bitmap;

public class Model {


    private int ID;
    private String CATEGORY;
    private String   ITEMNAME;
    private Bitmap ITEMIMAGE;
    private String  COST;
    private String RATING;
    private String MODELNO;
    private String FRONTCAMERA;
    private String BACKCAMERA;
    private String HYBRIDSIMSLOT;
    private String OTGCOMPATIBLE;
    private String TOUCHSCREEN;
    private String STATUS;
    private String EXTRA;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCATEGORY() {
        return CATEGORY;
    }

    public void setCATEGORY(String CATEGORY) {
        this.CATEGORY = CATEGORY;
    }

    public String getITEMNAME() {
        return ITEMNAME;
    }

    public void setITEMNAME(String ITEMNAME) {
        this.ITEMNAME = ITEMNAME;
    }

    public Bitmap getITEMIMAGE() {
        return ITEMIMAGE;
    }

    public void setITEMIMAGE(Bitmap ITEMIMAGE) {
        this.ITEMIMAGE = ITEMIMAGE;
    }

    public String getCOST() {
        return COST;
    }

    public void setCOST(String COST) {
        this.COST = COST;
    }

    public String getRATING() {
        return RATING;
    }

    public void setRATING(String RATING) {
        this.RATING = RATING;
    }

    public String getMODELNO() {
        return MODELNO;
    }

    public void setMODELNO(String MODELNO) {
        this.MODELNO = MODELNO;
    }

    public String getFRONTCAMERA() {
        return FRONTCAMERA;
    }

    public void setFRONTCAMERA(String FRONTCAMERA) {
        this.FRONTCAMERA = FRONTCAMERA;
    }

    public String getBACKCAMERA() {
        return BACKCAMERA;
    }

    public void setBACKCAMERA(String BACKCAMERA) {
        this.BACKCAMERA = BACKCAMERA;
    }

    public String getHYBRIDSIMSLOT() {
        return HYBRIDSIMSLOT;
    }

    public void setHYBRIDSIMSLOT(String HYBRIDSIMSLOT) {
        this.HYBRIDSIMSLOT = HYBRIDSIMSLOT;
    }

    public String getOTGCOMPATIBLE() {
        return OTGCOMPATIBLE;
    }

    public void setOTGCOMPATIBLE(String OTGCOMPATIBLE) {
        this.OTGCOMPATIBLE = OTGCOMPATIBLE;
    }

    public String getTOUCHSCREEN() {
        return TOUCHSCREEN;
    }

    public void setTOUCHSCREEN(String TOUCHSCREEN) {
        this.TOUCHSCREEN = TOUCHSCREEN;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getEXTRA() {
        return EXTRA;
    }

    public void setEXTRA(String EXTRA) {
        this.EXTRA = EXTRA;
    }


    public Model(int ID, String CATEGORY, String ITEMNAME, Bitmap ITEMIMAGE, String COST, String RATING, String MODELNO, String FRONTCAMERA, String BACKCAMERA, String HYBRIDSIMSLOT, String OTGCOMPATIBLE, String TOUCHSCREEN, String STATUS, String EXTRA) {
        this.ID = ID;
        this.CATEGORY = CATEGORY;
        this.ITEMNAME = ITEMNAME;
        this.ITEMIMAGE = ITEMIMAGE;
        this.COST = COST;
        this.RATING = RATING;
        this.MODELNO = MODELNO;
        this.FRONTCAMERA = FRONTCAMERA;
        this.BACKCAMERA = BACKCAMERA;
        this.HYBRIDSIMSLOT = HYBRIDSIMSLOT;
        this.OTGCOMPATIBLE = OTGCOMPATIBLE;
        this.TOUCHSCREEN = TOUCHSCREEN;
        this.STATUS = STATUS;
        this.EXTRA = EXTRA;
    }




   }
