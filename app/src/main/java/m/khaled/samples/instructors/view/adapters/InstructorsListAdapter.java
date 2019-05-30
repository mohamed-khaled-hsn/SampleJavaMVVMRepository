package m.khaled.samples.instructors.view.adapters;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import m.khaled.samples.R;
import m.khaled.samples.helpers.CustomItemClickListener;
import m.khaled.samples.helpers.Logger;
import m.khaled.samples.helpers.base.BaseAdapter;
import m.khaled.samples.helpers.base.BindingViewHolder;
import m.khaled.samples.instructors.model.Instructor;

import java.util.List;

/**
 * Created by Mohamed Khaled on Wed, 17/Oct/2018 at 1:48 PM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */
public class InstructorsListAdapter extends BaseAdapter {

    private CustomItemClickListener<Instructor> clickListener;
    private List<Instructor> instructorList;

    public InstructorsListAdapter(List<Instructor> instructorList) {
        this.instructorList = instructorList;
    }

    public InstructorsListAdapter(List<Instructor> instructorList,
                                  CustomItemClickListener<Instructor> itemClickListener) {
        this.instructorList = instructorList;
        this.clickListener = itemClickListener;

    }

    @NonNull
    @Override
    public BindingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    protected Object getObjForPosition(int position) {
        return instructorList.get(position);
    }

    @Override
    protected int getLayoutIdForPosition(int position) {
        return R.layout.item_instructor;
    }

    @Override
    public int getItemCount() {
        return instructorList == null ? 0 : instructorList.size();
    }

    public void onItemClicked(View v, Instructor instructor) {
        Logger.d("instructor clicked with id: " + instructor.getId());
        clickListener.onItemClick(v, instructor);
    }

}

