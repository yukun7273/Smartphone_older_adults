package com.example.wwb.click2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class drag0 extends Activity implements View.OnTouchListener {

	private WindowManager wm = null;
	private WindowManager.LayoutParams wmParams = null;
	private ImageView iv;

	private int[] image = new int[]{
			R.id.drag0_2,R.id.drag0_1,R.id.drag0_3,R.id.drag0_4,R.id.drag0_5,R.id.drag0_6,R.id.drag0_7,R.id.drag0_8,
			R.id.drag0_9,R.id.drag0_10,R.id.drag0_11,R.id.drag0_12,R.id.drag0_13,R.id.drag0_14,R.id.drag0_15};

	private int i = 0;
	private float startX ;
	private float startY ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_drag0);
		createView();
	}

	@SuppressLint("ClickableViewAccessibility")
	public void createView () {

		wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		iv = new ImageView(this);
		iv.setBackgroundResource(R.drawable.ck_bd);


		wmParams = new WindowManager.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,0,0,
				PixelFormat.TRANSPARENT
		);

		wmParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
		wmParams.format = PixelFormat.RGBA_8888;
		wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
				| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
		wmParams.gravity =  Gravity.START|Gravity.TOP;
		wmParams.x = 0;
		wmParams.y = 522;
		wmParams.width = 144;
		wmParams.height = 144;
		iv.setOnTouchListener(this);
		wm.addView(iv,wmParams);
	}


	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()){
			case MotionEvent.ACTION_DOWN:
				startX = event.getX();
				startY = event.getY();
				break;
			case MotionEvent.ACTION_MOVE:
				float x = event.getRawX();
				float y = event.getRawY();
				wmParams.x = (int)(x - startX);
				wmParams.y = (int)(y - startY);
				wm.updateViewLayout(iv, wmParams);
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
