package com.yuvrajpatel.tictactoe.model;

public class Cell {

    public Player player;

    public Cell(Player player) {
        this.player = player;
    }

    public boolean isEmpty() {
        if(player == null){
            return true;
        } else {
            return false;
        }
    }
}
