package com.example.lenovo.data;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "MESSAGE";
    //private ListView obj;

    // listening to single list item on click
    Database db;
    Adapter adapter;
    ArrayList<Contact> arrayOfUsers;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] values = new String[]{"Android", "iPhone", "WindowsMobile",
                "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
                "Linux", "OS/2"};
        lv = (ListView) findViewById(R.id.listView1);
        Database db = new Database(this);

        // arrayOfUsers=new ArrayList<Contact>();
        //ArrayList<Contact> arrayOfUsers = new ArrayList<Contact>();

        arrayOfUsers = db.getAllCotacts();
        adapter = new Adapter(this, arrayOfUsers);

        lv.setAdapter(adapter);
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent i = new Intent(getApplicationContext(), Singlelist.class);
//                startActivity(i);
//
//              //  Toast.makeText(MainActivity.this, position, Toast.LENGTH_SHORT).show();
//            }
//        });


//

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.item1:
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", 0);


                Intent intent = new Intent(getApplicationContext(), DisplayContact.class);
                intent.putExtras(dataBundle);

                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
        }
        return super.onKeyDown(keycode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Database db = new Database(getApplicationContext());
        db.getWritableDatabase();
        arrayOfUsers = db.getAllCotacts();
        adapter = new Adapter(this, arrayOfUsers);

        lv.setAdapter(adapter);
    }
}

