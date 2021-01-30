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
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class rotate1 extends Activity {

	private Matrix mCurrentMatrix = new Matrix();
	private Matrix savedMatrix = new Matrix();

	//上一次单点触控的坐标
	private PointF mLastSinglePoint = new PointF();

	//记录上一次两只手指中点的位置
	private PointF vector = new PointF();

	//记录上一次两只手指构成的一个向量
	private PointF mLastVector = new PointF();

	private int MODE;
	ImageView view;
	TextView txtView;
	
	private int[] image = new int[]{
			R.id.ro_9,R.id.ro_4,R.id.ro_13,R.id.ro_6,R.id.ro_15,R.id.ro_2,R.id.ro_10,R.id.ro_5,
			R.id.ro_14,R.id.ro_8,R.id.ro_11,R.id.ro_7,R.id.ro_1,R.id.ro_12,R.id.ro_3};

	private int[] textView = new int[]{
			R.id.rofz_9, R.id.rofz_4, R.id.rofz_13, R.id.rofz_6, R.id.rofz_15, R.id.rofz_2,
			R.id.rofz_10, R.id.rofz_5, R.id.rofz_14, R.id.rofz_8, R.id.rofz_11, R.id.rofz_7,
			R.id.rofz_1, R.id.rofz_12, R.id.rofz_3
	};

	private int[] num = new int[]{
			9 ,4, 13, 6, 15, 2, 10, 5, 14, 8, 11, 7, 1, 12, 3
	};

	private int m = 0;
	private int n = 1;
	private int s = 0;

	private String filename;
	private float degree_offset;
	private long startTime;

	@SuppressLint("ClickableViewAccessibility")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_rotate);

		String subOrder = this.getter();
		filename = "被试_" + subOrder +"_data.txt";

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x;
		float y;
		switch (event.getActionMasked()){
			case MotionEvent.ACTION_DOWN:
				startTime  = System.nanoTime();
				if(m <= 14){
					view = findViewById(image[m]);
					m = m+1;
				}
				float x0 = event.getX(0);
				float y0 = event.getY(0);
				float z0 = event.getSize(0);
				String str0 = "     ,f1d," + x0 + "," + y0 + "," + z0 ;
				SaveData.store(str0,filename);
				MODE = 1;
				mCurrentMatrix.set(view.getImageMatrix());
				savedMatrix.set(mCurrentMatrix);
				mLastSinglePoint.set(event.getX(),event.getY());
				break;
			case MotionEvent.ACTION_POINTER_DOWN:
				MODE = 2;
				mLastVector.set(event.getX(0) - event.getX(1),
						event.getY(0) - event.getY(1));
				view.setScaleType(ImageView.ScaleType.MATRIX);
				float x1 = event.getX(1);
				float y1 = event.getY(1);
				float z1 = event.getSize(1);
				String str1 = "     ,f2d," + x1 + "," + y1 + "," + z1 ;
				SaveData.store(str1,filename);
				break;
			case MotionEvent.ACTION_MOVE:
				if(MODE == 2) {
					x = event.getX(0) - event.getX(1);
					y = event.getY(0) - event.getY(1);
					vector.set(x, y); //当前两只手指构成的向量
					float degree = calculateDeltaDegree(mLastVector, vector); //计算本次向量和上一次向量之间的夹角
					if(degree > 3f) {
						mCurrentMatrix.set(savedMatrix);
						mCurrentMatrix.postRotate(degree, view.getWidth() / 2, view.getHeight() / 2);
					} else if(degree < - 3f) {
						mCurrentMatrix.set(savedMatrix);
						mCurrentMatrix.postRotate(degree, view.getWidth() / 2, view.getHeight() / 2);
					}

					float x2 = event.getX(0);
					float y2 = event.getY(0);
					float z2 = event.getSize(0);
					String str2 = "     ,f1m," + x2 + "," + y2 + "," + z2 ;
					float x3 = event.getX(1);
					float y3 = event.getY(1);
					float z3 = event.getSize(1);
					String str3 = "f2m," + x3 + "," + y3 + "," + z3 ;
					SaveData.store(str2 + "," + str3,filename);
				}
				break;
			case MotionEvent.ACTION_UP:
				long consumeTime = System.nanoTime()- startTime;
				String string = "rotate1," + num[s] + "," + degree_offset + "," + consumeTime*Math.pow(10,-6);
				SaveData.store(string,filename);
				MODE = 0;
				if(m <= 14){
					txtView = findViewById(textView[s]);
					view.setVisibility(View.INVISIBLE);
					txtView.setVisibility(View.INVISIBLE);
					s = s + 1;

					findViewById(image[n]).setVisibility(View.VISIBLE);
					findViewById(textView[n]).setVisibility(View.VISIBLE);
					n = n +1;
				}else{
					SaveData.store("       ",filename);
					SaveData.tips(this);
					new Timer().schedule(new TimerTask() {
						@Override
						public void run() {
							startActivity(new Intent(rotate1.this,TaskList.class));
						}
					},2000);
				}
				break;
			case MotionEvent.ACTION_POINTER_UP:
				x = event.getX(0) - event.getX(1);
				y = event.getY(0) - event.getY(1);
				vector.set(x, y);
				degree_offset = calculateDeltaDegree(mLastVector, vector) - 15;
				float x4 = event.getX(0);
				float y4 = event.getY(0);
				float z4 = event.getSize(0);
				String str4 = "     ,f1u," + x4 + "," + y4 + "," + z4 ;
				float x5 = event.getX(1);
				float y5 = event.getY(1);
				float z5 = event.getSize(1);
				String str5 = "f2u," + x5 + "," + y5 + "," + z5 ;
				SaveData.store(str4 + "," + str5,filename);
				MODE = 0;
				break;
		}
		view.setImageMatrix(mCurrentMatrix);
		return true;
	}


	private float calculateDeltaDegree(PointF lastVector, PointF vector) {

		float angel;
		double lastLen = Math.sqrt(lastVector.x*lastVector.x+ lastVector.y*lastVector.y);
		double curLen = Math.sqrt(vector.x*vector.x + vector.y*vector.y);
//		float lastDegree = (float) Math.atan2(lastVector.y, lastVector.x);
//		float degree = (float) Math.atan2(vector.y, vector.x);
//		float deltaDegree = degree - lastDegree;
		double cosAlpha = (lastVector.x * vector.x + lastVector.y * vector.y)
				/ ( lastLen* curLen);
		//由于计算误差，可能会带来略大于1的cos，例如
		if (cosAlpha > 1.0f) {
			cosAlpha = 1.0f;
		}
		//本次的角度已经计算出来。
		double dAngle = Math.acos(cosAlpha) * 180.0 / Math.PI;

		lastVector.x /= lastLen;
		lastVector.y /= lastLen;
		vector.x /= curLen;
		vector.y /= curLen;
		PointF verticalVec = new PointF(vector.y, -vector.x);
		float vDot = lastVector.x*verticalVec.x + lastVector.y*verticalVec.y;

		if(vDot > 0){
			angel = (float) dAngle;
		}else {
			angel = -(float) dAngle;
		}
		return angel;
	}

	public String getter() {
		SharedPreferences sp1 = getSharedPreferences("order",MODE_PRIVATE);
		return sp1.getString("bh",null);
	}

}
