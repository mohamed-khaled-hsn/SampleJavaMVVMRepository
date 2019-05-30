package m.khaled.samples.helpers.base;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Mohamed Khaled on Tue, 14/Aug/2018 at 2:22 PM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */
public abstract class BaseAdapter extends RecyclerView.Adapter<BindingViewHolder> {
//    private CustomItemClickListener listener;

    @NonNull
    public BindingViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(
                layoutInflater, viewType, parent, false);
//        listener = setOnClickListener();
        BindingViewHolder viewHolder = new BindingViewHolder(binding);

        viewHolder.bind(this);
//        binding.getRoot().setOnClickListener(v ->
//                listener.onItemClick(v, viewHolder.getAdapterPosition()));
        return viewHolder;
    }

    public void onBindViewHolder(BindingViewHolder holder,
                                 int position) {
        Object obj = getObjForPosition(position);
        holder.bind(obj);
        Log.i("BaseAdapter", "onBindViewHolder");
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    protected abstract Object getObjForPosition(int position);

    protected abstract int getLayoutIdForPosition(int position);

//    protected abstract CustomItemClickListener setOnClickListener();
}