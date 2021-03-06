package com.dronicfore.android;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.widget.AdapterView;
import android.widget.Filterable;

import com.dronicfore.java.Robot;

import java.io.File;

/**
 * <b>WARNING</b>: This Robot is still under Construction...
 *
 * @author Moses Katsina
 */
public class AndroidRobot extends Robot {

    private static AndroidRobot me = null;

    protected AndroidRobot() {

    }

    /**
     * Allows you to interact with this robot.
     *
     * @return An {@link AndroidRobot}.
     */
    public static AndroidRobot comeHere() {
        return me != null ? me : (me = new AndroidRobot());
    }

    /**
     * @deprecated
     * 
     * @see #enterDatabase(Context)
     */
    public SharedPreferences enterDatabase(Activity activity) {
//        return activity.getPreferences(Context.MODE_PRIVATE);
        return enterDatabase(activity);
    }

    /**
     * @deprecated
     * 
     * An operation that sends this Robot to a Database.
     *
     * <p>
     * For example, You can use this to <b>Store</b> or <b>Read</b> textual
     * data, like user information ({@code String, int, double, boolean}).
     * </p>
     *
     * <ul>
     * <li>
     * This should only be used for <b>NOTE TAKING</b>.
     * </li>
     * </ul>
     *
     * @param context The context this Robot will be sent to.
     *
     * @return {@link SharedPreferences} of the context.
     *
     * @see SharedPreferences
     */
    public SharedPreferences enterDatabase(Context context) {
        return context.getSharedPreferences(context.getClass().getName(), Context.MODE_PRIVATE);
    }
	
	/**
	 * Finds where the given object is located.
	 * 
	 * @param object The object to be searched for.
	 * @param where The place ({@link Filterable} Adapters) to search.
	 * @return true If the search event was successful.
	 */
	public boolean search(Object object, Filterable... where) {
		for(Filterable filter : where) filter.getFilter().filter(object.toString());
		return where != null && where.length > 0;
	}
	
	/**
	 * Finds where the given object is located.
	 * 
	 * @param object The object to be searched for.
	 * @param where The views to search.
	 * @return true If the search event was successful.
	 */
	public boolean search(Object object, AdapterView... where) {
		Filterable[] filters = new Filterable[where != null ? where.length : 0];
		for(int i = 0; i < where.length; i++) {
			AdapterView view = where[i];
			if (!(view.getAdapter() instanceof Filterable)) throw new UnsupportedOperationException("The " + view + " adapter (" + view.getAdapter() + ") must implement android.widget.Filterable related interface to continue (for example: try using ArrayAdapter)");
			filters[i] = (Filterable) view.getAdapter();
		}
		return AndroidRobot.this.search(object, filters);
	}

    /**
     * {@inheritDoc}
     *
     * @see CountDownTimer
     */
    @Override
    public Thread doInAnotherThread(/*final Activity activity, */long delay, final Runnable code) {
        // Android implementation
        return super.doInAnotherThread(delay, new Runnable() {
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
     * @deprecated
     * 
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
    public void doInBackground(Intent extra, Service service) {
        service.startService(extra);
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
     * DO NOT USE.
     *
     * @throws UnsupportedOperationException
     */
    @Override
    public void doPauseOrResume(Thread thread) {
        throw new UnsupportedOperationException("Pausing and Resuming of Threads is not allowed on Android");
    }

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
	 * @see android.os.Environment
     */
    @Override
    public File enter(@NonNull File folder, @NonNull String... continueWithPath) {
        return super.enter(folder, continueWithPath);
    }

}
