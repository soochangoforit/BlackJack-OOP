package oop.blackjack.domain;

import java.util.List;

/**
 * Dealer와 Gamer의 공통적인 행동을 추상화
 *
 * 1. 카드를 받고
 * 2. 자신의 카드를 확인한다.
 * 3. 카드를 오픈한다.
 *
 * 추상화를 통해서 인터페이스와 Composition을 활용하고자 한다.
 */
public interface Player {

    void receiveCard(Card card);

    void showCards();

    List<Card> openCards();
}
