package com.example.cmpe295b;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Profile extends ActionBarActivity {
    private String[] myNavigationItemTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        NavigationItem[ ] navigationItems = new NavigationItem[4];
        ListView mItemList = (ListView) findViewById(R.id.list_view);
        navigationItems[0] = new NavigationItem("Experience");
        navigationItems[1] = new NavigationItem("Job");
        navigationItems[2] = new NavigationItem("Leave Request");
        navigationItems[3] = new NavigationItem("Compensation");

        CustomAdapter adapter = new CustomAdapter(this, R.layout.listview_item_row, navigationItems);
        mItemList.setAdapter(adapter);
        Intent intent = getIntent();

        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        System.out.println(message);
        try {
            JSONObject obj = new JSONObject(message);
            TextView empName = (TextView) findViewById(R.id.text_name);
            JSONArray arr = obj.getJSONArray("emp_info");
            JSONObject empInfo = (JSONObject) arr.get(0);
            System.out.println(empInfo);
            empName.setText(empInfo.get("fname") + " " + empInfo.get("lname"));

            TextView empDesignation = (TextView) findViewById(R.id.text_designation);
            empDesignation.setText(empInfo.getString("emp_designation"));

            Button home_phone = (Button) findViewById(R.id.home_phone);

            home_phone.setText("Home    " + empInfo.getString("home_phone"));

            Button work_email = (Button) findViewById(R.id.work_email);
            work_email.setText("Email   " + empInfo.getString("work_email"));

        }catch(JSONException e){
            e.printStackTrace();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
