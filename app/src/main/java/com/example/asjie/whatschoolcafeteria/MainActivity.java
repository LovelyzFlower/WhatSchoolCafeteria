package com.example.asjie.whatschoolcafeteria;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    DatePicker datePicker1;
    Button button;
    Button selectbutton;
    String msg;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SimpleDateFormat dateFormat = new  SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault());
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        msg = dateFormat.format(date);

        datePicker1 = (DatePicker) findViewById(R.id.datePicker1);
        button = (Button) findViewById(R.id.button);
        selectbutton = (Button) findViewById(R.id.select);

        selectbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SelectActivity.class);
                startActivity(intent);
            }
        });
        datePicker1.init(datePicker1.getYear(), datePicker1.getMonth(),datePicker1.getDayOfMonth(),
                new DatePicker.OnDateChangedListener(){
                    @Override
                    public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                        i1++;
                        msg = String.format("%d-%d-%d",i,i1,i2);
                        if(i1<10)
                        { msg = String.format("%d-0%d-%d",i,i1,i2);
                            if(i2<10)
                            {
                                msg = String.format("%d-0%d-0%d",i,i1,i2);
                            }
                        }
                        else if(i2<10){
                            msg = String.format("%d-%d-0%d",i,i1,i2);
                        }

                    }
                });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //String selectDate = sdf.format(new Date(calendar1.getDate()));

                Log.d("날짜 : ", msg);

               Intent intent = new Intent(getApplicationContext(), OutputActivity.class);
               intent.putExtra("date",msg);
               startActivity(intent);
            }
        });
    }

}

