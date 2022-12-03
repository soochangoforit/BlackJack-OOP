package oop.blackjackv2.domain;

import java.util.LinkedList;
import java.util.List;

public class Dealer implements Person {

    private CardDeck cardDeck;
    private List<Card> myCards = new LinkedList<>();

    private final int POINT_SIXTEEN_LIMIT = 16;

    public Dealer(CardDeck cardDeck){
        this.cardDeck = cardDeck;
    }

    @Override
    public void initDraw() {
        addToMine(this.cardDeck.draw());
    }

    /**
     * 딜러는 16점 이하면 카드를 한장 더 뽑아야 하고
     * 17점 이상이면 카드를 더 뽑을 수 없다 라는 제한 조건을 가지고 있다.
     * 객체 스스로가 그 제한조건에 위반하지 않는지 확인하는 책임은 갖고 있지만
     * 스스로 그 제약 조건에 따라서 더 뽑을지 말지 결정하지는 못 한다.
     * 그래서 그 결정(더 뽑아야 하는지, 그만 뽑아야 하는지)을 내리는 책임은 누가 가져야 할까?
     * 바로 Rule이 가져야 한다.
     *
     * 그래서 Rule이라는 객체를 만들어서 그 객체에게 더 뽑아야 하는지 말아야 하는지를 물어보는 책임을 부여한다.
     * dealer라는 객체는 그저 "뽑는다", "자신의 카드 상태를 제약 조건에 맞게 상태 값을 알려주는 책임"만 갖고 있다.
     * 그래서 draw()에서 if 분기문으로 카드 점수에 따라서 더 뽑을지 말지 책임을 추가로 부여하지 않았다.
     */
    @Override
    public void draw() {
        addToMine(this.cardDeck.draw());
    }

    public boolean isOverMinLimit() {
        return getPoints() > POINT_SIXTEEN_LIMIT;
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

    public boolean canDraw(View view) {
        if (isOverMinLimit()) {
            view.show("딜러는 17점 이상이기 때문에, 더 이상 카드를 뽑을 수 없습니다.");
            return false;
        }
        draw();
        return true;
    }

    public boolean canStop(View view) {
        if (isOverMinLimit()) {
            return true;
        }
        view.show("딜러는 16점 이하이기 때문에, 카드를 더 뽑아야 합니다.");
        draw();
        return false;
    }
}
