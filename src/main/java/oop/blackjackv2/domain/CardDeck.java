package oop.blackjackv2.domain;

import java.util.Collections;
import java.util.Stack;

public class CardDeck {

    private Card card;

    public CardDeck(Card card){
        this.card = card;
    }

    public Stack<Card> createCards() {
        Stack<Card> cards = new Stack<>();
        card.addCards(cards);
        Collections.shuffle(cards);
        return cards;
    }
}
