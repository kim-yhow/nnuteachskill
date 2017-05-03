package com.kim.teachskill.fragment;

import android.view.View.OnClickListener;

import com.kim.teachskill.LoginActivity;
import com.kim.teachskill.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;

public class PersonFragment extends Fragment {
	
	private Button bt1;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view=inflater.inflate(R.layout.fragment_personality, container,false);
		bt1=(Button)view.findViewById(R.id.buttonTourist);
		
		bt1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent
						(getActivity(),LoginActivity.class);
				startActivity(intent);// TODO Auto-generated method stub
				
			}
		});
		
		
		return view;
	}
	
	@Override
	
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
	}
}