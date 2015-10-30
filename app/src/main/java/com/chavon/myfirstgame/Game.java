package com.chavon.myfirstgame;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import static android.app.PendingIntent.getActivity;
import static java.lang.reflect.Array.getInt;


public class Game extends Activity {

    public int bestScore;
    public GamePanel thisGame;
    public static MediaPlayer mediaPlayer;

    //this is what happens when the app is opened
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //turn title off
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //set to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // content of the game

        thisGame = new GamePanel(this);
        setContentView(thisGame);

        //getting preferences
        SharedPreferences prefs = getSharedPreferences("integers", Context.MODE_PRIVATE);
        int best = prefs.getInt("best_score",0);
        thisGame.setBest(best);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop(){
        super.onStop();

        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences prefs = getSharedPreferences("integers", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        bestScore = thisGame.getBest();
        editor.putInt("best_score", bestScore);

        // Commit the edits!
        editor.commit();


    }
}
