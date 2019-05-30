package m.khaled.samples.instructors.view.adapters;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import m.khaled.samples.helpers.CustomItemClickListener;
import m.khaled.samples.instructors.model.Instructor;

import java.util.List;

/**
 * Created by Mohamed Khaled on Wed, 17/Oct/2018 at 2:49 PM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */
public class InstructorListBindingAdapter {
    @BindingAdapter({"instructors", "onItemClicked"})
    public static void setOnItemClickedListener(RecyclerView view,
                                                List<Instructor> instructorList,
                                                CustomItemClickListener<Instructor> itemClickListener) {
        InstructorsListAdapter adapter = new InstructorsListAdapter(instructorList, itemClickListener);
        view.setAdapter(adapter);
    }
}
