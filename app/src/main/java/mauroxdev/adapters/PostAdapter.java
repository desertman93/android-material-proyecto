package mauroxdev.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.mauricio.zte.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import mauroxdev.models.post;
import mauroxdev.network.VolleySingleton;
import mauroxdev.parser.CoreParser;

/**
 * Created by Mauricio on 22-05-2015.
 */
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    public Clicklistener mclicklistener;

    private LayoutInflater mInflater;
    private Context mContext;


    private ArrayList<post> mListPost;
    private int itemLayout;

    public PostAdapter(Context context, int layout, Clicklistener clicklistener) {

        mContext = context;
        mclicklistener = clicklistener;
        mInflater = LayoutInflater.from(context);

        //this.posts = posts;
        this.itemLayout = layout;

    }

    public ArrayList<post> getMposts() {
        return mListPost;
    }

    public void getdata(){

        String URL = "http://www.uptos.edu.ve/?q=node/noticias";
        RequestQueue queue = VolleySingleton.getInstance().getRequestQueue();

        final ProgressDialog progressDialog = ProgressDialog.show(mContext, "Espere...", "Procesando");

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //mTextView.setText("Response is: "+ response.substring(0,500));

                        mListPost = new CoreParser().proccesPost(response, "http://www.uptos.edu.ve");
                        setPosts();
                        progressDialog.cancel();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                progressDialog.cancel();
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

/*
    public PostAdapter(Context context) {
        super(context);
    }*/

    public void setPosts(ArrayList<post> posts) {
        mListPost = posts;
        notifyDataSetChanged();
    }

    public void setPosts() {
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(itemLayout, parent, false);
        //View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        ViewHolder viewholder = new ViewHolder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        post post = mListPost.get(position);
        holder.postTitulo.setText(post.getTitulo());
        holder.postUrl.setText(post.getUrl());

        Uri uri = Uri.parse(post.getImg());
        Context context = holder.postImage.getContext();
        Picasso.with(context).load(uri).fit().centerCrop().placeholder(R.drawable.empty_photo)
                .into(holder.postImage);

    }

    @Override
    public int getItemCount() {
        if (mListPost != null) {
            return mListPost.size();
        } else {
            return 0;
        }

    }


    public interface Clicklistener {
        public void itemClicked(View view, int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView postTitulo;
        public TextView postUrl;
        public ImageView postImage;


        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            postTitulo = (TextView) itemView.findViewById(R.id.titulo_post);
            postUrl = (TextView) itemView.findViewById(R.id.url_post);
            postImage = (ImageView) itemView.findViewById(R.id.post_img);

        }



        @Override
        public void onClick(View v) {
            Log.e("xd:","toco");


                mclicklistener.itemClicked(v, getPosition());




        }
    }
}
