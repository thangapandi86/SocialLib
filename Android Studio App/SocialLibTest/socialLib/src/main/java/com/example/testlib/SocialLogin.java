/**
 * 
 * Main class of the SocialLogin  Android SDK. <br>
 * 
 * The main objective of this example is to access social media providers
 * Facebook, Twitter, Lnkedin,Googleplus and others by creating your own UI.

 */


package com.example.testlib;


import java.util.List;

import org.brickred.socialauth.Album;
import org.brickred.socialauth.Career;
import org.brickred.socialauth.Contact;
import org.brickred.socialauth.Feed;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.android.DialogListener;
import org.brickred.socialauth.android.SocialAuthAdapter;
import org.brickred.socialauth.android.SocialAuthAdapter.Provider;
import org.brickred.socialauth.android.SocialAuthListener;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SocialLogin {
	Context ctx;
	static SocialAuthAdapter adapter;
	
	public SocialLogin(Context inContext,DialogListener Listener)
	{
		this.ctx = inContext;
		adapter = new SocialAuthAdapter(Listener);
	}
	/****************************************************************/
	/* login facebook using  SocialAuthAdapter*/
	/*****************************************************************/
	public void getfacebooklogin()
	{
		adapter.authorize(ctx, Provider.FACEBOOK);
		
	}
	
	/****************************************************************/
	/* login google  using  SocialAuthAdapter*/
	/*****************************************************************/
	
	public void getgooglepluslogin()
	{
		adapter.addCallBack(Provider.GOOGLE, "http://socialauth.in/socialauthdemo");
		adapter.authorize(ctx, Provider.GOOGLE);
	}
	
	/****************************************************************/
	/* login twitter using  SocialAuthAdapter*/
	/*****************************************************************/
	public void gettwitterlogin()
	{
		adapter.authorize(ctx, Provider.TWITTER);
	}
	
	/****************************************************************/
	/* login google plus using  SocialAuthAdapter*/
	/*****************************************************************/
	
	public void GetloginGooleplus()
	{
		adapter.authorize(ctx, Provider.GOOGLEPLUS);
	}
	
	/****************************************************************/
	/* login linked in using  SocialAuthAdapter*/
	/*****************************************************************/
	public void getlnkedinlogin()
	{
		adapter.authorize(ctx, Provider.LINKEDIN);
	}
	
	/****************************************************************/
	/* get user detals data using  SocialAuthAdapter*/
	/*****************************************************************/
	
	public void getUserFacebookdetails(SocialAuthListener<Profile> Listener)
	{
		adapter.getUserProfileAsync(Listener);
	}
	
	/****************************************************************/
	/* get user contact data using  SocialAuthAdapter*/
	/*****************************************************************/
	public void getFBContactDetails(SocialAuthListener<List<Contact>> Listener)
	{
		adapter.getContactListAsync(Listener);
	}
	
	/****************************************************************/
	/* get user Albums data using  SocialAuthAdapter*/
	/*****************************************************************/
	public void getFBAlbums(SocialAuthListener<List<Album>> Listener)
	{
		adapter.getAlbumsAsync(Listener);
	}
	
	
	/****************************************************************/
	/* get user Feeds data using  SocialAuthAdapter*/
	/*****************************************************************/
	public void getFBFeeds(SocialAuthListener<List<Feed>> Listener)
	{
		adapter.getFeedsAsync(Listener);
	}
	
	

	/****************************************************************/
	/* get user Career  data using  SocialAuthAdapter*/
	/*****************************************************************/
	public void GetCareer(SocialAuthListener<Career> Listener)
	{
		adapter.getCareerAsync(Listener);
	}
	
	
	/****************************************************************/
	/* logout the social provider*/
	/*****************************************************************/
	public Boolean CheckLoginStatus(String provider)
	{
		return  adapter.signOut(ctx, provider);
		
	}
}
