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

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private EditText _emailField;
	private ImageView _imageField;
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        _emailField = (EditText)findViewById(R.id.email_edit_text);
        _imageField = (ImageView)findViewById(R.id.imageview);

		_emailField.setOnEditorActionListener(new EditText.OnEditorActionListener() {
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if ( actionId == EditorInfo.IME_ACTION_GO ) {
					requestAvatar();
				}
				return false;
			}
		});
		
		findViewById(R.id.request_avatar_btn).setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				requestAvatar();
			}
		});
    }
    
    private void requestAvatar() {
    	Gravatar.getInstance().getAvatar(_emailField.getText().toString(), new Gravatar.Listener() {
			public void onGravatarReady(String emailAddress, Bitmap avatar) {
				if ( avatar != null ) {
					_imageField.setImageBitmap(avatar);
				}
			}
		});
    }
}
