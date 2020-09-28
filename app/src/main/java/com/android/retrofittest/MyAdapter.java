package com.android.retrofittest;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolderView> {

    private View view;
    private Context context;
    private List<PhotoBean.DataBean> list;

    public MyAdapter(Context context, List<PhotoBean.DataBean> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.list_item, parent,false);
        return new MyHolderView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolderView holder, int position) {
        if ( holder instanceof MyHolderView){
            holder.desText.setText(list.get(position).getDesc());
            holder.timeText.setText(list.get(position).getCreatedAt());
            holder.authorText.setText(list.get(position).getAuthor());
            holder.titleText.setText(list.get(position).getTitle());
            Uri uri = Uri.parse(list.get(position).getUrl());
            holder.imageView.setImageURI(uri);
            RequestOptions options = new RequestOptions()
                    .placeholder(R.mipmap.ic_launcher)//图片加载出来前，显示的图片
                    .fallback(R.mipmap.a) //url为空的时候,显示的图片
                    .error(R.mipmap.b);//图片加载失败后，显示的图片 图片加载失败
            Glide.with(context).load(list.get(position).getUrl())
                    .apply(options)
                    .into(holder.imageView);
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolderView extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView titleText;
        public TextView authorText;
        public TextView timeText;
        public TextView desText;

        public MyHolderView(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.images);
            titleText = (TextView) itemView.findViewById(R.id.title_text);
            authorText = (TextView) itemView.findViewById(R.id.author_text);
            timeText = (TextView) itemView.findViewById(R.id.time_text);
            desText = (TextView) itemView.findViewById(R.id.des_text);
        }
    }
}
