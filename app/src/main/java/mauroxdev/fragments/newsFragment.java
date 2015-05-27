package mauroxdev.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import com.example.mauricio.zte.MainActivity;
import com.example.mauricio.zte.R;

import java.util.ArrayList;

import mauroxdev.activities.DetailPostActivity;
import mauroxdev.adapters.PostAdapter;
import mauroxdev.callbacks.PostsLoadedListener;
import mauroxdev.models.post;
import mauroxdev.task.TaskLoadPosts;

/**
 * A simple {@link Fragment} subclass.
 */
public class newsFragment extends Fragment implements PostsLoadedListener, SwipeRefreshLayout.OnRefreshListener, PostAdapter.Clicklistener {

    private static final String STATE_POSTS = "state_posts";


    ArrayList<post> mListPost;

    private RecyclerView mRecyclerPost;

    private PostAdapter mAdapter;

    private SwipeRefreshLayout mSwipeRefreshLayout;




    public newsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View layout = inflater.inflate(R.layout.fragment_news, container, false);

        mSwipeRefreshLayout = (SwipeRefreshLayout) layout.findViewById(R.id.swipePost);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerPost = (RecyclerView) layout.findViewById(R.id.my_recycler_view);
        mRecyclerPost.setHasFixedSize(true);

        //set the layout manager before trying to display data
        mRecyclerPost.setLayoutManager(new LinearLayoutManager(getActivity()));
        addlistener();

        mAdapter = new PostAdapter(getActivity(), R.layout.row_post, this);


        if (savedInstanceState != null) {

            mListPost = savedInstanceState.getParcelableArrayList(STATE_POSTS);
            mAdapter.setPosts(mListPost);

        } else {
                getActivity().setProgressBarIndeterminateVisibility(true);
                new TaskLoadPosts(this).execute();


        }

        mRecyclerPost.setAdapter(mAdapter);

        return layout;
    }

    private void addlistener() {
        final GestureDetector mGestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener() {

            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });
        mRecyclerPost.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = rv.findChildViewUnder(e.getX(), e.getY());

                if(child!= null && mGestureDetector.onTouchEvent(e)){
                    Toast.makeText(getActivity(),"The Item Clicked is: "+ rv.getChildPosition(child),Toast.LENGTH_SHORT).show();

                    if(mListPost!=null) {
                        DetailPostActivity.launch(getActivity(), mListPost.get(rv.getChildPosition(child)));
                    }
                    return true;
                }

                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }
        } );
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        if(mListPost!= null){
            outState.putParcelableArrayList(STATE_POSTS, mListPost);
            Toast.makeText(getActivity(), "Guardado", Toast.LENGTH_SHORT);
        }

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void OnPostsLoaded(ArrayList<post> ListPosts) {
        if(mSwipeRefreshLayout.isRefreshing()){
            mSwipeRefreshLayout.setRefreshing(false);
        }
        mListPost = ListPosts;
        mAdapter.setPosts(ListPosts);

    }

    @Override
    public void OnPostsFail() {
        if(mSwipeRefreshLayout.isRefreshing()){
            mSwipeRefreshLayout.setRefreshing(false);
        }
        Log.e("FOS ","falllo esta verga");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Fallo en cargar los posts.")
                .setTitle("Opss!")
                .setPositiveButton("Aceptar", null)
                .show();

    }


    @Override
    public void onRefresh() {
        Toast.makeText(getActivity(),"Refrescando...", Toast.LENGTH_LONG).show();
        new TaskLoadPosts(this).execute();
    }

    @Override
    public void itemClicked(View view, int position) {
        Log.e("qlq", "epa ");

    }
}
