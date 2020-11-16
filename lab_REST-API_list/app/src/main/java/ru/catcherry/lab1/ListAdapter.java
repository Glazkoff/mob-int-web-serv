package ru.catcherry.lab1;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    Context context;
    List<FactDetail> list;

    public ListAdapter(Context context, List<FactDetail> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_fact_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FactDetail fact = list.get(position);
        holder.factIdText.setText("Cat Fact #"+fact._id);
        int rand = (int) Math.ceil(Math.random()*1000);
        Log.d(TAG, "onBindViewHolder: ");
        Glide.with(context).load("https://picsum.photos/id/"+rand+"/400/400").into(holder.factImage);
        holder.item.setOnClickListener(v -> {
            Intent intent = new Intent(context, FactActivity.class);
            intent.putExtra("factid", fact._id);
//            intent.putExtra("factid", "591d9b2f227c1a0020d26823");
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView factImage;
        TextView factIdText;
        LinearLayout item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            factImage = itemView.findViewById(R.id.factImage);
            factIdText = itemView.findViewById(R.id.factIdText);
            item = itemView.findViewById(R.id.item);
        }
    }
}
