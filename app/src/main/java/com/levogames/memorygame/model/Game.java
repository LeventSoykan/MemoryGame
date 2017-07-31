package com.levogames.memorygame.model;

import android.content.Intent;
import android.media.Image;

import com.levogames.memorygame.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Karate Kid on 15.07.2017.
 */

public class Game {

    private List<Tile> tiles = new ArrayList<>();

    public List<Tile> getTiles() {
        return tiles;
    }

    private List<Integer> tileImageId = new ArrayList<>();
    private String playerName;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    private int moveCount = 0;
    private Random random = new Random();
    private String themeName;
    private boolean allTileImagesOpen = false;

    public void incrementMove () {
        moveCount++;
    }

    public int getMoveCount () {
        return moveCount;
    }

    private Tile tile1;

    public Tile getTile1() {
        return tile1;
    }

    public void setTile1(Tile tile1) {
        this.tile1 = tile1;
    }

    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }

    public String getThemeName() {

        return themeName;
    }

    public Game(String themeName, String playerName) {
        this.themeName = themeName;
        this.playerName = playerName;

        Player player = new Player(playerName);
        createTiles(themeName);

    }

    public void createTiles(String themeName) {
        if (tiles != null)   {
            tiles.clear();
        }
        switch (themeName) {
            case "Animals":
                setTileImages(TileTheme.ANIMALS);
                break;
            case "Flags":
                setTileImages(TileTheme.FLAGS);
                break;
            case "Instruments":
                setTileImages(TileTheme.INSTRUMENTS);
                break;
        }
    }

    private void setTileImages (TileTheme tileTheme) {
        switch (tileTheme) {
            case ANIMALS:
                Collections.addAll(tileImageId, R.drawable.bear, R.drawable.cow, R.drawable.dog, R.drawable.elephant,
                        R.drawable.giraffe, R.drawable.lion, R.drawable.owl, R.drawable.penguin, R.drawable.sheep);
                updateImgIDtoTiles();
                break;
            case INSTRUMENTS:
                Collections.addAll(tileImageId, R.drawable.drum, R.drawable.accordion, R.drawable.guitar, R.drawable.flute,
                        R.drawable.mandolin, R.drawable.maracas, R.drawable.harp, R.drawable.piano, R.drawable.violin);
                updateImgIDtoTiles();
                break;
            case FLAGS:
                Collections.addAll(tileImageId, R.drawable.turkey, R.drawable.croatia, R.drawable.australia, R.drawable.germany,
                        R.drawable.ghana, R.drawable.italy, R.drawable.india, R.drawable.yemen, R.drawable.norway);
                updateImgIDtoTiles();
                break;
        }
    }


    public boolean isAMatch (Tile tile, Tile tile1) {
        if (tile1.getTileImageId() == tile.getTileImageId()) {
            return true;
        } else {return  false;}
    }

    private void updateImgIDtoTiles() {
        for (int imageid: tileImageId) {
            Tile tile = new Tile(imageid);
            tiles.add(tile);
        }
        for (int imageId: tileImageId) {
            Tile tile = new Tile(imageId);
            tiles.add(tile);
        }

        Collections.shuffle(tiles);
    }

    public List<Integer> getTileImageIds () {
        List <Integer> imageIds = new ArrayList<>();
        for (Tile tile : tiles) {
            if (tile.getTileImageId() != 0) {
            imageIds.add(tile.getTileImageId());
            }
        }
        return imageIds;
    }

    public boolean isAllTileImagesOpen () {
        for (Tile tile : tiles) {
            if (!tile.isImageOpen()) {
                return  false;
            }
        }
        return true;
    }

    public void setAllTileImagesClosed () {
        for (Tile tile : tiles) {
            tile.setImageClosed();
        }
    }

    @Override
    public String toString() {
        return String.format("Name: %s %n Count: %s %n Theme: %s %n",
                playerName, moveCount, themeName);
    }

    public void saveScore () {
        try {
            FileOutputStream fos = new FileOutputStream(new File("scores.srt"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            String scoreDetail = toString();
            oos.writeObject(scoreDetail);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
