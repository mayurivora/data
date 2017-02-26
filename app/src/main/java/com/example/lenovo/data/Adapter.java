package com.example.lenovo.data;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Lenovo on 2/21/2017.
 */
public class Adapter extends BaseAdapter {

    ArrayList<Contact> student = new ArrayList<>();
    Context context;

    Contact cn;

    public Adapter(Context context, ArrayList<Contact> array) {

        this.student = array;
        this.context = context;
        //super(context, R.layout.row_item, resource);
    }

    @Override
    public int getCount() {
        return student.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder;
        LayoutInflater layoutInflater;
        if (convertView == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.row_item, null);

            holder = new Holder();
            //convertView = layoutInflater.inflate(R.layout.row_item, parent, false);
            holder.name = (TextView) convertView.findViewById(R.id.product_name);
            holder.r1 = (LinearLayout) convertView.findViewById(R.id.r1);
            cn = student.get(position);
            holder.name.setText(cn.getName());

            holder.r1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Contact cc = student.get(position);
                    String name = String.valueOf(cc.getName());
                    String Phone = String.valueOf(cc.getPhone());
                    String email = String.valueOf(cc.getEmail());
                    String place = String.valueOf(cc.getCounter());
                    String street = String.valueOf(cc.getStreet());
                    Intent i = new Intent(context, Singlelist.class);
                    i.putExtra("name", name);
                    i.putExtra("Phone", Phone);
                    i.putExtra("email", email);
                    i.putExtra("place", place);
                    i.putExtra("street", street);


                    context.startActivity(i);
                }
            });


            convertView.setTag(holder);

        } else {

            holder = (Holder) convertView.getTag();
        }


        return convertView;
    }

    private static class ViewHolder {
        TextView name;
        TextView phone;
        TextView email;
        TextView street;
        TextView place;

    }

    class Holder {
        LinearLayout r1;
        TextView name;


    }


}























