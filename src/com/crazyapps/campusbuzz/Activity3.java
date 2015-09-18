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
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.crazyapps.campusbuzz.R;

public class Activity3 extends Activity 
{

	String result;
	String[] data;
	TextView tv1;
	EditText e1;
	int flag;
	int len;
	Button b1,b2,b3;
	EditText sv;
	String[] all_posts;
	Exception e;
	ListView lv;
	HttpPost httpPost;
	ArrayAdapter<String> arrayAdapter;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity3);
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		final List<String> your_array_list = new ArrayList<String>();
		arrayAdapter = new ArrayAdapter<String>(this,
       		 android.R.layout.simple_list_item_1,
       		 your_array_list );
		
		result = getIntent().getExtras().getString("data");
		tv1 = (TextView) findViewById(R.id.text11);
		e1 = (EditText) findViewById(R.id.editText1);
		lv = (ListView) findViewById(R.id.listView1);
		sv = (EditText) findViewById (R.id.text1);
		

		final String university = result;
		
		tv1.setText(university);
		
		InputStream is = null;
		
		List <NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		nameValuePairs.add(new BasicNameValuePair("UUniv",university));

		
		try{
			HttpClient httpClient = new DefaultHttpClient();
			
			httpPost = new HttpPost("http://www.**********.com/get_posts.php/");
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		}
		catch(ClientProtocolException e)
		{
		Log.e("ClientProtocol","Log_tag");
		e.printStackTrace();
		Toast.makeText(getApplicationContext(),"No Internet",Toast.LENGTH_LONG).show();
		}catch (IOException e)
		{
			Log.e("Log_tag","IOException");
			e.printStackTrace();
			Toast.makeText(getApplicationContext(),"No Internet",Toast.LENGTH_LONG).show();
			}
		try
		{
		BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
		StringBuilder sb = new StringBuilder();
		sb.append(reader.readLine());
		String newresult = sb.toString();
		is.close();
		
		all_posts = newresult.split("   ");
		len = all_posts.length;
		if(len==1) your_array_list.add("No posts yet");
		else{
			
		for (int i =0 ;i<len-1;i++)
		{
			your_array_list.add("\n"+all_posts[i]);
		}
		}
		
         lv.setAdapter(arrayAdapter); 
		}catch (Exception e)
		{
			Toast.makeText(getApplicationContext(),"Error retieving posts",Toast.LENGTH_LONG).show();
		}	

		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			   @Override
			   public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
			   {
			    
				
			    String postsss = all_posts[position];				
			    String[] newpost = postsss.split("> ");
			    final String PPost = newpost[1];
			    Thread thread = new Thread(new Runnable(){
			    	  @Override
			    	  public void run(){
			    		 	
							
			    		  InputStream is = null;
							List <NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
							nameValuePairs.add(new BasicNameValuePair("PPost",PPost));
							
							
							try{
								HttpClient httpClient = new DefaultHttpClient();
								HttpPost httpPost = new HttpPost("http://www.peerstudy.byethost18.com/get_post_details.php/");
								httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
								HttpResponse response = httpClient.execute(httpPost);
								HttpEntity entity = response.getEntity();
								
							}
							catch(ClientProtocolException e)
							{
							Log.e("ClientProtocol","Log_tag");
							e.printStackTrace();
							Toast.makeText(getApplicationContext(),"No Internet",Toast.LENGTH_LONG).show();
							}catch (IOException e)
							{
								Log.e("Log_tag","IOException");
								e.printStackTrace();
								Toast.makeText(getApplicationContext(),"No Internet",Toast.LENGTH_LONG).show();
								}
							
			    	  }
			    	});
			    	thread.start();
			   	
			   } 
			});
		
		b1 = (Button) findViewById(R.id.button1);
		b1.setOnClickListener( new View.OnClickListener()
		{

			@Override
			public void onClick(View arg0) 
			{
				Intent act4 = new Intent(Activity3.this,Activity4.class);
				act4.putExtra("data",result);
				startActivity(act4);
				finish();
				
			}
		});

		b2 = (Button) findViewById(R.id.button2);
		b2.setOnClickListener( new View.OnClickListener()
		{

			@Override
			public void onClick(View arg0) 
			{
				
				Intent act6 = new Intent(Activity3.this,Activity5.class);
				act6.putExtra("data",result);
				startActivity(act6);
				finish();
				
			}
		});

		b3 = (Button) findViewById(R.id.button3);
		b3.setOnClickListener( new View.OnClickListener()
		{

			@Override
			public void onClick(View arg0) 
			{
				
				recreate();
				
				
			}
		});
		
		sv.addTextChangedListener(new TextWatcher() {
            
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                Activity3.this.arrayAdapter.getFilter().filter(cs);   
            }
             
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                    int arg3) {
                // TODO Auto-generated method stub
                 
            }
             
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}
        });
		
	}
}