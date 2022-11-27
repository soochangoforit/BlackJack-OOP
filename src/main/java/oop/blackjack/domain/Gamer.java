package oop.blackjack.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 게이머
 * Gamer의 역할
 * 1. 추가로 카드를 받는다.
 * 2. 봅은 카드를 소유한다.
 * 3. 카드를 오픈한다.
 */
public class Gamer {

    private List<Card> cards;

    /**
     * 생성자를 통해서 cards를 담을 구현체를 추가
     */
    public Gamer() {
        cards = new ArrayList<>();
    }

    /**
    * 카드를 뽑는다. (받는다)
     * 카드를 받을떄마다, 소유한 카드들의 숫자를 확인해야 하니, 카드를 받을떄마다 확인하는 책임을 가지는 메서드를 추가
    */
    public void receiveCard(Card card) {
        this.cards.add(card);
        this.showCards();
    }

    /**
    * 카드 모두를 오픈한다.
     * 현재 갖고 있는 모든 카드들을 전달하는 역할
    */
    private List<Card> openAllCards() {
        return this.cards;
    }

    /**
     * Gamer의 경우, 사용자가 현재 카드들 총 Point를 보며 카드를 더 뽑을지 말지 결정하게 된다.
     * 이를 위해서는 Gamer는 현재 카드들의 숫자의 합을 확인할 수 있어야 한다.
     */
    private void showCards() {
        StringBuilder sb = new StringBuilder();
        sb.append("현재 보유 카드 목록 \n");

        for (Card card : cards) {
            sb.append(card.toString());
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }


}
