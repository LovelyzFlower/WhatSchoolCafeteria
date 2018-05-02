package com.example.asjie.whatschoolcafeteria;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import static com.example.asjie.whatschoolcafeteria.R.layout.activity_output;

/**
 * Created by asjie on 2017-08-02.
 */

public class OutputActivity extends Activity{

    TextView std;
    TextView lib;
    TextView home;
    String selectDate;
    String str;
    String str1;
    String str2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_output);
        std = (TextView)findViewById(R.id.std);
        home = (TextView)findViewById(R.id.home);
        lib = (TextView)findViewById(R.id.lib);
        Intent intent = getIntent();
        selectDate = intent.getExtras().getString("date");
       // Toast.makeText(this,selectDate,Toast.LENGTH_LONG).show();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect("http://www.donga.ac.kr/MM_PAGE/SUB007/SUB_007005005.asp?PageCD=007005005&seldate="
                            + selectDate
                            + "#st").get();
                    Document doc2 = doc2 = Jsoup.connect("http://hanlim.donga.ac.kr/SubPage/SUB001002/SUB001002007.asp?seldate="
                            + selectDate).get();

                    Elements a = doc.select(".sk03TBL");
                    Element where = a.get(13);
                    Elements temp = where.select("td");
                    str = temp.toString();
                    str = str.replace("<td> ","");
                    str = str.replace("</td>","");
                    str = str.replace("&amp;","&");
                    str = str.replace("+","&");
                    str = str.replace("<br> <br>","");
                    str = str.replace(" ","");
                    str = str.replace("</font>","");
                    str = str.replace("<fontcolor=\"#FF6600\">", "<br>");
                    str = str.replace("<br>","\n");
                    if(str.equals("<p>&nbsp;</p>"))
                        str="오늘은 식당운영을 안해요";

                    where = a.get(15);
                    temp = where.select("td");
                    str1 = temp.toString();

                    str1 = str1.replace("&amp;","&");
                    str1 = str1.replace("+","&");
                    str1 = str1.replace("<td> ","");
                    str1 = str1.replace("</td>","");
                    str1 = str1.replace("<br> <br>","");
                    str1 = str1.replace(" ","");
                    str1 = str1.replace("</font>","");
                    str1 = str1.replace("<fontcolor=\"#FF6600\">", "<br>");
                    str1 = str1.replace("<br>","\n");
                    if(str1.equals("<p>&nbsp;</p>"))
                        str1="오늘은 식당운영을 안해요";

                    a = doc2.select("[style=padding:5px;]");
                    where = a.get(1);
                    str2 = where.toString();
                    str2 = str2.replace("</td>","");
                    str2 = str2.replace("&amp;","&");
                    str2 = str2.replace("+","&");
                    str2 = str2.replace("<br><br>","");
                    str2 = str2.replace(" ","");
                    str2 = str2.replace("<tdstyle=\"padding:5px;\">","");
                    str2 = str2.replace("<br>","\n");
                    str2 = str2.replace("12:30~13:30","");


                    new Thread()
                   {
                       public void run()
                       {
                           Message msg = handler.obtainMessage();

                           handler.sendMessage(msg);
                       }
                   }.start();

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }}
        });
        thread.start();

        }

      final Handler handler=new Handler()
      {
          public void handleMessage(Message msg)
          {
              std.setText(str);
              lib.setText(str1);
              home.setText(str2);
          }
      };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onDestroy();
        finish();
    }
}


