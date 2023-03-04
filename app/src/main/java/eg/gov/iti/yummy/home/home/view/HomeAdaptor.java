package eg.gov.iti.yummy.home.home.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.meal_details.view.page_item_details;
import eg.gov.iti.yummy.model.MealDetail;

public class HomeAdaptor extends RecyclerView.Adapter<HomeAdaptor.ViewHolder> {
    private final Context context;
    private List<MealDetail> list;
    private HomeOnClick listener;

    public HomeAdaptor(List<MealDetail> list, HomeOnClick listener, Context context) {
        this.context = context;
        this.listener = listener;
        this.list = list;
    }
    @Override
    public HomeAdaptor.ViewHolder onCreateViewHolder(ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.recyclerview_row_availabledish, recyclerView, false);
        HomeAdaptor.ViewHolder vh = new HomeAdaptor.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MealDetail meal = list.get(position);
        holder.txtTitle.setText(list.get(position).strMeal);
        Glide.with(context)
                .load(list.get(position).strMealThumb)
                .into(holder.imageView);
        holder.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, meal.strMeal + " added to your list", Toast.LENGTH_SHORT).show();
                listener.addToFavHome(meal);
                listener.addToFavFireOnClick(meal);
            }
        });

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, page_item_details.class);
                intent.putExtra("MealName", holder.txtTitle.getText().toString());
                context.startActivity(intent);
            }
        });
    }

    public void setList(List<MealDetail> list) {
        this.list = list;
    }


    @Override
    public int getItemCount() {
        return list.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle;
        public ImageView imageView;
        public ImageView imageView2;


        public CardView layout;

        public ViewHolder(View v) {
            super(v);
            layout = v.findViewById(R.id.home);
            txtTitle = v.findViewById(R.id.textView1);
            imageView = v.findViewById(R.id.imageView1);
            imageView2 = v.findViewById(R.id.imglove);
        }
    }
}
