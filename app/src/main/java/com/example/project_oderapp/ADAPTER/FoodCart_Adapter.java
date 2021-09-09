package com.example.project_oderapp.ADAPTER;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_oderapp.INTERFACE.ChangeNumberItemListenner;
import com.example.project_oderapp.INTERFACE.SendLinkImage;
import com.example.project_oderapp.INTERFACE.Update_layout_Cart;
import com.example.project_oderapp.MANAGE.ManageCard;
import com.example.project_oderapp.MANAGE.ShareReferenceDatabase;
import com.example.project_oderapp.MODEL.Product;
import com.example.project_oderapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FoodCart_Adapter extends RecyclerView.Adapter<FoodCart_Adapter.FoodCartItemViewHolder> {
    Context context;
    ArrayList<Product> list_inCart = new ArrayList<>();
    ChangeNumberItemListenner changeNumberItemListenner;
    ManageCard manageCard;
    ShareReferenceDatabase shareReferenceDatabase;
    Update_layout_Cart update_layout_cart;

    public FoodCart_Adapter(Context context, ArrayList<Product> list_inCart, ChangeNumberItemListenner changeNumberItemListenner, Update_layout_Cart update_layout_cart)
    {
        this.context = context;
        this.list_inCart = list_inCart;
        manageCard= new ManageCard(context);
        this.changeNumberItemListenner = changeNumberItemListenner;
        this.update_layout_cart= update_layout_cart;
        shareReferenceDatabase = new ShareReferenceDatabase(context);
    }
    @NonNull
    @Override
    public FoodCart_Adapter.FoodCartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(context).inflate(R.layout.item_foodcard,parent,false);
        return new FoodCart_Adapter.FoodCartItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodCart_Adapter.FoodCartItemViewHolder holder, int position) {
        Product product = list_inCart.get(position);
        holder.name.setText(product.getName());
        holder.desciption.setText(product.getDescription());
        holder.price.setText(product.getPrice());
        holder.numberinCart.setText(String.valueOf(product.getNumberinCart()));
        Picasso.with(context)
                .load(product.getImage()).resize(200,0).into(holder.image);
      holder.img_add.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              manageCard.PlusNumber(list_inCart,holder.getAdapterPosition(), new ChangeNumberItemListenner() {
                  @Override
                  public void change() {
                       notifyDataSetChanged();
                       changeNumberItemListenner.change();
                  }
              });
          }
      });
      holder.img_reduce.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              manageCard.MinNumberProduct(list_inCart, holder.getAdapterPosition(), new ChangeNumberItemListenner() {
                  @Override
                  public void change() {
                      notifyDataSetChanged();
                      changeNumberItemListenner.change();
                  }
              }, new Update_layout_Cart() {
                  @Override
                  public void setData() {
                      update_layout_cart.setData();
                  }
              });
          }
      });

      holder.img_delete_Item.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              AlertDialog.Builder builder = new AlertDialog.Builder(context);
              builder.setTitle("Notification !");
              builder.setTitle("Are you sure you want to remove this product from your cart ?");
              builder.setCancelable(true);
              builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                          dialogInterface.cancel();
                  }
              });
              builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                      list_inCart.remove(product);
                      shareReferenceDatabase.putListObject("CartList",list_inCart);
                      notifyDataSetChanged();
                      changeNumberItemListenner.change();

                      int cout_item = manageCard.getListCart().size();
                      if (cout_item==0)
                      {
                          update_layout_cart.setData();
                      }

                  }
              });
              builder.show();
          }
      });

      //
      Double x = product.getNumberinCart() * Double.parseDouble(product.getPrice());
      Double y = Math.round(x)*100/100.0;
      holder.priceTotalItem.setText(String.valueOf(y));

    }

    @Override
    public int getItemCount() {
        return list_inCart.size();
    }

    public class FoodCartItemViewHolder extends RecyclerView.ViewHolder {
        TextView name,desciption,numberinCart,price,priceTotalItem;
        ImageView image,img_reduce,img_add,img_delete_Item;
        public FoodCartItemViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_foodcard_name);
            desciption = itemView.findViewById(R.id.item_foodcard_description);
            numberinCart = itemView.findViewById(R.id.item_foodcard_numberfoodinCart);
            price= itemView.findViewById(R.id.item_foodcard_price);
            priceTotalItem = itemView.findViewById(R.id.item_foodcard_priceItem);
            image = itemView.findViewById(R.id.item_foodcard_image);
            img_reduce = itemView.findViewById(R.id.item_foodcard_reduce);
            img_add = itemView.findViewById(R.id.item_foodcard_add);
            img_delete_Item = itemView.findViewById(R.id.img_delete_item);
        }
    }
}
