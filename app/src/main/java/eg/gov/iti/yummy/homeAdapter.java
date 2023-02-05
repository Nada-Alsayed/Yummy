package eg.gov.iti.yummy;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class homeAdapter extends RecyclerView.Adapter<homeAdapter.ViewHolder>{

    private final Context context;
    private List<Integer> values;

    public homeAdapter(Context _context , List<Integer> myDataset){
        values = myDataset;
        context = _context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup recyclerView, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View v = inflater.inflate(R.layout.weeklyplanitem,recyclerView,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.imageView.setImageResource(values.get(position));
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public View layout;


        public ViewHolder(View v) {
            super(v);
            layout = v;
            imageView = v.findViewById(R.id.stackImage);
        }
    }


}
