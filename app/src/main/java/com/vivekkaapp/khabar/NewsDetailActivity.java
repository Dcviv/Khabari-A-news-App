package com.vivekkaapp.khabar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class NewsDetailActivity extends AppCompatActivity {
    String title, description, content, imageUrl, url;
    private TextView titleTv,subsTV, contentTv;
    private ImageView newsIv;
    private Button but;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        title= getIntent().getStringExtra("title");
        content= getIntent().getStringExtra("content");
        description= getIntent().getStringExtra("description");
        imageUrl= getIntent().getStringExtra("image");
        url= getIntent().getStringExtra("url");
        titleTv= findViewById(R.id.newsTitleTV);
        subsTV= findViewById(R.id.sub_des_TV);
        contentTv= findViewById(R.id.contentTV);
        newsIv= findViewById(R.id.newsIV);
        but= findViewById(R.id.button);
        titleTv.setText(title);
        subsTV.setText(description);
        contentTv.setText(content);
        Picasso.get().load(url).into(newsIv);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(content));
                startActivity(i);
            }
        });
    }
}