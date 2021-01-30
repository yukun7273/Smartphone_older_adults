package com.example.wwb.click2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TaskList extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_task_list);

		Button btn1 = findViewById(R.id.Button_1);
		Button btn2 = findViewById(R.id.Button_2);
		Button btn3 = findViewById(R.id.Button_3);
		Button btn4 = findViewById(R.id.Button_4);
		Button btn5 = findViewById(R.id.Button_5);
		Button btn6 = findViewById(R.id.Button_6);
		Button btn7 = findViewById(R.id.Button_7);
		Button btn8 = findViewById(R.id.Button_8);
		Button btn9 = findViewById(R.id.Button_9);
		Button btn10 = findViewById(R.id.Button_10);
		Button btn11 = findViewById(R.id.Button_11);
		Button btn12 = findViewById(R.id.Button_12);


		btn1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent1 = new Intent(TaskList.this,tap1.class);
				startActivity(intent1);
			}
		});

		btn2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent1 = new Intent(TaskList.this,tap2.class);
				startActivity(intent1);
			}
		});

		btn3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(new Intent(TaskList.this,tap0.class));
			}
		});

		btn4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent1 = new Intent(TaskList.this,drag2.class);
				startActivity(intent1);
			}
		});
		btn5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent1 = new Intent(TaskList.this, drag1.class);
				startActivity(intent1);
//			    passer();
			}
		});
		btn6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(TaskList.this,drag0.class));
			}
		});

		btn7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(TaskList.this, scale1.class);
				startActivity(intent);
			}
		});
		btn8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(TaskList.this,scale2.class);
				startActivity(intent);
			}
		});

		btn9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(TaskList.this,scale0.class));
			}
		});

		btn10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(TaskList.this, rotate1.class);
				startActivity(intent);
			}
		});

		btn11.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(TaskList.this,rotate2.class);
				startActivity(intent);
			}
		});

		btn12.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(TaskList.this,rotate0.class));
			}
		});
	}
}
