package oop.blackjackv2.domain;

import java.util.LinkedList;
import java.util.List;

public class Gamer implements Person {

    private CardDeck cardDeck;

    private List<Card> myCards = new LinkedList<>();

    public Gamer(CardDeck cardDeck){
        this.cardDeck = cardDeck;
    }

    @Override
    public void initDraw() {
        addToMine(this.cardDeck.draw());
    }

    @Override
    public void draw() {
        addToMine(this.cardDeck.draw());
    }

    private void addToMine(Card card) {
        this.myCards.add(card);
    }

    public List<Card> getMyCards() {
        return myCards;
    }
}
