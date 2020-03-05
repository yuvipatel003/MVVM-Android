package com.yuvrajpatel.tictactoe.model;

import static com.yuvrajpatel.tictactoe.utilities.StringUtilities.isNullOrEmpty;

public class Cell {

    public Player player;

    public Cell(Player player) {
        this.player = player;
    }

    public boolean isEmpty() {
        return player == null || isNullOrEmpty(player.value);
    }
}