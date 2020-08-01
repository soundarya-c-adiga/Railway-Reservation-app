package loc.com.railwaysystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import static loc.com.railwaysystem.MainActivity.global;
import static loc.com.railwaysystem.ViewTrainDetails.text;
public class Passenger extends AppCompatActivity {
    EditText _name,  ed1, a, b;
    TextView country,gen,berth,pass_det;
    Spinner sp1, sp3, g, sp4, sp5;
    String gender[] = { "Male", "Female", "Others"};
    String Berthpref[] = { "NO PREFERENCE", "LOWER", "MIDDLE", "UPPER", "SIDE LOWER", "SIDE UPPER"};
    Button proceed;

    FloatingActionButton fa1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passenger);
        pass_det=findViewById(R.id.p1);
         _name = findViewById(R.id.p2);
             country = findViewById(R.id.c);
      //  ed1 = findViewById(R.id.ed);
       // ed2 = findViewById(R.id.ed3);
        a = findViewById(R.id.p4);
      //  b = findViewById(R.id.p5);
        //age = findViewById(R.id.p6);
        sp1 = findViewById(R.id.spinner);
        gen=findViewById(R.id.te1);
        berth=findViewById(R.id.bp);
      // g = findViewById(R.id.sp2);
        //f = findViewById(R.id.sp3);
        //sp2 = findViewById(R.id.spinner2);
        sp3 = findViewById(R.id.spinner3);
     //   sp4 = findViewById(R.id.spinner4);
        sp5 = findViewById(R.id.spinner5);
        proceed = findViewById(R.id.p3);
        fa1 = findViewById(R.id.f1);

        DbManager db=new DbManager(this);
        //db.deletepass();
        //db.deletetrans();



        ArrayAdapter<String> myadap = new ArrayAdapter<String>(Passenger.this, android.R.layout.simple_list_item_1, gender);
//myadap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(myadap);

       // ArrayAdapter<String> myadapn = new ArrayAdapter<String>(Passenger.this, android.R.layout.simple_list_item_1, gender);
//myadap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //g.setAdapter(myadapn);

       // ArrayAdapter<String> myadaph = new ArrayAdapter<String>(Passenger.this, android.R.layout.simple_list_item_1, gender);
//myadap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //f.setAdapter(myadaph);


        //ArrayAdapter<String> myadap1 = new ArrayAdapter<String>(Passenger.this, android.R.layout.simple_list_item_1, Berthpref);
//myadap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        sp2.setAdapter(myadap1);

      //  ArrayAdapter<String> myadap2 = new ArrayAdapter<String>(Passenger.this, android.R.layout.simple_list_item_1, Berthpref);
//myadap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //sp4.setAdapter(myadap2);

        ArrayAdapter<String> myadap3 = new ArrayAdapter<String>(Passenger.this, android.R.layout.simple_list_item_1, Berthpref);
//myadap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp5.setAdapter(myadap3);


        Locale[] locale = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<String>();
        String country;
        for (Locale loc : locale) {
            country = loc.getDisplayCountry();
            if (country.length() > 0 && !countries.contains(country)) {
                countries.add(country);
            }
        }
        Collections.sort(countries, String.CASE_INSENSITIVE_ORDER);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countries);
        sp3.setAdapter(adapter);

        fa1.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  DbManager db1 = new DbManager(Passenger.this);
                  if ((!_name.getText().toString().equals("")) && (!a.getText().toString().equals("")) && (!sp1.getSelectedItem().toString().equals("")) && (!sp3.getSelectedItem().toString().equals("")) && (!sp5.getSelectedItem().toString().equals(""))) {
                      String t=db1.insertPassInfo(_name.getText().toString(), a.getText().toString(), sp1.getSelectedItem().toString(), sp5.getSelectedItem().toString(), sp3.getSelectedItem().toString(),global,text);
                      Toast.makeText(Passenger.this,""+t,Toast.LENGTH_SHORT).show();
                      _name.setText("");
                      a.setText("");


                  }
                  else{
                      Toast.makeText(Passenger.this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
                  }
              }
          });

     proceed.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             DbManager db1 = new DbManager(Passenger.this);
             if ((!_name.getText().toString().equals("")) && (!a.getText().toString().equals("")) && (!sp1.getSelectedItem().toString().equals("")) && (!sp3.getSelectedItem().toString().equals("")) && (!sp5.getSelectedItem().toString().equals(""))) {
                 String t=db1.insertPassInfo(_name.getText().toString(), a.getText().toString(), sp1.getSelectedItem().toString(), sp5.getSelectedItem().toString(), sp3.getSelectedItem().toString(),global,text);
                 Toast.makeText(Passenger.this,""+t,Toast.LENGTH_SHORT).show();
                 startActivity(new Intent(Passenger.this,Transaction.class));

                 finish();


             }else{

                 Toast.makeText(Passenger.this,"Fill the details!",Toast.LENGTH_SHORT).show();
             }

         }
     });
    }
}

