package oop.blackjack.domain;

import java.util.ArrayList;
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
public class Dealer implements Player {

    private List<Card> cards; // 딜러가 가지고 있는 카드 목록 (카드를 소유한다)

    private final int CAN_RECEIVE_POINT = 16;

    public Dealer() {
        this.cards = new ArrayList<>();
    }

    /**
     * 카드를 뽑는다 (받는다).
     */
    public void receiveCard(Card card) {
        if(this.isReceiveCard()) {
            this.cards.add(card);
            this.showCards();
        }else {
            System.out.println("카드의 총 합이 17이상입니다. 더 이상 카드를 받을 수 없습니다.");
        }
    }

    /**
     * 딜러가 가질 수 있는 카드 점수의 총합이 16점 이하인지 판단하는 행동을 한다.
     *
     * 단순히 boolean을 return 하는데 이러한 메서드가 필요할까? 의문이 들 수 있다.
     * 하지만 누가 봐도 이 코드가 무엇을 하는지 명확히 표현하는 것이 중요하다.
     * 그 일은 주석이 하는 것이 아니다. 의도가 명확한 코드와 변수명, 메소드명이 해야하는 것이다.
     */
    private boolean isReceiveCard(){
        return getPointSum() <= CAN_RECEIVE_POINT;
    }

    private int getPointSum(){
        int sum = 0;
        for(Card card : cards){
            sum += card.getPoint();
        }
        return sum;
    }

    public void showCards(){
        StringBuilder sb = new StringBuilder();
        sb.append("딜러 현재 보유 카드 목록 \n");

        for(Card card : cards){
            sb.append(card.toString());
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    /**
     * 카드를 모두 오픈한다.
     */
    @Override
    public List<Card> openCards() {
        return this.cards;
    }

}
