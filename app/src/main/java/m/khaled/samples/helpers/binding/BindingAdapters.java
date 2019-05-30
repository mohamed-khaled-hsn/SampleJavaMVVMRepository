package m.khaled.samples.helpers.binding;

import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.BindingAdapter;
import m.khaled.samples.R;
import m.khaled.samples.helpers.Logger;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Mohamed Khaled on Thu, 16/Aug/2018 at 4:17 PM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */
public class BindingAdapters {
    @BindingAdapter("imageUrl")
    public static void loadImage(ImageView view, String imageUrl) {
        Logger.i("loadingImage");
        Glide.with(view.getContext()).load(imageUrl).into(view);
    }


    @BindingAdapter("ImageUrlCircular")
    public static void loadCircularImage(ImageView view, String imageUrl) {
        Logger.i("loading circular image");
        Glide.with(view.getContext()).load(imageUrl).apply(RequestOptions.circleCropTransform()).into(view);
    }

    @BindingAdapter({"fromDate", "toDate"})
    public static void dateFromTo(TextView textView, Date dateFrom, Date dateTo) {
        SimpleDateFormat format = new SimpleDateFormat("d MMMM, yyyy", Locale.getDefault());
        String dateStringFrom = format.format(dateFrom);
        String dateStringTo = format.format(dateTo);
        textView.setText(String.format("%s To %s", dateStringFrom, dateStringTo));

    }
}
