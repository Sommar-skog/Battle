package com.battleship;

import com.battleship.graphic.LoginView;
import javafx.application.Application;

public class Main {
    public static void main(String[] args) {


        GameBoard gameBoard = new GameBoard(true);
        gameBoard.displayBoard();


        //GB-15-SA
        try{
            Application.launch(LoginView.class, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


}