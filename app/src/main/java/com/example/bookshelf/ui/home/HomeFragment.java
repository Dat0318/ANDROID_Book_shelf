package com.example.bookshelf.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.ItemSuggest;
import com.example.bookshelf.ItemSuggestAdapter;
import com.example.bookshelf.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private static final String TAG = "HomeFragment";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        setupTabHome(root);

        BottomNavigationView bottomNavigationView = root.findViewById(R.id.nav_bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Log.e(TAG, "Click home tag");
                        break;
                    case R.id.navigation_dashboard:
                        Log.e(TAG, "Click dashboard tag");
                        break;
                    case R.id.navigation_news:
                        Log.e(TAG, "Click notifications tag");
                        break;
                }

                return true;
            }
        });
        return root;
    }

    private void setupTabHome(View root) {
        ItemSuggest[] listSuggest = new ItemSuggest[]{};
        String home = readJsonFile(root, "home.json");
        try {
            JSONObject data = new JSONObject(home);
            JSONArray suggestList = data.getJSONArray("data");

            for (int i = 0 ; i < suggestList.length(); i++) {
                JSONObject obj = suggestList.getJSONObject(i);
                ItemSuggest  itemSuggest = new ItemSuggest();
                itemSuggest.setDescription(obj.getString("title"));
                System.out.println(obj.getString("avatar"));
                itemSuggest.setImgId(123);
                listSuggest = addItem(listSuggest, itemSuggest);
                System.out.println("=====================");
                System.out.println(listSuggest);

                JSONArray suggestItem = obj.getJSONArray("list");
                for (int j = 0 ; j < suggestItem.length(); j++) {
                    JSONObject suggestObj = suggestItem.getJSONObject(j);

//                    System.out.println(suggestObj);
//                    System.out.println(suggestObj.getClass().getSimpleName());

                    String A = suggestObj.getString("name");
                    String B = suggestObj.getString("src");
//                    System.out.println(A);
//                    System.out.println(B);
                }
            }
            printInfo(root);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private ItemSuggest[] addItem(ItemSuggest[] listSuggest, ItemSuggest itemSuggest) {
        final int N = listSuggest.length;
        listSuggest = Arrays.copyOf(listSuggest, N + 1);
        listSuggest[N] = itemSuggest;
        return listSuggest;
    }

    private void printInfo(View root) {
        ItemSuggest[] myListData = new ItemSuggest[]{
                new ItemSuggest("Email", R.drawable.avatar0),
                new ItemSuggest("Info", R.drawable.avatar1),
                new ItemSuggest("Delete", R.drawable.avatar2),
                new ItemSuggest("Dialer", R.drawable.avatar3),
                new ItemSuggest("Alert", R.drawable.avatar4),
                new ItemSuggest("Map", R.drawable.avatar3),
                new ItemSuggest("Email", R.drawable.avatar4),
                new ItemSuggest("Info", R.drawable.avatar2),
                new ItemSuggest("Delete", R.drawable.avatar1),
                new ItemSuggest("Dialer", R.drawable.avatar0),
                new ItemSuggest("Alert", R.drawable.avatar3),
        };
//         horizontal scroll cycle view
//        LinearLayoutManager layoutManager
//                = new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false);
//        recyclerView.setLayoutManager(layoutManager);

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recViewSuggest);
        ItemSuggestAdapter adapter = new ItemSuggestAdapter(myListData);
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