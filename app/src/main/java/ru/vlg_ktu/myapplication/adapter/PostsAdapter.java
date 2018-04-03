package ru.vlg_ktu.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import ru.vlg_ktu.myapplication.R;
import ru.vlg_ktu.myapplication.model.Posts;

/**
 * Created by V_Semibratov on 02.04.2018.
 */

public class PostsAdapter extends ArrayAdapter<Posts> {

    private static final String TAG = "MyApp";

    private LayoutInflater inflater;
    private List<Posts> postsList;
    Context context;

    public PostsAdapter (Context context, List<Posts> objects){
        super(context, 0, objects);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        postsList = objects;
    }

    @Override
    public Posts getItem(int position){
        return postsList.get(position);
    }

    private static class ViewHolder{
        public final RelativeLayout relativeLayout;
        public final TextView postTitle, postBody;

        private ViewHolder(RelativeLayout relativeLayout, TextView postTitle, TextView postBody) {
            this.relativeLayout = relativeLayout;
            //this.userId = userId;
            //this.postId = postId;
            this.postTitle = postTitle;
            this.postBody = postBody;
        }

        public static ViewHolder create(RelativeLayout relativeLayout){
            //TextView userId = (TextView)relativeLayout.findViewById(R.id.user_Id);
            //TextView postId = (TextView)relativeLayout.findViewById(R.id.posts_id);
            TextView postTitle = (TextView)relativeLayout.findViewById(R.id.posts_title);
            TextView postBody = (TextView)relativeLayout.findViewById(R.id.posts_body);
            return new ViewHolder(relativeLayout, postTitle, postBody);
        }
    }

    public View getView (int position, View convertView, ViewGroup parent){

        final ViewHolder vh;
        if (convertView == null){
            View view = inflater.inflate(R.layout.post_item, parent, false);
            vh = ViewHolder.create((RelativeLayout)view);
            view.setTag(vh);
        }
        else{
            vh = (ViewHolder)convertView.getTag();
        }

        Posts post = getItem(position);

        //Log.i(TAG, String.valueOf(post.getUserId()));

        //vh.userId.setText(String.valueOf(post.getUserId()));
        //vh.postId.setText(String.valueOf(post.getId()));
        vh.postTitle.setText(String.valueOf(post.getTitle()));
        vh.postBody.setText(String.valueOf(post.getBody()));

        return vh.relativeLayout;
    }
}
