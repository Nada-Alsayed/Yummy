package eg.gov.iti.yummy.fragments.favourite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import eg.gov.iti.yummy.R;

public class AdapterFavList extends RecyclerView.Adapter<AdapterFavList.Holder> {
    private List<FavList> listdata;
    private Context context;
    // RecyclerView recyclerView;
    public AdapterFavList(Context context, List<FavList> listdata) {
        this.context = context;
        this.listdata = listdata;
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
        holder.textView1.setText( listdata.get(position).getTitle());
        holder.textView2.setText( listdata.get(position).getDesc());
        holder.imageView1.setImageResource( listdata.get(position).getImgId());
        holder.imageView2.setImageResource( listdata.get(position).getImgId2());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context.getApplicationContext(), listdata.get(position).getTitle(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        ImageView imageView1;
        ImageView imageView2;
        TextView textView1;
        TextView textView2;
    ConstraintLayout constraintLayout;
        public Holder(View itemView) {
            super(itemView);
            this.imageView1 = (ImageView) itemView.findViewById(R.id.imageViewFav);
            this.imageView2 = (ImageView) itemView.findViewById(R.id.imageViewDel);
            this.textView1 = (TextView) itemView.findViewById(R.id.txtMealName);
            this.textView2 = (TextView) itemView.findViewById(R.id.txtMealOrigin);
            this.constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.card);

        }

    }
}
