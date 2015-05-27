package mauroxdev.task;

import android.os.AsyncTask;

import com.android.volley.RequestQueue;

import java.util.ArrayList;

import mauroxdev.callbacks.PostsLoadedListener;
import mauroxdev.extras.UrlEndpoints;
import mauroxdev.models.post;
import mauroxdev.network.Requestor;
import mauroxdev.network.VolleySingleton;

/**
 * Created by Mauricio on 24-05-2015.
 */
public class TaskLoadPosts extends AsyncTask<Void, Void, ArrayList<post>> {

    private PostsLoadedListener myComponent;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;

    public TaskLoadPosts(PostsLoadedListener Component) {

        myComponent = Component;
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();

    }

    @Override
    protected ArrayList<post> doInBackground(Void... voids) {
        ArrayList<post> listposts = Requestor.requestPosts(requestQueue, UrlEndpoints.URL_NEWS_POST);
        return listposts;
    }

    @Override
    protected void onPostExecute(ArrayList<post> posts) {
        super.onPostExecute(posts);
        if (myComponent != null) {
            if (posts != null) {
                myComponent.OnPostsLoaded(posts);
            } else {
                myComponent.OnPostsFail();
            }
        }

    }
}
