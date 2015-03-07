package com.example.cmpe295b;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

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
      //  System.out.println(message);
        try {
            JSONObject obj = new JSONObject(message);
            TextView empName = (TextView) findViewById(R.id.text_name);
            JSONArray arr = obj.getJSONArray("emp_info");
            JSONObject empInfo = (JSONObject) arr.get(0);
            System.out.println(empInfo);
            empName.setText(empInfo.get("fname") + " " + empInfo.get("lname"));

            TextView empDesignation = (TextView) findViewById(R.id.text_designation);
            empDesignation.setText(empInfo.getString("emp_designation"));

            TextView empDepartment = (TextView) findViewById(R.id.text_dept);
            empDepartment.setText(empInfo.getString("emp_department"));

            Button workPhone = (Button) findViewById(R.id.home_phone);

            workPhone.setText("Home    " + empInfo.getString("home_phone"));

            Button workEmail = (Button) findViewById(R.id.work_email);
            workEmail.setText("Email   " + empInfo.getString("work_email"));

            workPhone.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view){
                    makeCall();
                }
            });

            workEmail.setOnClickListener(new View.OnClickListener() {
               public void onClick(View View){
                   sendEmail();
               }
            });

        }catch(JSONException e){
            e.printStackTrace();
        }

    }

    protected void makeCall(){
        Log.i("Make call", "");
        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:408-442-8559"));

        try {
            startActivity(phoneIntent);
            finish();
        }catch(ActivityNotFoundException ex){
            Toast.makeText(Profile.this,
            "Call faild, please try again later.", Toast.LENGTH_SHORT).show();
        }
    }

    protected void sendEmail() {
        Log.i("Send Email", "");
        Intent emailIntent = new Intent();
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        String[] TO = {"gaurav.kesarwani2@gmail.com" };
        String[] CC = {"gaurav2.forever@gmail.com"};

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your Subject");

        try {
            startActivity(emailIntent.createChooser(emailIntent, "Send Email..."));
            finish();
            Log.i("Finished Sending email...", "");
        }catch(ActivityNotFoundException ex){
            Toast.makeText(Profile.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
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
