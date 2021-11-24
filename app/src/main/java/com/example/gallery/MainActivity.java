package com.example.gallery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String url = "https://pixabay.com/api/?key=24428676-bcada7641b29b2138dff0bd54&q=dog&image_type=photo&pretty=true";
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.list);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Log.i("gejun","response = " +response);
                        Gson gson = new Gson();
                        Pixabay bay = gson.fromJson(response, Pixabay.class);
//                        Log.i("gejun", ""+bay.hits);
                        for (int i = 0; i < bay.hits.size(); i++) {
                            Log.i("gejun",i+":"+bay.hits.get(i).previewURL);
                        }
                        recyclerView.setAdapter(new MyAdapter(bay.hits));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        queue.add(request);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView photo;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.imageView);
        }
    }

    class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
        ArrayList<PhotoItem> photolist;

        public MyAdapter(ArrayList<PhotoItem> result){
            photolist = result;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item, null);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Glide.with(MainActivity.this)
                    .load(photolist.get(position).previewURL)
                    .into(holder.photo);
        }

        @Override
        public int getItemCount() {
            return photolist.size();
        }
    }
}