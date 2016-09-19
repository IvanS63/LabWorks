package com.mypackage.lab8.store.entity;

import com.mypackage.lab8.store.model.Store;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class StoreProxy implements Serializable {
    private Set<ArtistProxy> artistProxySet;


    public Set<ArtistProxy> getArtistProxySet() {
        return artistProxySet;
    }

    public void setArtistProxySet(Set<ArtistProxy> artistProxySet) {
        this.artistProxySet = artistProxySet;
    }

    public StoreProxy(Store store) {
        artistProxySet = new HashSet<>();
        artistProxySet.addAll(store.getArtistSet().stream().map(ArtistProxy::new).collect(Collectors.toList()));
    }

    public StoreProxy() {
        artistProxySet = new HashSet<>();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (ArtistProxy artistProxy : artistProxySet) {
            stringBuilder.append(artistProxy.toString());
        }
        return stringBuilder.toString();
    }
}
