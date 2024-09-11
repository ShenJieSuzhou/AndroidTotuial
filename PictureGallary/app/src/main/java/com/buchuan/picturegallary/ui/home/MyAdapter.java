package com.buchuan.picturegallary.ui.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.buchuan.picturegallary.DetailActivity;
import com.buchuan.picturegallary.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    //private List<Person> personList;
    private List<ImageItem> imageList;
    private Context context;

    // Constructor for passing the data list
    public MyAdapter(List<ImageItem> imageList, Context context) {
        this.imageList = imageList;
        this.context = context;
    }

    // ViewHolder class for caching view references
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);
        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageItem imageItem = imageList.get(position);

        // 使用 Glide 加载图片
        Glide.with(context)
                .load(imageItem.getImageUrl())
                .into(holder.imageView);

        holder.itemView.setOnClickListener(v ->{
           Intent intent = new Intent(context, DetailActivity.class);
           intent.putExtra("item_name", "11111" + ", Age: " + 123);
           context.startActivity(intent);
        });
    }

    // Return the size of the dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return imageList.size();
    }
}
