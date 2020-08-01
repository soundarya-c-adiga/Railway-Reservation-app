package loc.com.railwaysystem;

import android.content.ContentValues;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Settings;
import android.support.v7.view.SupportActionModeWrapper;
import android.widget.Toast;

import java.io.StringBufferInputStream;
import java.net.IDN;
import java.sql.ResultSet;

import static loc.com.railwaysystem.MainActivity.global;

public class DbManager extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "userinfodb.db";
    public static final String TABLE_NAME = "user_table";
    public static final String TABLE_ADMIN = "admin_table";
    public static final String TABLE_PASSENGER = "passenger_table";
    public static final String TABLE_TRANSACTION="transaction_table";

    public static final String col_1 = "ID";
    public static final String col_2 = "USERNAME";
    public static final String col_3 = "EMAIL";
    public static final String col_4 = "MOBILE";
    public static final String col_5 = "PASSWORD";

    public static final String clm_1 = "ID";
    public static final String clm_2 = "Train_id";
    public static final String clm_3 = "Train_name";
    public static final String clm_4 = "Train_type";
    public static final String clm_5 = "Avail_class";
    public static final String clm_6 = "Date1";
    public static final String clm_7 = "Arrival_time";
    public static final String clm_8 = "Depart_time";

    public static final String clm_9 = "from_station_name";
    public static final String clm_10 = "to_station_name";
    public static final String clm_11 = "Arrival_platform_no";


   public static final String cl1 = "Pass_id";
    public static final String cl2 = "User_id";

    public static final String cl3 = "Train_id";
    public static final String cl4= "Passenger_name";
    public static final String cl5 = "Age";
    public static final String cl6 = "Genger";
    public static final String cl7 = "Birth_preference";
    public static final String cl8 = "Country";

    public static final String col1="User_id";
    public static final String col2="Trans_id";
    public static final String col3="Payment_mode";
    public static final String col4="Price";



    public Context Mcontext;


    public DbManager(Context context) {
        super(context, DATABASE_NAME, null, 1);
        Mcontext = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("create table " + TABLE_ADMIN + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,Train_id TEXT ,Train_name TEXT,Train_type TEXT,Avail_class TEXT,Date1 REAL,Arrival_time REAL,Depart_time REAL,from_station_name TEXT,to_station_name TEXT,Arrival_platform_no TEXT)");
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT ,EMAIL TEXT,PASSWORD TEXT,MOBILE INTEGER)");
        db.execSQL("create table " + TABLE_PASSENGER + "(Pass_id INTEGER PRIMARY KEY AUTOINCREMENT,User_id TEXT,Train_id TEXT,Passenger_name TEXT,Age TEXT,Genger TEXT,Birth_preference TEXT,Country TEXT,FOREIGN KEY(User_id) references TABLE_NAME(ID),FOREIGN KEY(Train_id) references TABLE_ADMIN(Train_id)) ");
//db.execSQL("create table " + TABLE_PASSENGER + "(Pass_id INTEGER PRIMARY KEY AUTOINCREMENT,Passenger_name TEXT,Age TEXT,Gender TEXT,Birth_preference TEXT,Country TEXT )");
        db.execSQL("create table " + TABLE_TRANSACTION + "(Trans_id INTEGER PRIMARY KEY AUTOINCREMENT,User_id TEXT,Payment_mode,Price,FOREIGN KEY(User_id) references TABLE_USER(User_id))" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMIN);
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_PASSENGER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTION);


        onCreate(db);

    }
   public String getID(String us,String pass)
    {
        Cursor cursor=null;
        SQLiteDatabase db=this.getWritableDatabase();
        cursor=db.rawQuery("Select * from USER_TABLE where USERNAME=? AND PASSWORD=?",new String[] {us,pass});
        String id="";
        if(cursor.moveToFirst()) {
             id = cursor.getString(cursor.getColumnIndex("ID"));

        }
        return id;

    }

    public String addRecord(String p1, String p2, String p3, String p4) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("USERNAME", p1);
        cv.put("MOBILE", p2);
        cv.put("EMAIL", p3);
        cv.put("PASSWORD", p4);


        long res = db.insert("USER_TABLE", null, cv);
        if (res == -1) return "FAILED";
        else return "SUCCESSFULLY INSERTED";
    }
    public String insert(String p0,String p1,String p2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("User_id",p0);
        cv.put("Payment_mode", p1);
        cv.put("Price", p2);



        long res = db.insert("TRANSACTION_TABLE", null, cv);
        if (res == -1) return "FAILED";
        else return "SUCCESSFULLY INSERTED";
    }


    public String insertAdminInfo(String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9, String p10) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Train_id", p1);
        cv.put("Train_name", p2);
        cv.put("Train_type", p3);
        cv.put("Avail_class", p4);
        cv.put("Date1", p5);
        cv.put("Arrival_time", p6);
        cv.put("Depart_time", p7);
        cv.put("from_station_name", p8);
        cv.put("to_station_name", p9);
        cv.put("Arrival_platform_no", p10);

        long res = db.insert("ADMIN_TABLE", null, cv);
        if (res == -1) return "FAILED";
        else return "SUCCESSFULLY INSERTED";

    }

 public String insertPassInfo(String s1,String s2,String s3,String s4,String s5, String s6,String s7) {
        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Passenger_name", s1);
        cv.put("Age", s2);
        cv.put("Genger", s3);
        cv.put("Birth_preference", s4);
        cv.put("Country", s5);
        cv.put("User_id",s6);
        cv.put("Train_id",s7);
        long res = db1.insert("passenger_table", null, cv);
        if (res == -1) return "FAILED";
        else return "SUCCESSFULLY INSERTED";

    }

    public boolean signin(String USERNAME, String PASSWORD) {

            Cursor cursor=null;
            SQLiteDatabase db = this.getWritableDatabase();
            cursor=db.rawQuery("Select * from USER_TABLE WHERE USERNAME =? AND PASSWORD=?",new String[]{USERNAME, PASSWORD});
      //  cursor=db.rawQuery("Select * from USER_TABLE WHERE USERNAME = 'Soundarya'", null);

            int sizeofcr = cursor.getCount();
            if (sizeofcr < 1) {
                return false;

            } else {

                return true;


            }




    }



    public Cursor disptraindetails(String Date) {
        Cursor cursor=null;
        SQLiteDatabase db = this.getWritableDatabase();
        //Toast.makeText(this,""+Date,Toast.LENGTH_SHORT).show();
        cursor=db.rawQuery("Select * from ADMIN_TABLE where Date1= ?", new String[]{Date});

        //", new String[]{Date});
    //    int sizeofcr = cursor.getCount();
//        if (sizeofcr < 1) {
           //return false;

  //      } else {
          return cursor;
    }
    public Cursor get_from_station(){
        Cursor c;
        SQLiteDatabase db2=this.getWritableDatabase();
        c=db2.rawQuery("Select * from ADMIN_TABLE ",null);
       return c;

    }
    public Cursor getTicket(){
        Cursor c=null;
        SQLiteDatabase db3=this.getWritableDatabase();
        c=db3.rawQuery("Select distinct(p.Pass_id and t.Trans_id) ,p.User_id,p.Train_id,p.Passenger_name," +
                "p.Age,p.Genger,p.Country,t.Payment_mode,t.Price from PASSENGER_TABLE p,TRANSACTION_TABLE t,USER_TABLE u where p.User_id=t.User_id and p.User_id=u.ID and  p.User_id=?",new String[]{global});
        return c;
    }




   /* public Cursor viewData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * from " + TABLE_ADMIN;
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }*/
 /*  public void deletetrans(){
      SQLiteDatabase db=this.getWritableDatabase();
      db.execSQL("Delete from Transaction_table ");
   }
   public void deletepass(){
       SQLiteDatabase db=this.getWritableDatabase();
       db.execSQL("Delete from Passenger_table ");
   } */
}
