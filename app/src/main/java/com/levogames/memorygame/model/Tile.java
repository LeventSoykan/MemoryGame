package com.levogames.memorygame.model;

import com.levogames.memorygame.R;

/**
 * Created by Karate Kid on 15.07.2017.
 */

public class Tile {
    private int tileImageId = 0;
    private int defaultImageId = R.drawable.penguin;
    private boolean imageOpen = false;

    public void setImageOpen () {
        imageOpen = true;
    }

    public boolean isImageOpen () {
        return imageOpen;
    }

    public void setImageClosed () {
        imageOpen = false;
    }


    public int getDefaultImageId() {
        return defaultImageId;
    }

    public void setDefaultImageId(int defaultImageId) {
        this.defaultImageId = defaultImageId;
    }

    public int getTileImageId() {
        return tileImageId;
    }

    public void setTileImageId(int tileImageId) {
        this.tileImageId = tileImageId;
    }


    public Tile(int tileImage) {
        this.tileImageId = tileImage;
    }
}
