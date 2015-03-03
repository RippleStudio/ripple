package top.focuson.ripple.android.adapter;

import java.util.List;

import top.focuson.ripple.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class CommentAdapter extends BaseObjectListAdapter {

	
	public CommentAdapter(Context context, List<Object> datas) {
		super(context, datas);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView==null){
			convertView=mInflater.inflate(R.layout.item_list_comment, null);
		}
		return convertView;
	}
	
	

}
