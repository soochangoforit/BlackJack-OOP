package oop.blackjack.domain;

import java.util.List;

/**
 * blackjack 게임 규칙
 */
public class Rule {

    /**
    * 받은 카드들의 점수를 측정한다.
    */
    private int calculateScore(List<Card> cards) {
        return 0;
    }

    /**
    * Player들의 점수를 비교하여 승자를 판단하는 책임을 담당한다.
    */
    public Player getWinner(List<Player> players) {

        Player highestPlayer = null;
        int highestPoint = 0;

        for (Player player : players) {
            int playerPointSum = getPointSum(player.openCards());
            if (playerPointSum > highestPoint) {
                highestPoint = playerPointSum;
                highestPlayer = player;
            }
        }

        return highestPlayer;
    }

    /**
     * 오픈된 카드의 점수를 계산하는 책임을 담당한다.
     */
    private int getPointSum(List<Card> cards) {
        int sum = 0;
        for(Card card : cards){
            sum += card.getPoint();
        }
        return sum;
    }



}
