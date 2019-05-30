package m.khaled.samples.helpers.livedata;

import androidx.annotation.StringRes;
import androidx.lifecycle.LifecycleOwner;

/**
 * A UiMessageEvent is used for UI messages. Like a {@link SingleLiveEvent} but also prevents
 * null messages and uses a custom observer.
 * Can be used with snackbar or toast messages, etc..
 * <p>
 * Note that only one observer is going to be notified of changes.
 */
public class UiMessageEvent extends SingleLiveEvent<Integer> {

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
         * @param messageResourceId The new message, non-null.
         */
        void onNewMessage(@StringRes int messageResourceId);
    }

}
