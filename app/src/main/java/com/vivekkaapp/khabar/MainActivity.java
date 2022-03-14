package com.vivekkaapp.khabar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoryRVadaptor.CategoryClickInterface {
    private RecyclerView newsRV, categoryRV;
    private ProgressBar loadingPB;
    private ArrayList<Articles> articlesArrayList;
    private ArrayList<CategoryRVmodalClass> categoryRVmodalClassArrayList;
    private CategoryRVadaptor categoryRVadaptor;
    private NewsRVadapter newsRVadapter;

// 44432ca90582447e88c9c6b71e95bded
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsRV = findViewById(R.id.newsRV);
        categoryRV = findViewById(R.id.categoriesRV);
        loadingPB= findViewById(R.id.progressBar);
        articlesArrayList= new ArrayList<>();
        categoryRVmodalClassArrayList= new ArrayList<>();
        newsRVadapter= new NewsRVadapter(articlesArrayList,this);
        categoryRVadaptor = new CategoryRVadaptor(categoryRVmodalClassArrayList, this, this::onCategoryClick);
        newsRV.setLayoutManager(new LinearLayoutManager(this));
        newsRV.setAdapter(newsRVadapter);
        //categoryRV.setLayoutManager(new LinearLayoutManager(this));
        categoryRV.setAdapter(categoryRVadaptor);
        getCategories();
        getNews("All");
        newsRVadapter.notifyDataSetChanged();


    }

    private void getCategories(){
        categoryRVmodalClassArrayList.add(new CategoryRVmodalClass("All", "https://images.unsplash.com/photo-1566378246598-5b11a0d486cc?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1534&q=80"));
        categoryRVmodalClassArrayList.add(new CategoryRVmodalClass("Technology", "https://images.unsplash.com/photo-1485827404703-89b55fcc595e?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80"));
        categoryRVmodalClassArrayList.add(new CategoryRVmodalClass("Science", "https://images.unsplash.com/photo-1532094349884-543bc11b234d?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80"));
        categoryRVmodalClassArrayList.add(new CategoryRVmodalClass("Sports", "https://images.unsplash.com/photo-1575361204480-aadea25e6e68?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=751&q=80"));
        categoryRVmodalClassArrayList.add(new CategoryRVmodalClass("Health", "https://images.unsplash.com/photo-1505751172876-fa1923c5c528?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80"));
        categoryRVmodalClassArrayList.add(new CategoryRVmodalClass("Education", "https://images.unsplash.com/photo-1503676260728-1c00da094a0b?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=709&q=80"));
        categoryRVmodalClassArrayList.add(new CategoryRVmodalClass("General", "https://images.unsplash.com/photo-1572949645841-094f3a9c4c94?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=334&q=80"));
        categoryRVmodalClassArrayList.add(new CategoryRVmodalClass("Business", "https://images.unsplash.com/photo-1454165804606-c3d57bc86b40?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80"));
        categoryRVmodalClassArrayList.add(new CategoryRVmodalClass("Entertainment", "https://images.unsplash.com/photo-1603739903239-8b6e64c3b185?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=751&q=80"));
        categoryRVadaptor.notifyDataSetChanged();


    }
    private void getNews(String category){
        loadingPB.setVisibility(View.VISIBLE);
        articlesArrayList.clear();
        String categoryURL="https://newsapi.org/v2/top-headlines/?country=in&category=" + category +"&apiKey=44432ca90582447e88c9c6b71e95bded";
        String url = "https://newsapi.org/v2/top-headlines?country=in&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apiKey=44432ca90582447e88c9c6b71e95bded";
        String BASE_URL="https://newsapi.org/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        Retrofit_Inter retrofit_inter = retrofit.create(Retrofit_Inter.class);
        Call<NewsModalClass> call;
        if(category.equals("All")){
            call= retrofit_inter.getAllNews(url);
        }
        else{
            call= retrofit_inter.getNewsByCategory(categoryURL);
        }
        call.enqueue(new Callback<NewsModalClass>() {
            @Override
            public void onResponse(Call<NewsModalClass> call, Response<NewsModalClass> response) {
                NewsModalClass newsModalClass= response.body();
                loadingPB.setVisibility(View.GONE);
                ArrayList<Articles> articles= newsModalClass.getArticles();
                for(int i=0; i<articles.size(); i++){
                    articlesArrayList.add(new Articles(articles.get(i).getTitle(), articles.get(i).getDescription(), articles.get(i).getUrlToImage(), articles.get(i).getUrl(),articles.get(i).getContent()));
                }
                newsRVadapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<NewsModalClass> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to load...", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onCategoryClick(int position) {
        String category= categoryRVmodalClassArrayList.get(position).getCategory();
        getNews(category);

    }
}