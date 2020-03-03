package com.yuvrajpatel.tictactoe.model;

import androidx.lifecycle.MutableLiveData;

public class Game {

    private static final String TAG = "GAME";
    private static final int BOARD_SIZE = 3;

    public Player player1;
    public Player player2;

    public Player currentPlayer = player1;
    public Cell[][] cells;

    public MutableLiveData<Player> winner = new MutableLiveData<>();
    public Game(String playerOne, String playerTwo) {
        cells = new Cell[BOARD_SIZE][BOARD_SIZE];
        player1 = new Player(playerOne, "X");
        player2 = new Player(playerTwo, "O");
        currentPlayer = player1;
    }

    public void switchPlayer() {
        currentPlayer = currentPlayer == player1 ? player2 : player1;
    }
}
