package com.levogames.memorygame;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.levogames.memorygame.model.TileTheme;

public class MainActivity extends AppCompatActivity {


    //TODO: Receive playername and create a class for player
    //TODO: Make the design better


    public String[] themeNames = {"Animals", "Flags", "Instruments"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Theme Selection");
                int checkedItem = -1;
                builder.setSingleChoiceItems(themeNames, checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, GameScreen.class);
                        switch (which) {
                            case 0 : intent.putExtra("Theme", "Animals");
                                break;
                            case 1 : intent.putExtra("Theme", "Flags");
                                break;
                            case 2 : intent.putExtra("Theme", "Instruments");
                                break;
                        }

                            startActivity(intent);

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }


}
