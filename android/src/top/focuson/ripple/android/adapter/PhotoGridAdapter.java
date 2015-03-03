package top.focuson.ripple.android.adapter;

import java.util.List;

import top.focuson.ripple.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class PhotoGridAdapter extends BaseObjectListAdapter {

	public PhotoGridAdapter(Context context, List<Object> datas) {
		super(context, datas);

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView==null){
			holder =new ViewHolder();
			convertView =mInflater.inflate(R.layout.item_grid_feed,null);
			convertView.setTag(holder);
		}
		else{
			holder=(ViewHolder)convertView.getTag();
		}

		return convertView;
	}

	class ViewHolder {

	}

}
