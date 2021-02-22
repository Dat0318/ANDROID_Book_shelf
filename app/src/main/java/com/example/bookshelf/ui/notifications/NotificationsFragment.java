package com.example.bookshelf.ui.notifications;

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

import com.example.bookshelf.ItemNotification;
import com.example.bookshelf.ItemNotificationAdapter;
import com.example.bookshelf.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        setupNotificationScreen(root);
        return root;
    }

    private void setupNotificationScreen(View root) {
        ItemNotification[] listNotification = new ItemNotification[]{};
        String home = readJsonFile(root, "notification.json");
        Resources resources = root.getContext().getResources();
        try {
            JSONObject data = new JSONObject(home);
            JSONArray notificationList = data.getJSONArray("list");

            for (int i = 0; i < notificationList.length(); i++) {
                JSONObject obj = notificationList.getJSONObject(i);
                ItemNotification itemSuggest = new ItemNotification();
                itemSuggest.setTitle(obj.getString("title"));
                itemSuggest.setDescription(obj.getString("description"));
                final int resourceId = resources.getIdentifier(obj.getString("avatar"), "drawable", root.getContext().getPackageName());
                itemSuggest.setAvatar(resourceId);
                itemSuggest.setStatus(obj.getInt("status"));

                listNotification = addItem(listNotification, itemSuggest);
            }
            printInfo(root, listNotification);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private ItemNotification[] addItem(ItemNotification[] listNotification, ItemNotification itemSuggest) {
        final int N = listNotification.length;
        listNotification = Arrays.copyOf(listNotification, N + 1);
        listNotification[N] = itemSuggest;
        return listNotification;
    }

    private void printInfo(View root, ItemNotification[] listNotification) {
//        ItemNotification[] myListData = new ItemNotification[]{
//                new ItemNotification("Email", R.drawable.avatar0),
//                new ItemNotification("Info", R.drawable.avatar1),
//                new ItemNotification("Delete", R.drawable.avatar2),
//                new ItemNotification("Dialer", R.drawable.avatar3),
//                new ItemNotification("Alert", R.drawable.avatar4),
//                new ItemNotification("Map", R.drawable.avatar3),
//                new ItemNotification("Email", R.drawable.avatar4),
//                new ItemNotification("Info", R.drawable.avatar2),
//                new ItemNotification("Delete", R.drawable.avatar1),
//                new ItemNotification("Dialer", R.drawable.avatar0),
//                new ItemNotification("Alert", R.drawable.avatar3),
//        };

//         horizontal scroll cycle view
//        LinearLayoutManager layoutManager
//                = new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false);
//        recyclerView.setLayoutManager(layoutManager);

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recViewNotification);
        ItemNotificationAdapter adapter = new ItemNotificationAdapter(listNotification);
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