package com.example.testapplication;

import java.util.List;

import org.brickred.socialauth.Album;
import org.brickred.socialauth.Photo;

import com.example.testlib.ImageLoader;
import com.example.testlib.PhotoAdapter;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("ValidFragment")
public class PhotosFragment extends Fragment {
	
	List<Album> albumList;
	String provider_name;
	Context ctx;
	
	   // Android Components
		private LinearLayout dataSectionView, frameView;
		TextView textView;

		// Other Components
		String providerName;
		boolean photoListFlag = false;
		boolean photoFlag = false;
		View rootView;
	
	public PhotosFragment(){}
	
	public PhotosFragment(List<Album> albumList, String provider_name,
			Context context) {
		// TODO Auto-generated constructor stub
		this.albumList = albumList;
		this.provider_name = provider_name;
		this.ctx = context;
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        rootView = inflater.inflate(R.layout.fragment_photos, container, false);
        textView = (TextView) rootView.findViewById(R.id.albumTitle);
		dataSectionView = (LinearLayout) rootView.findViewById(R.id.dataSection);
		frameView = (LinearLayout) rootView.findViewById(R.id.frame);
		getData(albumList);
         
        return rootView;
    }
	
	// To set albums in gridview
		public void getData(final List<Album> albumList) {
			final GridView view = new GridView(ctx);
			getGridProperties(view);

			view.setAdapter(new AlbumsAdapter(ctx, 0, albumList));

			String[] urls = new String[albumList.size()];
			for (int i = 0; i < albumList.size(); i++) {
				urls[i] = albumList.get(i).getCoverPhoto();
			}

			view.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
					showPhoto(view, albumList, position);
					photoListFlag = true;
				}
			});
			dataSectionView.addView(view);
		}
		
		public void showPhoto(final GridView gridView, final List<Album> albumList, int position) {

			Album bean = albumList.get(position);
			Log.d("Custom-UI", "Album Clicked");

			final List<Photo> photoList = bean.getPhotos();
			PhotoAdapter photoAdapter = new PhotoAdapter(ctx, 0, photoList);
			gridView.setAdapter(photoAdapter);
			textView.setText(bean.getName());

			final ImageLoader imageLoader = new ImageLoader(ctx);

			gridView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

					Toast.makeText(ctx, "Loading Photo..... Please Wait", Toast.LENGTH_SHORT).show();
					Log.d("Custom-UI", "Photo Clicked");
					dataSectionView.setVisibility(View.GONE);
					photoListFlag = false;
					photoFlag = true;
					Photo photoBean = photoList.get(position);

					ImageView picture = (ImageView) rootView.findViewById(R.id.picture);
					TextView pictureTitle = (TextView) rootView.findViewById(R.id.pictureTitle);
					frameView.setVisibility(View.VISIBLE);
					imageLoader.DisplayImage(photoBean.getLargeImage(), picture);

					pictureTitle.setText(photoBean.getTitle());

				}
			});
		}
		/**
		 * collection of grid properties
		 */
		@SuppressWarnings("static-access")
		public void getGridProperties(GridView view) {
			view.setNumColumns(3);
			view.setVerticalSpacing(5);
			view.setScrollBarStyle(view.SCROLLBARS_OUTSIDE_OVERLAY);
			view.setScrollingCacheEnabled(false);
			view.setGravity(Gravity.TOP);
			view.setSelector(new ColorDrawable(Color.parseColor("#00000000")));
			view.setClipChildren(true);
			view.setPadding(5, 5, 5, 5);
		}

		public void clearView() {
			dataSectionView.removeAllViews();
			dataSectionView.invalidate();
		}

}
