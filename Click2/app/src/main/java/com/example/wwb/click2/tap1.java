package com.example.wwb.click2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;

public class tap1 extends Activity {

	private int[] images = new int[]{
		R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5, R.id.btn_9, R.id.btn_17,R.id.btn_16,R.id.btn_7,
		R.id.btn_4, R.id.btn_10,R.id.btn_2, R.id.btn_11,R.id.btn_4, R.id.btn_18,R.id.btn_2, R.id.btn_19,
		R.id.btn_13,R.id.btn_6, R.id.btn_12,R.id.btn_8, R.id.btn_19,R.id.btn_22,R.id.btn_18,R.id.btn_23,
		R.id.btn_14,R.id.btn_11,R.id.btn_10,R.id.btn_15,R.id.btn_17,R.id.btn_20,R.id.btn_21,R.id.btn_16,
		R.id.btn_9, R.id.btn_8, R.id.btn_6, R.id.btn_7,
		R.id.btn_24,R.id.btn_26,R.id.btn_27,R.id.btn_25,R.id.btn_66,R.id.btn_41,R.id.btn_42,R.id.btn_65,
		R.id.btn_36,R.id.btn_38,R.id.btn_39,R.id.btn_37,R.id.btn_16,R.id.btn_56,R.id.btn_20,R.id.btn_53,
		R.id.btn_59,R.id.btn_30,R.id.btn_29,R.id.btn_60,R.id.btn_33,R.id.btn_62,R.id.btn_61,R.id.btn_34,
		R.id.btn_6, R.id.btn_48,R.id.btn_12,R.id.btn_49,R.id.btn_51,R.id.btn_2, R.id.btn_5, R.id.btn_50,
		R.id.btn_37,R.id.btn_64,R.id.btn_38,R.id.btn_63,R.id.btn_29,R.id.btn_31,R.id.btn_28,R.id.btn_30,
		R.id.btn_42,R.id.btn_40,R.id.btn_41,R.id.btn_43,R.id.btn_45,R.id.btn_82,R.id.btn_80,R.id.btn_44,
		R.id.btn_57,R.id.btn_26,R.id.btn_58,R.id.btn_25,R.id.btn_64,R.id.btn_44,R.id.btn_38,R.id.btn_67,
		R.id.btn_9, R.id.btn_53,R.id.btn_16,R.id.btn_52,R.id.btn_74,R.id.btn_17,R.id.btn_75,R.id.btn_7,
		R.id.btn_38,R.id.btn_45,R.id.btn_44,R.id.btn_39,
		R.id.btn_14,R.id.btn_47,R.id.btn_46,R.id.btn_10,R.id.btn_22,R.id.btn_86,R.id.btn_55,R.id.btn_88,
		R.id.btn_70,R.id.btn_8, R.id.btn_13,R.id.btn_71,R.id.btn_78,R.id.btn_83,R.id.btn_21,R.id.btn_85,
		R.id.btn_19,R.id.btn_77,R.id.btn_76,R.id.btn_23,R.id.btn_15,R.id.btn_69,R.id.btn_11,R.id.btn_68,
		R.id.btn_21,R.id.btn_75,R.id.btn_17,R.id.btn_78,R.id.btn_54,R.id.btn_22,R.id.btn_18,R.id.btn_55,
		R.id.btn_4, R.id.btn_73,R.id.btn_72,R.id.btn_3, R.id.btn_90,R.id.btn_77,R.id.btn_23,R.id.btn_92,
		R.id.btn_67,R.id.btn_82,R.id.btn_84,R.id.btn_44,R.id.btn_38,R.id.btn_45,R.id.btn_44,R.id.btn_39,
		R.id.btn_42,R.id.btn_87,R.id.btn_89,R.id.btn_43,R.id.btn_42,R.id.btn_91,R.id.btn_89,R.id.btn_66,
		R.id.btn_56,R.id.btn_81,R.id.btn_20,R.id.btn_79,R.id.btn_33,R.id.btn_35,R.id.btn_34,R.id.btn_32
	};   // total 168

	private int[] num = new int[]{
		2,3,4,5,     9,17,16,7,   4,10,2,11,   4,18,2,19,   13,6,12,8,   19,22,18,23, 14,11,10,15, 17,20,21,16,
		9,8,6,7,     24,26,27,25, 66,41,42,65, 36,38,39,37, 16,56,20,53, 59,30,29,60,
		33,62,61,34, 6,48,12,49,  51,2,5,50,   37,64,38,63, 29,31,28,30, 42,40,41,43, 45,82,80,44, 57,26,58,25,
		64,44,38,67, 9,53,16,52,  74,17,75,7,  38,45,44,39, 14,47,46,10, 22,86,55,88, 70,8,13,71,  78,83,21,85,
		19,77,76,23, 15,69,11,68, 21,75,17,78, 54,22,18,55, 4,73,72,3,   90,77,23,92, 67,82,84,44, 38,45,44,39,
		42,87,89,43, 42,91,89,66, 56,81,20,79, 33,35,34,32
	};

	private int i = 0;
	private int m = 2;
	private String filename;

	private float midX;
	private float midY;
	int[] loc = new int[2];

	private Long tim1,tim2;
	private float startX,startY;


	@SuppressLint("SimpleDateFormat")
	//private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SSS");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.avtivity_tap1);
		String subOrder = this.getter();
		filename = "被试_" + subOrder +"_data.txt";

	}

	public boolean onTouchEvent(MotionEvent event){
		switch (event.getAction()){
			case MotionEvent.ACTION_DOWN:
				startX = event.getRawX();
				startY = event.getRawY();
				float z = event.getSize();
				tim1 = System.currentTimeMillis();
				if(i < images.length){
					TextView tev = findViewById(images[i]);
					tev.getLocationOnScreen(loc);
					midX = loc[0] + (float)tev.getWidth()/2;
					midY = loc[1] + (float)tev.getWidth()/2;
				}
				//String str1 = "tap1,"+num[i] + ","  + loc[0] + ","+loc[1]+ "," +
						//x + "," + y + "," + z + "," + tim1;

				//SaveData.store(str1,filename);
				//findViewById(images[i]).setVisibility(View.INVISIBLE);
				break;
//			case MotionEvent.ACTION_MOVE:
//				float x0 = event.getRawX();
//				float y0 = event.getRawY();
//				float z0 = event.getSize();
//				String str0 = "     ,,"+ x0 + "," + y0 + "," + z0 ;
//				SaveData.store(str0,filename);
//				break;
			case MotionEvent.ACTION_UP:
				float x1 = event.getRawX();
				float y1 = event.getRawY();
				float z1 = event.getSize();
//				double distX = Math.pow(midX-x1,2);
//				double distY = Math.pow(midY-y1,2);
//				double offset = Math.sqrt(distX+distY); // 计算目标位置中心点和手抬起点的距离作为 偏差。
				//String str2 = "     ,,"+x1 + "," + y1 + ","+ z1 + "," + offset;

				double distX = Math.pow(startX-x1,2);
				double distY = Math.pow(startY-y1,2);
				double offset = Math.sqrt(distX+distY);

				tim2 = System.currentTimeMillis();
				String str2 = num[i]+ "-" + num[i+1] + ","+(tim2-tim1)+","+offset;
				SaveData.store(str2,filename);
//				if(i == 167) {
//					SaveData.store("       ",filename);
//					SaveData.tips(this);
//					new Timer().schedule(new TimerTask() {
//						@Override
//						public void run() {
//							startActivity(new Intent(tap1.this,TaskList.class));
//						}
//					},2000);
//
//				}else{
//					findViewById(images[i]).setVisibility(View.INVISIBLE);
//					findViewById(images[m]).setVisibility(View.VISIBLE);
//
//					i = i+1;
//					m = m+1;
//				}

				findViewById(images[i]).setVisibility(View.INVISIBLE);
				i = i+1;
				while(i == 167){
					SaveData.store("       ",filename);
					SaveData.tips(this);
//					new Timer().schedule(new TimerTask() {
//						@Override
//						public void run() {
//							startActivity(new Intent(tap1.this,TaskList.class));
//						}
//					},2000);
				}
				findViewById(images[m]).setVisibility(View.VISIBLE);
				m = m+1;


				//long consumeTime  = System.nanoTime()-startTime;
				//Log.i("test", String.valueOf(consumeTime*Math.pow(10,-6)));
				break;
			default:
				break;
		}
		return true;
	}


	public String getter() {
		SharedPreferences sp1 = getSharedPreferences("order",MODE_PRIVATE);
		return sp1.getString("bh",null);
	}




}
