package mauroxdev.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.mauricio.zte.R;

import mauroxdev.fragments.DetailPostFragment;
import mauroxdev.models.post;

/**
 * Created by Mauricio on 24-05-2015.
 */
public class DetailPostActivity extends AppCompatActivity {

    private static final String EXTRA_POST = "post";

    private ActionBar mActionBar;

    public static void launch(Activity activity, post post){
        Intent intent = getLaunchIntent(activity, post);
        activity.startActivity(intent);
        Log.e("DPA", "YES "+post.getTitulo());
    }

    public static Intent getLaunchIntent(Context context, post post) {
        Intent intent = new Intent(context, DetailPostActivity.class);
        intent.putExtra(EXTRA_POST, post);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        Log.e("DPA", "START");
        post post = getIntent().getParcelableExtra(EXTRA_POST);
        mActionBar = getSupportActionBar();
        mActionBar.setTitle("Noticia");
        //mActionBar.b

//        getActionBar().setDisplayShowTitleEnabled(true);
        if(savedInstanceState == null) {
            Log.e("DPA","LLEGO");
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, DetailPostFragment.createInstance(post))
                    .commit();
        }
    }
}
