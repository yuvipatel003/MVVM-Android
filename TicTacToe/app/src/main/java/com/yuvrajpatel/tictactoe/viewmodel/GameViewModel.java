package com.yuvrajpatel.tictactoe.viewmodel;

import android.util.ArrayMap;

import com.yuvrajpatel.tictactoe.model.Cell;
import com.yuvrajpatel.tictactoe.model.Game;
import com.yuvrajpatel.tictactoe.model.Player;

import androidx.databinding.ObservableArrayMap;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import static com.yuvrajpatel.tictactoe.utilities.StringUtilities.stringFromNumbers;

public class GameViewModel extends ViewModel {

    public ObservableArrayMap<String, String> cells;
    private Game mGame;

    public void init(String player1, String player2) {
        mGame = new Game(player1, player2);
        cells = new ObservableArrayMap<>();
    }

    public void onClickedCellAt(int row, int column) {
        if (mGame.cells[row][column] == null) {
            mGame.cells[row][column] = new Cell(mGame.currentPlayer);
            cells.put(stringFromNumbers(row, column), mGame.currentPlayer.value);
            if (mGame.hasGameEnded())
                mGame.reset();
            else
                mGame.switchPlayer();
        }
    }

    public LiveData<Player> getWinner() {
        return mGame.winner;
    }
}
