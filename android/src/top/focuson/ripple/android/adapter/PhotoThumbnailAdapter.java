package top.focuson.ripple.android.adapter;

import java.util.ArrayList;
import java.util.List;

import top.focuson.ripple.R;
import top.focuson.ripple.feed.ActivityComment;
import top.focuson.ripple.feed.ActivityProfile;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

public class PhotoThumbnailAdapter extends BaseObjectListAdapter {
    private Context mContext;
	public PhotoThumbnailAdapter(Context context, List<Object> datas) {
		super(context, datas);
		mContext =context;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if(convertView==null){
			viewHolder =new ViewHolder();
			convertView = mInflater.inflate(R.layout.item_list_profile,null);
			viewHolder.tv_time=(TextView)convertView.findViewById(R.id.item_list_profile_time);
			viewHolder.gv_thumbs=(GridView)convertView.findViewById(R.id.item_list_profile_gv);
			convertView.setTag(viewHolder);
			List<Object>thumbs=new ArrayList<Object>();
			for(int i=0;i<4;i++){
				thumbs.add("people");
			}
			
			PhotoGridAdapter gridAD=new PhotoGridAdapter(mContext, thumbs);
			
			viewHolder.gv_thumbs.setAdapter(gridAD);
			
			viewHolder.gv_thumbs.setOnItemClickListener(new GridView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int
                        position, long id) {
                	Intent i = new Intent(mContext, ActivityComment.class);
            		
                	mContext.startActivity(i);
                }
			});
		}
		else{
			viewHolder=(ViewHolder)convertView.getTag();
		}
		
	
		
		return convertView;
	}
	
	class ViewHolder{
		
		TextView tv_time;
		GridView gv_thumbs;
		
	}


}
