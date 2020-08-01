package loc.com.railwaysystem;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TicketView extends AppCompatActivity {
    ListView ticketlist;
    TextView t;
    ArrayAdapter adapter;
    ArrayList list;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticketview);


        ticketlist = findViewById(R.id.l1);
        t = findViewById(R.id.t1);

        list = new ArrayList<String>();
        list.clear();
        viewticket();

    }
    public void viewticket(){
    Cursor c = null;
        DbManager db = new DbManager(this);
    c = db.getTicket();
        if (c != null) {

            if (c.moveToFirst()) {
                do {

                    // Integer Pass_id = c.getInt(c.getColumnIndex("Pass_id"));
                    // Integer User_id = c.getInt(c.getColumnIndex("User_id"));
                    //String from=c.getString(c.getColumnIndex("From_station_name"));
                    //String to=c.getString(c.getColumnIndex("To_station_name"));
                    String Train_id = c.getString(c.getColumnIndex("Train_id"));
                    String Passenger_name = c.getString(c.getColumnIndex("Passenger_name"));
                    String Age = c.getString(c.getColumnIndex("Age"));
                    String Gender = c.getString(c.getColumnIndex("Genger"));
                     String Country = c.getString(c.getColumnIndex("Country"));
                    String Payment_mode = c.getString(c.getColumnIndex("Payment_mode"));
                      String Price = c.getString(c.getColumnIndex("Price"));
                    list.add(Train_id + "\n" + "Passenger_name :" + Passenger_name + "\n" + "Age:" + Age + "\n"
                    +"Gender :"+Gender+"\n"+"Country :"+Country+"\n"+"Payment_mode :"+Payment_mode+"\n"+"Price :"+Price+"\n");
                } while (c.moveToNext());
                adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
                ticketlist.setAdapter(adapter);
            }
        }
    }
}