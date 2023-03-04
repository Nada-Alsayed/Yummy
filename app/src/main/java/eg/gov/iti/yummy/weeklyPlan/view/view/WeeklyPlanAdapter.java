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
    private onWeeklyPlanClickListener onWeeklyPlanClickListener;

    public WeeklyPlanAdapter(Context context, List<WeekPlan> list1, onWeeklyPlanClickListener onWeeklyPlanClickListener) {
        this.context = context;
        this.list1 = list1;
        this.onWeeklyPlanClickListener = onWeeklyPlanClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.weeklyplanitem, recyclerView, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void setListWeek(List<WeekPlan> list1) {
        this.list1 = list1;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        WeekPlan mealDetail=list1.get(position);
        holder.txtTitle.setText(list1.get(position).strMeal);
        Glide.with(context)
                .load(list1.get(position).strMealThumb)
                .into(holder.imageView);
        holder.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //action
                if(mealDetail.fri.equals(1))
                    onWeeklyPlanClickListener.deleteMealPlanOnClick(mealDetail);
                if(mealDetail.sat.equals(1))
                    onWeeklyPlanClickListener.deleteMealPlanOnClick(mealDetail);
                if(mealDetail.sun.equals(1))
                    onWeeklyPlanClickListener.deleteMealPlanOnClick(mealDetail);
                if(mealDetail.mon.equals(1))
                    onWeeklyPlanClickListener.deleteMealPlanOnClick(mealDetail);
                if(mealDetail.tues.equals(1))
                    onWeeklyPlanClickListener.deleteMealPlanOnClick(mealDetail);
                if(mealDetail.equals(1))
                    onWeeklyPlanClickListener.deleteMealPlanOnClick(mealDetail);
                if(mealDetail.fri.equals(1))
                    onWeeklyPlanClickListener.deleteMealPlanOnClick(mealDetail);
            }
        });
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, page_item_details.class);
                intent.putExtra("MealName", holder.txtTitle.getText().toString());
                intent.putExtra("tableType", "weekPlan");
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
        public ImageView imageView2;

        public CardView layout;

        public ViewHolder(View v) {
            super(v);
            layout = v.findViewById(R.id.homeAndPlanListItem);
            txtTitle = v.findViewById(R.id.textView1);
            imageView = v.findViewById(R.id.imageView1);
            imageView2 = v.findViewById(R.id.removeWeekPlan);
        }
    }
}
