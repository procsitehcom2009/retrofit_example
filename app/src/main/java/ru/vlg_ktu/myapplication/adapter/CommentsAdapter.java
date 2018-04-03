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
import ru.vlg_ktu.myapplication.model.Comments;
import ru.vlg_ktu.myapplication.model.Posts;

/**
 * Created by V_Semibratov on 03.04.2018.
 */

public class CommentsAdapter extends ArrayAdapter<Comments>{

    private static final String TAG = "MyApp";

    private LayoutInflater inflater;
    private List<Comments> commentsList;
    Context context;

    public CommentsAdapter (Context context, List<Comments> objects){
        super(context, 0, objects);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        commentsList = objects;
    }

    @Override
    public Comments getItem(int position){
        return commentsList.get(position);
    }

    private static class ViewHolder{
        public final RelativeLayout relativeLayout;
        public final TextView commentName, commentEmail, commentBody;

        private ViewHolder(RelativeLayout relativeLayout, TextView commentName, TextView commentEmail, TextView commentBody) {
            this.relativeLayout = relativeLayout;
            //this.userId = userId;
            //this.postId = postId;
            this.commentName = commentName;
            this.commentEmail = commentEmail;
            this.commentBody = commentBody;
        }

        public static CommentsAdapter.ViewHolder create(RelativeLayout relativeLayout){
            TextView commentName = (TextView)relativeLayout.findViewById(R.id.comments_name);
            TextView commentEmail = (TextView)relativeLayout.findViewById(R.id.comments_email);
            TextView commentBody = (TextView)relativeLayout.findViewById(R.id.comments_body);
            return new CommentsAdapter.ViewHolder(relativeLayout, commentName, commentEmail, commentBody);
        }
    }

    public View getView (int position, View convertView, ViewGroup parent){

        final CommentsAdapter.ViewHolder vh;
        if (convertView == null){
            View view = inflater.inflate(R.layout.comment_item, parent, false);
            vh = CommentsAdapter.ViewHolder.create((RelativeLayout)view);
            view.setTag(vh);
        }
        else{
            vh = (CommentsAdapter.ViewHolder)convertView.getTag();
        }

        Comments comment = getItem(position);

        //Log.i(TAG, String.valueOf(post.getUserId()));

        //vh.userId.setText(String.valueOf(post.getUserId()));
        //vh.postId.setText(String.valueOf(post.getId()));
        vh.commentName.setText(String.valueOf(comment.getName()));
        vh.commentEmail.setText(String.valueOf(comment.getEmail()));
        vh.commentBody.setText(String.valueOf(comment.getBody()));

        return vh.relativeLayout;
    }
}

