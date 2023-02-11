package eg.gov.iti.yummy.favourite.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.favourite.FavList;

public class AdapterFavList extends RecyclerView.Adapter<AdapterFavList.Holder> {
    private List<FavList> listData;

    public void setListData(List<FavList> listData) {
        this.listData = listData;
    }

    private Context context;
    public AdapterFavList(Context context, List<FavList> listData) {
        this.context = context;
        this.listData = listData;
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
        holder.title.setText( listData.get(position).getName());
        holder.origin.setText( listData.get(position).getOrigin());
        Glide.with(context)
                .load(listData.get(position).getThumbnail())
                .into(holder.thumb);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView title;
        TextView origin;
        ImageView thumb;

        public Holder(View itemView) {
            super(itemView);
            this.thumb = (ImageView) itemView.findViewById(R.id.imageViewFav);
            this.title = (TextView) itemView.findViewById(R.id.txtMealName);
            this.origin = (TextView) itemView.findViewById(R.id.txtMealOrigin);
        }

    }
}
