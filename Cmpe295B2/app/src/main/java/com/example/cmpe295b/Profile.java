package com.example.cmpe295b;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;
import android.view.GestureDetector;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class Profile extends ActionBarActivity implements GestureDetector.OnGestureListener {
    private String[] myNavigationItemTitles;
    private static String compensationUrl;
    private static String teamInfoUrl;
    private String fbImageUrl = "https://graph.facebook.com/";
    private static String experienceUrl;
    private static String performanceUrl;
    private static String directoryUrl;
    public final static String COMP_MESSAGE = "com.example.compM";
    public final static String TEAMINFO_MESSAGE = "com.example.teaminfoM";
    public final static String EXPERIENCE_MESSAGE = "com.example.experienceM";
    public final static String PERORMANCE_MESSAGE = "com.example.performanceM";
    public final static String DIRECTORY_MESSAGE = "com.example.directoryM";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String appUrl = getString(R.string.ipaddress);
        compensationUrl = appUrl +  "compensation/?emp_id=1";
        teamInfoUrl = appUrl + "teamInfo/?emp_id=1";
        fbImageUrl = "https://graph.facebook.com/";
        experienceUrl =  appUrl + "experience/?emp_id=1";
        performanceUrl = appUrl + "performance/?emp_id=1";
        directoryUrl = appUrl + "directory/?emp_id=1";
        setContentView(R.layout.activity_profile);
        NavigationItem[ ] navigationItems = new NavigationItem[4];
        ListView mItemList = (ListView) findViewById(R.id.list_view);
        navigationItems[0] = new NavigationItem("Team Info");
        navigationItems[1] = new NavigationItem("Experience & Job History");
        navigationItems[2] = new NavigationItem("Compensation");
        navigationItems[3] = new NavigationItem("Talent & Performance");

        CustomAdapter adapter = new CustomAdapter(this, R.layout.listview_item_row, navigationItems);
        mItemList.setAdapter(adapter);
        Intent intent = getIntent();

        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        try {
            JSONObject obj = new JSONObject(message);
            TextView empName = (TextView) findViewById(R.id.text_name);
            JSONArray arr = obj.getJSONArray("emp_info"); //array of Json Object
            JSONObject empInfo = (JSONObject) arr.get(0); //get the first json object
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

        mItemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                if (position == 0) {
                    new CallTeamInfoAPI().execute(teamInfoUrl);
                }
                else if (position == 1){
                    new CallExperienceAPI().execute(experienceUrl);
                }
                else if (position == 2){
                    new CallCompensationAPI().execute(compensationUrl);
                }
                else if (position == 3){
                    new CallPerformanceAPI().execute(performanceUrl);
                }
            }
        });
    }

    protected void makeCall(){
        Log.i("Make call", "");
        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
        phoneIntent.setData(Uri.parse("tel:6309230545"));

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
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        String[] TO = {"email_to_send@gmail.com" };
        String[] CC = {"cc_to@gmail.com"};

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your Subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send Email..."));
            finish();
        //    Log.i("Finished Sending email...", "");
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

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
     //   Intent intent = new Intent(getApplicationContext(), CompensationActivity.class);
       // startActivity(intent);
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    /////End Gestures///

    ////0. Team Info API ////
    private class CallTeamInfoAPI extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params){

            String fullApi_url = params[0];
            HttpClient httpClientObject = new DefaultHttpClient();
            HttpGet httpGetCall = new HttpGet(fullApi_url);

            String jsonResult = null;
            try {

                HttpEntity httpEntityObject = httpClientObject.execute(httpGetCall).getEntity();
                System.out.println(httpEntityObject);
                if (httpEntityObject != null) {

                    InputStream inputStreamObject = httpEntityObject.getContent();
                    Reader readerObject = new InputStreamReader(inputStreamObject);
                    BufferedReader bufferedReaderObject = new BufferedReader(readerObject);

                    StringBuilder jsonResultStringBuilder = new StringBuilder();
                    String readLine = null;

                    while ((readLine = bufferedReaderObject.readLine()) != null) {
                        jsonResultStringBuilder.append(readLine + "\n");
                    }

                    jsonResult = jsonResultStringBuilder.toString();
                }

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return jsonResult;
        }


        protected void onPostExecute(String result) {
            System.out.println(" Result from call to team info API in profile class" + result);
            Intent intent = new Intent(getApplicationContext(), TeamInfoActivity.class);
            System.out.println(result);
//            new CallTeamImageAPI().execute("https://graph.facebook.com/gaurav.kesarwani85/picture");
            intent.putExtra(TEAMINFO_MESSAGE, result);

            startActivity(intent);
            //System.out.println(result);

        }
    }

            ////1. Team Experience API ////
    private class CallExperienceAPI extends AsyncTask<String, String, String> {
         @Override
        protected String doInBackground(String... params) {

            String fullApi_url = params[0];
            HttpClient httpClientObject = new DefaultHttpClient();
            HttpGet httpGetCall = new HttpGet(fullApi_url);

            String jsonResult = null;
            try {

                HttpEntity httpEntityObject = httpClientObject.execute(httpGetCall).getEntity();
                System.out.println(httpEntityObject);
                if (httpEntityObject != null) {

                    InputStream inputStreamObject = httpEntityObject.getContent();
                    Reader readerObject = new InputStreamReader(inputStreamObject);
                    BufferedReader bufferedReaderObject = new BufferedReader(readerObject);

                    StringBuilder jsonResultStringBuilder = new StringBuilder();
                    String readLine = null;

                    while ((readLine = bufferedReaderObject.readLine()) != null) {
                        jsonResultStringBuilder.append(readLine + "\n");
                    }

                    jsonResult = jsonResultStringBuilder.toString();
                }

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return jsonResult;
        }


        protected void onPostExecute(String result) {
            System.out.println(" Result from api call in profile class" + result);
            Intent intent = new Intent(getApplicationContext(), ExperienceActivity.class);

            intent.putExtra(EXPERIENCE_MESSAGE, result);

            startActivity(intent);
            //System.out.println(result);

        }
    }

            ////2. Compensation API ////
            private class CallCompensationAPI extends AsyncTask<String, String, String> {
                @Override
                protected String doInBackground(String... params) {

                    String fullApi_url = params[0];
                    HttpClient httpClientObject = new DefaultHttpClient();
                    HttpGet httpGetCall = new HttpGet(fullApi_url);

                    String jsonResult = null;
                    try {

                        HttpEntity httpEntityObject = httpClientObject.execute(httpGetCall).getEntity();
                        System.out.println(httpEntityObject);
                        if (httpEntityObject != null) {

                            InputStream inputStreamObject = httpEntityObject.getContent();
                            Reader readerObject = new InputStreamReader(inputStreamObject);
                            BufferedReader bufferedReaderObject = new BufferedReader(readerObject);

                            StringBuilder jsonResultStringBuilder = new StringBuilder();
                            String readLine = null;

                            while ((readLine = bufferedReaderObject.readLine()) != null) {
                                jsonResultStringBuilder.append(readLine + "\n");
                            }

                            jsonResult = jsonResultStringBuilder.toString();
                        }

                    } catch (ClientProtocolException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    return jsonResult;
                }


                protected void onPostExecute(String result) {
                    System.out.println(" Result from api call in profile class" + result);
                    Intent intent = new Intent(getApplicationContext(), CompensationActivity.class);

                    intent.putExtra(COMP_MESSAGE, result);

                    startActivity(intent);
                    //System.out.println(result);

                }
            }

            ////3. Performance API ////
            private class CallPerformanceAPI extends AsyncTask<String, String, String> {
                @Override
                protected String doInBackground(String... params) {

                    String fullApi_url = params[0];
                    HttpClient httpClientObject = new DefaultHttpClient();
                    HttpGet httpGetCall = new HttpGet(fullApi_url);

                    String jsonResult = null;
                    try {

                        HttpEntity httpEntityObject = httpClientObject.execute(httpGetCall).getEntity();
                        System.out.println(httpEntityObject);
                        if (httpEntityObject != null) {

                            InputStream inputStreamObject = httpEntityObject.getContent();
                            Reader readerObject = new InputStreamReader(inputStreamObject);
                            BufferedReader bufferedReaderObject = new BufferedReader(readerObject);

                            StringBuilder jsonResultStringBuilder = new StringBuilder();
                            String readLine = null;

                            while ((readLine = bufferedReaderObject.readLine()) != null) {
                                jsonResultStringBuilder.append(readLine + "\n");
                            }

                            jsonResult = jsonResultStringBuilder.toString();
                        }

                    } catch (ClientProtocolException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    return jsonResult;
                }


                protected void onPostExecute(String result) {
                    System.out.println(" Result from api call in profile class" + result);
                    Intent intent = new Intent(getApplicationContext(), PerformanceActivity.class);

                    intent.putExtra(PERORMANCE_MESSAGE, result);

                    startActivity(intent);
                    //System.out.println(result);

                }
            }

    ////4. Team Directory API ////
    private class CallDirectoryAPI extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params){

            String fullApi_url = params[0];
            HttpClient httpClientObject = new DefaultHttpClient();
            HttpGet httpGetCall = new HttpGet(fullApi_url);

            String jsonResult = null;
            try {

                HttpEntity httpEntityObject = httpClientObject.execute(httpGetCall).getEntity();
                System.out.println(httpEntityObject);
                if (httpEntityObject != null) {

                    InputStream inputStreamObject = httpEntityObject.getContent();
                    Reader readerObject = new InputStreamReader(inputStreamObject);
                    BufferedReader bufferedReaderObject = new BufferedReader(readerObject);

                    StringBuilder jsonResultStringBuilder = new StringBuilder();
                    String readLine = null;

                    while ((readLine = bufferedReaderObject.readLine()) != null) {
                        jsonResultStringBuilder.append(readLine + "\n");
                    }

                    jsonResult = jsonResultStringBuilder.toString();
                }

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return jsonResult;
        }


        protected void onPostExecute(String result) {
            System.out.println(" Result from call to directory API in profile class" + result);
            Intent intent = new Intent(getApplicationContext(), DirectoryActivity.class);
            System.out.println(result);
            intent.putExtra(DIRECTORY_MESSAGE, result);
            startActivity(intent);
        }
    }


    public class getPhotos {

        String PhotoID;
        String PhotoName;
        String PhotoPicture;
        String PhotoSource;

        // SET THE PHOTO ID
        public void setPhotoID(String PhotoID)  {
            this.PhotoID = PhotoID;
        }

        // GET THE PHOTO ID
        public String getPhotoID()  {
            return PhotoID;
        }

        // SET THE PHOTO NAME
        public void setPhotoName(String PhotoName)  {
            this.PhotoName = PhotoName;
        }

        // GET THE PHOTO NAME
        public String getPhotoName()    {
            return PhotoName;
        }

        // SET THE PHOTO PICTURE
        public void setPhotoPicture(String PhotoPicture)    {
            this.PhotoPicture = PhotoPicture;
        }

        // GET THE PHOTO PICTURE
        public String getPhotoPicture() {
            return PhotoPicture;
        }

        // SET THE PHOTO SOURCE
        public void setPhotoSource(String PhotoSource)  {
            this.PhotoSource = PhotoSource;
        }

        // GET THE PHOTO SOURCE
        public String getPhotoSource()  {
            return PhotoSource;
        }
    }

    public void showDirectory(View v){
        System.out.println("Directory Activity Invoked");
        new CallDirectoryAPI().execute(directoryUrl);
    }
}

