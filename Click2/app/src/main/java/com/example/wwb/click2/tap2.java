package com.example.wwb.click2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class tap2 extends Activity {

	private int[] image = new int[]{
		R.id.btn6_6,  R.id.btn6_7,  R.id.btn6_8,  R.id.btn6_9,  R.id.btn6_164,R.id.btn6_183,R.id.btn6_182,R.id.btn6_163,
		R.id.btn6_242,R.id.btn6_243,R.id.btn6_244,R.id.btn6_245,R.id.btn6_42, R.id.btn6_43, R.id.btn6_45, R.id.btn6_44,
		R.id.btn6_322,R.id.btn6_321,R.id.btn6_316,R.id.btn6_323,R.id.btn6_2,  R.id.btn6_3,  R.id.btn6_4,  R.id.btn6_5,
		R.id.btn6_11, R.id.btn6_10, R.id.btn6_12, R.id.btn6_13, R.id.btn6_15, R.id.btn6_14, R.id.btn6_17, R.id.btn6_16,
		R.id.btn6_122,R.id.btn6_123,R.id.btn6_124,R.id.btn6_125,R.id.btn6_135,R.id.btn6_134,R.id.btn6_136,R.id.btn6_137,
		R.id.btn6_130,R.id.btn6_131,R.id.btn6_133,R.id.btn6_132,R.id.btn6_127,R.id.btn6_126,R.id.btn6_128,R.id.btn6_129,
		R.id.btn6_18, R.id.btn6_19, R.id.btn6_20, R.id.btn6_21, R.id.btn6_35, R.id.btn6_34, R.id.btn6_36, R.id.btn6_37,
		R.id.btn6_26, R.id.btn6_27, R.id.btn6_28, R.id.btn6_29, R.id.btn6_43, R.id.btn6_42, R.id.btn6_45, R.id.btn6_44,
		R.id.btn6_24, R.id.btn6_25, R.id.btn6_22, R.id.btn6_23, R.id.btn6_38, R.id.btn6_39, R.id.btn6_40, R.id.btn6_41,
		R.id.btn6_33, R.id.btn6_32, R.id.btn6_30, R.id.btn6_31, R.id.btn6_46, R.id.btn6_47, R.id.btn6_48, R.id.btn6_49,
		R.id.btn6_108,R.id.btn6_231,R.id.btn6_230,R.id.btn6_107,R.id.btn6_143,R.id.btn6_232,R.id.btn6_233,R.id.btn6_144,
		R.id.btn6_44, R.id.btn6_235,R.id.btn6_43, R.id.btn6_234,R.id.btn6_12, R.id.btn6_237,R.id.btn6_11, R.id.btn6_236,
		R.id.btn6_238,R.id.btn6_67, R.id.btn6_68, R.id.btn6_239,R.id.btn6_104,R.id.btn6_241,R.id.btn6_240,R.id.btn6_103,
		R.id.btn6_50, R.id.btn6_51, R.id.btn6_52, R.id.btn6_53, R.id.btn6_55, R.id.btn6_54, R.id.btn6_56, R.id.btn6_57,
		R.id.btn6_59, R.id.btn6_58, R.id.btn6_61, R.id.btn6_60, R.id.btn6_62, R.id.btn6_63, R.id.btn6_64, R.id.btn6_65,
		R.id.btn6_290,R.id.btn6_291,R.id.btn6_293,R.id.btn6_292,R.id.btn6_141,R.id.btn6_295,R.id.btn6_294,R.id.btn6_203,
		R.id.btn6_297,R.id.btn6_41, R.id.btn6_296,R.id.btn6_205,R.id.btn6_319,R.id.btn6_325,R.id.btn6_324,R.id.btn6_315,
		R.id.btn6_298,R.id.btn6_299,R.id.btn6_301,R.id.btn6_300,R.id.btn6_326,R.id.btn6_256,R.id.btn6_250,R.id.btn6_327,
		R.id.btn6_67, R.id.btn6_66, R.id.btn6_68, R.id.btn6_69, R.id.btn6_72, R.id.btn6_73, R.id.btn6_70, R.id.btn6_71,
		R.id.btn6_76, R.id.btn6_77, R.id.btn6_74, R.id.btn6_75, R.id.btn6_80, R.id.btn6_81, R.id.btn6_78, R.id.btn6_79,
		R.id.btn6_84, R.id.btn6_85, R.id.btn6_82, R.id.btn6_83, R.id.btn6_89, R.id.btn6_88, R.id.btn6_87, R.id.btn6_86,
		R.id.btn6_305,R.id.btn6_33, R.id.btn6_304,R.id.btn6_215,R.id.btn6_309,R.id.btn6_308,R.id.btn6_307,R.id.btn6_306,
		R.id.btn6_258,R.id.btn6_331,R.id.btn6_330,R.id.btn6_264,R.id.btn6_223,R.id.btn6_310,R.id.btn6_89, R.id.btn6_311,
		R.id.btn6_261,R.id.btn6_322,R.id.btn6_262,R.id.btn6_333,R.id.btn6_312,R.id.btn6_225,R.id.btn6_93, R.id.btn6_313,
		R.id.btn6_91, R.id.btn6_90, R.id.btn6_93, R.id.btn6_92, R.id.btn6_108,R.id.btn6_109,R.id.btn6_106,R.id.btn6_107,
		R.id.btn6_100,R.id.btn6_101,R.id.btn6_99, R.id.btn6_98, R.id.btn6_105,R.id.btn6_104,R.id.btn6_102,R.id.btn6_103,
		R.id.btn6_97, R.id.btn6_96, R.id.btn6_94, R.id.btn6_95, R.id.btn6_111,R.id.btn6_110,R.id.btn6_112,R.id.btn6_113,
		R.id.btn6_116,R.id.btn6_117,R.id.btn6_114,R.id.btn6_115,R.id.btn6_119,R.id.btn6_118,R.id.btn6_121,R.id.btn6_120,
		R.id.btn6_152,R.id.btn6_213,R.id.btn6_212,R.id.btn6_151,R.id.btn6_56, R.id.btn6_205,R.id.btn6_204,R.id.btn6_55,
		R.id.btn6_24, R.id.btn6_207,R.id.btn6_206,R.id.btn6_23, R.id.btn6_80, R.id.btn6_209,R.id.btn6_208,R.id.btn6_79,
		R.id.btn6_115,R.id.btn6_210,R.id.btn6_116,R.id.btn6_211,R.id.btn6_203,R.id.btn6_156,R.id.btn6_202,R.id.btn6_155,
		R.id.btn6_52, R.id.btn6_215,R.id.btn6_51, R.id.btn6_214,R.id.btn6_216,R.id.btn6_19, R.id.btn6_217,R.id.btn6_20,
		R.id.btn6_141,R.id.btn6_140,R.id.btn6_138,R.id.btn6_139,R.id.btn6_142,R.id.btn6_143,R.id.btn6_144,R.id.btn6_145,
		R.id.btn6_148,R.id.btn6_149,R.id.btn6_147,R.id.btn6_146,R.id.btn6_153,R.id.btn6_152,R.id.btn6_150,R.id.btn6_151,
		R.id.btn6_266,R.id.btn6_267,R.id.btn6_269,R.id.btn6_268,R.id.btn6_271,R.id.btn6_153,R.id.btn6_270,R.id.btn6_87,
		R.id.btn6_91, R.id.btn6_272,R.id.btn6_273,R.id.btn6_53, R.id.btn6_277,R.id.btn6_276,R.id.btn6_274,R.id.btn6_275,
		R.id.btn6_183,R.id.btn6_278,R.id.btn6_149,R.id.btn6_279,R.id.btn6_281,R.id.btn6_49, R.id.btn6_280,R.id.btn6_185,
		R.id.btn6_156,R.id.btn6_157,R.id.btn6_155,R.id.btn6_154,R.id.btn6_158,R.id.btn6_159,R.id.btn6_160,R.id.btn6_161,
		R.id.btn6_162,R.id.btn6_163,R.id.btn6_164,R.id.btn6_165,R.id.btn6_169,R.id.btn6_168,R.id.btn6_166,R.id.btn6_167,
		R.id.btn6_170,R.id.btn6_171,R.id.btn6_173,R.id.btn6_172,R.id.btn6_177,R.id.btn6_176,R.id.btn6_174,R.id.btn6_175,
		R.id.btn6_133,R.id.btn6_249,R.id.btn6_43, R.id.btn6_248,
		R.id.btn6_251,R.id.btn6_250,R.id.btn6_252,R.id.btn6_253,R.id.btn6_254,R.id.btn6_139,R.id.btn6_161,R.id.btn6_255,
		R.id.btn6_178,R.id.btn6_179,R.id.btn6_181,R.id.btn6_180,R.id.btn6_132,R.id.btn6_185,R.id.btn6_184,R.id.btn6_131,
		R.id.btn6_127,R.id.btn6_187,R.id.btn6_186,R.id.btn6_126,R.id.btn6_123,R.id.btn6_188,R.id.btn6_124,R.id.btn6_189,
		R.id.btn6_317,R.id.btn6_316,R.id.btn6_314,R.id.btn6_315,R.id.btn6_266,R.id.btn6_335,R.id.btn6_272,R.id.btn6_334,
		R.id.btn6_318,R.id.btn6_233,R.id.btn6_163,R.id.btn6_319,R.id.btn6_269,R.id.btn6_336,R.id.btn6_337,R.id.btn6_270,
		R.id.btn6_168,R.id.btn6_191,R.id.btn6_167,R.id.btn6_190,R.id.btn6_192,R.id.btn6_160,R.id.btn6_193,R.id.btn6_159,
		R.id.btn6_194,R.id.btn6_59, R.id.btn6_60, R.id.btn6_195,R.id.btn6_28, R.id.btn6_197,R.id.btn6_27, R.id.btn6_196,
		R.id.btn6_84, R.id.btn6_199,R.id.btn6_83, R.id.btn6_198,R.id.btn6_200,R.id.btn6_119,R.id.btn6_120,R.id.btn6_201,
		R.id.btn6_242,R.id.btn6_347,R.id.btn6_338,R.id.btn6_314,R.id.btn6_322,R.id.btn6_339,R.id.btn6_348,R.id.btn6_250,
		R.id.btn6_258,R.id.btn6_349,R.id.btn6_340,R.id.btn6_326,R.id.btn6_330,R.id.btn6_341,R.id.btn6_266,R.id.btn6_350,
		R.id.btn6_76, R.id.btn6_219,R.id.btn6_218,R.id.btn6_75, R.id.btn6_220,R.id.btn6_111,R.id.btn6_112,R.id.btn6_221,
		R.id.btn6_225,R.id.btn6_48, R.id.btn6_47, R.id.btn6_224,R.id.btn6_165,R.id.btn6_247,R.id.btn6_143,R.id.btn6_246,
		R.id.btn6_15, R.id.btn6_226,R.id.btn6_227,R.id.btn6_16, R.id.btn6_72, R.id.btn6_229,R.id.btn6_71, R.id.btn6_228,
		R.id.btn6_242,R.id.btn6_243,R.id.btn6_244,R.id.btn6_245,R.id.btn6_148,R.id.btn6_223,R.id.btn6_147,R.id.btn6_222,
		R.id.btn6_252,R.id.btn6_344,R.id.btn6_290,R.id.btn6_353,R.id.btn6_345,R.id.btn6_260,R.id.btn6_354,R.id.btn6_298,
		R.id.btn6_346,R.id.btn6_268,R.id.btn6_355,R.id.btn6_306,R.id.btn6_314,R.id.btn6_356,R.id.btn6_347,R.id.btn6_276,
		R.id.btn6_257,R.id.btn6_61, R.id.btn6_39, R.id.btn6_256,R.id.btn6_258,R.id.btn6_259,R.id.btn6_260,R.id.btn6_261,
		R.id.btn6_157,R.id.btn6_263,R.id.btn6_262,R.id.btn6_135,R.id.btn6_244,R.id.btn6_361,R.id.btn6_352,R.id.btn6_316,
		R.id.btn6_282,R.id.btn6_283,R.id.btn6_284,R.id.btn6_285,R.id.btn6_145,R.id.btn6_287,R.id.btn6_193,R.id.btn6_286,
		R.id.btn6_288,R.id.btn6_195,R.id.btn6_289,R.id.btn6_45, R.id.btn6_31, R.id.btn6_264,R.id.btn6_57, R.id.btn6_265,
		R.id.btn6_300,R.id.btn6_350,R.id.btn6_330,R.id.btn6_359,R.id.btn6_351,R.id.btn6_308,R.id.btn6_360,R.id.btn6_334,
		R.id.btn6_137,R.id.btn6_303,R.id.btn6_213,R.id.btn6_302,R.id.btn6_254,R.id.btn6_329,R.id.btn6_253,R.id.btn6_328,
		R.id.btn6_334,R.id.btn6_342,R.id.btn6_274,R.id.btn6_351,R.id.btn6_282,R.id.btn6_352,R.id.btn6_343,R.id.btn6_244,
		R.id.btn6_322,R.id.btn6_357,R.id.btn6_348,R.id.btn6_284,R.id.btn6_292,R.id.btn6_349,R.id.btn6_326,R.id.btn6_358,
		R.id.btn6_131,R.id.btn6_321,R.id.btn6_320,R.id.btn6_235,
		R.id.btn6_362,R.id.btn6_139,R.id.btn6_140,R.id.btn6_371,R.id.btn6_39, R.id.btn6_363,R.id.btn6_372,R.id.btn6_40,
		R.id.btn6_136,R.id.btn6_386,R.id.btn6_135,R.id.btn6_377,R.id.btn6_31, R.id.btn6_378,R.id.btn6_32, R.id.btn6_387,
		R.id.btn6_8,  R.id.btn6_373,R.id.btn6_7,  R.id.btn6_364,R.id.btn6_374,R.id.btn6_64, R.id.btn6_365,R.id.btn6_63,
		R.id.btn6_379,R.id.btn6_3,  R.id.btn6_4,  R.id.btn6_388,R.id.btn6_380,R.id.btn6_35, R.id.btn6_389,R.id.btn6_36,
		R.id.btn6_100,R.id.btn6_375,R.id.btn6_99, R.id.btn6_366,R.id.btn6_368,R.id.btn6_159,R.id.btn6_371,R.id.btn6_324,
		R.id.btn6_96, R.id.btn6_390,R.id.btn6_95, R.id.btn6_381,R.id.btn6_385,R.id.btn6_326,R.id.btn6_382,R.id.btn6_329,
		R.id.btn6_323,R.id.btn6_372,R.id.btn6_59, R.id.btn6_369,R.id.btn6_353,R.id.btn6_367,R.id.btn6_252,R.id.btn6_376,
		R.id.btn6_383,R.id.btn6_155,R.id.btn6_328,R.id.btn6_386,R.id.btn6_55, R.id.btn6_384,R.id.btn6_327,R.id.btn6_387,
		R.id.btn6_325,R.id.btn6_367,R.id.btn6_370,R.id.btn6_322,R.id.btn6_382,R.id.btn6_354,R.id.btn6_260,R.id.btn6_391
	};  // total clicks is 512 + 72 = 592,

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

	private int i = 0;
	private int m = 2;
	private String filename;

	private float midX;
	private float midY;
	int[] loc = new int[2];

	private Long tim1,tim2;
	private float startX,startY;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_tap2);
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
				if(i < image.length){
					TextView tev = findViewById(image[i]);
					tev.getLocationOnScreen(loc);
					midX = loc[0] + (float)tev.getWidth()/2;
					midY = loc[1] + (float)tev.getWidth()/2;
				}
//				String str1 = "tap2,"+num[i] + ","  + loc[0] + ","+loc[1]+ "," +
//						x + "," + y + "," + z + "," + tim1;
//				findViewById(image[i]).setVisibility(View.INVISIBLE);
//				SaveData.store(str1,filename);
				break;
//			case MotionEvent.ACTION_MOVE:
//				float x0 = event.getRawX();
//				float y0 = event.getRawY();
//				float z0 = event.getSize();
//				String str0 = "      ,,"+ x0 + "," + y0 + "," + z0 ;
//				SaveData.store(str0,filename);
//				break;
			case MotionEvent.ACTION_UP:
				float x1 = event.getRawX();
				float y1 = event.getRawY();
				float z1 = event.getSize();

				double distX = Math.pow(startX-x1,2);
				double distY = Math.pow(startY-y1,2);
				double offset = Math.sqrt(distX+distY);
				//String str2 = "      ,,"+ x1 + "," + y1 + "," + z1 + "," + offset;

				tim2 = System.currentTimeMillis();
				String str2 = num[i]+ "-" + num[i+1] + ","+String.valueOf(tim2-tim1)+","+offset;
				SaveData.store(str2,filename);

//				if(i == 591) {
//					SaveData.store("       ",filename);
//					SaveData.tips(this);
//					new Timer().schedule(new TimerTask() {
//						@Override
//						public void run() {
//							startActivity(new Intent(tap2.this,TaskList.class));
//						}
//					},2000);
//				}else{
//					findViewById(image[m]).setVisibility(View.VISIBLE);
//					i = i+1;
//					m = m+1;
//				}
				findViewById(image[i]).setVisibility(View.INVISIBLE);
				i = i+1;
				while(i == 591){
					SaveData.store("       ",filename);
					SaveData.tips(this);
//					new Timer().schedule(new TimerTask() {
//						@Override
//						public void run() {
//							startActivity(new Intent(tap2.this,TaskList.class));
//						}
//					},2000);
				}
				findViewById(image[m]).setVisibility(View.VISIBLE);
				m = m+1;
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
