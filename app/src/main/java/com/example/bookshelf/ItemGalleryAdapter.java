package com.example.bookshelf;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;

public class ItemGalleryAdapter extends RecyclerView.Adapter<ItemGalleryAdapter.ViewHolder> {
    private ItemGallery[] listImg;
    private ItemImage[] listItem = new ItemImage[]{};

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

    private ItemImage[] addItem(ItemImage[] listItemImage, ItemImage itemImage) {
        final int N = listItemImage.length;
        listItemImage = Arrays.copyOf(listItemImage, N + 1);
        listItemImage[N] = itemImage;
        return listItemImage;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ItemGallery listItem = listImg[position];
        Resources resources = holder.relativeLayout.getContext().getResources();
        int[] imageArray = listItem.getListImg();
        ItemImage[] imageSrc = new ItemImage[]{};

        for (int i = 0; i < imageArray.length; i++) {
            ItemImage itemImage = new ItemImage();
            itemImage.setSrc(imageArray[i]);

            imageSrc = addItem(imageSrc, itemImage);
        }

        ItemImageAdapter adapter = new ItemImageAdapter(imageSrc);
        holder.recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(holder.relativeLayout.getContext(), LinearLayoutManager.VERTICAL, false);
        holder.recyclerView.setLayoutManager(layoutManager);
        holder.recyclerView.setAdapter(adapter);
        int numberOfColumns = 2;
        holder.recyclerView.setLayoutManager(new GridLayoutManager(holder.relativeLayout.getContext(), numberOfColumns));
        imageSrc = new ItemImage[]{};
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
