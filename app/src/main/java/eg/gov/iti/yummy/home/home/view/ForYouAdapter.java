package eg.gov.iti.yummy.home.home.view;

import android.content.Context;
import android.net.ConnectivityManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.List;

import eg.gov.iti.yummy.R;
import eg.gov.iti.yummy.model.Category;
import eg.gov.iti.yummy.model.MealDetail;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ForYouAdapter extends PagerAdapter {
    List<MealDetail> list;
    static Context context;

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
//        iv.setImageResource(list.get(position).);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

}
