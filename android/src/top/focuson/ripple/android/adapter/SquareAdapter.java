package top.focuson.ripple.android.adapter;

import java.util.ArrayList;
import java.util.List;

import top.focuson.ripple.R;



import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class SquareAdapter extends BaseObjectListAdapter {

	
	
	
	public SquareAdapter(Context context, List<Object> datas) {
		super(context, datas);
		
	}
	public SquareAdapter(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView==null){
			holder =new ViewHolder();
			convertView = mInflater.inflate(R.layout.item_list_square,null);
			List<Object> followers =new ArrayList<Object>();
			for(int i=0;i<5;i++){
				followers.add(new Object());
			}
			holder.gvFollowers=(GridView)convertView.findViewById(R.id.item_square_gv_follower);
			holder.gvFollowers.setAdapter(new FollowerAvatarAdapter(mContext, followers));
			convertView.setTag(holder);
		}
		else{
			holder=(ViewHolder)convertView.getTag();
		}
		return convertView;
	}
	
	class ViewHolder{
		GridView gvFollowers;
	}

}
