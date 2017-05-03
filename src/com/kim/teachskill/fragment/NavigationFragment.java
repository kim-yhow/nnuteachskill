package com.kim.teachskill.fragment;

import java.util.ArrayList;

import android.view.View.OnClickListener;

import com.kim.teachskill.CategoriesActivity;
import com.kim.teachskill.R;
import com.kim.teachskill.adapter.CateGridAdapter;
import com.kim.teachskill.model.CateItem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

public class NavigationFragment extends Fragment {
	private GridView cateGridView;
	private View rankView;
	private ArrayList<CateItem> areaList = new ArrayList<CateItem>();
	private int[] areaimages = new int[]{R.drawable.ic_cate_computer,R.drawable.ic_cate_chinese,
			R.drawable.ic_cate_math,R.drawable.ic_cate_english,R.drawable.ic_cate_polity,
			R.drawable.ic_cate_physical,R.drawable.ic_cate_history,R.drawable.ic_cate_chemistry,
			R.drawable.ic_cate_geography,R.drawable.ic_cate_biology};
	private String[] areatexts = new String[]{"计算机","汉语言","数学","英语","政治","物理"
			,"历史","化学","地理","生物"};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		for(int i=0;i<areaimages.length;i++){
			CateItem item = new CateItem();
			item.setImg(areaimages[i]);
			item.setText(areatexts[i]);
			areaList.add(item);
		}
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view=inflater.inflate(R.layout.fragment_navigation, container,false);
		cateGridView = (GridView)view.findViewById(R.id.cateGridView);
        cateGridView.setAdapter(new CateGridAdapter(getActivity(), areaList));
		return view;
	}
}
