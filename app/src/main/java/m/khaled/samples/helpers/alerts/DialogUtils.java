package m.khaled.samples.helpers.alerts;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.appcompat.app.AlertDialog;
import m.khaled.samples.R;

/**
 * Created by Mohamed Khaled on Thu, 09/Aug/2018 at 1:01 PM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */
public class DialogUtils {
    public static AlertDialog setProgressDialog(Context context) {

        int llPadding = 30;
        LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams llParam = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        llParam.gravity = Gravity.CENTER;
        ll.setLayoutParams(llParam);

        ProgressBar progressBar = new ProgressBar(context);
        progressBar.setIndeterminate(true);
        progressBar.setPadding(0, 0, 0, 0);
        progressBar.setLayoutParams(llParam);

        llParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        llParam.gravity = Gravity.CENTER;


        ll.addView(progressBar);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setView(ll);
        AlertDialog dialog = builder.create();
        Window window = dialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
//            layoutParams.copyFrom(dialog.getWindow().getAttributes());
            layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT;
            layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setAttributes(layoutParams);
        }

        return dialog;
    }
}
