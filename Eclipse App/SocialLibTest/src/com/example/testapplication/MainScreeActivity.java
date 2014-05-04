package com.example.testapplication;

import info.androidhive.slidingmenu.adapter.NavDrawerListAdapter;
import info.androidhive.slidingmenu.model.NavDrawerItem;
import java.util.ArrayList;
import java.util.List;
import org.brickred.socialauth.Album;
import org.brickred.socialauth.Career;
import org.brickred.socialauth.Contact;
import org.brickred.socialauth.Feed;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.android.DialogListener;
import org.brickred.socialauth.android.SocialAuthError;
import org.brickred.socialauth.android.SocialAuthListener;
import com.example.testlib.ActionItem;
import com.example.testlib.ImageLoader;
import com.example.testlib.QuickAction;
import com.example.testlib.SocialLogin;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MainScreeActivity extends Activity {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	ImageLoader imageLoader;

	SocialLogin Slogin;
	// nav drawer title
	private CharSequence mDrawerTitle;

	// used to store app title
	private CharSequence mTitle;

	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;

	String provider_name;
	Profile Userprofile;
	List<Contact> contactsList;
	List<Album> albumList;
	List<Feed> feedList;
	Career careerMap;
	private ProgressDialog LoaderDialog;

	// POP OVER MENU
	private static final int ID_FACEBOOK = 1;
	private static final int ID_LINKEDIN = 2;
	private static final int ID_TWITTER = 3;
	private static final int ID_GOOGLE = 4;
	QuickAction quickAction;
	TextView Titletext;
	int Aid = 0;
	Button Logout;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTitle = mDrawerTitle = getTitle();
		Slogin = new SocialLogin(MainScreeActivity.this,
				new ResponseListener());

		// load slide menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		// nav drawer icons from resources
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

		navDrawerItems = new ArrayList<NavDrawerItem>();
		AddSidemenuitem(0);
		
		LoaderDialog = new ProgressDialog(MainScreeActivity.this);
		LoaderDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		LoaderDialog.setMessage("Loading...");

		ActionItem nextItem = new ActionItem(ID_FACEBOOK, "FACEBOOK", null);
		ActionItem prevItem = new ActionItem(ID_LINKEDIN, "LINKEDIN", null);
		ActionItem searchItem = new ActionItem(ID_TWITTER, "TWITTER", null);
		ActionItem infoItem = new ActionItem(ID_GOOGLE, "GOOGLE PLUS", null);

		quickAction = new QuickAction(this, QuickAction.VERTICAL);

		// add action items into QuickAction
		quickAction.addActionItem(nextItem);
		quickAction.addActionItem(prevItem);
		quickAction.addActionItem(searchItem);
		quickAction.addActionItem(infoItem);

		// Set listener for action item clicked
		quickAction
				.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
					@Override
					public void onItemClick(QuickAction source, int pos,
							int actionId) {
						if (actionId == ID_FACEBOOK) {
							provider_name = "";
							Slogin.getfacebooklogin();
							AddSidemenuitem(actionId);

							return;
						} else if (actionId == ID_LINKEDIN) {
							provider_name = "";
							Slogin.getlnkedinlogin();
							AddSidemenuitem(actionId);

							return;
						} else if (actionId == ID_TWITTER) {
							provider_name = "";
							Slogin.gettwitterlogin();
							AddSidemenuitem(actionId);

							return;
						} else if (actionId == ID_GOOGLE) {
							provider_name = "";
							Slogin.GetloginGooleplus();
							AddSidemenuitem(actionId);

							return;
						}
					}
				});

		// set listnener for on dismiss event, this listener will be called only
		// if QuickAction dialog was dismissed
		// by clicking the area outside the dialog.
		quickAction.setOnDismissListener(new QuickAction.OnDismissListener() {
			@Override
			public void onDismiss() {

			}
		});

		// Recycle the typed array
		navMenuIcons.recycle();
		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);
		// enabling action bar app icon and behaving it as toggle button
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, // nav menu toggle icon
				R.string.app_name, // nav drawer open - description for
									// accessibility
				R.string.app_name // nav drawer close - description for
									// accessibility
		) {
			public void onDrawerClosed(View view) {
				if (Userprofile != null)
					getActionBar().setTitle(Userprofile.getFullName());
				imageLoader = new ImageLoader(MainScreeActivity.this);
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				if (Userprofile != null)
					getActionBar().setTitle(Userprofile.getFullName());
				getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		Button Showmenu = (Button) findViewById(R.id.showmwnu);
		Titletext = (TextView) findViewById(R.id.textTitle);
		Logout = (Button) findViewById(R.id.btnlogout);
		Showmenu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				quickAction.show(v);

			}
		});
		Logout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				checkLoginstatus();
			}
		});
	}

	/****************************************************************/
	/* DialogListener for login */
	/*****************************************************************/

	private final class ResponseListener implements DialogListener {

		@Override
		public void onComplete(Bundle values) {

			Log.d("Custom-UI", "Successful");
			LoaderDialog.show();
			Slogin.getUserFacebookdetails(new ProfileDataListener());

		}

		@Override
		public void onError(SocialAuthError error) {
			Log.d("Custom-UI", "Error");
			error.printStackTrace();
		}

		@Override
		public void onCancel() {
			Log.d("Custom-UI", "Cancelled");
		}

		@Override
		public void onBack() {
			Log.d("Custom-UI", "Dialog Closed by pressing Back Key");

		}
	}

	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			// displayView(position);
			if (provider_name != null && !provider_name.equalsIgnoreCase("")) {
				getSocialFBdata(position);
				mDrawerLayout.closeDrawer(mDrawerList);
			} else {
				new AlertDialog.Builder(MainScreeActivity.this)
						.setTitle("Login")
						.setMessage("please login any one social page?")
						.setPositiveButton(android.R.string.yes,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										// continue with delete
									}
								})

						.setIcon(android.R.drawable.ic_dialog_alert).show();
			}

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// toggle nav drawer on selecting action bar app icon/title
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action bar actions click
		switch (item.getItemId()) {
		case R.id.action_settings:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* *
	 * Called when invalidateOptionsMenu() is triggered
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// if nav drawer is opened, hide the action items
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		switch (position) {
		case 0:
			/**
			 * Diplaying user details fragment data
			 * */
			fragment = new HomeFragment(Userprofile, provider_name);
			break;
		case 1:
			/**
			 * Diplaying user friends fragment data
			 * */
			fragment = new FindPeopleFragment(contactsList, provider_name,
					MainScreeActivity.this);
			break;
		case 2:
			/**
			 * Diplaying user albums fragment data
			 * */
			fragment = new PhotosFragment(albumList, provider_name,
					MainScreeActivity.this);
			break;
		case 3:
			/**
			 * Diplaying user feeds fragment data
			 * */
			fragment = new FeedFragment(feedList, provider_name,
					MainScreeActivity.this);
			break;
		case 4:
			/**
			 * Diplaying user careers fragment data
			 * */
			fragment = new CareerFragment(careerMap, provider_name,
					MainScreeActivity.this);
			break;
		case 5:
			fragment = new LogoutFragment();
			break;

		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			setTitle(navMenuTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	/*****************************************************************/
	/* To receive the user contacts response after authentication */
	/*****************************************************************/

	private final class ContactDataListener implements
			SocialAuthListener<List<Contact>> {

		@Override
		public void onExecute(String provider, List<Contact> t) {

			Log.d("Custom-UI", "Receiving Data");
			LoaderDialog.cancel();
			contactsList = t;
			displayView(1);

		}

		@Override
		public void onError(SocialAuthError e) {

		}
	}

	/*****************************************************************/
	/* To receive the user profile response after authentication */
	/*****************************************************************/

	private final class ProfileDataListener implements
			SocialAuthListener<Profile> {

		@Override
		public void onExecute(String provider, Profile t) {
			LoaderDialog.dismiss();
			Userprofile = t;
			Titletext.setText(provider);
			provider_name = provider;
			getActionBar().setTitle(Userprofile.getFullName());
			displayView(0);
			Logout.setVisibility(View.VISIBLE);
		}

		@Override
		public void onError(SocialAuthError e) {

		}
	}

	/*****************************************************************/
	/* To receive the album response after authentication */
	/*****************************************************************/

	private final class AlbumDataListener implements
			SocialAuthListener<List<Album>> {

		@Override
		public void onExecute(String provider, List<Album> t) {

			Log.d("Custom-UI", "Receiving Data");
			LoaderDialog.cancel();
			albumList = t;
			displayView(2);

		}

		@Override
		public void onError(SocialAuthError e) {

		}
	}

	/*****************************************************************/
	/* To receive the feed response after authentication */
	/*****************************************************************/

	private final class FeedDataListener implements
			SocialAuthListener<List<Feed>> {

		@Override
		public void onExecute(String provider, List<Feed> t) {

			Log.d("Custom-UI", "Receiving Data");
			LoaderDialog.cancel();
			feedList = t;
			displayView(3);

		}

		@Override
		public void onError(SocialAuthError e) {
		}
	}

	/*****************************************************************/
	/* To receive the feed response after authentication */
	/*****************************************************************/

	private final class CareerListener implements SocialAuthListener<Career> {

		@Override
		public void onExecute(String provider, Career t) {
			LoaderDialog.cancel();
			careerMap = t;
			displayView(4);
		}

		@Override
		public void onError(SocialAuthError e) {
		}
	}

	private void getSocialFBdata(int position) {
		// update the main content by replacing fragments

		switch (position) {
		case 0:
			LoaderDialog.show();
			Slogin.getUserFacebookdetails(new ProfileDataListener());
			break;
		case 1:
			LoaderDialog.show();
			Slogin.getFBContactDetails(new ContactDataListener());
			break;
		case 2:
			LoaderDialog.show();
			if (Aid == 2) {
				Slogin.getFBFeeds(new FeedDataListener());
			} else
				Slogin.getFBAlbums(new AlbumDataListener());

			break;
		case 3:
			LoaderDialog.show();
			if (Aid != 2)
				Slogin.getFBFeeds(new FeedDataListener());
			else if (Aid == 2)
				Slogin.GetCareer(new CareerListener());
			break;

		default:
			break;
		}

	}

	/****************************************************************/
	/* Add Right side based on secial login */
	/*****************************************************************/

	public void AddSidemenuitem(int actionId) {
		Aid = actionId;
		navDrawerItems.clear();
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons
				.getResourceId(0, -1)));
		// Friends
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons
				.getResourceId(1, -1)));
		// Photos
		if (actionId != ID_LINKEDIN)
			navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons
					.getResourceId(2, -1)));

		// Feed
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons
				.getResourceId(3, -1)));

		// Career
		if (actionId == ID_LINKEDIN) {
			navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons
					.getResourceId(4, -1)));
		}
		if (adapter != null)
			adapter.notifyDataSetChanged();
	}

	/****************************/
	/* Logout function */
	/****************************/

	public void checkLoginstatus() {
		boolean status = Slogin.CheckLoginStatus(provider_name);
		Log.d("status", String.valueOf(status));
		if (status) {
			getActionBar().setTitle(R.string.app_name);
			Logout.setVisibility(View.INVISIBLE);
			displayView(5);
		}

	}

}
