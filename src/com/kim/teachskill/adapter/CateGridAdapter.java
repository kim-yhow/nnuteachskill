package com.kim.teachskill.adapter;

import java.util.List;

import com.kim.teachskill.R;
import com.kim.teachskill.model.CateItem;

import android.view.View.OnClickListener;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
//适配器
public class CateGridAdapter extends BaseAdapter {
	private Context mContext;
	private List<CateItem> mList;
	public CateGridAdapter(Context mContext,List<CateItem> mList){
		this.mContext = mContext;
		this.mList = mList;	
	}
	

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public CateItem getItem(int position) {
		return mList == null ? null : mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		CateItem cateItem = getItem(position);
		convertView = LayoutInflater.from(mContext).inflate(R.layout.item_cate, null);
		ImageView cateImageView = (ImageView) convertView.findViewById(R.id.cateImageView);
		TextView cateTextView = (TextView) convertView.findViewById(R.id.cateTextView);
		cateImageView.setImageResource(cateItem.getImg());
		cateTextView.setText(cateItem.getText());
		
		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ������ת�߼�
				
			}
		});
		
		
		return convertView;
	}
}
