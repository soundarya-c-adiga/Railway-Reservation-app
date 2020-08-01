package loc.com.railwaysystem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewPassdetails extends AppCompatActivity {
    ListView ls;
    ArrayList<String> listItem;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpassdetails);
        ls=findViewById(R.id.listview);

        viewPassData();


    }
    public void viewPassData(){
        String p1=getIntent().getStringExtra("name1");
        String p2=getIntent().getStringExtra("name2");
        listItem.add(p1);
        listItem.add(p2);
        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listItem);
        ls.setAdapter(adapter);
    }
}
