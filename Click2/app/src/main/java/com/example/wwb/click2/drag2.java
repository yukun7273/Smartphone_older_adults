package com.example.wwb.click2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class drag2 extends Activity implements View.OnTouchListener {

	private int[] image = new int[]{
			R.id.btn7_6,  R.id.btn7_7,  R.id.btn7_8,  R.id.btn7_9,  R.id.btn7_164,R.id.btn7_183,R.id.btn7_182,R.id.btn7_163,
			R.id.btn7_242,R.id.btn7_243,R.id.btn7_244,R.id.btn7_245,R.id.btn7_42, R.id.btn7_43, R.id.btn7_45, R.id.btn7_44,
			R.id.btn7_322,R.id.btn7_321,R.id.btn7_316,R.id.btn7_323,R.id.btn7_2,  R.id.btn7_3,  R.id.btn7_4,  R.id.btn7_5,
			R.id.btn7_11, R.id.btn7_10, R.id.btn7_12, R.id.btn7_13, R.id.btn7_15, R.id.btn7_14, R.id.btn7_17, R.id.btn7_16,
			R.id.btn7_122,R.id.btn7_123,R.id.btn7_124,R.id.btn7_125,R.id.btn7_135,R.id.btn7_134,R.id.btn7_136,R.id.btn7_137,
			R.id.btn7_130,R.id.btn7_131,R.id.btn7_133,R.id.btn7_132,R.id.btn7_127,R.id.btn7_126,R.id.btn7_128,R.id.btn7_129,
			R.id.btn7_18, R.id.btn7_19, R.id.btn7_20, R.id.btn7_21, R.id.btn7_35, R.id.btn7_34, R.id.btn7_36, R.id.btn7_37,
			R.id.btn7_26, R.id.btn7_27, R.id.btn7_28, R.id.btn7_29, R.id.btn7_43, R.id.btn7_42, R.id.btn7_45, R.id.btn7_44,
			R.id.btn7_24, R.id.btn7_25, R.id.btn7_22, R.id.btn7_23, R.id.btn7_38, R.id.btn7_39, R.id.btn7_40, R.id.btn7_41,
			R.id.btn7_33, R.id.btn7_32, R.id.btn7_30, R.id.btn7_31, R.id.btn7_46, R.id.btn7_47, R.id.btn7_48, R.id.btn7_49,
			R.id.btn7_108,R.id.btn7_231,R.id.btn7_230,R.id.btn7_107,R.id.btn7_143,R.id.btn7_232,R.id.btn7_233,R.id.btn7_144,
			R.id.btn7_44, R.id.btn7_235,R.id.btn7_43, R.id.btn7_234,R.id.btn7_12, R.id.btn7_237,R.id.btn7_11, R.id.btn7_236,
			R.id.btn7_238,R.id.btn7_67, R.id.btn7_68, R.id.btn7_239,R.id.btn7_104,R.id.btn7_241,R.id.btn7_240,R.id.btn7_103,
			R.id.btn7_50, R.id.btn7_51, R.id.btn7_52, R.id.btn7_53, R.id.btn7_55, R.id.btn7_54, R.id.btn7_56, R.id.btn7_57,
			R.id.btn7_59, R.id.btn7_58, R.id.btn7_61, R.id.btn7_60, R.id.btn7_62, R.id.btn7_63, R.id.btn7_64, R.id.btn7_65,
			R.id.btn7_290,R.id.btn7_291,R.id.btn7_293,R.id.btn7_292,R.id.btn7_141,R.id.btn7_295,R.id.btn7_294,R.id.btn7_203,
			R.id.btn7_297,R.id.btn7_41, R.id.btn7_296,R.id.btn7_205,R.id.btn7_319,R.id.btn7_325,R.id.btn7_324,R.id.btn7_315,
			R.id.btn7_298,R.id.btn7_299,R.id.btn7_301,R.id.btn7_300,R.id.btn7_326,R.id.btn7_256,R.id.btn7_250,R.id.btn7_327,
			R.id.btn7_67, R.id.btn7_66, R.id.btn7_68, R.id.btn7_69, R.id.btn7_72, R.id.btn7_73, R.id.btn7_70, R.id.btn7_71,
			R.id.btn7_76, R.id.btn7_77, R.id.btn7_74, R.id.btn7_75, R.id.btn7_80, R.id.btn7_81, R.id.btn7_78, R.id.btn7_79,
			R.id.btn7_84, R.id.btn7_85, R.id.btn7_82, R.id.btn7_83, R.id.btn7_89, R.id.btn7_88, R.id.btn7_87, R.id.btn7_86,
			R.id.btn7_305,R.id.btn7_33, R.id.btn7_304,R.id.btn7_215,R.id.btn7_309,R.id.btn7_308,R.id.btn7_307,R.id.btn7_306,
			R.id.btn7_258,R.id.btn7_331,R.id.btn7_330,R.id.btn7_264,R.id.btn7_223,R.id.btn7_310,R.id.btn7_89, R.id.btn7_311,
			R.id.btn7_261,R.id.btn7_322,R.id.btn7_262,R.id.btn7_333,R.id.btn7_312,R.id.btn7_225,R.id.btn7_93, R.id.btn7_313,
			R.id.btn7_91, R.id.btn7_90, R.id.btn7_93, R.id.btn7_92, R.id.btn7_108,R.id.btn7_109,R.id.btn7_106,R.id.btn7_107,
			R.id.btn7_100,R.id.btn7_101,R.id.btn7_99, R.id.btn7_98, R.id.btn7_105,R.id.btn7_104,R.id.btn7_102,R.id.btn7_103,
			R.id.btn7_97, R.id.btn7_96, R.id.btn7_94, R.id.btn7_95, R.id.btn7_111,R.id.btn7_110,R.id.btn7_112,R.id.btn7_113,
			R.id.btn7_116,R.id.btn7_117,R.id.btn7_114,R.id.btn7_115,R.id.btn7_119,R.id.btn7_118,R.id.btn7_121,R.id.btn7_120,
			R.id.btn7_152,R.id.btn7_213,R.id.btn7_212,R.id.btn7_151,R.id.btn7_56, R.id.btn7_205,R.id.btn7_204,R.id.btn7_55,
			R.id.btn7_24, R.id.btn7_207,R.id.btn7_206,R.id.btn7_23, R.id.btn7_80, R.id.btn7_209,R.id.btn7_208,R.id.btn7_79,
			R.id.btn7_115,R.id.btn7_210,R.id.btn7_116,R.id.btn7_211,R.id.btn7_203,R.id.btn7_156,R.id.btn7_202,R.id.btn7_155,
			R.id.btn7_52, R.id.btn7_215,R.id.btn7_51, R.id.btn7_214,R.id.btn7_216,R.id.btn7_19, R.id.btn7_217,R.id.btn7_20,
			R.id.btn7_141,R.id.btn7_140,R.id.btn7_138,R.id.btn7_139,R.id.btn7_142,R.id.btn7_143,R.id.btn7_144,R.id.btn7_145,
			R.id.btn7_148,R.id.btn7_149,R.id.btn7_147,R.id.btn7_146,R.id.btn7_153,R.id.btn7_152,R.id.btn7_150,R.id.btn7_151,
			R.id.btn7_266,R.id.btn7_267,R.id.btn7_269,R.id.btn7_268,R.id.btn7_271,R.id.btn7_153,R.id.btn7_270,R.id.btn7_87,
			R.id.btn7_91, R.id.btn7_272,R.id.btn7_273,R.id.btn7_53, R.id.btn7_277,R.id.btn7_276,R.id.btn7_274,R.id.btn7_275,
			R.id.btn7_183,R.id.btn7_278,R.id.btn7_149,R.id.btn7_279,R.id.btn7_281,R.id.btn7_49, R.id.btn7_280,R.id.btn7_185,
			R.id.btn7_156,R.id.btn7_157,R.id.btn7_155,R.id.btn7_154,R.id.btn7_158,R.id.btn7_159,R.id.btn7_160,R.id.btn7_161,
			R.id.btn7_162,R.id.btn7_163,R.id.btn7_164,R.id.btn7_165,R.id.btn7_169,R.id.btn7_168,R.id.btn7_166,R.id.btn7_167,
			R.id.btn7_170,R.id.btn7_171,R.id.btn7_173,R.id.btn7_172,R.id.btn7_177,R.id.btn7_176,R.id.btn7_174,R.id.btn7_175,
			R.id.btn7_133,R.id.btn7_249,R.id.btn7_43, R.id.btn7_248,
			R.id.btn7_251,R.id.btn7_250,R.id.btn7_252,R.id.btn7_253,R.id.btn7_254,R.id.btn7_139,R.id.btn7_161,R.id.btn7_255,
			R.id.btn7_178,R.id.btn7_179,R.id.btn7_181,R.id.btn7_180,R.id.btn7_132,R.id.btn7_185,R.id.btn7_184,R.id.btn7_131,
			R.id.btn7_127,R.id.btn7_187,R.id.btn7_186,R.id.btn7_126,R.id.btn7_123,R.id.btn7_188,R.id.btn7_124,R.id.btn7_189,
			R.id.btn7_317,R.id.btn7_316,R.id.btn7_314,R.id.btn7_315,R.id.btn7_266,R.id.btn7_335,R.id.btn7_272,R.id.btn7_334,
			R.id.btn7_318,R.id.btn7_233,R.id.btn7_163,R.id.btn7_319,R.id.btn7_269,R.id.btn7_336,R.id.btn7_337,R.id.btn7_270,
			R.id.btn7_168,R.id.btn7_191,R.id.btn7_167,R.id.btn7_190,R.id.btn7_192,R.id.btn7_160,R.id.btn7_193,R.id.btn7_159,
			R.id.btn7_194,R.id.btn7_59, R.id.btn7_60, R.id.btn7_195,R.id.btn7_28, R.id.btn7_197,R.id.btn7_27, R.id.btn7_196,
			R.id.btn7_84, R.id.btn7_199,R.id.btn7_83, R.id.btn7_198,R.id.btn7_200,R.id.btn7_119,R.id.btn7_120,R.id.btn7_201,
			R.id.btn7_242,R.id.btn7_347,R.id.btn7_338,R.id.btn7_314,R.id.btn7_322,R.id.btn7_339,R.id.btn7_348,R.id.btn7_250,
			R.id.btn7_258,R.id.btn7_349,R.id.btn7_340,R.id.btn7_326,R.id.btn7_330,R.id.btn7_341,R.id.btn7_266,R.id.btn7_350,
			R.id.btn7_76, R.id.btn7_219,R.id.btn7_218,R.id.btn7_75, R.id.btn7_220,R.id.btn7_111,R.id.btn7_112,R.id.btn7_221,
			R.id.btn7_225,R.id.btn7_48, R.id.btn7_47, R.id.btn7_224,R.id.btn7_165,R.id.btn7_247,R.id.btn7_143,R.id.btn7_246,
			R.id.btn7_15, R.id.btn7_226,R.id.btn7_227,R.id.btn7_16, R.id.btn7_72, R.id.btn7_229,R.id.btn7_71, R.id.btn7_228,
			R.id.btn7_242,R.id.btn7_243,R.id.btn7_244,R.id.btn7_245,R.id.btn7_148,R.id.btn7_223,R.id.btn7_147,R.id.btn7_222,
			R.id.btn7_252,R.id.btn7_344,R.id.btn7_290,R.id.btn7_353,R.id.btn7_345,R.id.btn7_260,R.id.btn7_354,R.id.btn7_298,
			R.id.btn7_346,R.id.btn7_268,R.id.btn7_355,R.id.btn7_306,R.id.btn7_314,R.id.btn7_356,R.id.btn7_347,R.id.btn7_276,
			R.id.btn7_257,R.id.btn7_61, R.id.btn7_39, R.id.btn7_256,R.id.btn7_258,R.id.btn7_259,R.id.btn7_260,R.id.btn7_261,
			R.id.btn7_157,R.id.btn7_263,R.id.btn7_262,R.id.btn7_135,R.id.btn7_244,R.id.btn7_361,R.id.btn7_352,R.id.btn7_316,
			R.id.btn7_282,R.id.btn7_283,R.id.btn7_284,R.id.btn7_285,R.id.btn7_145,R.id.btn7_287,R.id.btn7_193,R.id.btn7_286,
			R.id.btn7_288,R.id.btn7_195,R.id.btn7_289,R.id.btn7_45, R.id.btn7_31, R.id.btn7_264,R.id.btn7_57, R.id.btn7_265,
			R.id.btn7_300,R.id.btn7_350,R.id.btn7_330,R.id.btn7_359,R.id.btn7_351,R.id.btn7_308,R.id.btn7_360,R.id.btn7_334,
			R.id.btn7_137,R.id.btn7_303,R.id.btn7_213,R.id.btn7_302,R.id.btn7_254,R.id.btn7_329,R.id.btn7_253,R.id.btn7_328,
			R.id.btn7_334,R.id.btn7_342,R.id.btn7_274,R.id.btn7_351,R.id.btn7_282,R.id.btn7_352,R.id.btn7_343,R.id.btn7_244,
			R.id.btn7_322,R.id.btn7_357,R.id.btn7_348,R.id.btn7_284,R.id.btn7_292,R.id.btn7_349,R.id.btn7_326,R.id.btn7_358,
			R.id.btn7_131,R.id.btn7_321,R.id.btn7_320,R.id.btn7_235,
			R.id.btn7_362,R.id.btn7_139,R.id.btn7_140,R.id.btn7_371,R.id.btn7_39, R.id.btn7_363,R.id.btn7_372,R.id.btn7_40,
			R.id.btn7_136,R.id.btn7_386,R.id.btn7_135,R.id.btn7_377,R.id.btn7_31, R.id.btn7_378,R.id.btn7_32, R.id.btn7_387,
			R.id.btn7_8,  R.id.btn7_373,R.id.btn7_7,  R.id.btn7_364,R.id.btn7_374,R.id.btn7_64, R.id.btn7_365,R.id.btn7_63,
			R.id.btn7_379,R.id.btn7_3,  R.id.btn7_4,  R.id.btn7_388,R.id.btn7_380,R.id.btn7_35, R.id.btn7_389,R.id.btn7_36,
			R.id.btn7_100,R.id.btn7_375,R.id.btn7_99, R.id.btn7_366,R.id.btn7_368,R.id.btn7_159,R.id.btn7_371,R.id.btn7_324,
			R.id.btn7_96, R.id.btn7_390,R.id.btn7_95, R.id.btn7_381,R.id.btn7_385,R.id.btn7_326,R.id.btn7_382,R.id.btn7_329,
			R.id.btn7_323,R.id.btn7_372,R.id.btn7_59, R.id.btn7_369,R.id.btn7_353,R.id.btn7_367,R.id.btn7_252,R.id.btn7_376,
			R.id.btn7_383,R.id.btn7_155,R.id.btn7_328,R.id.btn7_386,R.id.btn7_55, R.id.btn7_384,R.id.btn7_327,R.id.btn7_387,
			R.id.btn7_325,R.id.btn7_367,R.id.btn7_370,R.id.btn7_322,R.id.btn7_382,R.id.btn7_354,R.id.btn7_260,R.id.btn7_391

	};  // total clicks is 512 + 72 =584,

	private int[] num = new int[]{
			6,7,8,9,164,183,182,163,242,243,244,245,42,43,45,44,
			322,321,316,323,2,3,4,5,11,10,12,13,15,14,17,16,
			122,123,124,125,135,134,136,137,130,131,133,132,
			127,126,128,129,18,19,20,21,35,34,36,37,
			26,27,28,29,43,42,45,44,24,25,22,23,38,39,40,41,
			33,32,30,31,46,47,48,49,108,231,230,107,
			143,232,233,144,44,235,43,234,12,237,11,236,
			238,67,68,239,104,241,240,103,50,51,52,53,
			55,54,56,57,59,58,61,60,62,63,64,65,
			290,291,293,292,141,295,294,203,297,41,296,205,
			319,325,324,315,298,299,301,300,326,256,250,327,
			67,66,68,69,72,73,70,71,76,77,74,75,80,81,78,79,
			84,85,82,83,89,88,87,86,305,33,304,215,309,308,307,306,
			258,331,330,264,223,310,89,311,261,322,262,333,
			312,225,93,313,91,90,93,92,108,109,106,107,
			100,101,99,98,105,104,102,103,97,96,94,95,
			111,110,112,113,116,117,114,115,119,118,121,120,
			152,213,212,151,56,205,204,55,24,207,206,23,
			80,209,208,79,115,210,116,211,203,156,202,155,
			52,215,51,214,216,19,217,20,141,140,138,139,
			142,143,144,145,148,149,147,146,153,152,150,151,
			266,267,269,268,271,153,270,87,91,272,273,53,
			277,276,274,275,183,278,149,279,281,49,280,185,
			156,157,155,154,158,159,160,161,162,163,164,165,
			169,168,166,167,170,171,173,172,177,176,174,175,
			133,249,43,248,251,250,252,253,254,139,161,255,
			178,179,181,180,132,185,184,131,127,187,186,126,
			123,188,124,189,317,316,314,315,266,335,272,334,
			318,233,163,319,269,336,337,270,168,191,167,190,
			192,160,193,159,194,59,60,195,28,197,27,196,
			84,199,83,198,200,119,120,201,242,347,338,314,
			322,339,348,250,258,349,340,326,330,341,266,350,
			76,219,218,75,220,111,112,221,225,48,47,224,
			165,247,143,246,15,226,227,16,72,229,71,228,
			242,243,244,245,148,223,147,222,252,344,290,353,
			345,260,354,298,346,268,355,306,314,356,347,276,
			257,61,39,256,258,259,260,261,157,263,262,135,
			244,361,352,316,282,283,284,285,145,287,193,286,
			288,195,289,45,31,264,57,265,300,350,330,359,
			351,308,360,334,137,303,213,302,254,329,253,328,
			334,342,274,351,282,352,343,244,322,357,348,284,
			292,349,326,358,131,321,320,235,
			362,139,140,371,39,363,372,40,136,386,135,377,31,378,32,387,8,373,7,364,374,64,365,63,
			379,3,4,388,380,35,389,36,100,375,99,366,368,159,371,324,96,390,95,381,385,326,382,329,
			323,372,59,369,353,367,252,376,383,155,328,386,55,384,327,387,325,367,370,322,382,354,260,391
	};

	private WindowManager wm = null;
	private WindowManager.LayoutParams wmParams = null;
	private ImageView iv;

	private float startX ;
	private float startY ;
	private int i = 0;
	private int m = 1;
	private String filename;
	private long startTime;
	private int[] loc = new int[2];

	@SuppressLint("SimpleDateFormat")
	private SimpleDateFormat sdf  = new SimpleDateFormat("HH:mm:ss:SSS");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_drag2);
		String subOrder = this.getter();
		filename = "被试_" + subOrder +"_data.txt";

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
		wmParams.x = 234;
		wmParams.y = 789;
		wmParams.width = 69;
		wmParams.height = 69;
		iv.setOnTouchListener(this);
		wm.addView(iv,wmParams);
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				startX = event.getX();
				startY = event.getY();
				startTime = System.nanoTime();
				float x0 = event.getRawX();
				float y0 = event.getRawY();
				float z0 = event.getSize();
				String str0 = "      ,,"+ x0 + "," + y0 + "," + z0;
				SaveData.store(str0,filename);

				if(i < image.length){
					TextView tev = findViewById(image[i]);
					tev.getLocationOnScreen(loc);
				}
				break;
			case MotionEvent.ACTION_MOVE:
				float x = event.getRawX();
				float y = event.getRawY();
				float z = event.getSize();
				wmParams.x = (int) (x - startX);
				wmParams.y = (int) (y - startY);
				wm.updateViewLayout(iv, wmParams);
				String str =  "      ,,"+ x + "," + y + "," + z;
				SaveData.store(str,filename);
				break;
			case MotionEvent.ACTION_UP:
				float x1 = event.getRawX();
				float y1 = event.getRawY();
				float z1 = event.getSize();
				double distX = Math.pow(loc[0]-(x1-startX),2);
				double distY = Math.pow(loc[1]-(y1-startY),2);
				double offset = Math.sqrt(distX+distY);

				String string = x1 + "," + y1 + "," + z1;
				long conTime = System.nanoTime()-startTime;
				String time = String.valueOf(conTime*Math.pow(10,-6));
				String str1 = "drag2," + num[i]+"," + string + "," + time + "," + offset;
				SaveData.store(str1,filename);
				findViewById(image[i]).setVisibility(View.INVISIBLE);
				if(i == 583){
					SaveData.store("       ",filename);
					SaveData.tips(this);
					new Timer().schedule(new TimerTask() {
						@Override
						public void run() {
							startActivity(new Intent(drag2.this,TaskList.class));
						}
					},2000);
				}else{
					findViewById(image[m]).setVisibility(View.VISIBLE);
					m = m + 1;
					i = i + 1;
				}
				break;
		}

		return true;
	}

	public String getter() {
		SharedPreferences sp1 = getSharedPreferences("order",MODE_PRIVATE);
		return sp1.getString("bh",null);
	}
}
