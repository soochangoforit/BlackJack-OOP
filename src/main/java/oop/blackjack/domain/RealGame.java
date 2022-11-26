package oop.blackjack.domain;

/**
 * blackjack 게임을 실행시킬 객체
 */
public class RealGame {

    /**
    * 블랙잭 게임을 시작한다.
    */
    public void play(){
        System.out.println("=====BlackJack Game Start=====");

        // 딜러 생성
        Dealer dealer = new Dealer();

        // 게이머 생성
        Gamer gamer = new Gamer();

        // 카드 뭉치 생성
        CardDeck cardDeck = new CardDeck();

        // 규칙 생성
        Rule rule = new Rule();

        // 첫번째 카드를 뽑기 위해
        //Card card = cardDeck.getCard();

        System.out.println(cardDeck.toString());

    }
}
