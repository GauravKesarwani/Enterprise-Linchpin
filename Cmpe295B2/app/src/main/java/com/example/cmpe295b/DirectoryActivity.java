package com.example.cmpe295b;

import com.example.cmpe295b.beans.EmployeeRowItem;
import com.example.cmpe295b.utils.HorizontalListView;
import com.example.cmpe295b.utils.ImageLoader;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class DirectoryActivity extends ActionBarActivity {

    private static String[] dataObjects;
    private EmployeeRowItem[] rowItems;
    private String fbImageUrl = "https://graph.facebook.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("Inside Directory Activity On create activity");
        setContentView(R.layout.listviewdemo);
        Intent intent = getIntent();
        String message = intent.getStringExtra(Profile.DIRECTORY_MESSAGE);
        System.out.println("Message in Directory Activity " + message);

        try {
            JSONObject obj = new JSONObject(message);
            JSONArray directory = obj.getJSONArray("directory");
            System.out.println(directory.length());
            dataObjects = new String[directory.length()];
            rowItems = new EmployeeRowItem[directory.length()];
            for (int i=0;i < directory.length();i++){
                EmployeeRowItem rowItem = new EmployeeRowItem(fbImageUrl+ directory.getJSONObject(i).getString("fbusername") + "/picture?width=400", directory.getJSONObject(i).getString("fname"),null);
                rowItems[i] = rowItem;
               // rowItems[i].setEmpName(directory.getJSONObject(i).getString("fname"));
                System.out.println(dataObjects[i]);
              //  rowItems[i].setImageId(fbImageUrl+ directory.getJSONObject(i).getString("fbusername") + "/picture?width=400");
            }

        }catch(JSONException e){
            e.printStackTrace();
        }

        HorizontalListView listview = (HorizontalListView) findViewById(R.id.listview);
        listview.setAdapter(mAdapter);

    }


    private BaseAdapter mAdapter = new BaseAdapter() {

        private OnClickListener mOnButtonClicked = new OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DirectoryActivity.this);
                builder.setMessage("hello from " + v);
                builder.setPositiveButton("Cool", null);
                builder.show();

            }
        };

        @Override
        public int getCount() {
            return dataObjects.length;
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
        public View getView(int position, View convertView, ViewGroup parent) {
            View retval = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewitem, null);
            TextView title = (TextView) retval.findViewById(R.id.title);
            title.setText(rowItems[position].getEmpName());
            ImageView empImageView = (ImageView) retval.findViewById(R.id.image);
            Context c = parent.getContext();
            ImageLoader imgLoader = new ImageLoader(c);
            imgLoader.DisplayImage(rowItems[position].getImageId(),empImageView);
            empImageView.setOnClickListener(mOnButtonClicked);
            return retval;
        }

    };


}



