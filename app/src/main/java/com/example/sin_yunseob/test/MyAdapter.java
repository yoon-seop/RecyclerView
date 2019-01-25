package com.example.sin_yunseob.test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    ArrayList<UserInfo> data;
    Context context;

    public MyAdapter(ArrayList<UserInfo> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
        View view = null;
        view = LayoutInflater.from(context).inflate(R.layout.recycler_item, viewGroup, false);
        if(view != null){
            return new ViewHolder(view);
        }else{
            return null;
        }



    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        CompareSeqDesc compareSeqDesc = new CompareSeqDesc();
        Collections.sort(data, compareSeqDesc);

        for(int k = 0 ; k < data.size() ; k ++){
            Log.d("RRRRRR", "sequence: " + data.get(i).getPost_stargazers_count() + "\n");
        }

        for(int j = 0; j < data.size() ; j++){
            viewHolder.post_name.setText(data.get(i).getPost_name());
            viewHolder.post_description.setText(data.get(i).getPost_description());
            viewHolder.starCount.setText(data.get(i).getPost_stargazers_count());
        }

    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView post_name;
        TextView post_description;
        TextView starCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            post_name = itemView.findViewById(R.id.textView_name);
            post_description = itemView.findViewById(R.id.textView_post_description);
            starCount = itemView.findViewById(R.id.textView_star);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

        }
    }

    class CompareSeqDesc implements Comparator<UserInfo> {

        @Override
        public int compare(UserInfo o1, UserInfo o2) {
            return Integer.parseInt(o1.getPost_stargazers_count()) > Integer.parseInt(o2.getPost_stargazers_count()) ? -1 :
                    Integer.parseInt(o1.getPost_stargazers_count()) < Integer.parseInt(o2.getPost_stargazers_count()) ? 1:0;
        }
    }


}


