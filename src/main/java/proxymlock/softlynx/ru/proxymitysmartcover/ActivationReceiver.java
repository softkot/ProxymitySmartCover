package proxymlock.softlynx.ru.proxymitysmartcover;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/***
 * Receiver will start on device boot up and any phone activity
 * like ring, answer and so on to keep service running.
 */
public class ActivationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ProxymityWakeLockService.start(context);
    }
}
