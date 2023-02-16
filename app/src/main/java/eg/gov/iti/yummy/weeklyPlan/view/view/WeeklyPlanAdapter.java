package eg.gov.iti.yummy.weeklyPlan.view.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.meal_details.view.page_item_details;
import eg.gov.iti.yummy.model.MealDetail;
import eg.gov.iti.yummy.model.WeekPlan;


public class WeeklyPlanAdapter extends RecyclerView.Adapter<WeeklyPlanAdapter.ViewHolder> {

    private final Context context;
    private List<WeekPlan> list1;

    public WeeklyPlanAdapter(Context context,List<WeekPlan> list1) {
        this.list1 = list1;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.weeklyplanitem, recyclerView, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void setListweek(List<WeekPlan> list1) {
        this.list1 = list1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.txtTitle.setText(list1.get(position).strMeal);
        Glide.with(context)
                .load(list1.get(position).strMealThumb)
                .into(holder.imageView);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, page_item_details.class);
                intent.putExtra("MealName",holder.txtTitle.getText().toString());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
      return list1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle;
        public ImageView imageView;
        public CardView layout;

        public ViewHolder(View v) {
            super(v);
            layout = v.findViewById(R.id.homeAndPlanListItem);
            txtTitle = v.findViewById(R.id.textView1);
            imageView = v.findViewById(R.id.imageView1);

        }
    }
}
