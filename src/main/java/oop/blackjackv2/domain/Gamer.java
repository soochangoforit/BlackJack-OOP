package oop.blackjackv2.domain;

import java.util.LinkedList;
import java.util.List;

public class Gamer implements Person {

    private CardDeck cardDeck;

    private List<Card> myCards = new LinkedList<>();

    private String name;

    public Gamer(CardDeck cardDeck, String name){
        this.cardDeck = cardDeck;
        this.name = name;
    }

    @Override
    public void initDraw() {
        addToMine(this.cardDeck.draw());
    }

    @Override
    public void draw() {
        if (isBust()) {
            System.out.println("버스트!! 점수가 21점 초과이기 때문에, 더 이상 카드를 더 뽑을 수 없습니다.");
        } else {
            addToMine(this.cardDeck.draw());
        }
    }

    private boolean isBust() {
        return getPoints() > 21;
    }

    @Override
    public int getPoints() {
        return myCards.stream()
                .mapToInt(Card::getPoint)
                .sum();
    }

    @Override
    public String getName() {
        return name;
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
