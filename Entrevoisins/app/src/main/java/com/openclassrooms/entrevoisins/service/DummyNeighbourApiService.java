package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements NeighbourApiService {

    private static final String TAG = "DummyNeighbourApi";
    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> favNeighbours;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     *
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    public void addToFavorite(Neighbour neighbour) {
        for (Neighbour i : neighbours) {
            if (i.getId() == neighbour.getId()) {
                neighbour.setIsFav(true);
                i.setIsFav(true);
            }
        }
    }

    @Override
    public void deleteFromFavorite(Neighbour neighbour) {
        for (Neighbour i : neighbours) {
            if (i.getId() == neighbour.getId()) {
                neighbour.setIsFav(false);
                i.setIsFav(false);
            }
        }
    }

    @Override
    public List<Neighbour> getFavNeighbours() {
        favNeighbours = new ArrayList<>();
        favNeighbours.addAll(neighbours);
        for (Neighbour i : neighbours) {
            if (!i.getIsFav()) {
                favNeighbours.remove(i);
            }
        }
        return favNeighbours;
    }
}

