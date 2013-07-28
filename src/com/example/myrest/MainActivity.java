package com.example.myrest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private Button butGet;
	private Button butClear;
	private Button butPost;
	private Button butPut;
	private Button butDelete;
	private TextView txtView;
	private EditText editTextShow;
	private EditText editTextInput;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.txtView = (TextView)findViewById(R.id.textView1);
		this.editTextShow = (EditText)findViewById(R.id.editTextShow);
		this.editTextInput = (EditText)findViewById(R.id.editTextInput);
		
		this.butGet = (Button)findViewById(R.id.butGet);		
		this.butGet.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//����һ��http�ͻ���  
				HttpClient client=new DefaultHttpClient();  
				//����һ��GET����  
				HttpGet httpGet=new HttpGet("http://192.168.1.101:8088/user/:uid=" + editTextInput.getText().toString());  
				//��������������󲢻�ȡ���������صĽ��  
				HttpResponse response = null;
				try {
					response = client.execute(httpGet);
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
					Log.e("����", e.toString());
				}  
				
				//���صĽ�����ܷŵ�InputStream��http Header�еȡ�  
				InputStream inputStream = null;
				try {
					inputStream=response.getEntity().getContent();
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}  
				Header[] headers=response.getAllHeaders();  
				
				BufferedReader in;
				String str = null;
				try {
					in = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
					str = in.readLine();
					
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				editTextShow.setText(str);
				
				Log.i("��������Ϣ", str);
				Log.i("��������Ϣ", headers.toString());
				
				
			}
		});
		
		this.butClear = (Button)findViewById(R.id.butClear);
		this.butClear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				editTextShow.setText("");
			}
		});
		
		this.butPost = (Button)findViewById(R.id.butPost);
		this.butPost.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//����һ��http�ͻ���  
				HttpClient client=new DefaultHttpClient();  
				//����һ��POST����  
				HttpPost httpPost=new HttpPost("http://192.168.1.101:8088/user/:uid=22");  
				//��װ���ݷŵ�HttpEntity�з��͵�������  
				final List dataList = new ArrayList();  
				dataList.add(new BasicNameValuePair("productName", "cat"));  
				dataList.add(new BasicNameValuePair("price", "14.87"));  
				HttpEntity entity = null;
				try {
					entity = new UrlEncodedFormEntity(dataList, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}  
				httpPost.setEntity(entity);  
				
				Log.i("Post", httpPost.getURI().toString());
				Log.i("Post", entity.getContentType().toString());
				try {
					Log.i("Post", EntityUtils.toString(entity));
				} catch (ParseException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				HttpResponse response = null;
				//�����������POST���󲢻�ȡ���������صĽ�������������ӳɹ�������ƷID������ʧ�ܵ���Ϣ  
				try {
					response=client.execute(httpPost);
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} 
				
				//���صĽ�����ܷŵ�InputStream��http Header�еȡ�  
				InputStream inputStream = null;
				try {
					inputStream=response.getEntity().getContent();
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}  
				Header[] headers=response.getAllHeaders();  
				
				BufferedReader in;
				String str = null;
				try {
					in = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
					str = in.readLine();
					
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				editTextShow.setText(str);
				
				Log.i("��������Ϣ", str);
				Log.i("��������Ϣ", headers.toString());
				
			}
		});
		
		
		this.butPut = (Button)findViewById(R.id.butPut);
		this.butPut.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//����һ��http�ͻ���  
				HttpClient client=new DefaultHttpClient();  
				//����һ��PUT����  
				HttpPut httpPut=new HttpPut("http://192.168.1.101:8088/user/");  
				//��װ���ݷŵ�HttpEntity�з��͵�������  
				final List dataList = new ArrayList();  
				dataList.add(new BasicNameValuePair("price", "11.99"));  
				HttpEntity entity = null;
				try {
					entity = new UrlEncodedFormEntity(dataList, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}  
				httpPut.setEntity(entity);  
				
				HttpResponse response = null;
				//�����������PUT���󲢻�ȡ���������صĽ�����������޸ĳɹ�������ʧ�ܵ���Ϣ  
				try {
					response=client.execute(httpPut);
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}  
				
				
				//���صĽ�����ܷŵ�InputStream��http Header�еȡ�  
				InputStream inputStream = null;
				try {
					inputStream=response.getEntity().getContent();
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}  
				Header[] headers=response.getAllHeaders();  
				
				BufferedReader in;
				String str = null;
				try {
					in = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
					str = in.readLine();
					
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				editTextShow.setText(str);
				
				Log.i("��������Ϣ", str);
				Log.i("��������Ϣ", headers.toString());
				
			}
		});
		
		
		this.butDelete = (Button)findViewById(R.id.butDelete);
		this.butDelete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//����һ��http�ͻ���  
				HttpClient client=new DefaultHttpClient();  
				//����һ��DELETE����  
				HttpDelete httpDelete=new HttpDelete("http://192.168.1.101:8088/user/:uid=22");  
				
				HttpResponse response = null;
				//�����������DELETE���󲢻�ȡ���������صĽ����������ɾ���ɹ�������ʧ�ܵ���Ϣ  
				try {
					response=client.execute(httpDelete);
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}  
				
				
				//���صĽ�����ܷŵ�InputStream��http Header�еȡ�  
				InputStream inputStream = null;
				try {
					inputStream=response.getEntity().getContent();
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}  
				Header[] headers=response.getAllHeaders();  
				
				BufferedReader in;
				String str = null;
				try {
					in = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
					str = in.readLine();
					
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				editTextShow.setText(str);
				
				Log.i("��������Ϣ", str);
				Log.i("��������Ϣ", headers.toString());
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
