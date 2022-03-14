package com.vivekkaapp.khabar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.net.InterfaceAddress;
import java.util.ArrayList;

public class CategoryRVadaptor extends RecyclerView.Adapter<CategoryRVadaptor.ViewHolder> {
    private ArrayList<CategoryRVmodalClass> categoryRVmodalClassArrayList;
    private Context context;
    private CategoryClickInterface categoryClickInterface;

    public CategoryRVadaptor(ArrayList<CategoryRVmodalClass> categoryRVmodalClassArrayList, Context context, CategoryClickInterface categoryClickInterface) {
        this.categoryRVmodalClassArrayList = categoryRVmodalClassArrayList;
        this.context = context;
        this.categoryClickInterface = categoryClickInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CategoryRVmodalClass categoryRVmodalClass = categoryRVmodalClassArrayList.get(position);
        holder.categorytv.setText(categoryRVmodalClass.getCategory());
        Picasso.get().load(categoryRVmodalClass.getCategoryImageUrl()).into(holder.catIV);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryClickInterface.onCategoryClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryRVmodalClassArrayList.size();
    }

    public interface CategoryClickInterface{
        void onCategoryClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView categorytv;
        private ImageView catIV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categorytv = itemView.findViewById(R.id.category_name);
            catIV = itemView.findViewById(R.id.categoryImageView);

        }
    }
}
