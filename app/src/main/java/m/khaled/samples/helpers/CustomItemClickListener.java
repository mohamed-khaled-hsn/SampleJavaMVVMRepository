package m.khaled.samples.helpers;

import android.view.View;

/**
 * Created by Mohamed Khaled on Thu, 18/Oct/2018 at 12:35 PM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */
public interface CustomItemClickListener<T> {
    void onItemClick(View v, T obj);
}