package com.example.cmpe295b;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import com.example.cmpe295b.adapters.NavDrawerListAdapter;
import com.example.cmpe295b.beans.NavDrawerItem;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


public class MainActivity extends ActionBarActivity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    // nav drawer title
    private CharSequence mDrawerTitle;

    // used to store app title
    private CharSequence mTitle;

    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;

    private static String profileUrl = null;
    public final static String EXTRA_MESSAGE = "com.example.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String linchpinip =  getString(R.string.ipaddress);
        profileUrl = linchpinip + "employee/?emp_id=1";
        setContentView(R.layout.activity_main);
       }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest¬.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void showProfile(View view){
        new CallProfileAPI().execute(profileUrl);
    }

    private class CallProfileAPI extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params){
            String fullApi_url = params[0];
            HttpClient httpClientObject = new DefaultHttpClient();
            HttpGet httpGetCall = new HttpGet(fullApi_url);
            String jsonResult = null;
            try {
                HttpEntity httpEntityObject = httpClientObject.execute(httpGetCall).getEntity();
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
            Intent intent = new Intent(getApplicationContext(), Profile.class);
            intent.putExtra(EXTRA_MESSAGE, result);
            startActivity(intent);
        }
    }
}
