package eg.gov.iti.yummy.available_dishes.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.favourite.view.FavList;

public class AdapterAvailableDishes extends RecyclerView.Adapter<AdapterAvailableDishes.Holder>{
    private List<FavList> listdata;
    private Context context;
    // RecyclerView recyclerView;
    public AdapterAvailableDishes(Context context, List<FavList> listdata) {
        this.context = context;
        this.listdata = listdata;
    }
    @NonNull
    @Override
    public AdapterAvailableDishes.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.recyclerview_row_availabledish,parent,false);
        AdapterAvailableDishes.Holder vh=new AdapterAvailableDishes.Holder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAvailableDishes.Holder holder,@SuppressLint("RecyclerView") int position) {

//        holder.imageView1.setImageResource( listdata.get(position).getImgId());
//        holder.imageView2.setImageResource( listdata.get(position).getImgId2());
//        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context.getApplicationContext(), "available dish",Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        ImageView imageView1;
        ImageView imageView2;

        public Holder(View itemView) {
            super(itemView);
            this.imageView1 = (ImageView) itemView.findViewById(R.id.imageView1);
            this.imageView2 = (ImageView) itemView.findViewById(R.id.imglove);


        }

    }
}
