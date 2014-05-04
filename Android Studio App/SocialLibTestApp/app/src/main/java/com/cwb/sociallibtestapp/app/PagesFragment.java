package com.cwb.sociallibtestapp.app;

import org.brickred.socialauth.Career;
import org.brickred.socialauth.Education;
import org.brickred.socialauth.Position;
import org.brickred.socialauth.Recommendation;
import org.brickred.socialauth.android.SocialAuthAdapter;

import com.cwb.sociallib.lib.ImageLoader;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class PagesFragment extends Fragment implements OnClickListener{
	SocialAuthAdapter adapter;
	Career careerMap;

	// Android Components
	LinearLayout careerLayout;
	TextView headline;
	TextView companyName;
	TextView companyType;
	TextView industry;
	TextView title;
	TextView companyDate;
	TextView recommender;
	TextView recommenderType;
	TextView recommenderText;
	TextView eduDegree;
	TextView eduSchool;
	TextView eduStudy;
	TextView eduDate;
	TextView btnEducation;
	TextView btnCareer;
	TextView btnRecommendation;

	ListView posList;
	ListView eduList;
	ListView recommendList;

	// Variables
	String provider_name;
	ImageLoader imageLoader;
	Context ctx;
	
	
	public PagesFragment(){}
	
	public PagesFragment(Career careerMap2, String provider_name2,
			Context context) {
		// TODO Auto-generated constructor stub
		this.careerMap = careerMap2;
		this.provider_name = provider_name2;
		this.ctx = context;
		
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_career, container, false);
        
        careerLayout = (LinearLayout) rootView.findViewById(R.id.careerLayout);

		btnCareer = (TextView) rootView.findViewById(R.id.btnCareer);
		btnRecommendation = (TextView) rootView.findViewById(R.id.btnRecommendation);
		btnEducation = (TextView) rootView.findViewById(R.id.btnEducation);
		
		btnCareer.setOnClickListener(this);
		btnRecommendation.setOnClickListener(this);
		btnEducation.setOnClickListener(this);

		headline = (TextView) rootView.findViewById(R.id.headline);
		posList = new ListView(ctx);
		recommendList = new ListView(ctx);
		eduList = new ListView(ctx);
		
		if (careerMap.getHeadline() != null)
			headline.setText(careerMap.getHeadline());

		if (careerMap.getPositions() != null && careerMap.getPositions().length > 0) {
			Position[] pos = careerMap.getPositions();
			posList.setAdapter(new PositionAdapter(ctx, 0, pos));

		}

		if (careerMap.getRecommendations() != null && careerMap.getRecommendations().length > 0) {
			Recommendation[] recommendation = careerMap.getRecommendations();
			recommendList.setAdapter(new RecommendAdapter(ctx, 0, recommendation));

		}

		if (careerMap.getEducations() != null && careerMap.getEducations().length > 0) {
			Education[] edu = careerMap.getEducations();
			eduList.setAdapter(new EducationAdapter(ctx, 0, edu));
		}

		careerLayout.addView(posList);
         
        return rootView;
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == btnCareer) {
			careerLayout.removeAllViews();
			careerLayout.addView(posList);
		} else if (v == btnRecommendation) {
			careerLayout.removeAllViews();
			careerLayout.addView(recommendList);
		} else if (v == btnEducation) {
			careerLayout.removeAllViews();
			careerLayout.addView(eduList);
		}
	}
	
	/**
	 * Adapter to show user job titles
	 */

	class PositionAdapter extends BaseAdapter {
		// Android Components
		private final LayoutInflater mInflater;
		private final Context ctx;
		Position[] posArray;

		public PositionAdapter(Context context, int textViewResourceId, Position[] positions) {
			// Cache the LayoutInflate to avoid asking for a new one each time.
			ctx = context;
			mInflater = LayoutInflater.from(ctx);
			posArray = positions;
		}

		@Override
		public int getCount() {
			return posArray.length;
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder holder;

			final Position pos = posArray[position];

			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.position, null);

				holder = new ViewHolder();
				holder.companyName = (TextView) convertView.findViewById(R.id.companyName);
				holder.industry = (TextView) convertView.findViewById(R.id.industry);
				holder.title = (TextView) convertView.findViewById(R.id.title);

				convertView.setTag(holder);
			} else {
				// Get the ViewHolder back to get fast access to the TextView
				// and the ImageView.
				holder = (ViewHolder) convertView.getTag();
			}

			holder.companyName.setText(pos.getCompanyName());
			holder.industry.setText(pos.getIndustry());
			holder.title.setText(pos.getTitle());

			return convertView;
		}

		class ViewHolder {
			TextView companyName;
			TextView industry;
			TextView title;
		}
	}
	
	/**
	 * Adapter to show recommendations
	 */

	class RecommendAdapter extends BaseAdapter {
		// Android Components
		private final LayoutInflater mInflater;
		private final Context ctx;
		Recommendation[] recArray;

		public RecommendAdapter(Context context, int textViewResourceId, Recommendation[] rec) {
			// Cache the LayoutInflate to avoid asking for a new one each time.
			ctx = context;
			mInflater = LayoutInflater.from(ctx);
			recArray = rec;
		}

		@Override
		public int getCount() {
			return recArray.length;
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder holder;

			final Recommendation recm = recArray[position];

			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.recommend, null);

				holder = new ViewHolder();
				holder.recommender = (TextView) convertView.findViewById(R.id.recommender);
				holder.recommenderType = (TextView) convertView.findViewById(R.id.recommenderType);
				holder.recommenderText = (TextView) convertView.findViewById(R.id.recommenderText);

				convertView.setTag(holder);
			} else {
				// Get the ViewHolder back to get fast access to the TextView
				// and the ImageView.
				holder = (ViewHolder) convertView.getTag();
			}

			holder.recommender.setText(recm.getRecommenderFirstName() + "" + recm.getRecommenderLastName());
			holder.recommenderType.setText(recm.getRecommendationType());
			holder.recommenderText.setText(recm.getRecommendationText());

			return convertView;
		}

		class ViewHolder {
			TextView recommender;
			TextView recommenderType;
			TextView recommenderText;
		}
	}

	/**
	 * Adapter to show education
	 */

	class EducationAdapter extends BaseAdapter {
		// Android Components
		private final LayoutInflater mInflater;
		private final Context ctx;
		Education[] eduArray;

		public EducationAdapter(Context context, int textViewResourceId, Education[] edus) {
			// Cache the LayoutInflate to avoid asking for a new one each time.
			ctx = context;
			mInflater = LayoutInflater.from(ctx);
			eduArray = edus;
		}

		@Override
		public int getCount() {
			return eduArray.length;
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			ViewHolder holder;

			final Education edu = eduArray[position];

			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.education, null);

				holder = new ViewHolder();
				holder.eduDegree = (TextView) convertView.findViewById(R.id.eduDegree);
				holder.eduStudy = (TextView) convertView.findViewById(R.id.eduStudy);
				holder.eduSchool = (TextView) convertView.findViewById(R.id.eduSchool);

				convertView.setTag(holder);
			} else {
				// Get the ViewHolder back to get fast access to the TextView
				// and the ImageView.
				holder = (ViewHolder) convertView.getTag();
			}

			holder.eduDegree.setText(edu.getDegree());
			holder.eduStudy.setText(edu.getFieldOfStudy());
			holder.eduSchool.setText(edu.getSchoolName());

			return convertView;
		}

		class ViewHolder {
			TextView eduDegree;
			TextView eduStudy;
			TextView eduSchool;
		}
	}
}
