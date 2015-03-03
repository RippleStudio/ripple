package top.focuson.ripple.entry;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

public class ProfileEntry implements Parcelable {

	public static final String UID = "uid";
	public static final String NAME = "name";
	public static final String PHOTOS = "photos";
	private String mUid;
	private String mName;
	private int mReputation;
	private List<String> mPhotos;// 照片

	public ProfileEntry() {
		// TODO Auto-generated constructor stub
	}

	public String getmUid() {
		return mUid;
	}

	public void setmUid(String mUid) {
		this.mUid = mUid;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public int getmReputation() {
		return mReputation;
	}

	public void setmReputation(int mReputation) {
		this.mReputation = mReputation;
	}

	public List<String> getmPhotos() {
		return mPhotos;
	}

	public void setmPhotos(List<String> mPhotos) {
		this.mPhotos = mPhotos;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flag) {
		dest.writeString(mUid);
		dest.writeString(mName);
		dest.writeInt(mReputation);
		dest.writeList(mPhotos);
	}

	public static final Parcelable.Creator<ProfileEntry> CREATOR = new Parcelable.Creator<ProfileEntry>() {

		public ProfileEntry createFromParcel(Parcel source) {
			ProfileEntry profile = new ProfileEntry();
			profile.setmUid(source.readString());
			profile.setmName(source.readString());
			profile.setmReputation(source.readInt());
			profile.setmPhotos(source.readArrayList(ProfileEntry.class
					.getClassLoader()));
			return profile;
		}

		@Override
		public ProfileEntry[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ProfileEntry[size];
		}

	};

}
