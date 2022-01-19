package com.example.meraphone.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.example.meraphone.Model;
import com.example.meraphone.faltu_context;
import com.example.meraphone.global_vars;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Db_frall extends SQLiteOpenHelper {
    public ByteArrayOutputStream byteArrayOutputStream;
    public byte[] imageinbtye;
    public String imgname;

       public static final String DATABASE_NAME = "db_meraph6.db";
    public static final String TABLE_NAME = "userregd";
    public static final String UCOL_1 = "ID";
    public static final String UCOL_2 = "FNAME";
    public static final String UCOL_3 = "LNAME";
    public static final String UCOL_4 = "EMAIL";
    public static final String UCOL_5 = "MOBILENO";
    public static final String UCOL_6 = "PASSWORD";
    public static final String UCOL_7 = "EXTRA";


    public static final String ADM_TABLE_NAME = "admindetails";
    public static final String ADM_COL_1 = "ID";
    public static final String ADM_COL_2 = "NAME";
    public static final String ADM_COL_3 = "EMAIL";
    public static final String ADM_COL_4 = "PASSWORD";
    public static final String ADM_COL_5 = "EXTRA";

    public static final String ITEM_TABLE_NAME = "itemdetails";
    public static final String ITEM_COL_1 = "ID";
    public static final String ITEM_COL_2 = "CATEGORY";
    public static final String ITEM_COL_3 = "ITEMNAME";
    public static final String ITEM_COL_4 = "ITEMIMAGE";
    public static final String ITEM_COL_5 = "COST";
    public static final String ITEM_COL_6 = "RATING";
    public static final String ITEM_COL_7 = "MODELNO";
    public static final String ITEM_COL_8 = "FRONTCAMERA";
    public static final String ITEM_COL_9 = "BACKCAMERA";
    public static final String ITEM_COL_10 = "HYBRIDSIMSLOT";
    public static final String ITEM_COL_11 = "OTGCOMPATIBLE";
    public static final String ITEM_COL_12 = "TOUCHSCREEN";
        public static final String ITEM_COL_13 = "STATUS";
    public static final String ITEM_COL_14 = "EXTRA";

    public static final String GENERATE_TABLE = "generateitems";
    public static final String G_T_1 = "ID";
    public static final String  G_T_2 = "IDSOFORDERANDQTY";
    public static final String  G_T_3  = "USERLOGINID";
    public static final String  G_T_4  = "BOOKINGDATEANDTIME";
    public static final String  G_T_5  = "AMOUNTTOPAY";
    public static final String  G_T_6  = "ORDERRECIVINGTIME";
    public static final String  G_T_7  = "QRCODE";
    public static final String  G_T_8  = "STATUS";
    public static final String  G_T_9  = "EXTRA";



     public Db_frall(Context context) {
        super(context, DATABASE_NAME, null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,FNAME TEXT,LNAME TEXT,EMAIL TEXT, MOBILENO TEXT, PASSWORD TEXT, EXTRA TEXT)");
        db.execSQL("create table " + ADM_TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,EMAIL TEXT,PASSWORD TEXT, EXTRA TEXT)");
        db.execSQL("create table " + ITEM_TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,CATEGORY TEXT,ITEMNAME TEXT,ITEMIMAGE BLOB, COST TEXT, RATING TEXT, MODELNO TEXT, FRONTCAMERA TEXT, BACKCAMERA TEXT, HYBRIDSIMSLOT TEXT, OTGCOMPATIBLE TEXT, TOUCHSCREEN TEXT,STATUS TEXT, EXTRA TEXT)");
        db.execSQL("create table " + GENERATE_TABLE +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,IDSOFORDERANDQTY TEXT,USERLOGINID INTEGER,BOOKINGDATEANDTIME TEXT,AMOUNTTOPAY TEXT,ORDERRECIVINGTIME TEXT,QRCODE TEXT ,STATUS TEXT,EXTRA TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ADM_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ITEM_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+GENERATE_TABLE);
        onCreate(db);
    }
    public boolean insertData(String fname,String lname,String email, String mobileno,String password, String extra) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UCOL_2,fname);
        contentValues.put(UCOL_3,lname);
        contentValues.put(UCOL_4,email);
        contentValues.put(UCOL_5,mobileno);
        contentValues.put(UCOL_6,password);
        contentValues.put(UCOL_7,extra);

        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }



    public Cursor getAllData(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String q;//= "select * from user where USERNAME = '"+username+"' and PASSWORD = '"+password+"'";
        q= "select * from userregd where ID = '"+id+"'";
        Cursor res = db.rawQuery(q, null);
        return res;
    }


    public Cursor checkLogin(String email, String password) {

        SQLiteDatabase db = this.getReadableDatabase();

        String q;//= "select * from user where USERNAME = '"+username+"' and PASSWORD = '"+password+"'";
        q= "select * from userregd where EMAIL = '"+email+"' and PASSWORD = '"+password+"'";
        Cursor res = db.rawQuery(q, null);
        return res;

    }
    public Cursor recoverPass(String email) {

        SQLiteDatabase db = this.getWritableDatabase();

        String q;//= "select * from user where USERNAME = '"+username+"' and PASSWORD = '"+password+"'";
        q= "select fname,Password from userregd where EMAIL = '"+email+"' ";
        Cursor res = db.rawQuery(q, null);
        return res;

    }
    public Cursor alreadyExist(String email) {

        SQLiteDatabase db = this.getReadableDatabase();

        String q;//= "select * from user where USERNAME = '"+username+"' and PASSWORD = '"+password+"'";
        q= "select Email from userregd where EMAIL = '"+email+"' ";
        Cursor res = db.rawQuery(q, null);
        return res;

    }
    public Cursor userLoginID(String email) {

        SQLiteDatabase db = this.getWritableDatabase();

        String q;//= "select * from user where USERNAME = '"+username+"' and PASSWORD = '"+password+"'";
        q= "select ID from userregd where EMAIL = '"+email+"' ";
        Cursor res2 = db.rawQuery(q, null);
        return res2;

    }
//------------------------------------------------------------------------------------------------------------------------------------

//============================================================================================================
// 11111111111111111-----------start admin details data contains   insertion etc

    public void deladmdata(){
        try{
            SQLiteDatabase db = getWritableDatabase();
            //  db = openOrCreateDatabase(DBNAME, Context.MODE_PRIVATE,null);
            db.execSQL("DELETE FROM  " + ADM_TABLE_NAME);

            db.close();
        }catch(Exception e){
            //Toast.makeText((), "Error encountered while dropping.", Toast.LENGTH_LONG);
        }
    }

    public void  ADMdata()
    { SQLiteDatabase db = this.getWritableDatabase();
        String dta = "insert into "+ ADM_TABLE_NAME +" values (null, 'arzu', 'admin@gmail.com', 'admin','0')";
        db.execSQL(dta);
    }


    public boolean admInsertData(String name,String email,String password, String extra) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ADM_COL_2,name);
        contentValues.put(ADM_COL_3,email);
        contentValues.put(ADM_COL_4,password);
        contentValues.put(ADM_COL_5,extra);
        long result = db.insert(ADM_TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public boolean updateUserInfo(String fname, String lname,String mobile, String id){
               SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(UCOL_2,fname);
        contentValues.put(UCOL_3,lname);
        contentValues.put(UCOL_5,mobile);

      long result=  db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        if(result == -1)
            return false;
        else
            return true;

    }
    public boolean updatePassInfo(String new_pass,  String id){
               SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(UCOL_6,new_pass);
              long result=  db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        if(result == -1)
            return false;
        else
            return true;

    }


    public Cursor checkLogin2(String email, String password) {

        SQLiteDatabase db = this.getWritableDatabase();

        String q;//= "select * from user where USERNAME = '"+username+"' and PASSWORD = '"+password+"'";
        q= "select * from admindetails where EMAIL = '"+email+"' and PASSWORD = '"+password+"'";
        Cursor res = db.rawQuery(q, null);
        return res;

    }


    public Cursor admGetAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ADM_TABLE_NAME,null);
        return res;
    }

    ////////ITEM DETAILS //////////////////

   public boolean insertUploadData(String category,String itemname,byte[] itemimage,String cost,String rating, String frontcamera, String modelno, String backcamera, String hybridsimslot, String otgcompatible, String touchscreen,String status, String extra){
       SQLiteDatabase db = this.getWritableDatabase();
       ContentValues contentValues = new ContentValues();
       contentValues.put(ITEM_COL_2, category);
       contentValues.put(ITEM_COL_3,itemname);
       contentValues.put(ITEM_COL_4,itemimage);
       contentValues.put(ITEM_COL_5,cost);
       contentValues.put(ITEM_COL_6,rating);
       contentValues.put(ITEM_COL_8,frontcamera);
       contentValues.put(ITEM_COL_7, modelno);
       contentValues.put(ITEM_COL_9, backcamera);
       contentValues.put(ITEM_COL_10,hybridsimslot);
       contentValues.put(ITEM_COL_11,otgcompatible);
       contentValues.put(ITEM_COL_12,touchscreen);
       contentValues.put(ITEM_COL_13,status);
       contentValues.put(ITEM_COL_14,extra);

       long result = db.insert(ITEM_TABLE_NAME,null ,contentValues);

       if(result == -1)
           return false;
       else
           return true;
   }

    public ArrayList<Model> getAllImagesData(String s, String b)
    {
        try {
            SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
            ArrayList<Model> modelArrayList=new ArrayList<>();
            Cursor cursor=sqLiteDatabase.rawQuery("select * from itemdetails where category=? and status=?", new String[] {s, b});
            if (cursor.getCount()!=0){
                while (cursor.moveToNext()){
                    String nameofimage =cursor.getString(2);
                    byte[] imageByte =cursor.getBlob(3);
                    String cost =cursor.getString(4);
                    String category =cursor.getString(1);
                    String  rating  =cursor.getString(5);
                    String  frontcamera  =cursor.getString(7);
                    String  modelno  =cursor.getString(6);
                    String  backcamera  =cursor.getString(8);
                    String  hybrid  =cursor.getString(9);
                    String  otg  =cursor.getString(10);
                    String  touch  =cursor.getString(11);

                    String status =cursor.getString(12);
                    String extra =cursor.getString(13);
                    Integer faltu=cursor.getInt(0);
                    Bitmap bitmap= BitmapFactory.decodeByteArray(imageByte,0,imageByte.length);
                    //int ID, String CATEGORY, String ITEMNAME, Bitmap ITEMIMAGE, String COST, String STATUS, String EXTRA
                    modelArrayList.add(new Model(faltu,category,nameofimage,bitmap,cost,rating, modelno, frontcamera,backcamera,hybrid,otg,touch,status,extra));

                }
                return modelArrayList;
            }
            else {
                Toast.makeText(faltu_context.context,"No values in database",Toast.LENGTH_SHORT).show();
                return null;
            }
        }
        catch (Exception e)
        {
            Toast.makeText(faltu_context.context,e.getMessage(),Toast.LENGTH_SHORT).show();
            return null;
        }

    }

/////////////////////////////cart adapter///////////////////

    public ArrayList<Model> getcartdata()
    {
        try {
            SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
            ArrayList<Model> cartarray=new ArrayList<>();
            Float xz=Float.parseFloat("0");
            Cursor cursor=sqLiteDatabase.rawQuery("select * from " +ITEM_TABLE_NAME, null);
            global_vars globalVariable = (global_vars) faltu_context.context;
            if (cursor.getCount()!=0){

                while (cursor.moveToNext()){
                boolean  jk=false;
                int qtyorder=0;
                 for(int i=0;i< globalVariable .myGlobalArray.size();i++)
                 {
                     String CSV = (String) globalVariable.myGlobalArray.get(i);
                     String[] values = CSV.split(",");

                     if(String.valueOf(cursor.getInt(0)).equals(values[0]))
                     {
                         jk=true;
                         qtyorder= Integer.parseInt(values[1]);
                     }

                 }
                 if(jk==true){
                 String nameofimage =cursor.getString(2);
                    byte[] imageByte =cursor.getBlob(3);
                    String cost =cursor.getString(4);
                   // String category =cursor.getString(1);
                    String status =cursor.getString(5);
                    String extra =cursor.getString(6);
                    Integer faltu=cursor.getInt(0);
                    String kinna=String.valueOf(qtyorder);

                     Float f=Float.parseFloat(cost)*Float.parseFloat(kinna);
                     xz=xz+f;
                     //global_vars globalVariable = (global_vars) faltu_context.context;
                     globalVariable.setAmounttopay(xz);
                    // String s=Float.toString(f) ;

                    Bitmap bitmap= BitmapFactory.decodeByteArray(imageByte,0,imageByte.length);
                    //int ID, String CATEGORY, String ITEMNAME, Bitmap ITEMIMAGE, String COST, String STATUS, String EXTRA
                    ////// cartarray.add(new Model(faltu,kinna,nameofimage,bitmap,cost,status,extra));
                 }
                }
                return cartarray;
            }
            else {
                Toast.makeText(faltu_context.context,"No values in database",Toast.LENGTH_SHORT).show();
                return null;
            }
        }
        catch (Exception e)
        {
            Toast.makeText(faltu_context.context,e.getMessage(),Toast.LENGTH_SHORT).show();
            return null;
        }

    }
    /////////////////////////////////generate insert////////////////////////////////////
   // ID INTEGER PRIMARY KEY AUTOINCREMENT,IDSOFORDER INTEGER,USERLOGINID INTEGER,BOOKINGDATEANDTIME TEXT,AMOUNTTOPAY TEXT,ORDERRECIVINGTIME TEXT,QRCODE TEXT ,STATUS TEXT,EXTRA TEXT)");

    public boolean generateitems(String idsofordeerandqty,Integer userloginid,String bookingdateandtime,String amounttopay,String orderrecivingtime,String qrcode,String status, String extra) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(G_T_2,idsofordeerandqty);
        contentValues.put(G_T_3,userloginid);
        contentValues.put(G_T_4,bookingdateandtime);
        contentValues.put(G_T_5,amounttopay);
        contentValues.put(G_T_6,orderrecivingtime);
        contentValues.put(G_T_7,qrcode);
        contentValues.put(G_T_8,status);
        contentValues.put(G_T_9,extra);
        long result = db.insert(GENERATE_TABLE,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
/////////////////////////////////////////////
public Cursor GetGenerateDetails() {
    SQLiteDatabase db = this.getReadableDatabase();
   Cursor res = db.rawQuery("select * from "+GENERATE_TABLE  ,null);
    return res;

}

   ////////////////////////////////////////allitemdetails remove and update
   public ArrayList<Model> getAllitemsData(String b)
   {
       try {
           SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
           ArrayList<Model> allitemsList=new ArrayList<>();
           Cursor cursor=sqLiteDatabase.rawQuery("select * from itemdetails where  status=?", new String[] {b});
           if (cursor.getCount()!=0){
               while (cursor.moveToNext()){
                   String nameofimage =cursor.getString(2);
                   byte[] imageByte =cursor.getBlob(3);
                   String cost =cursor.getString(4);
                   String category =cursor.getString(1);
                   String status =cursor.getString(5);
                   String extra =cursor.getString(6);
                   Integer faltu=cursor.getInt(0);
                   Bitmap bitmap= BitmapFactory.decodeByteArray(imageByte,0,imageByte.length);
                   //int ID, String CATEGORY, String ITEMNAME, Bitmap ITEMIMAGE, String COST, String STATUS, String EXTRA
                  ///// allitemsList.add(new Model(faltu,category,nameofimage,bitmap,cost,status,extra));

               }
               return allitemsList;
           }
           else {
               Toast.makeText(faltu_context.context,"No values in database",Toast.LENGTH_SHORT).show();
               return null;
           }
       }
       catch (Exception e)
       {
           Toast.makeText(faltu_context.context,e.getMessage(),Toast.LENGTH_SHORT).show();
           return null;
       }

   }
//////////////////////////////////update items
public boolean updateITEMDETAILS(String ID,String ITEMNAME,String COST,String STATUS) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put(ITEM_COL_1,ID);
    contentValues.put(ITEM_COL_3,ITEMNAME);
    contentValues.put(ITEM_COL_5,COST);
    contentValues.put(ITEM_COL_6,STATUS);
    db.update(ITEM_TABLE_NAME, contentValues, "ID = ?",new String[] { ID });
    return true;
}
////////////////////////////////////////////////////////////////////
    /*
public ArrayList<Orderview> fetchdetails()
{
    try {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        // Cursor res = sqLiteDatabase.rawQuery("select GENERATE_TABLE.ID, GENERATE_TABLE.STATUS,GENERATE_TABLE.ORDERRECIVINGTIME ,TABLE_NAME.FNAME from "+GENERATE_TABLE +"GENERATE_TABLE"+"LEFT JOIN"+TABLE_NAME+"TABLE_NAME"+"ON" + UCOL_1 +"="+G_T_1,null);
        ArrayList<Orderview> orderviewArrayList=new ArrayList<>();
//Cursor res1= sqLiteDatabase.rawQuery("select * from "+TABLE_NAME,null);
        String s="SELECT a.ID, a.STATUS, a.ORDERRECIVINGTIME, b.FNAME FROM generateitems a LEFT JOIN userregd b ON a.USERLOGINID=b.ID";
//String s ="select * from generateitems where userloginid=? ";
        Cursor res=sqLiteDatabase.rawQuery(s,null);
        if (res.getCount()!=0){
            while (res.moveToNext()){
                Integer id =res.getInt(0);
                String fname =res.getString(3);
                String recievingtime =res.getString(2);
                String status =res.getString(1);

                //int ID, String CATEGORY, String ITEMNAME, Bitmap ITEMIMAGE, String COST, String STATUS, String EXTRA
                orderviewArrayList.add(new Orderview(id,fname,recievingtime,status));

            }
            return orderviewArrayList;
        }
        else {
            Toast.makeText(faltu_context.context,"No values in database",Toast.LENGTH_SHORT).show();
            return null;
        }
    }
    catch (Exception e)
    {
        Toast.makeText(faltu_context.context,e.getMessage(),Toast.LENGTH_SHORT).show();
        return null;
    }

}
    ////////////////////////////////////////////////////

public ArrayList<ViewDetail> getAllOrderDetails(String b){
    try {
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        SQLiteDatabase sq=this.getReadableDatabase();
        ArrayList<ViewDetail> viewDetailArrayList=new ArrayList<>();

        Cursor res=sqLiteDatabase.rawQuery("select ID, IDSOFORDERANDQTY,AMOUNTTOPAY from generateitems where  ID=?", new String[] {b});

        if (res.getCount()!=0){
            while (res.moveToNext()){
                Integer id =res.getInt(0);
                String totalcost =res.getString(2);
                ////
                String xv = res.getString(1);
                String[] xv1=xv.split("k");
                int a =(xv1.length)-1;
                for(int x=0;x<a;x++){
                    String xv2=xv1[x+1];
                    String[] xv3=xv2.split(",");
                    Cursor res1=sq.rawQuery("select ITEMNAME, ITEMIMAGE from itemdetails where  ID=?", new String[] {xv3[0]});

                    while (res1.moveToNext()){
                    String itemname =res1.getString(0);


                    byte[] imageByte =res1.getBlob(1);
                    String nooforders =xv3[1];
                    String totalitems = String.valueOf(a);

                    Bitmap bitmap= BitmapFactory.decodeByteArray(imageByte,0,imageByte.length);
                    viewDetailArrayList.add(new ViewDetail(id,itemname,bitmap,totalcost,nooforders,totalitems));

                 }
                }



                //String[] values = res.split(",");
            }
            return viewDetailArrayList;
        }
        else {
            Toast.makeText(faltu_context.context,"No values in database",Toast.LENGTH_SHORT).show();
            return null;
        }
    }
    catch (Exception e)
    {
        Toast.makeText(faltu_context.context,e.getMessage(),Toast.LENGTH_SHORT).show();
        return null;
    }

}
*/





}
