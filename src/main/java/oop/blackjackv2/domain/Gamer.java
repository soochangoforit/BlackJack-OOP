package oop.blackjackv2.domain;

import java.util.LinkedList;
import java.util.List;

public class Gamer {

    private CardDeck cardDeck;

    private List<Card> myCards = new LinkedList<>();

    public Gamer(CardDeck cardDeck){
        this.cardDeck = cardDeck;
    }

    public void initDraw() {
        addToMine(this.cardDeck.draw());
    }

    private void addToMine(Card card) {
        this.myCards.add(card);
    }

    public List<Card> getMyCards() {
        return myCards;
    }
}
