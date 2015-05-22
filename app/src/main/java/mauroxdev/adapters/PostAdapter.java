package mauroxdev.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mauricio.zte.R;

import java.util.ArrayList;

import mauroxdev.models.post;

/**
 * Created by Mauricio on 22-05-2015.
 */
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private ArrayList<post> posts;
    private  int itemLayout;
/*
    public PostAdapter(Context context) {
        super(context);
    }*/

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        post post = posts.get(position);

        holder.postTitulo.setText(post.getTitulo());
        holder.postUrl.setText(post.getUrl());

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public PostAdapter(ArrayList<post> posts, int layout) {

        this.posts = posts;
        this.itemLayout = layout;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView postTitulo;
        public TextView postUrl;

        public ViewHolder(View itemView) {
            super(itemView);

            postTitulo = (TextView) itemView.findViewById(R.id.titulo_post);
            postUrl = (TextView) itemView.findViewById(R.id.url_post);

        }
    }
}
