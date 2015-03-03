package top.focuson.ripple.feed;

import top.focuson.ripple.BaseActivity;
import top.focuson.ripple.R;
import top.focuson.ripple.R.anim;
import top.focuson.ripple.R.id;
import top.focuson.ripple.R.layout;
import top.focuson.ripple.android.activity.ImageBrowserActivity;
import top.focuson.ripple.android.view.UserPhotosView;
import top.focuson.ripple.android.view.UserPhotosView.onPagerPhotoItemClickListener;
import top.focuson.ripple.entry.ProfileEntry;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Profile extends BaseActivity implements
		onPagerPhotoItemClickListener {
	private UserPhotosView mUpvPhotos;// 照片
	private ProfileEntry mProfile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_profile);
		initViews();
		initEvents();
		initProfile();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void initViews() {
		mUpvPhotos = (UserPhotosView) findViewById(R.id.otherprofile_upv_photos);
	}

	@Override
	protected void initEvents() {
		mUpvPhotos.setOnPagerPhotoItemClickListener(this);

	}

	private void initProfile() {
		mProfile = getIntent().getParcelableExtra("profile");
		mUpvPhotos.setPhotos(mApplication, mProfile.getmPhotos());

	}

	@Override
	public void onItemClick(View view, int position) {
		Intent intent = new Intent(Profile.this, ImageBrowserActivity.class);
		intent.putExtra(ImageBrowserActivity.IMAGE_TYPE,
				ImageBrowserActivity.TYPE_ALBUM);
		intent.putExtra("position", position);
		intent.putExtra("profile", mProfile);
		startActivity(intent);
		overridePendingTransition(R.anim.zoom_enter, 0);
	}

}
