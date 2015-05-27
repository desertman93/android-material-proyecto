package mauroxdev;

import android.app.Application;
import android.content.Context;

/**
 * Created by Mauricio on 23-05-2015.
 */
public class MyApplication extends Application {

    private static MyApplication sInstance;

    public static MyApplication getsInstance() {
        return sInstance;
    }

    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }


}
