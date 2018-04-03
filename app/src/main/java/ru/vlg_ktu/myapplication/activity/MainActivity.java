package ru.vlg_ktu.myapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.vlg_ktu.myapplication.R;
import ru.vlg_ktu.myapplication.adapter.PostsAdapter;
import ru.vlg_ktu.myapplication.api.RetroClient;
import ru.vlg_ktu.myapplication.model.Comments;
import ru.vlg_ktu.myapplication.model.Posts;
import android.widget.AdapterView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyApp";
    public List<Posts> postsList = new ArrayList<>();
    ListView postsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            final Intent intent = new Intent(this,DetailActivity.class);

            postsListView = (ListView)findViewById(R.id.postsList);
            Call<List<Posts>> call_posts = RetroClient.getApi().getPosts();
            call_posts.enqueue(new Callback<List<Posts>>() {
                @Override
                public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                    postsList.addAll(response.body());
                    PostsAdapter postsAdapter = new PostsAdapter(MainActivity.this, postsList);
                    postsListView.setAdapter(postsAdapter);
                    postsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            intent.putExtra("postId",postsList.get(position).getId());
                            intent.putExtra("postTitle",postsList.get(position).getTitle());
                            intent.putExtra("postBody",postsList.get(position).getBody());
                            startActivity(intent);
                        }
                    });
                }
                @Override
                public void onFailure(Call<List<Posts>> call, Throwable t) {
                    Log.i(TAG,t.toString());
                }
            });

        }
        catch (Exception ex){
            Log.i(TAG,ex.toString());
        }
    }
}