package com.example.retrofit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ApiResult extends RecyclerView.Adapter<ApiResult.ViewHolder> {
List<GithubUser>users;
Context context;
ApiResult(Context context)
{
    this.context=context;
}


    @NonNull
    @Override
    public ApiResult.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
 LayoutInflater inflater= LayoutInflater.from(parent.getContext());
 View view=inflater.inflate(R.layout.item_view,parent,false);
 return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApiResult.ViewHolder holder, int position) {
holder.bind(users.get(position));
      holder.t1.setText(users.get(position).name);
       holder.t2.setText(users.get(position).login);
        Picasso.get().load(users.get(position).avatar_url).into(holder.img1);
 holder.itemView.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
         Toast.makeText(context,users.get((holder.getAdapterPosition())).login, Toast.LENGTH_SHORT).show();
    Intent i=new Intent(context,UserActivity.class);
    i.putExtra("id",users.get(holder.getAdapterPosition()).login);
    context.startActivity(i);
     }
 });

    }
    void swapData(List<GithubUser> users)
    {
        this.users=users;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
    ImageView img1;
    TextView t1,t2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img1=itemView.findViewById(R.id.imageview);
            t1=itemView.findViewById(R.id.textview);
            t2=itemView.findViewById(R.id.textview2);


        }
        void bind(GithubUser item)
        {

        }





    }

}
