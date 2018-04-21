package com.ex.funapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {

	ImageView image;
	Button button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		

			addListenerOnButton();

		}

		public void addListenerOnButton() {

			image = (ImageView) findViewById(R.id.imageView1);

			button = (Button) findViewById(R.id.button1);
			button.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					image.setImageResource(R.drawable.img_7);
				}

			});
			button = (Button) findViewById(R.id.button2);
			button.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					image.setImageResource(R.drawable.img_8);
				}

			});
			button = (Button) findViewById(R.id.button3);
			button.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					image.setImageResource(R.drawable.img_9);
				}

			});
	}

	

}
