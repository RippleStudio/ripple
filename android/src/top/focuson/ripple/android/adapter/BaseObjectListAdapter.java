package top.focuson.ripple.android.adapter;

import java.util.ArrayList;
import java.util.List;


import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;


public class BaseObjectListAdapter extends BaseAdapter {


	protected Context mContext;
	protected LayoutInflater mInflater;
	protected List<Object> mDatas = new ArrayList<Object>();

	public BaseObjectListAdapter( Context context,
			List<Object> datas) {
		
		mContext = context;
		mInflater = LayoutInflater.from(context);
		if (datas != null) {
			mDatas = datas;
		}
	}
	public BaseObjectListAdapter( Context context
			) {
		
		mContext = context;
		mInflater = LayoutInflater.from(context);
		
	}

	@Override
	public int getCount() {
		return mDatas.size();
	}

	@Override
	public Object getItem(int position) {
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return null;
	}

	public List<Object> getDatas() {
		return mDatas;
	}
	
	public void addDatas(List<Object> newDatas){
		
		if(newDatas!=null)
			this.mDatas.addAll(newDatas);
		notifyDataSetChanged();
		
	}
	
	public void clear(){
		this.mDatas.clear();
		notifyDataSetChanged();
	}

	
}
