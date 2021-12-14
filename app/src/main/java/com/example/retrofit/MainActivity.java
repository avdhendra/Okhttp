package com.example.retrofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
TextView t1,t2;
ImageView img1;
RecyclerView recyclerView;
List<GithubUser>users;
List<GithubUser>originalList= new ArrayList<>();
SearchView searchView;
    ApiResult adapter;
    RetrofitObj api=new RetrofitObj();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url("https://api.github.com/users/avdhendra").build();
        //client.newCall(request).execute() is run on main thread sync code stop at this line after get response it forward

        t1=findViewById(R.id.textview);
        t2=findViewById(R.id.textview2);
        img1=findViewById(R.id.imageview);
recyclerView=findViewById(R.id.recyclerview);

searchView =findViewById(R.id.searchview);
adapter=new ApiResult(MainActivity.this);

retrofit2.Call<List<GithubUser>>call= api.api.getUser();
call.enqueue(new Callback<List<GithubUser>>() {
    @Override
    public void onResponse(@NonNull Call<List<GithubUser>> call, @NonNull Response<List<GithubUser>> response) {
        if(response.isSuccessful())
        {
users=response.body();

adapter.swapData(users);
//store the list in original list so that when i dont have anything to search it show all item
if(users!=null) {
    originalList.addAll(users);
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
}
        }
    }

    @Override
    public void onFailure(@NonNull Call<List<GithubUser>> call, Throwable t) {

    }
});
//search the item in recyclerview
searchView.setSubmitButtonEnabled(true);

searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
    @Override
    public boolean onQueryTextSubmit(String s) {

            searchUser(s);

        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        searchUser(s);

        return true;
    }
});
searchView.setOnCloseListener(new SearchView.OnCloseListener() {
    @Override
    public boolean onClose() {
        //when search is close set adapter for all item in list
        adapter.swapData(originalList);
        return true;
    }
});


    }
void searchUser(String query){

    retrofit2.Call<UserResponse>call= api.api.searchUsers(query);
    call.enqueue(new Callback<UserResponse>() {
        @Override
        public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
            if (response.isSuccessful()) {
    UserResponse userResponse =response.body();
                adapter.swapData(userResponse.getItems());
            }
        }

        @Override
        public void onFailure(Call<UserResponse> call, Throwable t) {

        }
    });
}
}