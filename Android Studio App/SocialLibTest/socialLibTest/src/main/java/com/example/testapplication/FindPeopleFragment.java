package com.example.testapplication;

import java.util.ArrayList;
import java.util.List;

import org.brickred.socialauth.Contact;

import com.example.testlib.ImageLoader;

import android.annotation.SuppressLint;
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
public class FindPeopleFragment extends Fragment {
	
	List<Contact> contactList;
	String provider_name;
	Context ctx;
	public FindPeopleFragment(List<Contact> contactsList,String provider_name, Context context) {
		// TODO Auto-generated constructor stub
		this.contactList = (ArrayList<Contact>) contactsList;
		this.provider_name = provider_name;
		this.ctx = context;
	}


	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_find_people, container, false);
        ListView list = (ListView)rootView.findViewById(R.id.contactList);
		list.setAdapter(new ContactAdapter(ctx, R.layout.contact_list, contactList));
        return rootView;
    }
	
	// adapter for contact list
		public class ContactAdapter extends ArrayAdapter<Contact> {
			private final LayoutInflater mInflater;
			List<Contact> contacts;
			ImageLoader imageLoader;

			public ContactAdapter(Context context, int textViewResourceId, List<Contact> contacts) {
				super(context, textViewResourceId);
				mInflater = LayoutInflater.from(context);
				this.contacts = contacts;
				imageLoader = new ImageLoader(context);
			}

			@Override
			public int getCount() {
				return contacts.size();
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {

				final Contact bean = contacts.get(position);
				View row = mInflater.inflate(R.layout.contact_list, parent, false);

				TextView label = (TextView) row.findViewById(R.id.cName);
				TextView email = (TextView) row.findViewById(R.id.cEmail);
				ImageView cImage = (ImageView) row.findViewById(R.id.cImage);

				Log.d("Custom-UI", "Display Name = " + bean.getDisplayName());
				Log.d("Custom-UI", "First Name = " + bean.getFirstName());
				Log.d("Custom-UI", "Last Name = " + bean.getLastName());
				Log.d("Custom-UI", "Contact ID = " + bean.getId());
				Log.d("Custom-UI", "Profile URL = " + bean.getProfileUrl());
				Log.d("Custom-UI", "Profile Image URL = " + bean.getProfileImageURL());
				Log.d("Custom-UI", "Email = " + bean.getEmail());

				imageLoader.DisplayImage(bean.getProfileImageURL(), cImage);

				if (provider_name.equalsIgnoreCase("twitter"))
					label.setText(bean.getFirstName() + "@" + bean.getDisplayName()); // twitter
				else if (provider_name.equalsIgnoreCase("yammer") || provider_name.equalsIgnoreCase("instagram")
						|| provider_name.equalsIgnoreCase("flickr"))
					label.setText(bean.getDisplayName()); // yammer
				else
					label.setText(bean.getFirstName() + bean.getLastName());

				// Show email for google , yammer , yahoo
				if (provider_name.equalsIgnoreCase("google") || provider_name.equalsIgnoreCase("yammer")
						|| provider_name.equalsIgnoreCase("yahoo") || provider_name.equalsIgnoreCase("googleplus")) {
					email.setVisibility(View.VISIBLE);
					email.setText(bean.getEmail());
				}
				return row;
			}
		}
	
	
}
