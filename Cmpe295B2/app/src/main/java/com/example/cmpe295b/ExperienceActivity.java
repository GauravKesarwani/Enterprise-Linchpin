package com.example.cmpe295b;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.cmpe295b.adapters.EmployerAdapter;
import com.example.cmpe295b.beans.EmployerRowItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ExperienceActivity extends ActionBarActivity {
    ListView listView;
    EmployerRowItem[] rowItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);
        Intent intent = getIntent();
        String message = intent.getStringExtra(Profile.EXPERIENCE_MESSAGE);
        System.out.println("Message in Experience " + message);


        try {
            JSONObject obj = new JSONObject(message);
            JSONArray arr = obj.getJSONArray("jobhist");
            rowItems = new EmployerRowItem[arr.length()];
            System.out.println(" arrlength " + arr.length());

            //  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
            //  Date d1 = null;
            //  Date d2 = null;
            for (int i = 0; i < arr.length(); i++) {
                JSONObject jobHist = arr.getJSONObject(0);
            //    d1 = dateFormat.parse(jobHist.getString("startDate"));
                System.out.println(jobHist);

                EmployerRowItem erow = new EmployerRowItem(jobHist.getString("companyName"), jobHist.getString("designation"), jobHist.getString("startDate"),jobHist.getString("endDate"));
            //    EmployerRowItem erow = new EmployerRowItem(jobHist.getString("designation"));
            //    EmployerRowItem erow = new EmployerRowItem(jobHist.getString("designation"));
                rowItems[i] = erow;


            // rowItems[i].setStartDate(getString("startDate"));
            // rowItems[i].setEndDate(getString("endDate"));
            // rowItems[i].setDesignation(getString("designation"));

            //    String endDate = jobHist.getString("endDate");

             //   TextView totalExp = (TextView) findViewById(R.id.totalExp);
             //   TextView totalExp = (TextView) findViewById(R.id.totalExp);
             //   totalExp.setText();
             //   TextView compFreq = (TextView) findViewById(R.id.textView22);
              //  compFreq.setText(compensationInfo.getString("frequency"));
            }


        }catch(JSONException e) {
            e.printStackTrace();
        }

        listView = (ListView) findViewById(R.id.list_experience);
        EmployerAdapter adapter = new EmployerAdapter(this, rowItems);
        listView.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_experience, menu);

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
