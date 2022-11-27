package oop.blackjack.domain;

/**
 * 카드
 */
public class Card {

    private String pattern; // 스페이드, 하트, 다이아몬드, 클로버
    private String denomination; // A, 2~10, K,Q,J
    private int point;

    /**
     * setter를 통해 객체를 초기화 하는 방향보다는 생성자를 통해서 초기화 하는 방향이 더 좋다.
     *
     * 기본 생성자와 set 메서드를 사용하지 않고, 추가된 생성자를 사용한 이유는 무엇일까?
     * 1. 끗수와 무늬를 가지고 Card가 어떤 행위를 하는지 CardDeck은 몰라도 된다.
     *    - 즉, Card에서 끗수와 무늬를 마음대로 활용하더라도 CardDeck은 아무런 영향이 없다.
     *
     * 2. Card는 끗수와 무늬가 필수임을 강제 할 수 있다.
     *    - Card에 기본 생성자가 있으면 끗수와 무늬가 없는 Card가 생성 될 수 있다.
     *    - 하지만 이렇게 생성자를 통해서 객체를 생성하도록 하면, 끗수와 무늬가 없는 Card는 생성 할 수 없다.
     */
    public Card(String pattern, String denomination, int point) {
        this.pattern = pattern;
        this.denomination = denomination;
        this.point = point;
    }

    /**
     * 이전의 코드에서는 CardDeck에서 Card를 생성하면서, 끗수를 결정하고 패턴을 결정했지만
     * 해당 책임을 이젠 Card가 직접 처리하도록 수정
     */
    public Card(String pattern, int i) {
        this.pattern = pattern;
        this.denomination = numberToDenomination(i);
        this.point = numberToPoint(this.denomination);
    }


    /**
     * 객체의 캡슐화를 보장하기 위해서, 기본적으로 Getter, Setter를 private 으로 선언한다.
     * 필요한 경우 public 으로 노출 시킨다.
     */
    private String getPattern() {
        return pattern;
    }

    private String getDenomination() {
        return denomination;
    }

    private void setPattern(String pattern) {
        this.pattern = pattern;
    }

    private void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    @Override
    public String toString() {
        return "Card [pattern=" + pattern + ", denomination=" + denomination + "]";
    }


    /**
     * 원래 해당 메서드(행동, 끗수를 보고 Point 점수 반영)은 CardDeck에서 처리를 했다.
     * 하지만 CardDeck은 단지 서로 다른 52장의 카드를 생성하는 책임만 갖고 있다.
     *
     * 실제 Card 끗수에 대한 Point를 지정할 책임은 없다. "정보 전문가 패턴"을 고려해서 Card의 끗수를 보고
     * 점수를 부여하는데 그 정보를 가장 잘 알고 있는 Card에게 그 책임을 부여하자.
     */
    private int numberToPoint(String denomination) {
        if (denomination.equals("A")) {
            return 1;
        } else if (denomination.equals("J") || denomination.equals("Q") || denomination.equals("K")) {
            return 10;
        } else {
            return Integer.parseInt(denomination);
        }
    }

    /**
     * 원래 해당 행동(숫자를 보고 끗수를 결정)은 CardDeck에서 처리를 했다.
     * 하지만 CardDeck은 단지 서로 다른 52장의 카드를 생성하는 책임만 갖고 있다.
     *
     * "정보 전문가 패턴"을 고려해서, Card의 끗수를 결정하는데 그 정보를 가장 잘 알고 있는 Card에게 그 책임을 부여하자.
     */
    private String numberToDenomination (int number) {
        if (number == 1) {
            return "A";
        } else if (number == 11) {
            return "J";
        } else if (number == 12) {
            return "Q";
        } else if (number == 13) {
            return "K";
        } else {
            return String.valueOf(number);
        }
    }

}
