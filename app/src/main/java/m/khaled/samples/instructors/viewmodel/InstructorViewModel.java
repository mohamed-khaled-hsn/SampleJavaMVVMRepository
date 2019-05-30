package m.khaled.samples.instructors.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import m.khaled.samples.App;
import m.khaled.samples.helpers.CustomItemClickListener;
import m.khaled.samples.helpers.livedata.AbsentLiveData;
import m.khaled.samples.helpers.livedata.Resource;
import m.khaled.samples.helpers.livedata.SingleLiveEvent;
import m.khaled.samples.helpers.livedata.UiStringMessageEvent;
import m.khaled.samples.instructors.model.Instructor;
import m.khaled.samples.instructors.model.repo.InstructorRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohamed Khaled on Thu, 09/Aug/2018 at 1:01 PM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 */
public class InstructorViewModel extends ViewModel {

    public final MutableLiveData<Boolean> dataLoading;
    public final MutableLiveData<List<Instructor>> instructorsList;
    public final SingleLiveEvent<Boolean> showNoNetworkScreenEvent;
    private final UiStringMessageEvent errorMessageEvent;

    @Inject
    InstructorRepository instructorRepository;
    private Observer<Resource<List<Instructor>>> instructorListObserver;
    private LiveData<Resource<List<Instructor>>> instructorListLiveData;
    private SingleLiveEvent<Integer> onInstructorClickedEvent = new SingleLiveEvent<>();
    public CustomItemClickListener<Instructor> onInstructorClickListener = (v, instructor) ->
            onInstructorClickedEvent.setValue(instructor.getId());

    public InstructorViewModel() {
        App.getAppComponent().inject(this);

        dataLoading = new MutableLiveData<>();
        showNoNetworkScreenEvent = new SingleLiveEvent<>();

        instructorsList = new MutableLiveData<>();

        errorMessageEvent = new UiStringMessageEvent();

        instructorListLiveData = AbsentLiveData.create();
        instructorListObserver = getInstructorListObserver();

    }

    private Observer<Resource<List<Instructor>>> getInstructorListObserver() {
        return instructorListResource -> {
            if (instructorListResource != null) {
                switch (instructorListResource.getStatus()) {
                    case LOADING:
                        showNoNetworkScreenEvent.setValue(false);
                        dataLoading.setValue(true);
                        break;
                    case SUCCESS:
                        if (instructorListResource.getData() != null) {
                            showNoNetworkScreenEvent.setValue(false);
                            instructorsList.setValue(instructorListResource.getData());
                        } else
                            showNoNetworkScreenEvent.setValue(true);

                        dataLoading.setValue(false);
                        errorMessageEvent.setValue(instructorListResource.message);
                        break;
                    case ERROR:
                        dataLoading.setValue(false);
                        errorMessageEvent.setValue(instructorListResource.message);

                }
            }
        };
    }

    public void getInstructorList() {
        instructorListLiveData = instructorRepository.getInstructorsList();
        instructorListLiveData.observeForever(instructorListObserver);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (instructorListLiveData != null)
            instructorListLiveData.removeObserver(instructorListObserver);
    }

    public UiStringMessageEvent getErrorMessageEvent() {
        return errorMessageEvent;
    }

    public SingleLiveEvent<Integer> getOnInstructorClickedEvent() {
        return onInstructorClickedEvent;
    }

    public void setInstructorList(ArrayList<Instructor> instructorsList) {
        this.instructorsList.setValue(instructorsList);
    }
}