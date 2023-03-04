package eg.gov.iti.yummy.favourite.view;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.meal_details.view.page_item_details;
import eg.gov.iti.yummy.model.MealDetail;


public class AdapterFavList extends RecyclerView.Adapter<AdapterFavList.Holder> {

     List<FavList> listData;
    List<MealDetail> list;

    onFavouriteClickListener listener;


    public void setListData(List<FavList> listData) {

        this.listData = listData;
    }
    public void setList(List<MealDetail> mealDetails) {

        this.list = mealDetails;
    }

    private Context context;

    public AdapterFavList(List<MealDetail> list, onFavouriteClickListener listener, Context context) {
        this.list = list;
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterFavList.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.recyclerview_row_fav,parent,false);
        Holder vh=new Holder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFavList.Holder holder,@SuppressLint("RecyclerView") int position) {
        MealDetail meal = list.get(position);
        holder.title.setText( list.get(position).strMeal);
        holder.origin.setText( list.get(position).strArea);
        Glide.with(context)
                .load(list.get(position).strMealThumb)
                .into(holder.thumb);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog=new MaterialAlertDialogBuilder(context).setTitle("Delete")
                                .setMessage("Are you sure you want to delete this item?").setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listener.OnClick(meal);
                                dialog.dismiss();
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create();
                dialog.show();
            }
        });

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, page_item_details.class);
                intent.putExtra("MealName",holder.title.getText().toString());
                intent.putExtra("tableType","favourite");
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView title;
        TextView origin;
        ImageView thumb;
        ImageView delete;
        ConstraintLayout layout;

        public Holder(@NonNull View itemView) {
            super(itemView);
            this.thumb = (ImageView) itemView.findViewById(R.id.imageViewFav);
            this.title = (TextView) itemView.findViewById(R.id.txtMealName);
            this.origin = (TextView) itemView.findViewById(R.id.txtMealOrigin);
            this.delete = (ImageView) itemView.findViewById(R.id.imageViewDel);
            this.layout = (ConstraintLayout) itemView.findViewById(R.id.favListItem);
        }

    }
}
