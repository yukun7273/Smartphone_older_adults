package com.example.wwb.click2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.Environment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class drag1 extends Activity implements View.OnTouchListener {

	private WindowManager wm = null;
	private WindowManager.LayoutParams wmParams = null;
	private ImageView iv;

	private float startX ;
	private float startY ;
	private int i = 0;
	private int m = 1;
	private String subOrder;
	private String filename;
	private long startTime;
	private int[] loc = new int[2];
	private int MODE;

	private int[] btn = new int[]{
			R.id.btn5_2, R.id.btn5_3, R.id.btn5_4, R.id.btn5_5, R.id.btn5_9, R.id.btn5_17,R.id.btn5_16,R.id.btn5_7,
			R.id.btn5_4, R.id.btn5_10,R.id.btn5_2, R.id.btn5_11,R.id.btn5_4, R.id.btn5_18,R.id.btn5_2, R.id.btn5_19,
			R.id.btn5_13,R.id.btn5_6, R.id.btn5_12,R.id.btn5_8, R.id.btn5_19,R.id.btn5_22,R.id.btn5_18,R.id.btn5_23,
			R.id.btn5_14,R.id.btn5_11,R.id.btn5_10,R.id.btn5_15,R.id.btn5_17,R.id.btn5_20,R.id.btn5_21,R.id.btn5_16,
			R.id.btn5_24,R.id.btn5_26,R.id.btn5_27,R.id.btn5_25,R.id.btn5_66,R.id.btn5_41,R.id.btn5_42,R.id.btn5_65,
			R.id.btn5_36,R.id.btn5_38,R.id.btn5_39,R.id.btn5_37,R.id.btn5_16,R.id.btn5_56,R.id.btn5_20,R.id.btn5_53,
			R.id.btn5_59,R.id.btn5_30,R.id.btn5_29,R.id.btn5_60,R.id.btn5_33,R.id.btn5_62,R.id.btn5_61,R.id.btn5_34,
			R.id.btn5_6, R.id.btn5_48,R.id.btn5_12,R.id.btn5_49,R.id.btn5_51,R.id.btn5_2, R.id.btn5_5, R.id.btn5_50,
			R.id.btn5_37,R.id.btn5_64,R.id.btn5_38,R.id.btn5_63,R.id.btn5_29,R.id.btn5_31,R.id.btn5_28,R.id.btn5_30,
			R.id.btn5_42,R.id.btn5_40,R.id.btn5_41,R.id.btn5_43,R.id.btn5_45,R.id.btn5_82,R.id.btn5_80,R.id.btn5_44,
			R.id.btn5_57,R.id.btn5_26,R.id.btn5_58,R.id.btn5_25,R.id.btn5_64,R.id.btn5_44,R.id.btn5_38,R.id.btn5_67,
			R.id.btn5_9, R.id.btn5_53,R.id.btn5_16,R.id.btn5_52,R.id.btn5_74,R.id.btn5_17,R.id.btn5_75,R.id.btn5_7,
			R.id.btn5_70,R.id.btn5_8, R.id.btn5_13,R.id.btn5_71,R.id.btn5_78,R.id.btn5_83,R.id.btn5_21,R.id.btn5_85,
			R.id.btn5_19,R.id.btn5_77,R.id.btn5_76,R.id.btn5_23,R.id.btn5_15,R.id.btn5_69,R.id.btn5_11,R.id.btn5_68,
			R.id.btn5_21,R.id.btn5_75,R.id.btn5_17,R.id.btn5_78,R.id.btn5_54,R.id.btn5_22,R.id.btn5_18,R.id.btn5_55,
			R.id.btn5_67,R.id.btn5_82,R.id.btn5_84,R.id.btn5_44,R.id.btn5_38,R.id.btn5_45,R.id.btn5_44,R.id.btn5_39,
			R.id.btn5_56,R.id.btn5_81,R.id.btn5_20,R.id.btn5_79,R.id.btn5_33,R.id.btn5_35,R.id.btn5_34,R.id.btn5_32,
			R.id.btn5_9, R.id.btn5_8, R.id.btn5_6, R.id.btn5_7, R.id.btn5_14,R.id.btn5_47,R.id.btn5_46,R.id.btn5_10,
			R.id.btn5_4, R.id.btn5_73,R.id.btn5_72,R.id.btn5_3,
	};   // total 148

	private int[] num = new int[]{
			2,3,4,5,      9,17,16,7,   4,10,2,11,   4,18,2,19,  13,6,12,8,   19,22,18,23,  14,11,10,15,  17,20,21,16,
			24,26,27,25, 66,41,42,65, 36,38,39,37, 16,56,20,53, 59,30,29,60,
			33,62,61,34,  6,48,12,49, 51,2,5,50,   37,64,38,63, 29,31,28,30, 42,40,41,43, 45,82,80,44, 57,26,58,25,
			64,44,38,67,  9,53,16,52, 74,17,75,7,  70,8,13,71,  78,83,21,85,
			19,77,76,23, 15,69,11,68, 21,75,17,78, 54,22,18,55, 67,82,84,44, 38,45,44,39,
			56,81,20,79, 33,35,34,32,  9,8,6,7,    14,47,46,10,  4,73,72,3
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_drag);
		subOrder = this.getter();
		filename = "被试_" + subOrder + "_data.txt";
//		DisplayMetrics metrics = new DisplayMetrics();
//		getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
//		int dpi = metrics.densityDpi;
//		pxPermm = dpi/25.4;
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
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				MODE = 0;
				startX = event.getX();
				startY = event.getY();
				startTime = System.nanoTime();
				float x0 = event.getRawX();
				float y0 = event.getRawY();
				float z0 = event.getSize();
				String str0 =  "      ,,"+ x0 + "," + y0 + "," + z0;
				storeData(str0,filename);
				if(i < btn.length){
					TextView tev = findViewById(btn[i]);
					tev.getLocationOnScreen(loc);
				}
				break;
			case MotionEvent.ACTION_MOVE:
				MODE = 1;
				float x = event.getRawX();
				float y = event.getRawY();
				float z = event.getSize();
				wmParams.x = (int)(x - startX);
				wmParams.y = (int)(y - startY);
				wm.updateViewLayout(iv, wmParams);
				String str =  "      ,,"+ x + "," + y + "," + z;
				storeData(str,filename);
				break;
			case MotionEvent.ACTION_UP:
					float x1 = event.getRawX();
					float y1 = event.getRawY();
					float z1 = event.getSize();
					double distX = Math.pow(loc[0] - (x1 - startX), 2);
					double distY = Math.pow(loc[1] - (y1 - startY), 2);
					double offset = Math.sqrt(distX + distY);   // 根据目标和可拖动窗口的位置差 计算出偏差。

					String string = "drag1," + num[i] + "," + x1 + "," + y1 + "," + z1;
					long conTime = System.nanoTime() - startTime;
					String tim1 = String.valueOf(conTime * Math.pow(10, -6));
					String str1 = string + "," + tim1 + "," + offset;
					storeData(str1, "被试_" + subOrder + "_data.txt");
					findViewById(btn[i]).setVisibility(View.INVISIBLE);

					if (i == 147) {
						SaveData.store("       ", filename);
						SaveData.tips(this);
						new Timer().schedule(new TimerTask() {
							@Override
							public void run() {
								startActivity(new Intent(drag1.this, TaskList.class));
							}
						}, 2000);
					} else {
						findViewById(btn[m]).setVisibility(View.VISIBLE);
						m = m + 1;
						i = i + 1;
					}
					break;

		}

		return true;
	}

	public void storeData(String string,String fileName){

		File file = new File(Environment.getExternalStorageDirectory(),fileName);
		FileOutputStream os = null ;

		try {
			os = new FileOutputStream(file,true);
			os.write(string.getBytes());
			os.write("\r\n".getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public String getter() {

		SharedPreferences sp1 = getSharedPreferences("order",MODE_PRIVATE);
		return sp1.getString("bh",null);
	}
}
