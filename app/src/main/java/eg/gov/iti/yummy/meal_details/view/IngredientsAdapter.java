package eg.gov.iti.yummy.meal_details.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.country.AdapterCountry;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.Holder> {

    private ArrayList<Recipe> data;
    private Context context;

    public IngredientsAdapter(ArrayList<Recipe> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public IngredientsAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.meal_ingridient,parent,false);
        IngredientsAdapter.Holder vh=new IngredientsAdapter.Holder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsAdapter.Holder holder, int position) {
        Glide.with(context)
                .load(data.get(position).getThumbnail())
                .into(holder.ingImage);
        holder.ingName.setText(data.get(position).getIngredientName());
        holder.amount.setText(data.get(position).getAmount());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        ImageView ingImage;
        TextView ingName;
        TextView amount;

        public Holder(View itemView) {
            super(itemView);
            this.ingImage = (ImageView) itemView.findViewById(R.id.ingredient);
            this.ingName = (TextView) itemView.findViewById(R.id.txtIngredient);
            this.amount = (TextView) itemView.findViewById(R.id.txtAmount);
        }

    }
}
