package oop.blackjackv2.domain;

public class Rule {

    private Gamer gamer;
    private Dealer dealer;
    private final int initCardsCount = 2;

    public Rule(Gamer gamer, Dealer dealer) {
        this.gamer = gamer;
        this.dealer = dealer;
    }

    /**
     * Rule의 책임을 딜러와 게이머 각각의 객체에 카드를 뽑도록 지시하고,
     * 순차적으로 뽑기 위해 순서를 관리하는 책임을 갖는다.
     */
    public void initialDraw(){
        for (int i = 0; i < initCardsCount; i++) {
            gamer.initDraw();
            dealer.initDraw();
        }
    }

}
