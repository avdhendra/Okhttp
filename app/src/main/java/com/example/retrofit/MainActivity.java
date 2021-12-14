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
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
TextView t1,t2;
ImageView img1;
RecyclerView recyclerView;
List<GithubUser>users;


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

ApiResult adapter=new ApiResult(MainActivity.this);

RetrofitObj api=new RetrofitObj();
        retrofit2.Call<List<GithubUser>>call= api.api.getUser();
call.enqueue(new Callback<List<GithubUser>>() {
    @Override
    public void onResponse(@NonNull Call<List<GithubUser>> call, @NonNull Response<List<GithubUser>> response) {
        if(response.isSuccessful())
        {
users=response.body();
adapter.swapData(users);
recyclerView.setAdapter(adapter);
recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        }
    }

    @Override
    public void onFailure(@NonNull Call<List<GithubUser>> call, Throwable t) {

    }
});




    }
}