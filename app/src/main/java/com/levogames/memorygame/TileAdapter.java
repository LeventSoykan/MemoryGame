package com.levogames.memorygame;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.levogames.memorygame.model.Game;
import com.levogames.memorygame.model.Tile;

import java.util.List;

import static com.levogames.memorygame.R.id.imageView;

/**
 * Created by Karate Kid on 16.07.2017.
 */
public class TileAdapter extends BaseAdapter {
    private Context context;
    public Game game;

    public TileAdapter(Context c, Game game) {
        this.context = c;
        this.game = game;
    }

    @Override
    public View getView (final int position, View imgView, ViewGroup parent) {
        final ImageView imageView;
        if (imgView == null) {
                imageView = new ImageView(context);
                imageView.setLayoutParams(new GridView.LayoutParams(90, 90));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setPadding(1,1,1,1);
                imageView.setBackgroundColor(Color.WHITE);
        } else {
            imageView = (ImageView) imgView;
        }

        imageView.setImageResource(R.drawable.question_mark);
        return imageView;
    }

    @Override
    public int getCount() {
        return game.getTiles().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}

