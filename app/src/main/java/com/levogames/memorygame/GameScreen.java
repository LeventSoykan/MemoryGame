package com.levogames.memorygame;

import android.animation.TimeAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.levogames.memorygame.model.Game;
import com.levogames.memorygame.model.Tile;

import java.util.Collections;

public class GameScreen extends AppCompatActivity {


    private Drawable image;
    private ImageView firstImageView;
    private ImageView secondImageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        final String themeName = getIntent().getExtras().getString("Theme");
        final Game game = new Game(themeName, "Unknown Player");

        final TextView textView = (TextView) findViewById(R.id.gamePlayText);
        textView.setText(String.format(getString(R.string.gamePlayText), themeName, game.getMoveCount()));



        Button restartButton = (Button) findViewById(R.id.restartButton);

        final GridView gridView = (GridView) findViewById(R.id.tileGrid);
        gridView.setAdapter(new TileAdapter (this, game));


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                game.incrementMove();
                final ImageView imageView = (ImageView) view;
                if (imageView.isEnabled()) {
                    textView.setText(String.format(getString(R.string.gamePlayText), game.getThemeName(), game.getMoveCount()));
                    final Tile selectedTile = game.getTiles().get(position);
                    imageView.setImageResource(selectedTile.getTileImageId());

                    if (game.getTile1() == null) {
                        game.setTile1(selectedTile);
                        firstImageView = imageView;
                        firstImageView.setEnabled(false);


                    } else {
                        secondImageView = imageView;
                        if (!game.isAMatch(game.getTile1(), selectedTile)) {
                            gridView.setEnabled(false);
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    secondImageView.setImageResource(R.drawable.question_mark);
                                    firstImageView.setImageResource(R.drawable.question_mark);
                                    gridView.setEnabled(true);
                                    firstImageView.setEnabled(true);
                                    secondImageView.setEnabled(true);
                                }
                            }, 500);

                        } else {
                            secondImageView.setEnabled(false);
                            firstImageView.setEnabled(false);
                            selectedTile.setImageOpen();
                            game.getTile1().setImageOpen();
                        }
                        game.setTile1(null);
                        if(game.isAllTileImagesOpen()) {
                            Toast.makeText(GameScreen.this,
                                    String.format("Finished in %s moves", game.getMoveCount()),
                                    Toast.LENGTH_SHORT).show();
                            final AlertDialog.Builder builder = new AlertDialog.Builder(GameScreen.this);
                            builder.setTitle("Would you like to save ? ");
                            builder.setMessage("Player Name: ");
                            final EditText text = new EditText(GameScreen.this);
                            builder.setView(text);
                            final String playerName = text.getText().toString();
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (playerName != null) {
                                        game.setPlayerName(playerName);
                                    }
                                    game.saveScore();
                                    Toast.makeText(GameScreen.this,
                                            "Saved",
                                            Toast.LENGTH_SHORT)
                                            .show();
                                }
                            });
                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                            builder.show();
                            game.setAllTileImagesClosed();
                            restartGame(game, textView, gridView);

                        }

                    }
                }

            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame(game, textView, gridView);

            }
        });

    }

    private void restartGame(Game game, TextView textView, GridView gridView) {
        game.setMoveCount(0);
        textView.setText(String.format(getString(R.string.gamePlayText), game.getThemeName(), game.getMoveCount()));
        Collections.shuffle(game.getTiles());
        gridView.setAdapter(new TileAdapter(GameScreen.this, game));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
