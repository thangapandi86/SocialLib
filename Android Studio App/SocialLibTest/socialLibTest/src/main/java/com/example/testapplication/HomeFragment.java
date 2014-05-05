package com.example.testapplication;

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
		if (provider_name.equalsIgnoreCase("twitter"))
			displayName.setText("Display Name  :  " + profileMap.getDisplayName());
		else
			displayName.setVisibility(View.GONE);
		
		// Email
				// Return by Facebook, Twitter, Linkedin, Google, GooglePlus
				
				if (provider_name.equalsIgnoreCase("facebook") || provider_name.equalsIgnoreCase("linkedin")
						|| provider_name.equalsIgnoreCase("google") || provider_name.equalsIgnoreCase("googleplus"))
					email.setText("Email                  :  " + profileMap.getEmail());
				else
					email.setVisibility(View.GONE);

				// Location
				// Return by Facebook, MySpace, Linkedin
				
				if (provider_name.equalsIgnoreCase("facebook") || provider_name.equalsIgnoreCase("linkedin"))
					location.setText("Location            :  " + profileMap.getLocation());
				else
					location.setVisibility(View.GONE);

				// Gender
				// Return by FB, Yahoo, Runkeeper , FourSquare
				if (provider_name.equalsIgnoreCase("facebook"))
					gender.setText("Gender               :  " + profileMap.getGender());
				else
					gender.setVisibility(View.GONE);

				// Language
				// Return by Fcaebook, Twitter, Google, GooglePlus
				
				if (provider_name.equalsIgnoreCase("facebook") || provider_name.equalsIgnoreCase("twitter")
						|| provider_name.equalsIgnoreCase("google") || provider_name.equalsIgnoreCase("googleplus"))

					language.setText("Language          :  " + profileMap.getLanguage());
				else
					language.setVisibility(View.GONE);

				// Country
				// Return by FB , Google 
				if (provider_name.equalsIgnoreCase("facebook") || provider_name.equalsIgnoreCase("google"))
					country.setText("Country            :  " + profileMap.getCountry());
				else
					country.setVisibility(View.GONE);
				
      
       return rootView;
    }
}
