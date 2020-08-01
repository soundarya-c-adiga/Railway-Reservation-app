package loc.com.railwaysystem;


import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Sign_up extends AppCompatActivity {
    EditText t1,t2,t3,t4,t5;
    TextView v1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        t1=findViewById(R.id.username);
        t2=findViewById(R.id.mobile);
        t3=findViewById(R.id.email);
        t4=findViewById(R.id.password);
        t5=findViewById(R.id.conf_pass);
        b1=findViewById(R.id.signup);

        TextView signIn_text = findViewById(R.id.signIn_text);
        signIn_text.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                startActivity(new Intent(Sign_up.this, MainActivity.class));

                finish();
            }
        });



    }




    public void addRecord(View view)
    {

      if (!t4.getText().toString().equals(t5.getText().toString())){
            Toast.makeText(this, "password and confirm password is not matching", Toast.LENGTH_SHORT).show();
        }
        else if((!t1.getText().toString().equals(""))&&(!t2.getText().toString().equals(""))&&(!t3.getText().toString().equals(""))&&(!t4.getText().toString().equals(""))&&(!t5.getText().toString().equals(""))){


            DbManager db = new DbManager(this);
            String res = db.addRecord(t1.getText().toString(), t2.getText().toString(), t3.getText().toString(), t4.getText().toString());
            Toast.makeText(this, res, Toast.LENGTH_LONG).show();
            t1.setText("");
            t2.setText("");
            t3.setText("");
            t4.setText("");
            t5.setText("");

        }
        else
      {
        Toast.makeText(this,"invalid credentials",Toast.LENGTH_SHORT).show();
      }


    }


}
