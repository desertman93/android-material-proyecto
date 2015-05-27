package mauroxdev.callbacks;

import java.util.ArrayList;

import mauroxdev.models.post;

/**
 * Created by Mauricio on 24-05-2015.
 */
public interface PostsLoadedListener {
    public void OnPostsLoaded(ArrayList<post> ListPosts);
    public void OnPostsFail();
}
