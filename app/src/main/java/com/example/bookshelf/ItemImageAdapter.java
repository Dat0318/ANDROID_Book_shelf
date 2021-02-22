package com.example.bookshelf;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemImageAdapter extends RecyclerView.Adapter<ItemImageAdapter.ViewHolder> {
    private ItemImage[] listImg;

    // RecyclerView recyclerView;
    public ItemImageAdapter(ItemImage[] listImg) {
        this.listImg = listImg;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_image, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ItemImage listImage = listImg[position];
        holder.imageView.setImageResource(listImage.getSrc());

        int mWidth = ((RelativeLayout) holder.imageView.getParent()).getWidth();
        System.out.println(mWidth);

//        holder.imageView.getLayoutParams().height = 320;
    }


    @Override
    public int getItemCount() {
        return listImg.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public RelativeLayout relativeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);
            imageView = (ImageView) itemView.findViewById(R.id.imageViewSrc);
        }
    }
}
