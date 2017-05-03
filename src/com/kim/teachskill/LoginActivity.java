package com.kim.teachskill;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private Button loginBtn;  
    private Button registerBtn;  
    private EditText inputUsername;  
    private EditText inputPassword;  
    private ProgressDialog mDialog;  
    private Message message = new Message();
    private static boolean IS_EMPTY=false;
    private static final int SUCCESS = 0;
    private static final int USER_FAIL = 1;
    private static final int PASSD_FAIL = 2;
    private static final int EMPTY_FAIL = 3;
    private static final int NO_USER =4;
    private static final int CANNOT_CONNECT =5;
    
   private String Address="https://www.nnuteachskill.com/app_login.php";
    //php�ļ��ڷ�������λ��
//	private String Address="http://192.168.56.101/app_login.php";
    private Handler handler = new Handler()  
    {  
        public void handleMessage(Message msg)  
        {      
//        	 String s=String.valueOf(msg.what);
//        	 
//             Log.v("HelloWorldActivity",s);
            switch(msg.what)  
            {  
            case SUCCESS:  
                mDialog.cancel();  
                Toast.makeText(getApplicationContext(), "��¼�ɹ���", Toast.LENGTH_SHORT).show();  
                /*Intent intent = new Intent();  
        			intent.setClass(LoginActivity.this, MainActivity.class);  
                startActivity(intent);  */
                finish();  
                break;  
            case USER_FAIL:  
                mDialog.cancel();  
                Toast.makeText(getApplicationContext(), "�û���������", Toast.LENGTH_SHORT).show();  
                finish();  
                break;  
            case PASSD_FAIL:  
                mDialog.cancel();  
                Toast.makeText(getApplicationContext(), "�������", Toast.LENGTH_SHORT).show();  
                finish();  
                break;

            case EMPTY_FAIL:
            	 mDialog.cancel();  
            	 Toast.makeText(getApplicationContext(), "�û����������벻��Ϊ��", Toast.LENGTH_SHORT).show(); 
            	 finish();              	
                break;  
                
            case NO_USER:
            	 mDialog.cancel();  
            	 Toast.makeText(getApplicationContext(), "û�и��û���", Toast.LENGTH_SHORT).show(); 
            	 finish();  
            	 break;
            	 
            	case CANNOT_CONNECT:
               	 mDialog.cancel();  
               	 Toast.makeText(getApplicationContext(), "���ӳ�ʱ", Toast.LENGTH_SHORT).show(); 
               	 finish();  
               	 break;
            default : return;
            }  
              
        }  
    };  
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		loginBtn=(Button)findViewById(R.id.buttonlogin);
		registerBtn=(Button)findViewById(R.id.buttonregister);
		
		 inputUsername = (EditText)findViewById(R.id.username);  
	     inputPassword = (EditText)findViewById(R.id.password); 
	     
	   //Handler ��Ҫ�������̷߳��͵����ݣ� ���ô�����������̸߳���UI��
	     //��¼ 
	       loginBtn.setOnClickListener(new Button.OnClickListener()  
	       {

			@Override
			public void onClick(View v) {
				mDialog = new ProgressDialog(LoginActivity.this);  
                mDialog.setTitle("��½");  
                mDialog.setMessage("���ڵ�½�����������Ժ�...");  
                mDialog.show();  
                postRequestWithHttpClient();
	              
			}
	    	   
	       });

	     //ע��
	       registerBtn.setOnClickListener(new Button.OnClickListener()  
	        {  
	  
	            @Override  
	            public void onClick(View arg0) {  
	                Intent intent = new Intent();  
	                intent.setClass(LoginActivity.this, RegisterActivity.class);  
	                startActivity(intent);  
	            }  
	              
	        });  
	   }
	

	protected void postRequestWithHttpClient() {
		new Thread(new Runnable(){
			@Override	
			public void run(){
			    String Resultms = null;
				String lines;
				StringBuffer response = new StringBuffer("");
				HttpURLConnection connection=null;
				try{
					URL url = new URL(Address);
					 String username = inputUsername.getText().toString(); 					
				     String password = inputPassword.getText().toString();		
				     //���ӷ�����
					connection=(HttpURLConnection) url.openConnection();					
					//�ϴ�����������
				connection.setRequestMethod("POST");				
				connection.setConnectTimeout(8000);
				connection.setDoInput(true);//��������		
				connection.setDoOutput(true);//�������				
				connection.setUseCaches(false);
   				connection.setRequestProperty("Accept-Charset", "UTF-8");
				connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
				connection.connect();					
				DataOutputStream outStream = new DataOutputStream(connection.getOutputStream());  
				outStream.writeBytes("username="+ username+"&password="+password);
				outStream.flush();  
		        outStream.close();  		               
		        //��ȡ��Ӧ
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream())); 
                //������
                while ((lines = reader.readLine()) != null) {
                      lines = new String(lines.getBytes(), "utf-8");
                      response.append(lines);
                   }
                Log.v("HelloWorldActivity", response.toString());
                reader.close();        
                //ȥ��response�Ŀհ��ַ�
                Resultms=response.toString().trim();
                // �����������صĽ����ŵ�Message��
                Log.v("HelloWorldActivity",Resultms);                      
                if(Resultms.equals("error"))  
                  {  
             		message.what = PASSD_FAIL;  
                    handler.sendMessage(message); 
                   }             
                if(Resultms.equals("success"))  
                  {  
           	 		message.what = SUCCESS; 
           	 		handler.sendMessage(message); 
                  }
          //     Log.v("HelloWorldActivity", response.toString());             
                 if(Resultms.equals("empty"))  
                  {  
               		message.what = EMPTY_FAIL;  
                    handler.sendMessage(message);                           
                  }
                 //�������û�
             //   Log.v("HelloWorldActivity",String.valueOf(Resultms.equals("nouser")));                
                if(Resultms.equals("nouser"))  
             	{  
                    message.what = NO_USER;  
                    handler.sendMessage(message);                     
          		}                   	
				} 
				catch (Exception e) {				
				e.printStackTrace();
				Log.d("HelloWorldActivity",e.toString());
					message.what=CANNOT_CONNECT;
					handler.sendMessage(message); 
				}
			finally {
				if (connection != null) {
					connection.disconnect();
				}
			}
		}
		}).start();
		
}
		


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}