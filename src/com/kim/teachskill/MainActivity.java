package com.kim.teachskill;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.kim.teachskill.fragment.MyFragmentStatePagerAdapter;
import com.kim.teachskill.fragment.NavigationFragment;
import com.kim.teachskill.fragment.PersonFragment;
import com.kim.teachskill.fragment.VideoFragment;
import com.nineoldandroids.view.ViewPropertyAnimator;

public class MainActivity extends FragmentActivity {

	private ViewPager viewPager;
	
	private ArrayList<Fragment> fragments;

	private TextView tab_person;
	
	private TextView tab_navig;

	private TextView tab_video;

	private int line_width;

	private View line;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		tab_navig = (TextView) findViewById(R.id.tap_navig);
		tab_video= (TextView)findViewById(R.id.tap_video);
		tab_person= (TextView)findViewById(R.id.tap_Person);
		line= (View)findViewById(R.id.line);
	
		
		
		// 初始化TextView动画 点击的时候会有稍微的缩放
		ViewPropertyAnimator.animate(tab_video).scaleX(1.2f).setDuration(0);
	    ViewPropertyAnimator.animate(tab_video).scaleY(1.2f).setDuration(0);
	    tab_video.setTextColor(getResources().getColor(R.color.white));
	    fragments = new ArrayList<Fragment>();
	    fragments.add(new VideoFragment());
	    
	    fragments.add(new NavigationFragment());
	    fragments.add(new PersonFragment());
	
	    //获取屏幕的尺寸
	    line_width =getWindowManager().getDefaultDisplay().getWidth()
	    		/fragments.size();
	    line.getLayoutParams().width = line_width;
		line.requestLayout();
		
		//fragment装填到适配器中
		viewPager =(ViewPager) findViewById(R.id.viewPager);
		MyFragmentStatePagerAdapter my=
				new MyFragmentStatePagerAdapter(getSupportFragmentManager(),fragments);
		viewPager.setAdapter(my);

			viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				changeState(arg0);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				float tagerX = arg0 * line_width + arg2 / fragments.size();
				ViewPropertyAnimator.animate(line).translationX(tagerX)
						.setDuration(0);
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
	

			tab_video.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				viewPager.setCurrentItem(0);
				
			}
		});
			
			

		tab_person.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				viewPager.setCurrentItem(2);
			}
		});
		
		tab_navig.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				viewPager.setCurrentItem(1);
			}
		});
	}
	

	/* 根据传入的值来改变状态 */
	private void changeState(int arg0) {
		switch(arg0)
		{
		case 0:
			tab_video.setTextColor(getResources().getColor(R.color.white));
			tab_person.setTextColor(getResources().getColor(R.color.gray));
			tab_navig.setTextColor(getResources().getColor(R.color.gray));
			
			ViewPropertyAnimator.animate(tab_video).scaleX(1.2f).setDuration(200);
			ViewPropertyAnimator.animate(tab_video).scaleY(1.2f).setDuration(200);
			ViewPropertyAnimator.animate(tab_person).scaleX(1.0f)
					.setDuration(200);
			ViewPropertyAnimator.animate(tab_person).scaleY(1.0f)
					.setDuration(200);
			ViewPropertyAnimator.animate(tab_navig).scaleX(1.0f)
			.setDuration(200);
			ViewPropertyAnimator.animate(tab_navig).scaleY(1.0f)
			.setDuration(200);
			break;

		case 1:
			tab_navig.setTextColor(getResources().getColor(R.color.white));
			tab_person.setTextColor(getResources().getColor(R.color.gray));
			tab_video.setTextColor(getResources().getColor(R.color.gray));
			ViewPropertyAnimator.animate(tab_video).scaleX(1.0f).setDuration(200);
			ViewPropertyAnimator.animate(tab_video).scaleY(1.0f).setDuration(200);
			ViewPropertyAnimator.animate(tab_person).scaleX(1.0f)
					.setDuration(200);
			ViewPropertyAnimator.animate(tab_person).scaleY(1.0f)
					.setDuration(200);
			ViewPropertyAnimator.animate(tab_navig).scaleX(1.2f)
			.setDuration(200);
			ViewPropertyAnimator.animate(tab_navig).scaleY(1.2f)
			.setDuration(200);break;
			
		case 2:
			tab_person.setTextColor(getResources().getColor(R.color.white));
			tab_video.setTextColor(getResources().getColor(R.color.gray));
			tab_navig.setTextColor(getResources().getColor(R.color.gray));
			ViewPropertyAnimator.animate(tab_video).scaleX(1.0f).setDuration(200);
			ViewPropertyAnimator.animate(tab_video).scaleY(1.0f).setDuration(200);
			ViewPropertyAnimator.animate(tab_person).scaleX(1.2f)
					.setDuration(200);
			ViewPropertyAnimator.animate(tab_person).scaleY(1.2f)
					.setDuration(200);
			ViewPropertyAnimator.animate(tab_navig).scaleX(1.0f)
			.setDuration(200);
			ViewPropertyAnimator.animate(tab_navig).scaleY(1.0f)
			.setDuration(200);break;
			
		}

	
 }
}
