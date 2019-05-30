package m.khaled.samples.helpers.livedata;


import androidx.lifecycle.LifecycleOwner;

/**
 * Created by Mohamed Khaled on Mon, 13/Aug/2018 at 8:20 PM.
 * <p>
 * mohamed.khaled@apptcom.com
 * linkedin.com/in/mohamed5aled
 * <p>
 * A UiMessageEvent is used for UI messages. Like a {@link SingleLiveEvent} but also prevents
 * null messages and uses a custom observer.
 * Can be used with snackbar or toast messages, etc..
 * <p>
 * Note that only one observer is going to be notified of changes.
 */
public class UiStringMessageEvent extends SingleLiveEvent<String> {

    public void observe(LifecycleOwner owner, final UiMessageObserver observer) {
        super.observe(owner, t -> {
            if (t == null) {
                return;
            }
            observer.onNewMessage(t);
        });
    }

    public interface UiMessageObserver {
        /**
         * Called when there is a new message to be shown.
         *
         * @param messageText The new message, non-null.
         */
        void onNewMessage(String messageText);
    }

}