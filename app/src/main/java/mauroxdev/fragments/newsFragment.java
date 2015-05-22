package mauroxdev.fragments;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mauricio.zte.R;

import java.util.ArrayList;

import mauroxdev.adapters.PostAdapter;
import mauroxdev.models.post;

/**
 * A simple {@link Fragment} subclass.
 */
public class newsFragment extends Fragment {


    public newsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<post> postarray = new ArrayList<post>();

        post post = new post();
        post.setTitulo("Dolar a 400$");
        post.setUrl("xd.com");

        postarray.add(post);

        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new PostAdapter(postarray, R.layout.row_post));
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


    }
}
