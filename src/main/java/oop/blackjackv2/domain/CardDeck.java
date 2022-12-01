package oop.blackjackv2.domain;

import java.util.LinkedList;
import java.util.List;

public class CardDeck {

    private Card card;

    public CardDeck(Card card){
        this.card = card;
    }

    public List<Card> createCards() {
        List<Card> cards = new LinkedList<>();
        card.addCards(cards);
        return cards;
    }
}
