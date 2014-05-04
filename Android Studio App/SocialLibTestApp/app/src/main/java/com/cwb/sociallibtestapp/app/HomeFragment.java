package com.cwb.sociallibtestapp.app;

import org.brickred.socialauth.Profile;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class HomeFragment extends Fragment {
	Profile profileMap;
	String provider_name;
	TextView name;
	TextView displayName;
	TextView email;
	TextView location;
	TextView gender;
	TextView language;
	TextView country;
	
	public HomeFragment(){}
	
	public HomeFragment(Profile userprofile, String provider_name) {
		// TODO Auto-generated constructor stub
		this.profileMap = userprofile;
		this.provider_name = provider_name;
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
       View rootView = inflater.inflate(R.layout.fragment_home, container, false);
       
        name = (TextView) rootView.findViewById(R.id.name);
		displayName = (TextView) rootView.findViewById(R.id.displayName);
		email = (TextView) rootView.findViewById(R.id.email);
		location = (TextView) rootView.findViewById(R.id.location);
		gender = (TextView) rootView.findViewById(R.id.gender);
		language = (TextView) rootView.findViewById(R.id.language);
		country = (TextView) rootView.findViewById(R.id.country);
       
       if (profileMap.getFullName() == null)
			name.setText("Name                  :  " + profileMap.getFirstName() + profileMap.getLastName());
		else
			name.setText("Name                  :  " + profileMap.getFullName());

		// Display Name
		// Return by Twitter, MySpace, Yahoo , SalesForce, Flickr, Instagram
		if (provider_name.equalsIgnoreCase("twitter") || provider_name.equalsIgnoreCase("myspace")
				|| provider_name.equalsIgnoreCase("yahoo") || provider_name.equalsIgnoreCase("salesforce")
				|| provider_name.equalsIgnoreCase("flickr") || provider_name.equalsIgnoreCase("instagram"))
			displayName.setText("Display Name  :  " + profileMap.getDisplayName());
		else
			displayName.setVisibility(View.GONE);
		
		// Email
				// Return by Facebook, Twitter, Linkedin, Google, GooglePlus, Yammer,
				// Yahoo, FourSquare, Salesforce
				if (provider_name.equalsIgnoreCase("facebook") || provider_name.equalsIgnoreCase("linkedin")
						|| provider_name.equalsIgnoreCase("google") || provider_name.equalsIgnoreCase("googleplus")
						|| provider_name.equalsIgnoreCase("foursquare") || provider_name.equalsIgnoreCase("salesforce")
						|| provider_name.equalsIgnoreCase("yahoo") || provider_name.equalsIgnoreCase("yammer"))
					email.setText("Email                  :  " + profileMap.getEmail());
				else
					email.setVisibility(View.GONE);

				// Location
				// Return by Facebook, MySpace, Linkedin, Yammer,Runkeeper
				// Yahoo, FourSquare, Salesforce
				if (provider_name.equalsIgnoreCase("facebook") || provider_name.equalsIgnoreCase("linkedin")
						|| provider_name.equalsIgnoreCase("myspace") || provider_name.equalsIgnoreCase("runkeeper")
						|| provider_name.equalsIgnoreCase("foursquare") || provider_name.equalsIgnoreCase("yahoo")
						|| provider_name.equalsIgnoreCase("yammer"))
					location.setText("Location            :  " + profileMap.getLocation());
				else
					location.setVisibility(View.GONE);

				// Gender
				// Return by FB, Yahoo, Runkeeper , FourSquare
				if (provider_name.equalsIgnoreCase("facebook") || provider_name.equalsIgnoreCase("runkeeper")
						|| provider_name.equalsIgnoreCase("yahoo") || provider_name.equalsIgnoreCase("foursquare"))
					gender.setText("Gender               :  " + profileMap.getGender());
				else
					gender.setVisibility(View.GONE);

				// Language
				// Return by Fcaebook, Twitter, Google, GooglePlus, Salesforce, Myspace,
				// Yahoo
				if (provider_name.equalsIgnoreCase("facebook") || provider_name.equalsIgnoreCase("twitter")
						|| provider_name.equalsIgnoreCase("google") || provider_name.equalsIgnoreCase("googleplus")
						|| provider_name.equalsIgnoreCase("salesforce") || provider_name.equalsIgnoreCase("myspace")
						|| provider_name.equalsIgnoreCase("yahoo"))

					language.setText("Language          :  " + profileMap.getLanguage());
				else
					language.setVisibility(View.GONE);

				// Country
				// Return by FB , Google , SalesForce, Flickr
				if (provider_name.equalsIgnoreCase("facebook") || provider_name.equalsIgnoreCase("google")
						|| provider_name.equalsIgnoreCase("salesforce") || provider_name.equalsIgnoreCase("flickr"))
					country.setText("Country            :  " + profileMap.getCountry());
				else
					country.setVisibility(View.GONE);
				
      
       return rootView;
    }
}
