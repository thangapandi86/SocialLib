package com.cwb.sociallibtestapp.app;


import org.brickred.socialauth.Profile;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class SampleListFragment extends ListFragment {
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		SampleAdapter adapter = new SampleAdapter(getActivity());
		adapter.add(new SampleItem("GLOBAL MEDICINE", R.drawable.ic_launcher));
		adapter.add(new SampleItem("Pharmacy", R.drawable.ic_action_cloud));
		adapter.add(new SampleItem("Clinic", R.drawable.ic_launcher));
		adapter.add(new SampleItem("Distric", R.drawable.ic_action_search));
		adapter.add(new SampleItem("Tour", R.drawable.ic_action_web_site));
		adapter.add(new SampleItem("FAQ", R.drawable.ic_action_help));
		adapter.add(new SampleItem("About Us", R.drawable.ic_action_about));
		adapter.add(new SampleItem("OTHERS", R.drawable.ic_launcher));
		adapter.add(new SampleItem("Rate Us", R.drawable.ic_action_good));
		adapter.add(new SampleItem("Share It", R.drawable.ic_action_share));
		adapter.add(new SampleItem("Feedback", R.drawable.ic_action_chat));
		setListAdapter(adapter);
		
	}
	
	@Override
	public void onListItemClick(ListView lv, View v, int position, long id) {
		
			if(position == 1) //MedicalListActivity
			{
			
		   }
			else if(position == 3) //DistricList
			{
			
		}
			else if(position == 6) //WebViewActivity
			{
			
		    }
			else if(position == 9)
			{
			}
		
	}

	private class SampleItem {
		public String tag;
		public int iconRes;
		public SampleItem(String tag, int iconRes) {
			this.tag = tag; 
			this.iconRes = iconRes;
		}
	}

	public class SampleAdapter extends ArrayAdapter<SampleItem> {

		public SampleAdapter(Context context) {
			super(context, 0);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, null);
			}
			if(position == 0 || position == 7)
			{
				ImageView icon = (ImageView) convertView.findViewById(R.id.row_icon);
				icon.setImageResource(R.drawable.up);
				TextView title = (TextView) convertView.findViewById(R.id.row_title);
				title.setText(getItem(position).tag);
				title.setTextSize(18);
				title.setTypeface(Typeface.DEFAULT_BOLD);
				LinearLayout RowId = (LinearLayout)convertView.findViewById(R.id.rowid);
				RowId.setBackgroundColor(getResources().getColor(R.color.transparent));
			}
			
			else
			{
				ImageView icon = (ImageView) convertView.findViewById(R.id.row_icon);
				icon.setImageResource(getItem(position).iconRes);
				TextView title = (TextView) convertView.findViewById(R.id.row_title);
				title.setText(getItem(position).tag);
			}
			

			return convertView;
		}

	}
}
