package com.example.project_oderapp.MANAGE;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.example.project_oderapp.INTERFACE.ChangeNumberItemListenner;
import com.example.project_oderapp.INTERFACE.Update_layout_Cart;
import com.example.project_oderapp.MODEL.Product;

import java.util.ArrayList;
import java.util.List;

public class ManageCard {
    private Context context;
    private ShareReferenceDatabase shareReferenceDatabase;

    public ManageCard(Context context)
    {
        this.context = context;
        this.shareReferenceDatabase = new ShareReferenceDatabase(context);
    }
    public void InsertProduct(Product item)
    {
        ArrayList<Product> productArrayList = getListCart();
       int x=1;
        for (int i=0;i<productArrayList.size();i++)
        {
            if (item.getId().equals(productArrayList.get(i).getId()))
            {
                int new_count= item.getNumberinCart()+ productArrayList.get(i).getNumberinCart();
                productArrayList.get(i).setNumberinCart(new_count);
                x=2;
            }
        }
        if (x==1)
        {
            productArrayList.add(item);
        }
        shareReferenceDatabase.putListObject("CartList",productArrayList);
        Toast.makeText(context,String.valueOf(getListCart().size()), Toast.LENGTH_LONG).show();


    }
    public void UpdateCart(ArrayList<Product> products)
    {
        shareReferenceDatabase.putListObject("CartList", products);
    }
    public ArrayList<Product> getListCart() {
              return shareReferenceDatabase.getListObject("CartList");
    }
    public void Clear()
    {
        shareReferenceDatabase.clear();
    }
    public void MinNumberProduct(ArrayList<Product>productList, int position, ChangeNumberItemListenner changeNumberItemListenner,Update_layout_Cart update_layout_cart)
    {
        int x= productList.get(position).getNumberinCart();
        if (x>1)
        {
            productList.get(position).setNumberinCart(x-1);
            changeNumberItemListenner.change();
        }
        else
        {
            AlertDialog .Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Notification !");
            builder.setTitle("Are you sure you want to remove this product from your cart ?");
            builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    productList.remove(position);
                    changeNumberItemListenner.change();
                    int y= productList.size();
                    if (y==0)
                    {
                        update_layout_cart.setData();
                    }
                }
            });
            builder.show();
        }

   shareReferenceDatabase.putListObject("CartList",productList);
        changeNumberItemListenner.change();

    }
    public void PlusNumber(ArrayList<Product> productList, int position, ChangeNumberItemListenner changeNumberItemListenner )
    {
        int x= productList.get(position).getNumberinCart();
        productList.get(position).setNumberinCart(x+1);
        shareReferenceDatabase.putListObject("CartList",productList);
        changeNumberItemListenner.change();

    }
    public Double GetTotalItem()
    {
        ArrayList<Product> list= getListCart();
        Double total=0.0;
        for (int n=0;n<list.size();n++)
        {
            Double price = Double.parseDouble(getListCart().get(n).getPrice());
            total = total + (getListCart().get(n).getNumberinCart() * price );
        }

        return total;
    }

}
