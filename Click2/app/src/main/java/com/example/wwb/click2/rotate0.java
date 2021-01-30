package com.example.wwb.click2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class rotate0 extends Activity {

	private Matrix mCurrentMatrix = new Matrix();
	private Matrix savedMatrix = new Matrix();

	private PointF mLastSinglePoint = new PointF();
	private PointF vector = new PointF();
	private PointF mLastVector = new PointF();

	private int MODE;
	ImageView view;
	TextView txtView;

	private int[] image = new int[]{
			R.id.rota_7,R.id.rota_1,R.id.rota_2,R.id.rota_3,R.id.rota_4,R.id.rota_5,R.id.rota_6,
			R.id.rota_8,R.id.rota_9,R.id.rota_10};

	private int[] textView = new int[]{
			R.id.rota_7c,R.id.rota_1c,R.id.rota_2c,R.id.rota_3c,R.id.rota_4c,R.id.rota_5c,R.id.rota_6c,
			R.id.rota_8c,R.id.rota_9c,R.id.rota_10c};

	private int m = 0;
	private int n = 1;
	private int s = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_rotate0);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		float x;
		float y;
		switch (event.getActionMasked()){
			case MotionEvent.ACTION_DOWN:
				if(m <= 9){
					view = findViewById(image[m]);
					m = m+1;
				}
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
				}
				break;
			case MotionEvent.ACTION_UP:
				MODE = 0;
				if(m <= 9){
					txtView = findViewById(textView[s]);
					view.setVisibility(View.INVISIBLE);
					txtView.setVisibility(View.INVISIBLE);
					s = s + 1;

					findViewById(image[n]).setVisibility(View.VISIBLE);
					findViewById(textView[n]).setVisibility(View.VISIBLE);
					n = n +1;
				}else{
					finish();
				}
				break;
			case MotionEvent.ACTION_POINTER_UP:
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

}
