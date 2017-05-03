package com.kim.teachskill;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class SplashActivity extends Activity {

	private static final int TIME_DELAY=5000;
	private static final int GO_MAIN=100;
	private static final int GO_GUIDE=101;
	private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
	private long mBackPressed;
	
	//handler消息的跳转
	Handler mhandler=new Handler(){
		@Override
		public void handleMessage(Message msg){
			switch(msg.what)
			{
			case GO_MAIN:goMain();
				break;
			case GO_GUIDE:GoGuide();
				break;
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);		
		ReadytoStart();
		}
	//到向导
	protected void GoGuide() {
		Intent intent=new Intent(SplashActivity.this,GuideActivity.class);
		startActivity(intent);
		finish();
	}
	//到main函数
	protected void goMain() {
		Intent intent=new Intent(SplashActivity.this,MmainActivity.class);
		startActivity(intent);
		finish();
		
	}

	private void ReadytoStart() {
		//保存记录是否是第一次打开
			SharedPreferences pre=getSharedPreferences("mydata",MODE_PRIVATE);
			boolean isFirst=pre.getBoolean("isFirst",true);
			SharedPreferences.Editor edt=pre.edit();
			if(isFirst)
			{
				edt.putBoolean("isFirst", false);
				mhandler.sendEmptyMessageDelayed(GO_GUIDE, TIME_DELAY);
			}
			else
			{
				mhandler.sendEmptyMessageDelayed(GO_MAIN,TIME_DELAY);
			}
			edt.commit();
		}

	@Override
	public void onBackPressed()
	{
	    if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) 
	    { 
	        super.onBackPressed(); 
	        return;
	    }
	    else { Toast.makeText(getBaseContext(), "再按一次退出技能训练app", Toast.LENGTH_SHORT).show(); }

	    mBackPressed = System.currentTimeMillis();
	}
	
	
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
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
