package com.cwb.sociallibtestapp.app;

import java.util.ArrayList;
import java.util.List;

import org.brickred.socialauth.Contact;
import org.brickred.socialauth.Feed;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class FeedFragment extends Fragment {
	
	List<Feed> feedList;
	String provider_name;
	Context ctx;
	
	// Android Components
		ListView listview;
		AlertDialog dialog;
		TextView name;
		TextView displayName;
		TextView email;
		TextView location;
		TextView gender;
		TextView language;
		TextView birthday;
		TextView country;
		ImageView image;

		// Variables
		boolean status;
		public static int pos;
	
	public FeedFragment(){}
	
	public FeedFragment(List<Feed> feedList, String provider_name,
			Context context) {
		// TODO Auto-generated constructor stubthis.contactList = (ArrayList<Contact>) contactsList;
		this.feedList = (ArrayList<Feed>) feedList;
		this.provider_name = provider_name;
		this.ctx = context;
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_feed, container, false);
        
        ListView list = (ListView) rootView.findViewById(R.id.feedList);
		list.setAdapter(new MyCustomAdapter(ctx, R.layout.feed_list, feedList));
         
        return rootView;
    }
	
	public class MyCustomAdapter extends ArrayAdapter<Feed> {
		private final LayoutInflater mInflater;
		List<Feed> feeds;

		public MyCustomAdapter(Context context, int textViewResourceId, List<Feed> feeds) {
			super(context, textViewResourceId);
			mInflater = LayoutInflater.from(context);
			this.feeds = feeds;
		}

		@Override
		public int getCount() {
			return feeds.size();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// return super.getView(position, convertView, parent);
			final Feed bean = feeds.get(position);

			View row = mInflater.inflate(R.layout.feed_list, parent, false);

			TextView label = (TextView) row.findViewById(R.id.fName);
			TextView email = (TextView) row.findViewById(R.id.fMsg);
			TextView date = (TextView) row.findViewById(R.id.fDate);

			Log.d("Custom-UI", "From = " + bean.getFrom());
			Log.d("Custom-UI", "Message = " + bean.getMessage());
			Log.d("Custom-UI", "Screen Name = " + bean.getScreenName());
			Log.d("Custom-UI", "Feed Id = " + bean.getId());
			Log.d("Custom-UI", "Created At = " + bean.getCreatedAt());

			label.setText("From : " + bean.getFrom());
			email.setText(bean.getMessage());
			date.setText(" , Created At : " + bean.getCreatedAt().toString());

			return row;
		}

	}
}
