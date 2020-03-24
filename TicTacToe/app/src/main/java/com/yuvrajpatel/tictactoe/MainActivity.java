package com.yuvrajpatel.tictactoe;

import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;

import com.yuvrajpatel.tictactoe.databinding.ActivityMainBinding;
import com.yuvrajpatel.tictactoe.model.Game;
import com.yuvrajpatel.tictactoe.model.Player;
import com.yuvrajpatel.tictactoe.view.GameEndDialog;
import com.yuvrajpatel.tictactoe.view.GameStartDialog;
import com.yuvrajpatel.tictactoe.viewmodel.GameViewModel;

import java.util.Observer;

import static com.yuvrajpatel.tictactoe.utilities.StringUtilities.isNullOrEmpty;

public class MainActivity extends AppCompatActivity {

    private static final String NO_WINNER = "No one";
    private static final String TAG = "Main_Activity";
    private GameViewModel mGameViewModel;
    private String mNamePlayerOne, mNamePlayerTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startNewGame();
    }

    public void startNewGame(){
        // Creating dialog for start game
        GameStartDialog gameStartDialog = GameStartDialog.newInstance(MainActivity.this);
        gameStartDialog.setCancelable(true);
        gameStartDialog.show(getSupportFragmentManager(), "");
    }

    public void onPlayersSet(String namePlayerOne, String namePlayerTwo) {
        mNamePlayerOne = namePlayerOne;
        mNamePlayerTwo = namePlayerTwo;
        initDataBinding(namePlayerOne, namePlayerTwo);
    }

    private void initDataBinding(String player1, String player2) {
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mGameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);
        mGameViewModel.init(player1, player2);
        activityMainBinding.setGameViewModel(mGameViewModel);
        setUpOnGameEndListener();
    }

    private void setUpOnGameEndListener() {
        mGameViewModel.getWinner().observe(this, this::onGameWinnerChanged);
    }

    @VisibleForTesting
    public void onGameWinnerChanged(Player winner) {
        String winnerName = winner == null || isNullOrEmpty(winner.name) ? NO_WINNER : winner.name;
        GameEndDialog dialog = GameEndDialog.newInstance(this, winnerName);
        dialog.show(getSupportFragmentManager(), "");
        Log.d(TAG, "Winner is :" + winnerName);
    }

    public void playAgain()
    {
        initDataBinding(mNamePlayerOne, mNamePlayerTwo);
    }
}
