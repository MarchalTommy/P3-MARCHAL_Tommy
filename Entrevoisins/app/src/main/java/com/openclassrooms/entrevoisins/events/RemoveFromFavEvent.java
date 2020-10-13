package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

public class RemoveFromFavEvent {

    public Neighbour neighbour;

    public RemoveFromFavEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}
