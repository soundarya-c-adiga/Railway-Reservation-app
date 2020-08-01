package loc.com.railwaysystem;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class AdminLogin extends AppCompatActivity {


    Button bn;
    EditText usrname, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminlogin);

        usrname = findViewById(R.id.t1);
        pass = findViewById(R.id.t2);
        bn = findViewById(R.id.b1);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((usrname.getText().toString().equals("akshu")) && (pass.getText().toString().equals("akshu"))) {
                    startActivity(new Intent(AdminLogin.this, AdminAction.class));

                    finish();
                }

            }


        });

    }

    public void action(View view){
        if ((usrname.getText().toString().equals("akshu")) && (pass.getText().toString().equals("akshu"))) {
            startActivity(new Intent(AdminLogin.this, AdminAction.class));

            finish();
        } else {
            Toast.makeText(this, "INVALID CREDENTIALS", Toast.LENGTH_LONG).show();
        }
    }
}





