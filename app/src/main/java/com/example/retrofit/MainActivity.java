package com.example.retrofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
TextView t1,t2;
ImageView img1;
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
        Gson gson=new Gson();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                //  Log.v("TAG",response.body().string());
                String result = response.body().string();
                if(response.isSuccessful()) {
                 runOnUiThread(new Runnable() {
                     @Override
                     public void run() {
                         GithubUser user = gson.fromJson(result, GithubUser.class);
                         t1.setText(user.name);
                         t2.setText(user.login);
                         Picasso.get().load(user.avatar_url).into(img1);
                     }
                 });

                }
            }
        });// run on main thread async when u get callback you execute when we get the response
    }
}