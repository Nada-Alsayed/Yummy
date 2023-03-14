package eg.gov.iti.yummy.weeklyPlan.view.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.SignIn.view.Page_Sign_In;
import eg.gov.iti.yummy.meal_details.view.page_item_details;
import eg.gov.iti.yummy.model.MealDetail;
import eg.gov.iti.yummy.model.WeekPlan;

public class WeeklyPlanAdapter extends RecyclerView.Adapter<WeeklyPlanAdapter.ViewHolder> {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://yummy-app-f2567-default-rtdb.firebaseio.com/");

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
        SharedPreferences pref = context.getSharedPreferences(Page_Sign_In.PREF_NAME, Context.MODE_PRIVATE);
        String shP = pref.getString("USERNAME", "N/A");
        WeekPlan mealDetail=list1.get(position);
        holder.txtTitle.setText(list1.get(position).strMeal);
        Glide.with(context)
                .load(list1.get(position).strMealThumb)
                .into(holder.imageView);
        holder.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog=new MaterialAlertDialogBuilder(context).setTitle("Delete")
                        .setMessage("Are you sure you want to delete this item from your plan?").setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteFavFire(mealDetail,shP);
                                onWeeklyPlanClickListener.deleteMealPlanOnClick(mealDetail);
                                dialog.dismiss();
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create();
                dialog.show();

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
    void deleteFavFire(WeekPlan meal,String c){
        databaseReference.child(c).child("WeekPlan").child(meal.idMeal+meal.sat+
                meal.sun+meal.mon+meal.tues+meal.wed+meal.thurs+meal.fri).removeValue();
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
