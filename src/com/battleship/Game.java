package com.battleship;

import javafx.application.Platform;

import java.io.IOException;

public class Game {

    /*
    // GB-25-AA
    På inloggningssidan när spelaren loggar in som server eller klient. Lägg då till en boolean med true/false beroende
    på om det är en klient eller en server som skapats. Skicka med boolean tillsammans med CommunicationHandler-objektet
    när game-objekt skapas.
    */


    //GB-23-AA //GB-25-AA //GB-25-AA
    private CommunicationHandler player;
    private boolean isClientTurn;
    private GameBoard myGameBoard;
    private GameBoard enemyGameBoard;

    //GB-13-AA //GB-23-AA //GB-25-AA
    public Game(CommunicationHandler player, boolean isClient) {
        this.player = player;
        this.isClientTurn = isClient;
    }

    //GB-13-AA //GB-25-AA //GB-30-AA
    public void startGame() {
        myGameBoard = new GameBoard(true);
        enemyGameBoard = new GameBoard(false);

        waitOneSec();

        if (!isClientTurn) { // den här delen kanske kan tas bort sedan
            System.out.println("Waiting for client to connect and make it's fist move");
        }

        new Thread(this::gameLoop).start(); //startar spel-loopen asynkront - tror detta behövs för att inte stoppa upp flödet.
    }

    //GB-25-AA //GB-31-AA
    private void gameLoop(){
        boolean gameOver = false;
        boolean firstMove = true;
        while (!gameOver) {
            if (isClientTurn) {
                if (firstMove){
                    makeMove(player);
                    firstMove = false;
                } else {
                    gameOver = checkIfGameOver();
                    makeMove(player);
                    isClientTurn = false;
                    if (gameOver){
                        break;
                    }
                }
            } else {
                gameOver = checkIfGameOver();
                makeMove(player);
                isClientTurn = true;
                if (gameOver){
                    break;
                }
            }
            gameOver = checkIfGameOver();
            waitOneSec();
        }
        System.out.println("Game over!");
    }

    //GB-31-AA
    private void waitOneSec(){
        try {
            Thread.sleep(1000);  //Vänta 1 sek.
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //GB-19-AA
   private void makeMove(CommunicationHandler player){
        // Metod för att slumpa fram skott - retunera kordinater
        // Metod för att skicka skottet(kordinaterna) till motståndare
    }


    private void setShotOutcome(){ //denna metod bör kanske i BoardGame

    }

    private void getShotOutcome(){ //denna metod bör kanske i BoardGame

    }

    private void updateMaps(String coordinates){
        //Uppdatera GabeBoard-metod(coordinates)
        updateGameView(coordinates);
    }

    //GB-25-AA
    private void updateGameView(String coordinates){ // denna metod kanske bör ligga i GameBoard
        Platform.runLater(() ->{
            //Uppdatera GUI/GameView
        });
    }

    //GB-25-AA
    private boolean checkIfGameOver(){
        boolean gameOver;
        try {
            if (player.getReader().readLine().equals("game over")) {
                gameOver = true;
            } else {
                gameOver = false;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //"protokoll" för att se om spelet är slut / uppdatera GUI/ GameView med "Game Over" - Vinnare är:
        return gameOver;
    }

    public CommunicationHandler getPlayer() {
        return player;
    }

    public void setPlayer(CommunicationHandler player) {
        this.player = player;
    }

    public boolean isClientTurn() {
        return isClientTurn;
    }

    public void setClientTurn(boolean clientTurn) {
        isClientTurn = clientTurn;
    }

    public GameBoard getMyGameBoard() {
        return myGameBoard;
    }

    public void setMyGameBoard(GameBoard myGameBoard) {
        this.myGameBoard = myGameBoard;
    }

    public GameBoard getEnemyGameBoard() {
        return enemyGameBoard;
    }

    public void setEnemyGameBoard(GameBoard enemyGameBoard) {
        this.enemyGameBoard = enemyGameBoard;
    }
}
