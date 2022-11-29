package oop.blackjackv2.domain;

import java.util.List;

public class CardDeck {

    private Card card;

    public CardDeck(Card card){
        this.card = card;
    }

    public List<Card> createCards() {
        return card.getCards();
    }
}
