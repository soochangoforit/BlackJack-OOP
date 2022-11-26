package oop.blackjack;

import oop.blackjack.domain.RealGame;

public class Application {

    public static void main(String[] args) {
        RealGame realGame = new RealGame();
        realGame.play();
    }
}
