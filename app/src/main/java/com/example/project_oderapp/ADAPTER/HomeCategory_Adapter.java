package com.example.project_oderapp.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_oderapp.MODEL.Category;
import com.example.project_oderapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeCategory_Adapter extends RecyclerView.Adapter<HomeCategory_Adapter.CategoryItemViewHolder> {
    List<Category> categoryList = new ArrayList<>();
    Context context;

    public HomeCategory_Adapter(Context context, List<Category> categoryList)
    {
        this.context = context;
        this.categoryList = categoryList;
    }
    @NonNull
    @Override
    public HomeCategory_Adapter.CategoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category_food, parent, false);

        return new HomeCategory_Adapter.CategoryItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCategory_Adapter.CategoryItemViewHolder holder, int position) {
        holder.nameCategory.setText(categoryList.get(position).getName());
        Picasso.with(context).load(categoryList.get(position).getImage()).into(holder.imageCategory);

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CategoryItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageCategory;
        TextView  nameCategory;
        public CategoryItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageCategory = itemView.findViewById(R.id.itemCategory_imagefood);
            nameCategory= itemView.findViewById(R.id.itemCategory_namefood);
        }
    }
}
