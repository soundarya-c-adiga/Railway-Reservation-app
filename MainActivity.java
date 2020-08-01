package loc.com.railwaysystem;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import static loc.com.railwaysystem.DbManager.col1;

public class MainActivity extends AppCompatActivity {

    public static String global;
    DbManager db;
    EditText username,password;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView signUp_text = findViewById(R.id.t2);
        username=findViewById(R.id.user);
        password=findViewById(R.id.p1);
        button=findViewById(R.id.b1);


        db=new DbManager(this);

        signUp_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Sign_up.class));

                finish();
            }


        });
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          boolean a ;
                                          String u = "";
                                          String p = "";
                                          u = username.getText().toString();
                                          p = password.getText().toString();
                                          a=db.signin(u,p);
                                          //Toast.makeText(this,""+username.getText().toString(),Toast.LENGTH_SHORT).show();
                                          if (a == false) {
                                              Toast.makeText(MainActivity.this, "INVALID CREDENTIALS", Toast.LENGTH_LONG).show();
                                          } else {
                                             startActivity(new Intent(MainActivity.this,Search.class));
                                              //   DbManager db=new DbManager(MainActivity.this);
                                            //  Toast.makeText(MainActivity.this, "changes made", Toast.LENGTH_LONG).show();
                                              global= db.getID(u, p);
                                           //  Toast.makeText(MainActivity.this, ""+i, Toast.LENGTH_SHORT).show();
                                              finish();

                                          }
                                      }


                                  });
    }
    public void startdbapp(View view) {

        startActivity(new Intent(MainActivity.this, Sign_up.class));


    }



}
