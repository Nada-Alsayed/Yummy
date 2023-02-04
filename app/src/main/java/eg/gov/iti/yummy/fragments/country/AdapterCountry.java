package eg.gov.iti.yummy.fragments.country;

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
import eg.gov.iti.yummy.fragments.favourite.AdapterFavList;
import eg.gov.iti.yummy.fragments.favourite.FavList;

public class AdapterCountry extends RecyclerView.Adapter<AdapterCountry.Holder>{
    private List<FavList> listdata;
    private Context context;
    // RecyclerView recyclerView;
    public AdapterCountry(Context context, List<FavList> listdata) {
        this.context = context;
        this.listdata = listdata;
    }
    @NonNull
    @Override
    public AdapterCountry.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.recyclerview_row_country,parent,false);
        AdapterCountry.Holder vh=new AdapterCountry.Holder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCountry.Holder holder,@SuppressLint("RecyclerView") int position) {

        holder.imageView1.setImageResource( listdata.get(position).getImgId2());
        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context.getApplicationContext(), "hi",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        ImageView imageView1;
        ConstraintLayout constraintLayout;
        public Holder(View itemView) {
            super(itemView);
            this.imageView1 = (ImageView) itemView.findViewById(R.id.imagecoun);
            this.constraintLayout = (ConstraintLayout) itemView.findViewById(R.id.cardcountry);

        }

    }
}
