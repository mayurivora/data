package com.example.lenovo.data;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Lenovo on 2/22/2017.
 */
public class Singlelist extends Activity {
    public
    TextView tvname, tvemail, tvplace, tvphone, tvstreet;
    public String ConvertUserName;
    String name, GetName, GetPhone, Getemail, Getplace, Getstreet;
    String UserName;
    String GetSQliteQuery;
    Button update, delete;
    Cursor cursor, cursorCheckDataIsEmptyOrNot;
    TextView tText;
    TextView idtextview;
    Database helper;
    SQLiteDatabase db;
    SQLiteDatabase SQLITEDATABASE, SQLITEDATABASE2;
    Boolean CheckEditTextEmpty;
    String UpdateRecordQuery, DeleteRecordQuery;

    public void setName(String name) {
        this.name = name;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_list);
        tvname = (TextView) findViewById(R.id.textViewName);
        tvemail = (TextView) findViewById(R.id.textViewemail);
        tvphone = (TextView) findViewById(R.id.textViewphone);
        tvplace = (TextView) findViewById(R.id.textViewplace);
        tvstreet = (TextView) findViewById(R.id.textViewstreet);
        delete = (Button) findViewById(R.id.delete);
        update = (Button) findViewById(R.id.button);
        idtextview = (TextView) findViewById(R.id.tv);
        Intent i = getIntent();
        String p = i.getStringExtra("name");
        String q = i.getStringExtra("email");
        String r = i.getStringExtra("place");
        String s = i.getStringExtra("phone");
        String m = i.getStringExtra("street");
        //  Toast.makeText(Singlelist.this, "" + p, Toast.LENGTH_SHORT).show();
        // Toast.makeText(Singlelist.this, "" + q, Toast.LENGTH_SHORT).show();
        // Toast.makeText(Singlelist.this, "" + r, Toast.LENGTH_SHORT).show();
        // Toast.makeText(Singlelist.this, "" + s, Toast.LENGTH_SHORT).show();
        //Toast.makeText(Singlelist.this, "" + m, Toast.LENGTH_SHORT).show();
        //  Toast.makeText(Singlelist.this, "" + j, Toast.LENGTH_SHORT).show();
        tvname.setText(p);
        tvemail.setText(q);
        tvphone.setText(r);
        tvplace.setText(s);
        tvstreet.setText(m);


        GetSQliteQuery = "SELECT * FROM contacts";

        SQLITEDATABASE = openOrCreateDatabase("MyDBName.db", Context.MODE_PRIVATE, null);
        ConvertUserName = p;

        UserName = p;

        cursor = SQLITEDATABASE.rawQuery(GetSQliteQuery, null);

        cursor.moveToFirst();

        //GetSQLiteDatabaseRecords();

        update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                GetName = tvname.getText().toString();
                GetPhone = tvphone.getText().toString();
                Getemail = tvemail.getText().toString();
                Getplace = tvplace.getText().toString();
                Getstreet = tvstreet.getText().toString();
                //  ConvertUserName = idtextview.getText().toString();
                ConvertUserName = GetName;
                //ConvertUserName = idtextview.getText().toString();
                UserName = GetName.toString();

                UpdateRecordQuery = "UPDATE contacts SET name='" + GetName + "', phone_number='" + GetPhone + "',street='" + Getstreet + "',place='" + Getplace + "' email='" + Getemail + "' WHERE id=" + UserName + ";";

                CheckEditTextIsEmptyOrNot(GetName, GetPhone, Getemail, Getplace, Getstreet);

                if (CheckEditTextEmpty) {

                    SQLITEDATABASE.execSQL(UpdateRecordQuery);

                    cursor = SQLITEDATABASE.rawQuery(GetSQliteQuery, null);


                    cursor.moveToPosition(Integer.parseInt(idtextview.toString()));

                    Toast.makeText(Singlelist.this, "Data Updated Successfully", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(Singlelist.this, "Please Fill All the Fields", Toast.LENGTH_LONG).show();

                }
            }


            private boolean CheckEditTextIsEmptyOrNot(String Name, String Phone, String email, String street, String place) {
                CheckEditTextEmpty = !(TextUtils.isEmpty(Name) || TextUtils.isEmpty(Phone) || TextUtils.isEmpty(place) || TextUtils.isEmpty(email) || TextUtils.isEmpty(street));
                return CheckEditTextEmpty;
            }

        });


        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                //ConvertUserName = idtextview.getText().toString();

                UserName = GetName;


                // DeleteQuery = "DELETE FROM contacts WHERE name=" + UserName + ";";

                helper = new Database(getApplicationContext());
                helper.getWritableDatabase();
                helper.deleteContact(Integer.valueOf(UserName));
                helper.close();
                Toast.makeText(Singlelist.this, " Deleted Successfully", Toast.LENGTH_SHORT).show();

                //cursor = SQLITEDATABASE.rawQuery(GetSQliteQuery, null);

            }
        });
    }


}

