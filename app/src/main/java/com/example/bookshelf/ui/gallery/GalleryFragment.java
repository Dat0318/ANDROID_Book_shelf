package com.example.bookshelf.ui.gallery;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.ItemGallery;
import com.example.bookshelf.ItemGalleryAdapter;
import com.example.bookshelf.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        setupGalleryScreen(root);

        return root;
    }

    private void setupGalleryScreen(View root) {
        ItemGallery[] listGallery = new ItemGallery[]{};
        String home = readJsonFile(root, "gallery.json");
        Resources resources = root.getContext().getResources();
        try {
            JSONObject data = new JSONObject(home);
            JSONArray galleryList = data.getJSONArray("data");

            for (int i = 0; i < galleryList.length(); i++) {
                JSONObject obj = galleryList.getJSONObject(i);
                ItemGallery itemGallery = new ItemGallery();
                itemGallery.setTitle(obj.getString("title"));
                final int resourceId = resources.getIdentifier(obj.getString("avatar"), "drawable", root.getContext().getPackageName());
                itemGallery.setAvatar(resourceId);

                JSONArray suggestItem = obj.getJSONArray("listImg");
                int[] listImg = new int[0];
                for (int j = 0; j < suggestItem.length(); j++) {
                    final int srcId = resources.getIdentifier(suggestItem.getString(j), "drawable", root.getContext().getPackageName());
                    listImg = addItem(listImg, srcId);
                }
                itemGallery.setListImg(listImg);
                listGallery = addItem(listGallery, itemGallery);
            }
            printInfo(root, listGallery);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private int[] addItem(int[] listImg, int src) {
        final int N = listImg.length;
        listImg = Arrays.copyOf(listImg, N + 1);
        listImg[N] = src;
        return listImg;
    }

    private ItemGallery[] addItem(ItemGallery[] listGallery, ItemGallery itemGallery) {
        final int N = listGallery.length;
        listGallery = Arrays.copyOf(listGallery, N + 1);
        listGallery[N] = itemGallery;
        return listGallery;
    }

    private void printInfo(View root, ItemGallery[] listGallery) {

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recViewGallery);
        ItemGalleryAdapter adapter = new ItemGalleryAdapter(listGallery);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setAdapter(adapter);
    }

    private String readJsonFile(View root, String file_name) {
        // read file json to create demo data
        String jsonString;
        try {
            InputStream is = root.getContext().getAssets().open(file_name);

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return jsonString;
    }
}