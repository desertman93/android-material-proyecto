package mauroxdev.network;

import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import mauroxdev.models.post;
import mauroxdev.parser.CoreParser;

/**
 * Created by Mauricio on 24-05-2015.
 */
public class Requestor {

    public static ArrayList<post> requestPosts(RequestQueue requestQueue, String url) {
        String response = null;
        String TAG_REQUESTOR = "REQUESTOR";
        RequestFuture<String> requestFuture = RequestFuture.newFuture();

        StringRequest request = new StringRequest(Request.Method.GET,
                url
                , requestFuture, requestFuture);
        requestQueue.add(request);

        try {
            response = requestFuture.get(60000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e){
            Log.e(TAG_REQUESTOR,e.toString());
        } catch (ExecutionException e){
            Log.e(TAG_REQUESTOR,e.toString());
        } catch (TimeoutException e){
            Log.e(TAG_REQUESTOR,e.toString());
        }
        ArrayList<post> mListPost= null;
        if(response!=null){
            mListPost = new CoreParser().proccesPost(response, "http://www.uptos.edu.ve");
        }else {
            return null;
        }

        return mListPost;
    }
}
