package eg.gov.iti.yummy.weeklyPlan.view;

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


public class WeeklyPlanAdapter extends RecyclerView.Adapter<WeeklyPlanAdapter.ViewHolder> {

    private final Context context;

    private List<MealDetail> list;

    onWeeklyPlanClickListener listener;

    int source=0;


    public WeeklyPlanAdapter( List<MealDetail>list,Context context) {
        this.context = context;
        this.list = list;
    }

    public WeeklyPlanAdapter( Context context,onWeeklyPlanClickListener _listener,int recSource,List<MealDetail>list) {
        this.context = context;
        this.listener = _listener;
        source = recSource;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.weeklyplanitem, recyclerView, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void setList(List<MealDetail> list) {
        this.list = list;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.txtTitle.setText(list.get(position).strMeal);
        Glide.with(context)
                .load(list.get(position).strMealThumb)
                .into(holder.imageView);
        if(source==0)
            holder.delete.setVisibility(View.GONE);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnClick(list.get(position),source);
            }
        });
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
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTitle;
        public ImageView imageView;
        public ImageView delete;
        public CardView layout;


        public ViewHolder(View v) {
            super(v);
            layout = v.findViewById(R.id.homeAndPlanListItem);
            txtTitle = v.findViewById(R.id.textView1);
            delete = v.findViewById(R.id.removeWeekPlan);
            imageView = v.findViewById(R.id.imageView1);

        }
    }
}
