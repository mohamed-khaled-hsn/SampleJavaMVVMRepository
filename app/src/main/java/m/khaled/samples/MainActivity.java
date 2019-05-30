package m.khaled.samples;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import m.khaled.samples.helpers.ActivityUtils;
import m.khaled.samples.instructors.view.InstructorsListFragment;

/**
 * Created by Mohamed Khaled on Thu, 30/May/2019 at 4:42 AM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityUtils.replaceFragmentInActivity(getSupportFragmentManager(),
                InstructorsListFragment.newInstance(),
                R.id.container_fragment,
                false);
    }
}
