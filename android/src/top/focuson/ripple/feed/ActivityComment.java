package top.focuson.ripple.feed;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import top.focuson.ripple.BaseActivity;
import top.focuson.ripple.R;
import top.focuson.ripple.android.adapter.CommentAdapter;

public class ActivityComment extends BaseActivity {

	private CommentAdapter mCommentAdapter;
	private ListView mListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_comment);
		 initViews() ;
		 initEvents() ;
		
		 init();
		 
	
	}
	
	@Override
	protected void initViews() {
		mListView =(ListView)this.findViewById(R.id.comment_list);
		
		LinearLayout mProfileHeader =(LinearLayout)this.findViewById(R.id.comment_profile_header);
		
		RelativeLayout rl=(RelativeLayout)mProfileHeader.findViewById(R.id.profile_devider_bar);
		rl.setVisibility(View.GONE);
		

	}

	@Override
	protected void initEvents() {
		// TODO Auto-generated method stub

	}
	
	private void init(){
		List<Object>objs=new ArrayList<Object>();
		for(int i=0;i<10;i++){
			objs.add(new Object());
		}
		mCommentAdapter =new CommentAdapter(mContext, objs);
		mListView.setAdapter(mCommentAdapter);
	}

}
