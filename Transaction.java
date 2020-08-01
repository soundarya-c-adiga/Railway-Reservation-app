package loc.com.railwaysystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static loc.com.railwaysystem.MainActivity.global;

public class Transaction extends AppCompatActivity {
    TextView h1,h2,h3,h4;
    Spinner spin;
    String arr[]={"e-Wallets","BHIM/UPI,Netbanking,Credit/Debit/Cashcards,others","BHIM/UPI"};
    EditText ei;
    Button b,but;
int value;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction);
        h1=findViewById(R.id.t1);
        h2=findViewById(R.id.t2);
        h3=findViewById(R.id.t3);
        spin=findViewById(R.id.s1);
        h4=findViewById(R.id.t4);
        ei=findViewById(R.id.e1);
        b=findViewById(R.id.button);
        but=findViewById(R.id.b1);
        ArrayAdapter<String> myadap = new ArrayAdapter<String>(Transaction.this, android.R.layout.simple_list_item_1,arr);
//myadap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       spin.setAdapter(myadap);

       b.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               String i = ei.getText().toString();
               if (i != "") {
                   try {
                       value = Integer.parseInt(i);
                   } catch (Exception e) {


                   }

                   int j = value * 500;
                   String s = Integer.toString(j);
                   h3.setText(s);
               } else {
                   Toast.makeText(Transaction.this, "Enter no. of passengers:", Toast.LENGTH_SHORT).show();
               }
               DbManager db = new DbManager(Transaction.this);
               if ((!ei.getText().toString().equals("")) && (!h3.getText().equals(""))) {

                   String res = db.insert(global, spin.getSelectedItem().toString(),h3.getText().toString());
                   Toast.makeText(Transaction.this, "" + res, Toast.LENGTH_SHORT).show();
               } else {
                   Toast.makeText(Transaction.this, "Fill all the feilds!", Toast.LENGTH_SHORT).show();
               }
           }


       });
       }
    public void viewticket(View view){
     startActivity(new Intent(Transaction.this,TicketView.class));
     finish();
    }
    }
