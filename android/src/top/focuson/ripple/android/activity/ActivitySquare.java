package top.focuson.ripple.android.activity;

import java.util.ArrayList;
import java.util.List;




import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import top.focuson.ripple.BaseActivity;
import top.focuson.ripple.R;
import top.focuson.ripple.Ripple;
import top.focuson.ripple.android.adapter.SquareAdapter;
import top.focuson.ripple.feed.ActivityProfile;
import top.focuson.ripple.refreshlist.RefreshListView;
import top.focuson.ripple.refreshlist.RefreshListView.OnPullToRefreshListener;

public class ActivitySquare extends BaseActivity implements OnPullToRefreshListener,OnItemClickListener{
	LinearLayout mLayoutHeader;
	SquareAdapter mSquareAdapter;
	
	RefreshListView refreshListView;
	ImageView mIvProfile;
	TextView mTVTitle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_square);
		initViews();
		initEvents();
		init();
	}

	@Override
	protected void initViews() {

		refreshListView =(RefreshListView)this.findViewById(R.id.loadmore_list);
		mLayoutHeader =(LinearLayout)this.findViewById(R.id.square_header);
		mIvProfile =(ImageView)mLayoutHeader.findViewById(R.id.header_iv_edit);
		mIvProfile.setImageResource(R.drawable.people);
		mTVTitle=(TextView)mLayoutHeader.findViewById(R.id.header_tv_title);
		mTVTitle.setText("首页");
		
	}

	@Override
	protected void initEvents() {
		refreshListView.setOnPullToRefreshListener(this);
		mIvProfile.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			  Intent i = new Intent(ActivitySquare.this, ActivityProfile.class);
				
				
				ActivitySquare.this.startActivity(i);
				
			}
		
		});
		refreshListView.setmOnItemClickListener(this);

	}
	
	public void init(){
		mSquareAdapter =new SquareAdapter(this);
		refreshListView.setAdapter(mSquareAdapter);
		refreshListView.initData();
		
	}
	public List<Object> objects(int currentpage){
		ArrayList<Object> arrayList=new ArrayList<Object>();
		for(int i=0;i<20;i++){
			Object msg =new Object();
			arrayList.add(msg);
		}
		
		return arrayList;
	}

	@Override
	public List<Object> doInBackGround(int CurrentPage) {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return objects(CurrentPage);
	}

	@Override
	public void resolve(List<Object> list) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onHeadRefresh() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean filter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
		Intent i = new Intent(ActivitySquare.this, ActivityProfile.class);
		
		
		ActivitySquare.this.startActivity(i);
		
	}
}
