package mauroxdev.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mauricio.zte.R;
import com.squareup.picasso.Picasso;

import mauroxdev.models.post;

/**
 * Created by Mauricio on 24-05-2015.
 */
public class DetailPostFragment extends Fragment {

    private static final String EXTRA_POST = "post";

    private post post;



    private ImageView imageView;

    public static Fragment createInstance(post post) {

        DetailPostFragment detailPostFragment = new DetailPostFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_POST, post);
        detailPostFragment.setArguments(bundle);
        return detailPostFragment;
    }

    public DetailPostFragment() {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_post_detail, container, false);
        post = getArguments().getParcelable(EXTRA_POST);
        Log.e("DPF","LLEGO");
        if (post == null) {
            getActivity().finish();
            return null;
        }



        Toast.makeText(getActivity(),"Fucando "+post.getTitulo(), Toast.LENGTH_LONG);
        TextView titulo = (TextView) view.findViewById(R.id.nameTextView);
        TextView detalle = (TextView) view.findViewById(R.id.descriptionTextView);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        setImage();
        titulo.setText(post.getTitulo());

        return view;
    }

    private void setImage() {
        Uri uri = Uri.parse(post.getImg());
        Context context = getActivity();
        Picasso.with(context).load(uri).fit().centerCrop().placeholder(R.drawable.empty_photo)
                .into(imageView);
    }
}
