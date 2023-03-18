package fr.publicis.sapient.models;

import fr.publicis.sapient.mower.Clipper;

import java.util.List;
import java.util.Map;

/**
 * Author: Abou FOFANA
 * At: 18/03/2023
 **/
public class DataContainer {
    private LawnDimension lawnDimension;

    private Map<Clipper, List<Character>> clipperMap;

    public LawnDimension getLawnDimension() {
        return lawnDimension;
    }

    public void setLawnDimension(LawnDimension lawnDimension) {
        this.lawnDimension = lawnDimension;
    }

    public Map<Clipper, List<Character>> getClipperMap() {
        return clipperMap;
    }

    public void setClipperMap(Map<Clipper, List<Character>> clipperMap) {
        this.clipperMap = clipperMap;
    }
}
