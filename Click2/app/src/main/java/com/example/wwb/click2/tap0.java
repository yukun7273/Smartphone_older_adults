package com.example.wwb.click2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import java.util.Date;

public class tap0 extends Activity {

	private int[] image = new int[]{
		R.id.tap0_2,R.id.tap0_1,R.id.tap0_3,R.id.tap0_4,R.id.tap0_5,R.id.tap0_6,R.id.tap0_7,R.id.tap0_8,
		R.id.tap0_9,R.id.tap0_10,R.id.tap0_11,R.id.tap0_12,R.id.tap0_13,R.id.tap0_14,R.id.tap0_15};

	private int i = 0;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_tap0);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()){
			case MotionEvent.ACTION_DOWN:



				break;
			case MotionEvent.ACTION_MOVE:

				break;
			case MotionEvent.ACTION_UP:

				findViewById(image[i]).setVisibility(View.INVISIBLE);
				if(i == 14) {
					finish();
				}else{
					findViewById(image[i+1]).setVisibility(View.VISIBLE);
					i = i+1;

				}
				break;
			default:
				break;
		}
		return true;
	}


}
