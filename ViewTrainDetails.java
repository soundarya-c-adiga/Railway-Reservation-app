package loc.com.railwaysystem;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ViewTrainDetails extends AppCompatActivity {
    ListView trainlist;
    Cursor t;
    String date_p,t_id;
    ArrayList<String> listItem;
    ArrayAdapter adapter;
    public static  String text;

    Button b;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewtraindetails);
        listItem=new ArrayList<>();
        DbManager db=new DbManager(this);

        date_p = getIntent().getStringExtra("date_new");
        final TextView t1=findViewById(R.id.textView2);
        trainlist=findViewById(R.id.listview);
        b=findViewById(R.id.ok);
        viewData();
        trainlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

               text=trainlist.getItemAtPosition(i).toString();
               Toast.makeText(ViewTrainDetails.this,""+text,Toast.LENGTH_SHORT).show();
              // String r=t_id;
              //String t=  trainlist.getItemAtPosition(i).toString();
             // String r=listItem.get(0);

              //  Toast.makeText(ViewTrainDetails.this,""+r,Toast.LENGTH_SHORT).show();
              b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(ViewTrainDetails.this,Passenger.class));
                        finish();
                    }
                });
            }
        });

    }

    private void viewData() {
        DbManager db=new DbManager(this);
        t=db.disptraindetails(date_p);
        int sizeofcr = t.getCount();

        if( sizeofcr < 1) {
            Toast.makeText(ViewTrainDetails.this, "No trains available", Toast.LENGTH_SHORT).show();
        }
      else{
            if (t != null) {

                if (t.moveToFirst()){
                    do {
                        t_id = t.getString(t.getColumnIndex("Train_id"));
                       // l.add(t_id);
                        String t_name = t.getString(t.getColumnIndex("Train_name"));
                        String t_arrtime=t.getString(t.getColumnIndex("Arrival_time"));
                        String t_deptime=t.getString(t.getColumnIndex("Depart_time"));
                        listItem.add("Train Number: "+t_id);
                        listItem.add("Train Name: "+t_name+"\n"+"Arrival Time: "+t_arrtime+"\n"+"Departure Time: "+t_deptime+"\n") ;

                        //listItem.add(t_name);
                        //listItem.add(t_arrtime+"  "+t_deptime);

                    }while(t.moveToNext());
                }
            }
        }
        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listItem);
        trainlist.setAdapter(adapter);
    }



}
