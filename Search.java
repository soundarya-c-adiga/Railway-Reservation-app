package loc.com.railwaysystem;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCantOpenDatabaseException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteReadOnlyDatabaseException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Search extends AppCompatActivity {
    Button button1;
    private int mYear, mMonth, mDay;
    Button date_picker,button2;
    CheckBox ch;
    String s2[] = new String[100];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        button1 = findViewById(R.id.b1);
        button2=findViewById(R.id.b3);
        date_picker = findViewById(R.id.b2);
        ch=findViewById(R.id.checkBox);
     //   from_station = findViewById(R.id.spinner1);
        DbManager db = new DbManager(this);
       /* Cursor c = db.get_from_station();


        // for (int i = 0; i < c.getCount(); i++) {


        c.moveToFirst();
        for (int i = 0; i < c.getCount(); i++) {

            String s = c.getString(c.getColumnIndex("from_station_name"));

            s2[i] = s;
            c.moveToNext();
        }
/*

        //}
        /*  if (c.moveToFirst()) {
            do {

                    String str1 = c.getString(c.getColumnIndex("from_station_name"));
                    Toast.makeText(Search.this, "" + str1, Toast.LENGTH_SHORT).show();
                }
            while (c.moveToNext());
        }

    }*/
       /* ArrayAdapter<String> myadap = new ArrayAdapter<String>(Search.this, android.R.layout.simple_list_item_1, s2);
//myadap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        from_station.setAdapter(myadap);

from_station.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(getApplicationContext(),"Your selection is: "+s2[i],Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
});  */

    }

    public void selectdate(View view) {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String str_month, str_date;
                str_month = String.valueOf(monthOfYear + 1);
                if (str_month.length() == 1) {
                    str_month = "0" + str_month;
                }

                str_date = String.valueOf(dayOfMonth);
                if (str_date.length() == 1) {
                    str_date = "0" + str_date;
                }


                date_picker.setText(str_date + str_month + year);

            }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void onClick_search(View view) {

        Log.d("fafaf", date_picker.getText().toString());
        if (date_picker.getText().toString().equals("Select Journey Date")) {
            Toast.makeText(this, " Please select valid date!", Toast.LENGTH_SHORT).show();
        }else {
             if(ch.isChecked()) {
                 Intent intent = new Intent(Search.this, ViewTrainDetails.class);
                 intent.putExtra("date_new", date_picker.getText().toString());
                 //  startActivity(intent);
                 startActivityForResult(intent, 0);
             }
             else{
                 Toast.makeText(Search.this,"Please Agree to the terms and conditions",Toast.LENGTH_SHORT).show();
             }
        }

    }
    public void viewt(View view){
        startActivity(new Intent(Search.this,TicketView.class));
        finish();
    }

}
