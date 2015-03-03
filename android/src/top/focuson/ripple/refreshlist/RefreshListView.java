package top.focuson.ripple.refreshlist;

import java.util.List;

import top.focuson.ripple.R;
import top.focuson.ripple.android.adapter.BaseObjectListAdapter;
import top.focuson.ripple.refreshlist.PullToRefreshListView.IClickloadMoreBtnCallBack;
import top.focuson.ripple.refreshlist.PullToRefreshTask.ICallBackAsyncTaskLister;
import top.focuson.ripple.refreshlist.PullToRefreshView.OnFooterRefreshListener;
import top.focuson.ripple.refreshlist.PullToRefreshView.OnHeaderRefreshListener;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

public class RefreshListView extends LinearLayout implements
		ICallBackAsyncTaskLister, OnHeaderRefreshListener,
		IClickloadMoreBtnCallBack, OnFooterRefreshListener {
	OnPullToRefreshListener mIPullToRefreshListenerImpl=null;
	PullToRefreshView pullToRefreshView = null;
	PullToRefreshListLayout loadMorePullToreshView=null;
	PullToRefreshListView loadMoreListView=null;
	PullToRefreshTask  refreshTask=null;
	OnItemClickListener mOnItemClickListener;
	Context mContext = null;
	BaseObjectListAdapter mBaseAdapter;

	public RefreshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initWidget(context);
	}

	
	public void initWidget(Context context) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.refresh_list_layout, null);
		this.addView(layout);
		loadMorePullToreshView = (PullToRefreshListLayout) findViewById(R.id.lmpView);
		pullToRefreshView = loadMorePullToreshView.getPullToRefreshView();
		pullToRefreshView.setOnHeaderRefreshListener(this);
		pullToRefreshView.setOnFooterRefreshListener(this);
		loadMoreListView = loadMorePullToreshView.getLoadMoreListView();
		loadMoreListView.setClickloadMoreBtnCallBack(this);
		loadMoreListView.hideLoadMore();
		refreshTask =new PullToRefreshTask(context, pullToRefreshView, loadMoreListView);
		refreshTask.setCallBackAsyncTaskLister(this);	

	}

	
	public void initData(){
		pullToRefreshView.headerRefreshing();
	}
	
	public void setBaseAdapter(BaseObjectListAdapter adapter){
		mBaseAdapter =adapter;
	}
	
	@Override
	public void loadMore() {
		refreshTask.loadMore();

	}
	
	
	

	@Override
	public void onPreExecute(int currentPage) {
		
	}

	@Override
	public List<Object> doInBackground(int currentPage) {
		
		return mIPullToRefreshListenerImpl.doInBackGround(currentPage);
	}

	@Override
	public void onPostExecute(List<Object> data, int currentPage) {
		//mIPullToRefreshListenerImpl.resolve(data);
		//mBaseAdapter.clear();
		mBaseAdapter.addDatas(data);
	}

	
	
	/**
	 * 网络判断接口
	 * 
	 * **/
	@Override
	public boolean filter() {
		// TODO Auto-generated method stub
		return mIPullToRefreshListenerImpl.filter();
	}
	
	
	/**
	 * 上拉刷新的回调函数
	 * 
	 * **/
	@Override
	public void onFooterRefresh(PullToRefreshView view) {
		refreshTask.loadMore();
	}
	/**
	 * 下拉刷新的回调函数
	 * 
	 * **/
	@Override
	public void onHeaderRefresh(PullToRefreshView view) {
		//mIPullToRefreshListenerImpl.onHeadRefresh();
		mBaseAdapter.clear();
		refreshTask.init();

	}
	public static interface OnPullToRefreshListener{
		
		//后台执行函数，如连接网络等
		public List<Object> doInBackGround(int CurrentPage);
		//UI线程执行函数，如果更新Listview
		public void resolve(List<Object> list);
		//初始化函数
		public void onHeadRefresh();
		//开始下载条件
		boolean filter();
		
	}
	
	public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener){
		if(mOnItemClickListener!=null){
			loadMoreListView.setOnItemClickListener(mOnItemClickListener);
		}
	}
	
	public void setOnPullToRefreshListener(OnPullToRefreshListener listener){
		this.mIPullToRefreshListenerImpl=listener;
	}
	
	public PullToRefreshListView getListView(){
		return this.loadMoreListView;
	}
	
	public void setAdapter(BaseObjectListAdapter adapter){
		mBaseAdapter =adapter;
		loadMoreListView.setAdapter(adapter);
	}

}
