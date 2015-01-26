/*
===============================================================================

Android Gravatar

Andrey Butov
http://www.andreybutov.com
andreybutov@antair.com

Copyright (c) 2014-2015, Andrey Butov. All Rights Reserved.

===============================================================================
*/

package com.andreybutov.gravatar.sample;

import java.security.MessageDigest;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

final class Gravatar 
{
	public interface Listener {
		public void onGravatarReady(String emailAddress, Bitmap avatar);
	}
	
	private static Gravatar _instance = null;

	public static Gravatar getInstance() {
		if ( _instance == null ) {
			_instance = new Gravatar();
		}
		return _instance;
	}
	
	private Gravatar() {
	}
	
	private String _emailAddress;
	private Listener _listener;
	private boolean _processing = false;

	void getAvatar(String emailAddress, Listener listener) {
		if ( emailAddress == null ) {
			return;
		}
		String cleanEmail = emailAddress.replaceAll("\\s+","").trim();
		if ( _processing || cleanEmail.length() == 0 ) {
			return;
		}
		_processing = true;
		_emailAddress = cleanEmail;
		_listener = listener;
		try { 
			new HttpTask().execute(buildGravatarURL(_emailAddress));
		}
		catch ( Exception e ) {
			_processing = false;
		}
	}
	
	private String buildGravatarURL(String email) {
	    return 
	    	"http://www.gravatar.com/avatar/" +
	    	md5Hex(_emailAddress.toLowerCase()) + 
	    	"?s=200&d=mm";
	}
	
	private String hex(byte[] array) {
		StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < array.length; ++i) {
	    	sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
	    }
	    return sb.toString();
	}
	  
	private String md5Hex (String message) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return hex (md.digest(message.getBytes("CP1252")));
	    } catch ( Exception e ) {
	    	return null;
	    }
	}	
	
	private class HttpTask extends AsyncTask<String, Void, Bitmap> {
		protected Bitmap doInBackground(String... urls) {
			try {
				if ( urls.length > 0 ) {
					HttpClient client = new DefaultHttpClient();
					HttpGet getRequest = new HttpGet(urls[0]);
					HttpResponse response = client.execute(getRequest);
					StatusLine statusLine = response.getStatusLine();
					if ( statusLine.getStatusCode() == 200 ) {
						byte[] data = EntityUtils.toByteArray(response.getEntity());
				    	return BitmapFactory.decodeByteArray(data, 0, data.length);
					}
				}
			} catch ( Exception e ) {
			}
			return null;
		}
		protected void onPostExecute(Bitmap avatar) {
			_processing = false;
			if ( _listener != null ) {
				_listener.onGravatarReady(_emailAddress, avatar);
			}
		}
	}
}
