package com.mypackage.lab8.store.model;

import javax.xml.bind.annotation.*;
import java.util.Set;

/**
 * Store class
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "artists")
public class Store {
    @XmlElement(name = "artist")
    private Set<Artist> artistSet;

    public Set<Artist> getArtistSet() {
        return artistSet;
    }

    public void setArtistSet(Set<Artist> artistSet) {
        this.artistSet = artistSet;
    }


    public Store(Set<Artist> artistSet) {
        this.artistSet = artistSet;
    }

    public Store() {

    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Artist artist : artistSet) {
            stringBuilder.append(artist.toString());
        }
        return stringBuilder.toString();
    }


}
