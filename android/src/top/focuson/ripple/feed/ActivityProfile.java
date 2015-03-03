package top.focuson.ripple.feed;



import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import top.focuson.ripple.BaseActivity;
import top.focuson.ripple.R;
import top.focuson.ripple.Ripple;
import top.focuson.ripple.android.adapter.ImageBrowserAdapter;
import top.focuson.ripple.android.adapter.PhotoThumbnailAdapter;

public class ActivityProfile extends BaseActivity implements
		OnItemClickListener {

	private ListView mLvList;
	private View mHeaderView;
	private PhotoThumbnailAdapter mPhotoThumbnailAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_profile);
		 initViews() ;
		 initEvents() ;
		 init();
		 
		 
	
	}
	
	@Override
	protected void initViews() {
		mLvList = (ListView) findViewById(R.id.profile_listview);
//		mHeaderView = LayoutInflater.from(ActivityProfile.this).inflate(
//				R.layout.profile_header, null);
	}

	@Override
	protected void initEvents() {
		mLvList.setOnItemClickListener(this);

	}
	
	private void init(){
		
//	/	mLvList.addHeaderView(mHeaderView);
		
		List<Object> photos =new ArrayList<Object>();
		for(int i=0;i<10;i++){
			Object obj=new Object();
			photos.add(obj);
		}
		
		mPhotoThumbnailAdapter =new PhotoThumbnailAdapter(ActivityProfile.this ,photos);
		mLvList.setAdapter(mPhotoThumbnailAdapter);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		

	}

}
