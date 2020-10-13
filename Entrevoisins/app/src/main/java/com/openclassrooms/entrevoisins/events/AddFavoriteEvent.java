package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user adds a Neighbour to favorite
 */
public class AddFavoriteEvent {

    /**
     * Neighbour to add to Fav
     */
    public Neighbour neighbour;

    /**
     * Constructor.
     *
     * @param neighbour
     */
    public AddFavoriteEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}
