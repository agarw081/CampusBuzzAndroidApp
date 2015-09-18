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
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.crazyapps.campusbuzz.R;

public class Activity4 extends Activity 
{

	String result;
	String data;
	TextView tv1,tv2,tv3,tv4,tv5,tv6;
	EditText e1;
	Button b1;
	Exception e;
	ListView lv;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity4);
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		result = getIntent().getExtras().getString("data");
		e1 = (EditText) findViewById(R.id.editText1);
		final String university = result;
		b1 = (Button) findViewById(R.id.button1);
		b1.setOnClickListener( new View.OnClickListener()
		{

			@Override
			public void onClick(View arg0) 
			{
				InputStream is = null;
				// TODO Auto-generated method stub
				
				String post = e1.getText().toString();
				
				
				
				try{
				if (post.equals("")) throw e ;
				String[] string_w_nl = post.split("'|\\n");
				post = "";
				int len = string_w_nl.length;
				for (int i=0;i<len;i++)
				{
					post = post+string_w_nl[i]+" ";
				}
				List <NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
				nameValuePairs.add(new BasicNameValuePair("Univ",university));
				nameValuePairs.add(new BasicNameValuePair("Post",post));
				
					HttpClient httpClient = new DefaultHttpClient();
					HttpPost httpPost = new HttpPost("http://www.***********.com/insert_post.php/");
					httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					HttpResponse response = httpClient.execute(httpPost);
					HttpEntity entity = response.getEntity();
					is = entity.getContent();
				}
				catch (IOException e)
				{
					Log.e("Log_tag","IOException");
					e.printStackTrace();
					Toast.makeText(getApplicationContext(),"IO",Toast.LENGTH_LONG).show();
					}
				catch(Exception e)
				{
				Log.e("ClientProtocol","Log_tag");
				e.printStackTrace();
				Toast.makeText(getApplicationContext(),"Post Empty",Toast.LENGTH_LONG).show();
				}
				try
				{
				BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
				StringBuilder sb = new StringBuilder();
				sb.append(reader.readLine());
				String newresult = sb.toString();
				is.close();
				Toast.makeText(getApplicationContext(),"Posted",Toast.LENGTH_LONG).show();
				finish();
				Intent act3 = new Intent(Activity4.this,Activity3.class);
				act3.putExtra("data",result);
				startActivity(act3);
				}catch (Exception e)
				{
					
				}	
			}	
		});
	}
}
	
		