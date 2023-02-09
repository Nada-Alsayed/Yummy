package eg.gov.iti.yummy.weeklyPlan.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.model.MealDetail;


public class WeeklyPlanAdapter extends RecyclerView.Adapter<WeeklyPlanAdapter.ViewHolder> {

    private final Context context;
    private List<planListItem> values;
    private List<MealDetail> list;


    public WeeklyPlanAdapter(Context _context, List<planListItem> myDataset) {
        values = myDataset;
        context = _context;
    }

    public WeeklyPlanAdapter( List<MealDetail>list,Context context) {
        this.context = context;
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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTitle;
        public ImageView imageView;
        public View layout;


        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtTitle = v.findViewById(R.id.textView1);
            imageView = v.findViewById(R.id.imageView1);

        }
    }
}
