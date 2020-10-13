package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.AddFavoriteEvent;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.events.RemoveFromFavEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import static android.content.ContentValues.TAG;


public class FavoritesFragment extends Fragment {

    private NeighbourApiService mApiService = DI.getNeighbourApiService();
    private RecyclerView mRecyclerView;
    private List<Neighbour> mFavNeighbours;

    public static FavoritesFragment newInstance() {
        return (new FavoritesFragment());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_neighbour_favlist, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        EventBus.getDefault().register(this);
        return view;
    }

    /**
     * Init the List of neighbours
     */
    private void initList() {
        mFavNeighbours = mApiService.getFavNeighbours();
        mRecyclerView.setAdapter(new MyNeighbourRecyclerViewAdapter(mFavNeighbours));
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onAddFav(AddFavoriteEvent event) {
        mApiService.addToFavorite(event.neighbour);
        initList();
        Log.d(TAG, "onClick: User added to FAVORITES");
    }

    @Subscribe
    public void onRemoveFav(RemoveFromFavEvent event) {
        mApiService.deleteFromFavorite(event.neighbour);
        initList();
        Log.d(TAG, "onClick: User removed from FAVORITES");
    }

    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        Log.d(TAG, "onDeleteNeighbour: Neighbour has been deleted.");
        mApiService.deleteNeighbour(event.neighbour);
        initList();
    }
}
