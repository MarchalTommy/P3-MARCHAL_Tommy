package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     *
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Deletes a neighbour
     *
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     *
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);

    /**
     * Add to Favorite
     *
     * @param neighbour
     */
    void addToFavorite(Neighbour neighbour);

    /**
     * Delete from Favorite
     *
     * @param neighbour
     */
    void deleteFromFavorite(Neighbour neighbour);

    List<Neighbour> getFavNeighbours();
}


