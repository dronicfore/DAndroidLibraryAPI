package com.dronicfore.android.firebase;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.RequiresApi;

import com.dronicfore.android.AndroidRobot;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import com.dronicfore.java.Robot;

/**
 * <b>FirebaseRobot</b> is a Robot you can use to interact with all <a href="https://firebase.google.com">Firebase</a> features.
 *
 * <h1>And Guess What!</h1>
 *
 * You may use {@link #comeHere()} to call this robot from <b>anywhere at anytime</b> in your code.
 *
 * <p>
 * <b>NOTE:</b> Firebase has only <b>One</b> robot
 * </p>
 *
 * Meaning FirebaseRobot responds to only one Thread at a Time <b>:)</b>.
 *
 * @author Moses Katsina
 */
@RequiresApi(api = 16 /*Meaning Android 4.1 (Jelly Bean)*/ ) // Based on the Firebase Gradle SDKs
public final class FirebaseRobot extends Robot {

    /**
     * A variable holding single instance of this {@link FirebaseRobot}.
     */
    private static FirebaseRobot me = null;

    /**
     * {@inheritDoc}
     */
    private FirebaseRobot() {}

    /**
     * Allows you to interact with this robot.
     *
     * @return A {@link FirebaseRobot}.
     * That can do <b>anything</b> on a <a href="https://console.firebase.google.com">Firebase Project</a>.
     */
    public static synchronized FirebaseRobot comeHere() {
        return me != null ? me : (me = new FirebaseRobot());
    }

    /**
     * <p>
     * <b>An operation that sends this Robot to a FirebaseApp.</b>
     * </p>
     *
     * <li>Why not use {@link #goTo(Activity)} instead?</li>
     *
     * @param app The FirebaseApp this Robot will be sent to.
     * @return The {@link FirebaseAuth} platform the <b>app</b> is based on.
     *
     * @see FirebaseApp
     */
    public FirebaseAuth goTo(FirebaseApp app) {
        return FirebaseAuth.getInstance(app);
        // WARNING!! DO NOT return the statement below!
        // It always Re-Constructs a NEW FirebaseAuth instance object every time this method is called.
        // which is totally different from the one Firebase assigned to your app!!!!
//        return new FirebaseAuth(app);
    }

    /**
     * <p>
     * <b>An operation that sends this Robot to a Context.</b>
     * </p>
     *
     * <li>Why not use {@link #goTo(Activity)} instead?</li>
     *
     * @param context The Context this Robot will be sent to.
     * @return The {@link FirebaseAuth} platform the <b>context</b> is based on.
     */
    public FirebaseAuth goTo(Context context) {
        return goTo(FirebaseApp.initializeApp(context));
    }

    /**
     * <p>
     * <b>An operation that sends this Robot to an Activity.</b>
     * </p>
     *
     * For example, You can use this to get the current user interacting with the Activity,
     * or the environment about the current user interacting with your Android Application.
     *
     * @param activity The {@link Activity} this Robot will be sent to.
     * @return The {@link FirebaseAuth} platform the <b>activity</b> is based on.
     *
     * @throws RuntimeException If the <b>activity</b> has not been <a href="https://firebase.google.com/docs/android/setup">Baptised</a>
     * with <a href="https://firebase.google.com">Firebase</a>.
     */
    public FirebaseAuth goTo(Activity activity) {
        return goTo((Context) activity);
    }

    /**
     * <p>
     * <b>An operation that sends this Robot to a FirebaseDatabase.</b>
     * </p>
     *
     * For example, You can only use this to <b>Store</b> or <b>Read</b> textual
     * data, like user information ({@code String, int, double, boolean}).
     *
     * @param database The FirebaseDatabase this Robot will be sent to.
     *
     * @return A {@link DatabaseReference} which describes where the robot was sent to.
     *
     * @see FirebaseDatabase
     */
    public DatabaseReference enter(FirebaseDatabase database, String... path) {
        // initialize a root reference to return if (path[].length == 0)
        DatabaseReference dbReference = database.getReference();

        // This loop will only execute if (path[].length > 0)! (or was provided)
        for (String v: path) {
            // modify dbReference since a path was provided
            dbReference = dbReference.child(v);
        }

        return dbReference;
    }

    /**
     * <p>
     * <b>An operation that sends this Robot to a FirebaseDatabase.</b>
     * </p>
     *
     * Convenience for {@link #enter(FirebaseDatabase, String...) enter(FirebaseDatabase, "Users", "$userId")}
     * <p>or</p>
     * Convenience for {@link #enter(FirebaseDatabase, String...) enter(FirebaseDatabase).child("Users").child("$userId")}
     * <p>or</p>
     * Convenience for {@link #enter(FirebaseDatabase, String...) enter(FirebaseDatabase).child("Users/$userId")}.
     *
     * @param database The FirebaseDatabase this Robot will be sent to.
     * @param user The FirebaseUser this Robot will be sent to.
     *
     * @return A {@link DatabaseReference} which describes where the robot was sent to.
     *
     * @see FirebaseDatabase
     * @see FirebaseUser
     */
    public DatabaseReference enter(FirebaseDatabase database, FirebaseUser user) {
        return enter(database, "Users", user.getUid());
    }

    /**
     * <p>
     * <b>An operation that sends this Robot to a FirebaseDatabase.</b>
     * </p>
     *
     * Convenience for {@link #enter(FirebaseDatabase, String...)} but this automatically enters
     * the path of the given snapshot.
     *
     * @param database The FirebaseDatabase this Robot will be sent to.
     * @param snapshot The DataSnapshot this Robot will be sent to.
     *
     * @return A {@link DatabaseReference} which describes where the robot was sent to.
     *
     * @see FirebaseDatabase
     * @see DataSnapshot
     */
    @SuppressLint("RestrictedApi")
    public DatabaseReference enter(FirebaseDatabase database, DataSnapshot snapshot) {
//       an alternative:= return enter(database, Uri.parse(snapshot.getRef().toString()).getPath());
        return enter(database, snapshot.getRef().getPath().toString());
    }

    /**
     * <p>
     * <b>An operation that sends this Robot to a FirebaseDatabase.</b>
     * </p>
     *
     * Convenience for {@link #enter(FirebaseDatabase, DataSnapshot)} but this automatically enters
     * the database and path of the given snapshot.
     *
     * @param snapshot The DataSnapshot this Robot will be sent to.
     *
     * @return A {@link DatabaseReference} which describes where the robot was sent to.
     *
     * @see #enter(FirebaseDatabase, DataSnapshot)
     */
    public DatabaseReference enter(DataSnapshot snapshot) {
        return enter(snapshot.getRef().getDatabase(), snapshot);
    }

//    public CollectionReference enter(@NonNull FirebaseFirestore database, @NonNull String... pathString) {

        // initialize a root reference to return if (pathString[].length == 0)
//        CollectionReference colReference = database.collection();

        // This loop will only execute if (pathString[].length > 0)! (or was provided)
//        for (String v: pathString) {
            // modify dbReference since a path was provided
//            colReference = colReference.document(v);
//        }
//
//        return colReference;
//    }

    /**
     * <p>
     * <b>An operation that sends this Robot to a FirebaseStorage.</b>
     * </p>
     *
     * For example, You can only use this to <b>Store</b> or <b>Read</b> media files,
     * like photos and videos ({@code File, Uri}).
     *
     * @param storage The FirebaseStorage this Robot will be sent to.
     *
     * @return A {@link StorageReference} which describes where the robot was sent to.
     *
     * @see FirebaseStorage
     */
    public StorageReference enter(FirebaseStorage storage, String... path) {
        // initialize a root reference to return if (path[].length == 0)
        StorageReference sReference = storage.getReference();

        // This loop will only execute if (path[].length > 0)! (or was provided)
        for (String v: path) {
            // modify sReference since a path was provided
            sReference = sReference.child(v);
        }

        return sReference;
    }

    /**
     * <p>
     * <b>An operation that sends this Robot to a FirebaseStorage.</b>
     * </p>
     *
     * Convenience for {@link #enter(FirebaseStorage, String...) enter(FirebaseStorage, "Users", "$userId")}
     * <p>or</p>
     * Convenience for {@link #enter(FirebaseStorage, String...) enter(FirebaseStorage).child("Users").child("$userId")}
     * <p>or</p>
     * Convenience for {@link #enter(FirebaseStorage, String...) enter(FirebaseStorage).child("Users/$userId")}.
     *
     * @param storage The FirebaseStorage this Robot will be sent to.
     * @param user The FirebaseUser this Robot will be sent to.
     *
     * @return A {@link StorageReference} which describes where the robot was sent to.
     *
     * @see FirebaseStorage
     * @see FirebaseUser
     */
    public StorageReference enter(FirebaseStorage storage, FirebaseUser user) {
        return enter(storage, "Users", user.getUid());
    }

    /**
     * <p>
     * <b>An operation that sends this Robot to a FirebaseStorage.</b>
     * </p>
     *
     * Convenience for {@link #enter(FirebaseStorage, String...)} but this automatically enters
     * the storage of the given snapshot.
     *
     * @param storage The FirebaseStorage this Robot will be sent to.
     *
     * @return A {@link StorageReference} which describes where the robot was sent to.
     *
     * @see FirebaseStorage
     * @see FirebaseUser
     */
    @SuppressLint("RestrictedApi")
    public StorageReference enter(FirebaseStorage storage, DataSnapshot snapshot) {
        return enter(storage, snapshot.getRef().getPath().toString());
    }

    @Override
    public Thread doInAnotherThread(long delay, Runnable code) {
        return AndroidRobot.comeHere().doInAnotherThread(delay, code);
    }

    @Override
    public void doPauseOrResume(Thread thread) {
        AndroidRobot.comeHere().doPauseOrResume(thread);
    }
}

// ######### [fIRE/BASE] #########
