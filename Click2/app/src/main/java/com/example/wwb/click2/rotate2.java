package com.example.wwb.click2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class rotate2 extends Activity {

	private Matrix mCurrentMatrix = new Matrix();
	private Matrix savedMatrix = new Matrix();
	private PointF mLastSinglePoint = new PointF();
	private PointF vector = new PointF();
	private PointF mLastVector = new PointF();

	private int MODE;
	ImageView view;
	TextView txtView;

	private int[] image = new int[]{
			R.id.roa_2,R.id.roa_13, R.id.roa_8,R.id.roa_3,R.id.roa_7,R.id.roa_12,R.id.roa_1,R.id.roa_11,
			R.id.roa_6,R.id.roa_10,R.id.roa_5,R.id.roa_15,R.id.roa_9,R.id.roa_4,R.id.roa_14};

	private int[] textView = new int[]{
			R.id.rofza_2, R.id.rofza_13, R.id.rofza_8, R.id.rofza_3, R.id.rofza_7, R.id.rofza_12,
			R.id.rofza_1, R.id.rofza_11, R.id.rofza_6, R.id.rofza_10, R.id.rofza_5, R.id.rofza_15,
			R.id.rofza_9, R.id.rofza_4, R.id.rofza_14};

	private int[] num = new int[]{
			2, 13, 8, 3, 7, 12, 1, 11, 6, 10, 5, 15, 9, 4, 14};

	private int m = 0;
	private int n = 1;
	private int s = 0;

	private String filename;
	private float degree_offset;
	private long startTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_rotate2);

		String subOrder = this.getter();
		filename = "被试_" + subOrder +"_data.txt";
	}

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
				MODE = 1;
				mCurrentMatrix.set(view.getImageMatrix());
				savedMatrix.set(mCurrentMatrix);
				mLastSinglePoint.set(event.getX(),event.getY());

				float x0 = event.getX(0);
				float y0 = event.getY(0);
				float z0 = event.getSize(0);
				String str0 = "     ,f1d," + x0 + "," + y0 + "," + z0 ;
				SaveData.store(str0,filename);
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
				MODE = 0;
				long consumeTime = System.nanoTime()- startTime;
				String string = "rotate2," + num[s] + "," + degree_offset + "," + consumeTime*Math.pow(10,-6);
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
							startActivity(new Intent(rotate2.this,TaskList.class));
						}
					},2000);
				}
				break;
			case MotionEvent.ACTION_POINTER_UP:
				x = event.getX(0) - event.getX(1);
				y = event.getY(0) - event.getY(1);
				vector.set(x,y);
				degree_offset = calculateDeltaDegree(mLastVector, vector) - 75;
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
