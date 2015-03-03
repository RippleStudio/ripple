package top.focuson.ripple;

import top.focuson.ripple.android.activity.ActivitySquare;
import top.focuson.ripple.android.activity.ImageBrowserActivity;
import top.focuson.ripple.android.util.JsonResolveUtil;
import top.focuson.ripple.entry.ProfileEntry;
import top.focuson.ripple.feed.ActivityComment;
import top.focuson.ripple.feed.ActivityProfile;
import top.focuson.ripple.feed.Profile;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

public class Ripple extends BaseActivity {

	private ProfileEntry mProfile;
	private String mUid = "people_001";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_profile);
//		Intent i = new Intent(Ripple.this, ActivityProfile.class);
		
//		Intent i = new Intent(Ripple.this, ActivityComment.class);
//		
		Intent i = new Intent(Ripple.this, ActivitySquare.class);
		
		this.startActivity(i);
		
		this.finish();
		
		//initTest();

	}

	AsyncTask<Void, Void, Boolean> imageAsyncTask = new AsyncTask<Void, Void, Boolean>() {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showLoadingDialog("正在加载,请稍后...");
		}

		@Override
		protected Boolean doInBackground(Void... params) {
			return JsonResolveUtil.getProfile(Ripple.this, mProfile, mUid);
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			dismissLoadingDialog();
			if (!result) {
				showCustomToast("数据加载失败...");
			} else {

				int position = 0;
				Intent i = new Intent(Ripple.this, Profile.class);
				i.putExtra(ImageBrowserActivity.IMAGE_TYPE,
						ImageBrowserActivity.TYPE_ALBUM);
				i.putExtra("position", position);
				i.putExtra("profile", mProfile);
				Ripple.this.startActivity(i);
				overridePendingTransition(R.anim.zoom_enter, 0);
				Ripple.this.finish();
			}
		}

	};

	private void initTest() {
		mProfile = new ProfileEntry();
		imageAsyncTask.execute();
	}

	@Override
	protected void initViews() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void initEvents() {
		// TODO Auto-generated method stub

	}
}
