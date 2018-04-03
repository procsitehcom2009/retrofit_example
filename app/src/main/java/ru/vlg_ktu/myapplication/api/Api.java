package ru.vlg_ktu.myapplication.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.vlg_ktu.myapplication.model.Comments;
import ru.vlg_ktu.myapplication.model.Posts;

/**
 * Created by V_Semibratov on 30.03.2018.
 */

public interface Api {

    @GET("/posts")
    Call<List<Posts>> getPosts();

    @GET("/posts/{id}/comments")
    Call<List<Comments>> getComments(@Path("id") int postId);

}
