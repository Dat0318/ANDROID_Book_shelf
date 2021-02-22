package com.example.bookshelf;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class ItemNotificationAdapter extends RecyclerView.Adapter<ItemNotificationAdapter.ViewHolder> {
    private ItemNotification[] listNotification = new ItemNotification[]{};

    // RecyclerView recyclerView;
    public ItemNotificationAdapter(ItemNotification[] listNotification) {
        this.listNotification = listNotification;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_notification, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    private ItemNotification[] addItem(ItemNotification[] listNotification, ItemNotification ItemNotification) {
        final int N = listNotification.length;
        listNotification = Arrays.copyOf(listNotification, N + 1);
        listNotification[N] = ItemNotification;
        return listNotification;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ItemNotification listNotificationData = listNotification[position];
        holder.imageViewAvatar.setImageResource(listNotification[position].getAvatar());
        if (listNotification[position].getStatus() == 0) {
            holder.relative_red_dot.setVisibility(View.INVISIBLE);
        }
        holder.textViewTitle.setText(listNotification[position].getTitle());
        holder.textViewDescription.setText(listNotification[position].getDescription());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "click on item: " + listNotificationData.getDescription(), Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return listNotification.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageViewAvatar;
        public TextView textViewTitle, textViewDescription;
        public RelativeLayout relativeLayout, relative_avatar, relative_red_dot, relative_content;

        public ViewHolder(View itemView) {
            super(itemView);
            this.relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);
            this.relative_avatar = (RelativeLayout) itemView.findViewById(R.id.relative_avatar);
            this.relative_red_dot = (RelativeLayout) itemView.findViewById(R.id.relative_red_dot);
            this.relative_content = (RelativeLayout) itemView.findViewById(R.id.relative_content);
            this.imageViewAvatar = (ImageView) itemView.findViewById(R.id.imageViewAvatar);
            this.textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            this.textViewDescription = (TextView) itemView.findViewById(R.id.textViewDescription);
        }
    }
}
