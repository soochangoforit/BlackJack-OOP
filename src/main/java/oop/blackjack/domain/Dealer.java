package oop.blackjack.domain;

import java.util.List;

/**
 * 딜러
 *
 * Dealer의 역할
 * - 추가로 카드를 받는다.
 * - 단 2카드의 합계 점수가 16점 이하이면 반드시 1장을 추가로 뽑고, 17점 이상이면 받을 수 없다.
 * - 뽑은 카드를 소유한다.
 * - 카드를 오픈한다.
 *
 * 이렇게 될 수 있었던 이유는 게임의 승패를 판단하는 것은 Rule 객체가, 카드를 뽑는 것은 카드덱 객체가 맡았기 때문이다.
 */
public class Dealer {

    private List<Card> cards; // 딜러가 가지고 있는 카드 목록 (카드를 소유한다)

    /**
     * 카드를 뽑는다 (받는다).
     */
    private void receiveCard(Card card) {
    }

    /**
     * 카드를 모두 오픈한다.
     */
    private List<Card> openAllCards() {
        return null;
    }

}
