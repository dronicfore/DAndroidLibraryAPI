package com.dronicfore.android;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.dronicfore.java.Robot;

import java.io.File;

/**
 * <b>WARNING</b>: This Robot is still under Construction...
 *
 * @author Moses Katsina
 */
public class AndroidRobot extends Robot {

    private static AndroidRobot me = null;

    /**
     * @return An {@link AndroidRobot}.
     */
    public static AndroidRobot comeHere() {
        return me != null ? me : (me = new AndroidRobot());
    }

    /**
     * {@inheritDoc}
     *
     * @see CountDownTimer
     */
    @Override
    public void doAnotherThread(/*final Activity activity, */long wait, final Runnable code) {
        // Android implementation
        super.doAnotherThread(wait, new Runnable() {
            @Override
            public void run() {
//                if (!activity.isFinishing()) {
//                    Handler handler = new Handler(activity.getMainLooper());
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(code);
//                }
            }
        });
    }

    /**
     * For example, You can use this to perform long-running operations concerning your app
     * without a user interface.
     *
     * <p>
     * For example, You can use this to keep playing a music even when your app
     * is being closed by the user :)
     * </p>
     *
     * @param service The Service to start.
     *
     * @see Service
     */
    public void doInBackground(Intent intent, Service service) {
        service.startService(intent);
    }

//    public void doInBackground(Activity activity, long butWaitTillMillis, final Runnable code) {
//        // Android implementation
//        if (!activity.isFinishing()) this.doInBackground(butWaitTillMillis, code);
////        this.doInBackground(butWaitTillMillis, new Runnable() {
////            @Override
////            public void run() {
////                if (!activity.isFinishing()) {
////                    Handler handler = new Handler(activity.getMainLooper());
////                Handler handler = new Handler(Looper.getMainLooper());
////                handler.post(code);
////                }
////            }
////        });
//    }

    /**
     * {@inheritDoc}
     *
     * @see File
     * @see File#createTempFile(String, String)
     *
     * @see Context#getDataDir()
     * @see Context#getFilesDir()
     * @see Context#getCacheDir()
     * @see Context#getObbDir()
     */
    @Override
    public File enter(@NonNull File folder, @NonNull String... continueWithPath) {
        return super.enter(folder, continueWithPath);
    }

}
