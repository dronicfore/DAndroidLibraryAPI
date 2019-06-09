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
 * This Robot is still under Construction...
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
    public void doInBackground(/*final Activity activity, */long wait, final Runnable code) {
        // Android implementation
        super.doInBackground(wait, new Runnable() {
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
     * For example, You can use this to start long-running operations concerning your app
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
    public void doInBackground(Service service) {
        service.startService(new Intent());
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
     * <p>
     * <b>An operation that sends this Robot to a Folder in your computer.</b>
     * </p>
     *
     * For example, You can use this to access a Directory or File kept in the local computer.
     *
     * @param folder The File this Robot will be sent to.
     *
     * @return A {@link File} which describes where the robot was sent to.
     *
     * @see Context#getDataDir()
     * @see Context#getFilesDir()
     * @see Context#getCacheDir()
     * @see Context#getObbDir()
     *
     * @see File
     * @see File#createTempFile(String, String)
     */
    public File enter(@NonNull File folder, @NonNull String... continueWithPath) {
        // initialize a root reference to return if (continueWithPath[].length == 0)
        String pathString = "";

        // This loop will only execute if (continueWithPath[].length > 0)! (or was provided)
        for (String v: continueWithPath) {
            // modify pathString since a path was provided
            // pathString = pathString+"/"+"v";
            // pathString += "/"+"v";
            pathString = pathString.concat(File.separatorChar+v);
        }

        return new File(folder, pathString);
    }

}
