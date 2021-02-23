package com.example.bookshelf.ui.home;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookshelf.Home;
import com.example.bookshelf.ItemSuggest;
import com.example.bookshelf.ItemSuggestAdapter;
import com.example.bookshelf.R;
import com.example.bookshelf.ui.favorites.FavoritesFragment;
import com.example.bookshelf.ui.news.NewsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;


public class HomeFragment extends Fragment{

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

//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.add(R.id.navigation_dashboard, new FavoritesFragment(), "FavoritesFragment");
//        fragmentTransaction.add(R.id.navigation_news, new NewsFragment(), "NewsFragment");
//        fragmentTransaction.commit();

        BottomNavigationView bottomNavigationView = root.findViewById(R.id.nav_bottom);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Log.e(TAG, "Click home tag");
                        break;
                    case R.id.navigation_favorites:
                        Log.e(TAG, "Click dashboard tag");
//                        Navigation.findNavController(root).navigate(R.id.actionFavorites);
//                        Intent goFavorites = new Intent(container.getContext(), Home.class);
//                        startActivityForResult(goFavorites, 0);
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
        Resources resources = root.getContext().getResources();
        try {
            JSONObject data = new JSONObject(home);
            JSONArray suggestList = data.getJSONArray("data");

            for (int i = 0 ; i < suggestList.length(); i++) {
                JSONObject obj = suggestList.getJSONObject(i);
                ItemSuggest itemSuggest = new ItemSuggest();
                itemSuggest.setDescription(obj.getString("title"));
                final int resourceId = resources.getIdentifier(obj.getString("avatar"), "drawable", root.getContext().getPackageName());
                itemSuggest.setImgId(resourceId);

                JSONArray suggestItem = obj.getJSONArray("list");
                JSONArray listItem = new JSONArray();
                for (int j = 0; j < suggestItem.length(); j++) {
                    JSONObject suggestObj = suggestItem.getJSONObject(j);
                    JSONObject item = new JSONObject();
                    String name;
                    Resources src = root.getContext().getResources();
                    name = suggestObj.getString("name");
                    final int srcId = resources.getIdentifier(suggestObj.getString("src"), "drawable", root.getContext().getPackageName());
                    item.put("NAME", name);
                    item.put("SRC", srcId);
                    listItem.put(item);
                }
                itemSuggest.setListItem(listItem);
                listSuggest = addItem(listSuggest, itemSuggest);
            }
            printInfo(root, listSuggest);
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

    private void printInfo(View root, ItemSuggest[] listSuggest) {
//        ItemSuggest[] myListData = new ItemSuggest[]{
//                new ItemSuggest("Email", R.drawable.avatar0),
//                new ItemSuggest("Info", R.drawable.avatar1),
//                new ItemSuggest("Delete", R.drawable.avatar2),
//                new ItemSuggest("Dialer", R.drawable.avatar3),
//                new ItemSuggest("Alert", R.drawable.avatar4),
//                new ItemSuggest("Map", R.drawable.avatar3),
//                new ItemSuggest("Email", R.drawable.avatar4),
//                new ItemSuggest("Info", R.drawable.avatar2),
//                new ItemSuggest("Delete", R.drawable.avatar1),
//                new ItemSuggest("Dialer", R.drawable.avatar0),
//                new ItemSuggest("Alert", R.drawable.avatar3),
//        };

//         horizontal scroll cycle view
//        LinearLayoutManager layoutManager
//                = new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false);
//        recyclerView.setLayoutManager(layoutManager);

        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recViewSuggest);
        ItemSuggestAdapter adapter = new ItemSuggestAdapter(listSuggest);
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