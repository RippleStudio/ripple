package top.focuson.ripple.android.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import top.focuson.ripple.entry.ProfileEntry;
import android.content.Context;

public class JsonResolveUtil {

	// 用户资料文件夹
	private static final String PROFILE = "profile/";
	// 用户状态文件夹
	// 后缀名
	private static final String SUFFIX = ".json";

	// 状态评论

	public JsonResolveUtil() {

	}

	public static boolean getProfile(Context context, ProfileEntry profile,
			String uid) {

		if (!android.text.TextUtils.isEmpty(uid)) {
			String json = TextUtils.getJson(context, PROFILE + uid + SUFFIX);
			if (json != null) {
				try {
					JSONObject object = new JSONObject(json);
					String userId = object.getString(ProfileEntry.UID);
					String name = object.getString(ProfileEntry.NAME);
					JSONArray photosArray = object
							.getJSONArray(ProfileEntry.PHOTOS);
					List<String> photos = new ArrayList<String>();
					for (int i = 0; i < photosArray.length(); i++) {
						photos.add(photosArray.getString(i));
					}

					profile.setmUid(userId);
					profile.setmName(name);
					profile.setmPhotos(photos);
					profile.setmReputation(1000);

					return true;

				} catch (JSONException e) {
					profile = null;
					e.printStackTrace();

				}

			}
		}

		return false;

	}

}
