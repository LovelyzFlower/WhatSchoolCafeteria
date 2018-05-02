package com.example.asjie.whatschoolcafeteria;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by asjie on 2017-08-03.
 */

public class SelectActivity extends AppCompatActivity {

    Button button;
    SQLiteDatabase db;
    EditText like;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        button = (Button) findViewById(R.id.button);
        like = (EditText) findViewById(R.id.like);
        createDatabase("DB");



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = like.getText().toString();
                db.execSQL("insert into Menu (name, check) values ('"+str+", true");
            }
        });

    }

    private void GetDB()
    {
        Cursor c1 = db.rawQuery("select count(*) as Total from Menu" , null);
        int count = c1.getCount();
        for(int i=0;i<count;i++)
        {
            c1.moveToNext();
            String food = c1.getString(0);

        }
        c1.close();
    }

    private void createList(String food)
    {
        RelativeLayout likelayout = (RelativeLayout) findViewById(R.id.likelayout);
        TextView textView = new TextView(this);
        textView.setText(food);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        likelayout.addView(textView);

    }

    private void createDatabase(String name)
    {
        try
        {
            db=openOrCreateDatabase(name, MODE_WORLD_WRITEABLE,null);
            db.execSQL("create table if not exists Menu ("+"name text, check boolean);");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}