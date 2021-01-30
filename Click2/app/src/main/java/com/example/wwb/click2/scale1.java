package com.example.wwb.click2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;


public class scale1 extends Activity {

	private static final int NONE = 0;
	private static final int DRAG = 1;
	private static final int ZOOM = 2;
	private int mode = NONE;

	private Matrix matrix = new Matrix();
	private Matrix savedMatrix = new Matrix();
	// 第一个按下的手指的点
	private PointF startPoint = new PointF();
	// 两个按下的手指的触摸点的中点
	private PointF midPoint = new PointF();
	// 初始的两个手指按下的触摸点的距离
	private float oriDis = 1f;


	int[] loc = new int[2];
	private int m = 0;
	private int n = 0;

	private int[] image = new int[]{
			R.id.ima_a27, R.id.ima_a46, R.id.ima_a26, R.id.ima_a4,  R.id.ima_a43, R.id.ima_a25,
			R.id.ima_a42, R.id.ima_a23, R.id.ima_a5,  R.id.ima_a24, R.id.ima_a19, R.id.ima_a2,
			R.id.ima_a44, R.id.ima_a65, R.id.ima_a45, R.id.ima_a28, R.id.ima_a13, R.id.ima_a56,
			R.id.ima_a40, R.id.ima_a41, R.id.ima_a6,  R.id.ima_a51, R.id.ima_a36, R.id.ima_a21,
			R.id.ima_a8,  R.id.ima_a57, R.id.ima_a39, R.id.ima_a9,  R.id.ima_a58, R.id.ima_a47,
			R.id.ima_a1,  R.id.ima_a35, R.id.ima_a3,  R.id.ima_a17, R.id.ima_a53, R.id.ima_a34,
			R.id.ima_a18, R.id.ima_a50, R.id.ima_a62, R.id.ima_a66, R.id.ima_a20, R.id.ima_a37,
			R.id.ima_a32, R.id.ima_a31, R.id.ima_a49, R.id.ima_a7,  R.id.ima_a15, R.id.ima_a10,
			R.id.ima_a60, R.id.ima_a63, R.id.ima_a59, R.id.ima_a55, R.id.ima_a14, R.id.ima_a12,
			R.id.ima_a54, R.id.ima_a52, R.id.ima_a48, R.id.ima_a30, R.id.ima_a16, R.id.ima_a11,
			R.id.ima_a61, R.id.ima_a38, R.id.ima_a29, R.id.ima_a64, R.id.ima_a22, R.id.ima_a33
	};

	private int[] num = new int[]{
			27,46,26,4,43,25, 42,23,5,24,19,2, 44,65,45,28,13,56, 40,41,6,51,36,21,
			8,57,39,9,58,47, 1,35,3,17,53,34, 18,50,62,66,20,37, 32,31,49,7,15,10,
			60,63,59,55,14,12, 54,52,48,30,16,11, 61,38,29,64,22,33
	};

	private String filename;
	private long StartTime;
	private float scale_offset;

	private ImageView view;


	@SuppressLint("ClickableViewAccessibility")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_scale);

		String subOrder = this.getter();
		filename = "被试_" + subOrder +"_data.txt";
	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getActionMasked()){
			case MotionEvent.ACTION_DOWN:
				//一个手指触屏
				StartTime = System.nanoTime();
				if(m <= 65){
					view = findViewById(image[m]);
					m = m+1;
				}
				matrix.set(view.getImageMatrix());
				savedMatrix.set(matrix);
				startPoint.set(event.getX(), event.getY());
				float x0 = event.getX(0);
				float y0 = event.getY(0);
				float z0 = event.getSize(0);
				String str0 = "     ,f1d," + x0 + "," + y0 + "," + z0 ;
				SaveData.store(str0,filename);
				mode = DRAG;
				break;
			case MotionEvent.ACTION_POINTER_DOWN:
				//一个触屏的情况下，另一个手指触屏

				view.setScaleType(ImageView.ScaleType.MATRIX);
				oriDis = distance(event);
				float x1 = event.getX(1);
				float y1 = event.getY(1);
				float z1 = event.getSize(1);
				String str1 = "     ,f2d," + x1 + "," + y1 + "," + z1 ;
				SaveData.store(str1,filename);
				mode = ZOOM;
				break;
			case MotionEvent.ACTION_MOVE:
				if (mode == ZOOM) {
					// 两个手指滑动
					float x2 = event.getX(0);
					float y2 = event.getY(0);
					float z2 = event.getSize(0);
					String str2 = "     ,f1m," + x2 + "," + y2 + "," + z2 ;
					float x3 = event.getX(1);
					float y3 = event.getY(1);
					float z3 = event.getSize(1);
					String str3 = "f2m," + x3 + "," + y3 + "," + z3 ;
					SaveData.store(str2 + "," + str3,filename);
					float newDist = distance(event);
					if (newDist > 2f) {
						matrix.set(savedMatrix);
						float scale = newDist / oriDis;
						matrix.postScale(scale, scale, view.getWidth()/2, view.getHeight()/2);
					}
				}
				break;
			case MotionEvent.ACTION_UP:
				mode = 0;
				long consumeTime = System.nanoTime() - StartTime;
				String string = "scale1," + num[n] + "," + scale_offset + "," + consumeTime * Math.pow(10, -6);
				SaveData.store(string, filename);
				if (m <= 65) {
					view.setVisibility(View.INVISIBLE);
					findViewById(image[m]).setVisibility(View.VISIBLE);
					n = n + 1;
				} else {
					SaveData.store("       ", filename);
					SaveData.tips(this);
					new Timer().schedule(new TimerTask() {
						@Override
						public void run() {
							startActivity(new Intent(scale1.this, TaskList.class));
						}
					}, 2000);
				}
				break;
			case MotionEvent.ACTION_POINTER_UP:
				mode =0;
				float x4 = event.getX(0);
				float y4 = event.getY(0);
				float z4 = event.getSize(0);
				String str4 = "     ,f1u," + x4 + "," + y4 + "," + z4 ;
				float x5 = event.getX(1);
				float y5 = event.getY(1);
				float z5 = event.getSize(1);
				String str5 = "f2u," + x5 + "," + y5 + "," + z5 ;
				SaveData.store(str4 + "," + str5,filename);
				scale_offset = (distance(event) / oriDis - 2) * 144; //根据缩放倍数计算出来的缩放距离偏差,正值代表大于。
				break;
		}
			view.setImageMatrix(matrix);

		return true;
	}



	private float distance(MotionEvent event) {
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		return (float) Math.sqrt(x * x + y * y);//两点间距离公式
	}

	public String getter() {
		SharedPreferences sp1 = getSharedPreferences("order",MODE_PRIVATE);
		return sp1.getString("bh",null);
	}
}