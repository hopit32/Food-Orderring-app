package com.example.project_oderapp.ADAPTER;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_oderapp.INTERFACE.ChangeBoderItemCategory;
import com.example.project_oderapp.MODEL.Category;
import com.example.project_oderapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeCategory_Adapter extends RecyclerView.Adapter<HomeCategory_Adapter.CategoryItemViewHolder> {
    List<Category> categoryList = new ArrayList<>();
    Context context;
    ChangeBoderItemCategory changeBoderItemCategory;
    private static String postion_select= "default";
    private int position_previous=-2;

    public HomeCategory_Adapter(Context context, List<Category> categoryList, ChangeBoderItemCategory changeBoderItemCategory)
    {
        this.changeBoderItemCategory= changeBoderItemCategory;
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
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    holder.border.setBackgroundResource(R.drawable.selected_item_category);
                    holder.imageCategory.requestLayout();
                    holder.imageCategory.getLayoutParams().height = (int) context.getResources().getDimension(R.dimen.item_height);
                    holder.imageCategory.getLayoutParams().width = (int) context.getResources().getDimension(R.dimen.item_width);
                    changeBoderItemCategory.sendData(true, String.valueOf(categoryList.get(position).id), position);

            }
        });

    }
    public void UPdate_boderCart(int position)
    {

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CategoryItemViewHolder extends RecyclerView.ViewHolder {
        Boolean select= true;
        String view;
        ImageView imageCategory;
        TextView  nameCategory;
        RelativeLayout relativeLayout, border;
        public CategoryItemViewHolder(@NonNull View itemView) {
            super(itemView);
            border = itemView.findViewById(R.id.itemCategory_border);
            relativeLayout = itemView.findViewById(R.id.layout_itemcategory);
            imageCategory = itemView.findViewById(R.id.itemCategory_imagefood);
            nameCategory= itemView.findViewById(R.id.itemCategory_namefood);
        }
    }
}
