package com.example.wwb.click2;


import android.app.Activity;
import android.os.Environment;
import android.view.Gravity;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

class SaveData  {

	static void store(String string, String fileName){

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

	static void tips(Activity act1){
		Toast t = Toast.makeText(act1,"任务完成",Toast.LENGTH_LONG);
			t.setGravity(Gravity.CENTER,0,0);
			t.show();
	}
}
