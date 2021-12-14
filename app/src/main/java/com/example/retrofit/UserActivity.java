package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class UserActivity extends AppCompatActivity {
GithubUser user;
TextView t1,t2;
ImageView img1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        getIncomingIntent();
t1=findViewById(R.id.textview);
t2=findViewById(R.id.textview2);
img1=findViewById(R.id.imageview);
    }
    private void getIncomingIntent(){
        if(getIntent().hasExtra("id")){
            String name=getIntent().getStringExtra("id");
            RetrofitObj api=new RetrofitObj();
            retrofit2.Call<GithubUser>call= api.api.getUserId(name);
            call.enqueue(new Callback<GithubUser>() {
                @Override
                public void onResponse(Call<GithubUser> call, Response<GithubUser> response) {
                    if(response.isSuccessful())
                    {
user=response.body();
                        t1.setText(user.name);
                        t2.setText(user.login);
                        Picasso.get().load(user.avatar_url).into(img1);
                    }
                }

                @Override
                public void onFailure(Call<GithubUser> call, Throwable t) {

                }
            });

        }
    }
}