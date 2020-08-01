package loc.com.railwaysystem;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class AdminAction extends AppCompatActivity {
    EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7,ed8,ed9,ed10;
    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminaction);

        ed1 = findViewById(R.id.e1);
        ed2 = findViewById(R.id.e2);
        ed3 = findViewById(R.id.e3);
        ed4 = findViewById(R.id.e4);
        ed5 = findViewById(R.id.e5);
        ed6 = findViewById(R.id.e6);
        ed7 = findViewById(R.id.e7);
        ed8 = findViewById(R.id.e8);
        ed9 = findViewById(R.id.e9);
        ed10 = findViewById(R.id.e10);
        button1=findViewById(R.id.b1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               insert(view);

            }});
        }



    public void insert(View view) {

        if ((!ed1.getText().toString().equals("")) && (!ed2.getText().toString().equals("")) && (!ed3.getText().toString().equals("")) && (!ed4.getText().toString().equals("")) && (!ed5.getText().toString().equals("")) && (!ed6.getText().toString().equals("")) && (!ed7.getText().toString().equals("")) && (!ed8.getText().toString().equals("")) && (!ed9.getText().toString().equals("")) && (!ed10.getText().toString().equals(""))) {


            DbManager db = new DbManager(this);


            String res = db.insertAdminInfo(ed1.getText().toString(), ed2.getText().toString(), ed3.getText().toString(), ed4.getText().toString(),ed5.getText().toString(),ed6.getText().toString(),ed7.getText().toString(),ed8.getText().toString(),ed9.getText().toString(),ed10.getText().toString());
             Toast.makeText(this, res, Toast.LENGTH_LONG).show();
            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");
            ed5.setText("");
            ed6.setText("");
            ed7.setText("");
            ed8.setText("");
            ed9.setText("");
            ed10.setText("");

        } else {
            Toast.makeText(this, "invalid credentials", Toast.LENGTH_SHORT).show();
        }

    }}
