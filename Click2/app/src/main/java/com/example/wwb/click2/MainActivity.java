package com.example.wwb.click2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_start = findViewById(R.id.start);

	    btn_start.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
			    Intent intent = new Intent(MainActivity.this,TaskList.class);
			    startActivity(intent);
			    passer();
		    }
	    });
    }



    public void passer(){

	    SharedPreferences sp;
	    SharedPreferences.Editor editor;
	    String subject;

	    EditText editText = findViewById(R.id.edt);
	    subject = editText.getText().toString();
	    sp = getSharedPreferences("order",MODE_PRIVATE);
	    editor = sp.edit();
	    editor.putString("bh",subject);
	    editor.apply();
    }
}



