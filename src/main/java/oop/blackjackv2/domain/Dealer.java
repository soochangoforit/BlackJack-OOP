package oop.blackjackv2.domain;

import java.util.LinkedList;
import java.util.List;

public class Dealer implements Person {

    private CardDeck cardDeck;
    private List<Card> myCards = new LinkedList<>();

    private final int POINT_LIMIT = 16;

    public Dealer(CardDeck cardDeck){
        this.cardDeck = cardDeck;
    }

    @Override
    public void initDraw() {
        addToMine(this.cardDeck.draw());
    }

    @Override
    public void draw() {
        if (canDraw()) {
            addToMine(this.cardDeck.draw());
        } else {
            System.out.println("딜러는 17점 이상이므로 카드를 뽑지 않습니다.");
        }
    }

    private boolean canDraw() {
        return getPoints() <= POINT_LIMIT;
    }

    @Override
    public int getPoints() {
        return myCards.stream()
                .mapToInt(Card::getPoint)
                .sum();
    }

    @Override
    public String getName() {
        return "딜러";
    }

    @Override
    public String showCards() {
        StringBuilder sb = new StringBuilder();
        for (Card card : myCards) {
            sb.append(card.toString());
        }
        return sb.toString();
    }

    private void addToMine(Card card) {
        this.myCards.add(card);
    }

    public List<Card> getMyCards() {
        return myCards;
    }
}
