package m.khaled.samples.instructors.view;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import m.khaled.samples.R;
import m.khaled.samples.databinding.FragmentInstructorListBinding;
import m.khaled.samples.helpers.alerts.UiMessagesUtils;
import m.khaled.samples.helpers.livedata.UiStringMessageEvent;
import m.khaled.samples.instructors.viewmodel.InstructorViewModel;

public class InstructorsListFragment extends Fragment {

    private InstructorViewModel viewModel;
    private FragmentInstructorListBinding binding;
    private boolean shouldShowSnackBar = true;

    public InstructorsListFragment() {
        // Required empty public constructor
    }

    public static InstructorsListFragment newInstance() {
        return new InstructorsListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(this).get(InstructorViewModel.class);
        viewModel.getErrorMessageEvent().observe(this,
                (UiStringMessageEvent.UiMessageObserver) messageText -> {
                    if (shouldShowSnackBar)
                        Log.i("instructorListFragment", "should show snackbar");
                    new UiMessagesUtils().showSnackbar(binding.clContainer, messageText);
                });

            viewModel.getInstructorList();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_instructor_list, container, false);

        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        viewModel.getOnInstructorClickedEvent().observe(this, instructorUserId -> {
            //go to profile
        });
        return binding.getRoot();
    }
}
