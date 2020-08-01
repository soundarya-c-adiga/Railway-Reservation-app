package loc.com.railwaysystem;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Basic extends AppCompatActivity {


    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic);

        b1 = findViewById(R.id.user);
        b2 = findViewById(R.id.admin);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Basic.this, MainActivity.class));

                finish();

            }

        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Basic.this,AdminLogin.class));
                finish();
            }
        });
    }

    public void main(View view) {

        startActivity(new Intent(this, MainActivity.class));
        }
    public void admin(View view){
        startActivity(new Intent(this,AdminLogin.class));
    }


}