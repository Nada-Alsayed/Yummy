package eg.gov.iti.yummy.home.home.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.meal_details.view.page_item_details;
import eg.gov.iti.yummy.model.Category;
import eg.gov.iti.yummy.model.MealDetail;

public class ForYouAdapter extends PagerAdapter {
    List<MealDetail> list;
    Context context;
    private static final String TAG="hi";

    public ForYouAdapter(List<MealDetail> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public void setList(List<MealDetail> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (CardView)object;
    }

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.stackviewitem,container,false);
        container.addView(view);
        ImageView iv = view.findViewById(R.id.stackImage);
        Glide.with(context)
                .load(list.get(position).strMealThumb)
                .into(iv);
        CardView forYouListItem = view.findViewById(R.id.stackViewCard);
        forYouListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, page_item_details.class);
                intent.putExtra("MealName",list.get(position).strMeal);
                context.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

}
