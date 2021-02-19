package com.example.bookshelf;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class itemOfSuggestAdapter extends RecyclerView.Adapter<itemOfSuggestAdapter.ViewHolder> {
    private itemOfSuggest[] list_suggest;

    // RecyclerView recyclerView;
    public itemOfSuggestAdapter(itemOfSuggest[] list_suggest) {
        this.list_suggest = list_suggest;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_of_suggest, parent, false);
        ViewHolder viewHolder = new itemOfSuggestAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final itemOfSuggest mySuggest = list_suggest[position];
        holder.textView.setText(list_suggest[position].getName());
        holder.imageView.setImageResource(list_suggest[position].getImg());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "click on item: " + mySuggest.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return list_suggest.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageViewAvatar);
            this.textView = (TextView) itemView.findViewById(R.id.textName);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);
        }
    }
}
