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

public class scale0 extends Activity {

	private static final int NONE = 0;
	private static final int DRAG = 1;
	private static final int ZOOM = 2;
	private int mode = NONE;

	private Matrix matrix = new Matrix();
	private Matrix savedMatrix = new Matrix();

	private PointF startPoint = new PointF();
	private float oriDis = 1f;
	private int m = 0;
	private ImageView view;

	private int[] image = new int[]{
		R.id.scale_2,R.id.scale_1,R.id.scale_3,R.id.scale_4,R.id.scale_5,R.id.scale_6,R.id.scale_7,
			R.id.scale_8,R.id.scale_9,R.id.scale_10
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_scale0);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getActionMasked()){
			case MotionEvent.ACTION_DOWN:
				//一个手指触屏
				if(m <= 9){
					view = findViewById(image[m]);
					m = m+1;
				}

				matrix.set(view.getImageMatrix());
				savedMatrix.set(matrix);
				startPoint.set(event.getX(), event.getY());

				mode = DRAG;
				break;
			case MotionEvent.ACTION_POINTER_DOWN:
				//一个触屏的情况下，另一个手指触屏
				view.setScaleType(ImageView.ScaleType.MATRIX);
				oriDis = distance(event);
				mode = ZOOM;
				break;
			case MotionEvent.ACTION_MOVE:
				if (mode == ZOOM) {
					// 两个手指滑动
					float newDist = distance(event);
					if (newDist > 3f) {
						matrix.set(savedMatrix);
						float scale = newDist / oriDis;
						matrix.postScale(scale, scale, view.getWidth()/2, view.getHeight()/2);
					}
				}
				break;
			case MotionEvent.ACTION_UP:
				mode = 0;

				if(m <= 9){
					view.setVisibility(View.INVISIBLE);
					findViewById(image[m]).setVisibility(View.VISIBLE);
				}else{
					this.finish();
				}
				break;
			case MotionEvent.ACTION_POINTER_UP:
				mode = 0;
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
}
