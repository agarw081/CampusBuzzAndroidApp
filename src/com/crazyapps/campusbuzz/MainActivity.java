//Copyright Umang Agarwal

package com.crazyapps.campusbuzz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.crazyapps.campusbuzz.R;

public class MainActivity extends Activity 
{

	EditText t1,t2,t3,t4,t5,t6,t7,t8,t9;
	Exception e;
	Button b2;
	Spinner s1;
	String line = null;
	String result =  null;
	public void hideSoftKeyboard() {
	    if(getCurrentFocus()!=null) {
	        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
	        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
	    }
	}
	@SuppressLint("NewApi") 
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		hideSoftKeyboard();
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		s1 = (Spinner) findViewById (R.id.spinner1);
		b2 = (Button) findViewById(R.id.button2);
		
		b2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try{
				String university = ""+s1.getSelectedItem().toString();
				if(university.equals("University")) throw e;
				Intent ii = new Intent(MainActivity.this,Activity3.class);
				ii.putExtra("data",university);
				startActivity(ii);
				}catch(Exception e)
				{
					Toast.makeText(getApplicationContext(),"Please choose one university or open world",Toast.LENGTH_LONG).show();
				}
			}
				
		});
	
	
	}

	

}
