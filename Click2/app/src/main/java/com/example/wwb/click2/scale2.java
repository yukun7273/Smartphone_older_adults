package com.example.wwb.click2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class scale2 extends Activity {

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
	private float oriDis;

	int[] loc = new int[2];
	private int m = 0;
	private int n = 0;
	private ImageView view;

	private int[] image = new int[]{
			R.id.ima_b25, R.id.ima_b9,  R.id.ima_b28, R.id.ima_b10, R.id.ima_b29,
			R.id.ima_b12, R.id.ima_b30, R.id.ima_b13, R.id.ima_b32, R.id.ima_b15,
			R.id.ima_b34, R.id.ima_b17, R.id.ima_b1,  R.id.ima_b20, R.id.ima_b3,
			R.id.ima_b24, R.id.ima_b16, R.id.ima_b2,  R.id.ima_b23, R.id.ima_b7,
			R.id.ima_b11, R.id.ima_b14, R.id.ima_b26, R.id.ima_b35, R.id.ima_b21,
			R.id.ima_b22, R.id.ima_b27, R.id.ima_b36, R.id.ima_b31, R.id.ima_b19,
			R.id.ima_b4,  R.id.ima_b8,  R.id.ima_b5, R.id.ima_b33, R.id.ima_b18,
			R.id.ima_b6
	};

	private int[] num = new int[]{
			25, 9, 28, 10, 29, 12, 30, 13, 32, 15, 34, 17, 1, 20, 3, 24, 16, 2, 23, 
			7, 11, 14, 26, 35, 21, 22, 27, 36, 31, 19, 4, 8, 5, 33, 18, 6
	};

	private String filename;
	private long StartTime;
	private float scale_offset;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_scale2);

		String subOrder = this.getter();
		filename = "被试_" + subOrder +"_data.txt";
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getActionMasked()){
			case MotionEvent.ACTION_DOWN:
				//一个手指触屏
				StartTime = System.nanoTime();
				if(m <= 35){
					view = findViewById(image[m]);
					m = m+1;
				}
				matrix.set(view.getImageMatrix());
				savedMatrix.set(matrix);
				startPoint.set(event.getX(), event.getY());
				mode = DRAG;
				float x0 = event.getX(0);
				float y0 = event.getY(0);
				float z0 = event.getSize(0);
				String str0 = "     ,f1d," + x0 + "," + y0 + "," + z0 ;
				SaveData.store(str0,filename);
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
					if (newDist > 10f) {
						matrix.set(savedMatrix);
						float scale = newDist / oriDis;
						matrix.postScale(scale, scale, view.getWidth()/2, view.getHeight()/2);
					}
				}
				break;
			case MotionEvent.ACTION_UP:
				mode = 0;
				long consumeTime = System.nanoTime()- StartTime;
				String string = "scale2," + num[n] + "," + scale_offset + "," + consumeTime*Math.pow(10,-6);
				SaveData.store(string,filename);
				if(m <= 35){
					view.setVisibility(View.INVISIBLE);
					findViewById(image[m]).setVisibility(View.VISIBLE);
					n = n + 1;
				}else{
					SaveData.store("       ",filename);
					SaveData.tips(this);
					new Timer().schedule(new TimerTask() {
						@Override
						public void run() {
							startActivity(new Intent(scale2.this,TaskList.class));
						}
					},2000);
				}
				break;
			case MotionEvent.ACTION_POINTER_UP:
				mode = 0;
				float x4 = event.getX(0);
				float y4 = event.getY(0);
				float z4 = event.getSize(0);
				String str4 = "     ,f1u," + x4 + "," + y4 + "," + z4 ;
				float x5 = event.getX(1);
				float y5 = event.getY(1);
				float z5 = event.getSize(1);
				String str5 = "f2u," + x5 + "," + y5 + "," + z5 ;
				SaveData.store(str4 + "," + str5,filename);
				scale_offset = (distance(event) / oriDis - 4.53f) * 132; //根据缩放倍数计算出来的缩放距离偏差 = 倍数差*长度
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
