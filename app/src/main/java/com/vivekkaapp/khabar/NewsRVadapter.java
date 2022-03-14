package com.vivekkaapp.khabar;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.time.temporal.Temporal;
import java.util.ArrayList;

public class NewsRVadapter extends RecyclerView.Adapter<NewsRVadapter.ViewHolder> {
    private ArrayList<Articles> articlesArrayList;
    private Context context;

    public NewsRVadapter(ArrayList<Articles> articlesArrayList, Context context) {
        this.articlesArrayList = articlesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Articles articles = articlesArrayList.get(position);
        holder.titleTV.setText(articles.getTitle());
        holder.sub_head_TV.setText(articles.getDescription());
        Picasso.get().load(articles.getUrl()).into(holder.newsImageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewsDetailActivity.class);
                intent.putExtra("title", articles.getTitle());
                intent.putExtra("content", articles.getContent());
                intent.putExtra("description", articles.getDescription());
                intent.putExtra("image", articles.getUrlToImage());
                intent.putExtra("url", articles.getUrl());
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return articlesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTV,sub_head_TV;
        private ImageView newsImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.newsTitleTV);
            sub_head_TV= itemView.findViewById(R.id.news_sub_head_TV);
            newsImageView= itemView.findViewById(R.id.news_ImageView);

        }
    }
}
