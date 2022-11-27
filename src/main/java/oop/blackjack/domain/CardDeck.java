package oop.blackjack.domain;

import java.util.Collections;
import java.util.Stack;

/**
 * 카드(Card) 뭉치
 * CardDeck은 본인의 역할을 수행하기 위해 52개의 서로 다른 카드가 존재 해야만 한다.
 *
 * 역할
 * - 남아 있는 카드 중 랜덤한 1개의 카드를 준다.
 */
public class CardDeck {

    /**
     * get+remove를 사용하는것보다는 pop을 사용하는 것이 의미상 더 적절한 뜻이 되기도하고 코드가 더 깔끔해지기 때문에
     * List를 Stack으로 변경하겠습니다.
     */
    private Stack<Card> cards;
    private static final int CARD_COUNT = 13; // A, 2~10, J, Q, K


    /**
     * CardDeck을 생성하는 시점에는 52장의 카드들을 가지고 있어야 함으로
     * 생성자를 통해서 초기화 해준다.
     *
     * 수정) 뽑는다는 의미가 더 잘 전달되기 위해서 Stack으로 변경
     * 수정) 카드를 뽑을때마다, 랜덤한 함수를 돌리는 것 보단, 처음에 랜덤하게 섞어놓고 뽑는 것이 더 효율적이기 때문에
     *      shuffle을 통해 랜덤하게 섞어준다.
     */
    public CardDeck() {
        cards = this.generateCards();
        Collections.shuffle(this.cards);
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
     * 수정) 끗수와 점수에 대해서는 Enum을 통해서 그 범위를 제한하게 하여, 더욱 안전하게 만들었다. 간결해졌다.
     * 수정) 카드를 생성시 , 뽑는다는 의미가 더 적절한 뜻이 되기도 해서 Stack으로 변경
     */
    private Stack<Card> generateCards() {
        Stack<Card> cards = new Stack<>();
        for (Card.Pattern pattern : Card.Pattern.values()) {
            for (Card.Denomination denomination : Card.Denomination.values()) {
                cards.add(new Card(pattern, denomination));
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
        return this.cards.pop();
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

    public Stack<Card> getCards(){
        return this.cards;
    }

}
