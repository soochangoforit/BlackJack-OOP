package oop.blackjack.domain;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 카드(Card) 뭉치
 * CardDeck은 본인의 역할을 수행하기 위해 52개의 서로 다른 카드가 존재 해야만 한다.
 *
 * 역할
 * - 남아 있는 카드 중 랜덤한 1개의 카드를 준다.
 */
public class CardDeck {

    private List<Card> cards;

    private static final String[] PATTERNS = {"spade","heart", "diamond", "clover"};
    private static final int CARD_COUNT = 13; // A, 2~10, J, Q, K


    /**
     * CardDeck을 생성하는 시점에는 52장의 카드들을 가지고 있어야 함으로
     * 생성자를 통해서 초기화 해준다.
     */
    public CardDeck() {
        cards = this.generateCards();
    }

    /**
     * 생성자에서는 52개의 카드를 생성하는데 그 목적만 가지고 있다.
     * 숫자 1~13을 통해 끗자리 문양을 결정하는 책임을 분리하자.
     * 하나의 메서드(행동)는 하나의 책임만 가지도록 하자
     *
     * 해당 행동은 1~13을 통해 끗자리 문양을 결정하는 행동을 수행한다.
     */
    private String numberToDenomination (int number) {
        if (number == 1) {
            return "A";
        } else if (number == 11) {
            return "J";
        } else if (number == 12) {
            return "Q";
        } else if (number == 13) {
            return "K";
        } else {
            return String.valueOf(number);
        }
    }

    /**
     * 생성자가 실행을 시킬 역할이 있을 뿐이지 실제 비지니스 로직을 알고 있어야 할 피료는 없다.
     *
     * 그렇기에 생성자에서 해당 코드를 분리한다.
     * 생성자를 단지 생성하는 역할만 필요할 뿐, 구체적인 로직에 대해서는 알 필요가 없다.
     *
     * 이렇게 분리하고 나면, 각 메서드는 하나의 역할에만 충실할 수 있게 되었다.
     *
     * 수정) 삭제되는 성능을 고려해서 ArrayList -> LinkedList로 변경
     */
    private List<Card> generateCards() {
        List<Card> cards = new LinkedList<>();
        for (String pattern : PATTERNS) {
            for (int i = 1; i <= CARD_COUNT; i++) {
                String denomination = this.numberToDenomination(i);
                Card card = new Card(pattern, denomination);

                cards.add(card);
            }
        }
        return cards;
    }


    /**
     * 카드 뭉치에서 1개를 꺼내서 반환한다.
     *
     * draw()는 2가지 책임을 갖고 있다.
     * 1. 남아 있는 카드 중 1개를 뽑는다.
     * 2. 뽑은 카드는 카드덱에서 제거한다.
     *
     * 수정) 남아 있는 카드 1개를 뽑고, 카드 덱에서 제거하는 책임 2가리를 하나의 메서드 안에서 수행 -> 분리
     *      따라서, draw()는 카드를 뽑는 책임만 갖게 된다.
     */
    public Card draw() {
        Card selectedCard = this.getRandCard();
        cards.remove(selectedCard);
        return selectedCard;
    }

    /**
     * draw()에서 카드를 뽑아내는, 책임을 분리한다.
     * 하나의 메서드는 하나의 책임만 가져야 한다.
     *
     * private 접근 제어자를 둠으로써, 타인이 코드만 보고도, 해당 변수/메소드는 현재 클래스에서만
     * 사용된다는 것을 명시 하는것이 더 좋다.
     */
    private Card getRandCard() {
        int size = cards.size();
        int select = (int) (Math.random() * size);
        return cards.get(select);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(Card card : cards){
            sb.append(card.toString());
            sb.append("\n");
        }

        return sb.toString();
    }

}
