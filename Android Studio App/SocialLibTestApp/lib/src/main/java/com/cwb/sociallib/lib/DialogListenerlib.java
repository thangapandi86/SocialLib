package com.cwb.sociallib.lib;

import org.brickred.socialauth.android.SocialAuthError;

import android.os.Bundle;

public interface DialogListenerlib {

/**
* Called when a dialog completes. Executed by the thread that initiated the
* dialog.
*
* @param values
* Key-value string pairs extracted from the response.
*/
public void onComplete(Bundle values);

/**
* Called when a dialog has an error. Executed by the thread that initiated
* the dialog.
*/
public void onError(SocialAuthError e);

/**
* Called when a dialog is canceled by the user. Executed by the thread that
* initiated the dialog.
*
*/
public void onCancel();

/**
* Called when a dialog is closed by user by pressing back key
*
*/
public void onBack();

}