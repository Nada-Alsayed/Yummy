package eg.gov.iti.yummy.favourite.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.favourite.FavList;
import eg.gov.iti.yummy.meal_details.view.page_item_details;
import eg.gov.iti.yummy.model.MealDetail;


public class AdapterFavList extends RecyclerView.Adapter<AdapterFavList.Holder> {

    private List<FavList> listData;

    private onFavouriteClickListener listener;


    public void setListData(List<FavList> listData) {
        this.listData = listData;
    }

    private Context context;
    public AdapterFavList(Context context, onFavouriteClickListener _listener) {
        this.context = context;
        this.listener = _listener;
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
        FavList meal = listData.get(position);
        holder.title.setText( listData.get(position).getName());
        holder.origin.setText( listData.get(position).getOrigin());
        Glide.with(context)
                .load(listData.get(position).getThumbnail())
                .into(holder.thumb);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnClick(meal);
            }
        });

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, page_item_details.class);
                intent.putExtra("MealName",holder.title.getText().toString());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView title;
        TextView origin;
        ImageView thumb;
        ImageView delete;
        ConstraintLayout layout;

        public Holder(View itemView) {
            super(itemView);
            this.thumb = (ImageView) itemView.findViewById(R.id.imageViewFav);
            this.title = (TextView) itemView.findViewById(R.id.txtMealName);
            this.origin = (TextView) itemView.findViewById(R.id.txtMealOrigin);
            this.delete = (ImageView) itemView.findViewById(R.id.imageViewDel);
            this.layout = (ConstraintLayout) itemView.findViewById(R.id.favListItem);
        }

    }
}
