package oop.blackjackv2.domain;

import java.util.Collections;
import java.util.Stack;

public class CardDeck {

    private Card card;
    private Stack<Card> cards = new Stack<>();

    public CardDeck(Card card){
        this.card = card;
    }

    public void createCards() {
        this.card.addCards(this.cards);
        Collections.shuffle(this.cards);
    }

    public Card draw() {
        return cards.pop();
    }

    public Stack<Card> getCards() {
        return cards;
    }
}
