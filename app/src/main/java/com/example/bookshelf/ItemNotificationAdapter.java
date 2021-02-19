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
    private ItemNotification[] listdata;
    private itemOfSuggest[] listItem = new itemOfSuggest[]{};

    // RecyclerView recyclerView;
    public ItemNotificationAdapter(ItemNotification[] listdata) {
        this.listdata = listdata;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_suggest, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    private itemOfSuggest[] addItem(itemOfSuggest[] listOfSuggest, itemOfSuggest itemSuggest) {
        final int N = listOfSuggest.length;
        listOfSuggest = Arrays.copyOf(listOfSuggest, N + 1);
        listOfSuggest[N] = itemSuggest;
        return listOfSuggest;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ItemNotification myListData = listdata[position];
        holder.textView.setText(listdata[position].getDescription());
        holder.imageView.setImageResource(listdata[position].getImgId());
        JSONArray jsonArray = (JSONArray) listdata[position].getListItem();

        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                itemOfSuggest item_of_suggest = new itemOfSuggest();
                JSONObject suggestObj = jsonArray.getJSONObject(i);
                item_of_suggest.setName(suggestObj.getString("NAME"));
                item_of_suggest.setImg(suggestObj.getInt("SRC"));
                listItem = addItem(listItem, item_of_suggest);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

//            itemOfSuggest[] listDemo = new itemOfSuggest[]{
//                    new itemOfSuggest("Email", R.drawable.avatar0),
//                    new itemOfSuggest("Info", R.drawable.avatar1),
//                    new itemOfSuggest("Delete", R.drawable.avatar2),
//                    new itemOfSuggest("Dialer", R.drawable.avatar3),
//                    new itemOfSuggest("Alert", R.drawable.avatar4),
//                    new itemOfSuggest("Map", R.drawable.avatar3),
//                    new itemOfSuggest("Email", R.drawable.avatar4),
//                    new itemOfSuggest("Info", R.drawable.avatar2),
//                    new itemOfSuggest("Delete", R.drawable.avatar1),
//                    new itemOfSuggest("Dialer", R.drawable.avatar0),
//                    new itemOfSuggest("Alert", R.drawable.avatar3),
//            };

        itemOfSuggestAdapter adapter = new itemOfSuggestAdapter(listItem);
        holder.recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(holder.relativeLayout.getContext(), LinearLayoutManager.HORIZONTAL, false);
        holder.recyclerView.setLayoutManager(layoutManager);
        holder.recyclerView.setAdapter(adapter);
        listItem = new itemOfSuggest[]{};

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "click on item: " + myListData.getDescription(), Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public RelativeLayout relativeLayout;
        public RecyclerView recyclerView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.imageView);
            this.textView = (TextView) itemView.findViewById(R.id.textView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recViewItemSuggest);
        }
    }
}
