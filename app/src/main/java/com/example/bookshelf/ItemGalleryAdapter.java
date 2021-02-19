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

public class ItemGalleryAdapter extends RecyclerView.Adapter<ItemGalleryAdapter.ViewHolder> {
    private ItemGallery[] listImg;
    private ItemGallery[] listItem = new ItemGallery[]{};

    // RecyclerView recyclerView;
    public ItemGalleryAdapter(ItemGallery[] listImg) {
        this.listImg = listImg;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_gallery, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    private ItemGallery[] addItem(ItemGallery[] listOfSuggest, ItemGallery itemSuggest) {
        final int N = listOfSuggest.length;
        listOfSuggest = Arrays.copyOf(listOfSuggest, N + 1);
        listOfSuggest[N] = itemSuggest;
        return listOfSuggest;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ItemGallery myListData = listImg[position];
//        holder.textView.setText(listImg[position].getTitle());
//        holder.imageView.setImageResource(listImg[position].getAvatar());
        JSONArray jsonArray = (JSONArray) listImg[position].getListImg();

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                ItemGallery item_gallery = new ItemGallery();
                JSONObject suggestObj = jsonArray.getJSONObject(i);
                item_gallery.setTitle(suggestObj.getString("NAME"));
                item_gallery.setAvatar(suggestObj.getInt("SRC"));
                listItem = addItem(listItem, item_gallery);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        ItemGalleryAdapter adapter = new ItemGalleryAdapter(listItem);
        holder.recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(holder.relativeLayout.getContext(), LinearLayoutManager.VERTICAL, false);
        holder.recyclerView.setLayoutManager(layoutManager);
        holder.recyclerView.setAdapter(adapter);
        listItem = new ItemGallery[]{};

//        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(view.getContext(), "click on item: " + myListData.getTitle(), Toast.LENGTH_LONG).show();
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return listImg.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
//        public ImageView imageView;
//        public TextView textView;
        public RelativeLayout relativeLayout;
        public RecyclerView recyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
//            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
//            this.textView = (TextView) itemView.findViewById(R.id.textView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recViewGallery);
        }
    }
}
