package top.focuson.ripple.refreshlist;

import java.util.List;





import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.Toast;

public class PullToRefreshTask {

	private int currentPage = 0; // 加载当前页面

	private final int CAPACITY = 20; // 每页最多加载20个
	private boolean mDirty=false;
	private PullToRefreshListView mRefreshListView;
	private ICallBackAsyncTaskLister callBackAsyncTaskLister;
	private PullToRefreshView pullToRefreshView;// 显示正在刷新的控件
	private Context mContext;
	private AsyncLoader mAsyncLoader =new AsyncLoader();
	
	public PullToRefreshTask(Context context,PullToRefreshView pullToRefreshView,AbsListView listView){
		this.pullToRefreshView = pullToRefreshView;
		this.mContext = context;
		if(null != listView)
		{
			if(listView instanceof ListView)
			{
				try {
					mRefreshListView = (PullToRefreshListView) listView;
				} catch (Exception e) {
				}
			}
		}
	}
	
	public class AsyncLoader extends AsyncTask<Void, Void, List<Object>> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			
			//重新启动一个加载
			mDirty =false;
			
			// 准备加载的页码
			currentPage++;
			callBackAsyncTaskLister.onPreExecute(currentPage);
			
			
			//如果是第一页，需要将底部的“下拉更新”按钮隐藏掉
			if(null != mRefreshListView)
			{
				mRefreshListView.fillLoadMore(currentPage);
				mRefreshListView.setFooterDividersEnabled(false);
			}
		}

		@Override
		protected List<Object> doInBackground(Void... arg0) {
			List<Object> objects = callBackAsyncTaskLister
					.doInBackground(currentPage);
			//如果数据在加载的过程中这个线程被取消了
			if(mDirty){
				return null;
			}
			else {
				return objects;
			}
		}

		@Override
		protected void onPostExecute(List<Object> result) {
			super.onPostExecute(result);

			int size = 0;
			if (null != result) {
				size = result.size();
			}
			// 把底部正在刷新的状态去掉
			if (pullToRefreshView != null)
				pullToRefreshView.onFooterRefreshComplete(size);
			// 获取到了数据
			if (size > 0) {
				// 表示服务器可能还有新的数据
				if (size >= CAPACITY) {
					showLoadMoreBtn();
					//打开下拉刷新功能
					if(null!=pullToRefreshView)
						pullToRefreshView.setEnablePullLoadMoreDataStatus(true);
				}
				// 表示服务器已经没有新的数据了
				else {

					hideLoadMoreBtn();
					//关闭下拉刷新功能
					if(null!=pullToRefreshView)
						pullToRefreshView.setEnablePullLoadMoreDataStatus(false);
				}
				if(mRefreshListView!=null){
					mRefreshListView.setVisibility(View.VISIBLE);
				}

			}
			// 没有获取到数据
			else {
				//当获取数据为空的时候也禁用上拉加载更多
				if(null!=pullToRefreshView) pullToRefreshView.setEnablePullLoadMoreDataStatus(false);
				Toast.makeText(mContext, "服务器正在维修，请稍后下载", 3).show();
				hideLoadMoreBtn();
			}
			//当page==1表示当前是下拉状态，所以需要把头部隐藏回去
			if(currentPage==1){
				pullToRefreshView.onHeaderRefreshComplete();
			}
			
			callBackAsyncTaskLister.onPostExecute(result,currentPage);
		}
		@Override
		protected void onCancelled() {
			super.onCancelled();
			mDirty=true;
		}
		
	}

	// 显示“下拉加载更多的按钮”
	private void showLoadMoreBtn() {
		if(mRefreshListView!=null){
			mRefreshListView.showLoadMore();
		}
	}
	// 隐藏“下拉加载更多的按钮”
		private void hideLoadMoreBtn() {
			if(mRefreshListView!=null){
				mRefreshListView.hideLoadMore();
			}
		}
		
	//第一次进入界面初始化函数，也就是加载第一页的数据	
	public void init(){
		//先判断加载条件是否成立
		if(callBackAsyncTaskLister.filter()){
			currentPage = 0;
			mAsyncLoader.onCancelled();
			mAsyncLoader =new AsyncLoader();
			mAsyncLoader.execute();
		}
		//如果不成立则显示提示信息
		else{
			echoError();
		}
	}
	
	public void loadMore(){
		if(callBackAsyncTaskLister.filter()){
			new AsyncLoader().execute();
		}
		else{
			echoError();
		}
	}
	
	public void echoError(){
		Toast.makeText(mContext, "服务器出错", 3).show();
		if(null != pullToRefreshView)
		{
			pullToRefreshView.onHeaderRefreshComplete();
		}
	}

	public interface ICallBackAsyncTaskLister {

		/**
		 * 预准备的回调方法用于初始化基本信息
		 ***/
		public void onPreExecute(int currentPage);

		/**
		 * 执行网络状态并返回
		 * 
		 * @param ishasNetwork
		 * @param currentPage
		 * @return
		 */

		public List<Object> doInBackground(int currentPage);

		/**
		 * 返回数据并回调到界面
		 * 
		 * @param listDatas
		 * @param currentPage
		 */
		public void onPostExecute(List<Object> listDatas, int currentPage);
		
		/****
		 * 判断是否可以开始初始化的条件，如果是通过网络反问，那么这个接口要实现判断是否有网络，如果是数据库反问那么这个接口要判断数据库是否存在
		 * 
		 */
		
		public boolean filter();
	}
	public void setCallBackAsyncTaskLister(
			ICallBackAsyncTaskLister callBackAsyncTaskLister) {
		this.callBackAsyncTaskLister = callBackAsyncTaskLister;
	}
}
