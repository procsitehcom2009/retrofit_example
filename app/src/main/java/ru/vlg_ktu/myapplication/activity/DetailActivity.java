package ru.vlg_ktu.myapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.vlg_ktu.myapplication.R;
import ru.vlg_ktu.myapplication.adapter.CommentsAdapter;
import ru.vlg_ktu.myapplication.api.RetroClient;
import ru.vlg_ktu.myapplication.model.Comments;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "MyApp";
    public List<Comments> commemntsList = new ArrayList<>();
    ListView commentsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        setContentView(R.layout.activity_detail);

        try{

            commentsListView = (ListView)findViewById(R.id.commentsList);

            String postTitle = extras.getString("postTitle");
            TextView titleView = (TextView)findViewById(R.id.posts_title);
            titleView.setText(postTitle);

            String postBody = extras.getString("postBody");
            TextView bodyView = (TextView)findViewById(R.id.posts_body);
            bodyView.setText(postBody);

            Integer postId = extras.getInt("postId");
            Call<List<Comments>> call_comments = RetroClient.getApi().getComments(postId);
            call_comments.enqueue(new Callback<List<Comments>>() {
                @Override
                public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                    commemntsList.addAll(response.body());
                    CommentsAdapter commentsAdapter = new CommentsAdapter(DetailActivity.this,commemntsList);
                    commentsListView.setAdapter(commentsAdapter);
                }

                @Override
                public void onFailure(Call<List<Comments>> call, Throwable t) {
                    Log.i(TAG,t.toString());
                }
            });

        }
        catch (Exception ex)
        {
            Log.i(TAG,ex.toString());
        }
    }
}
