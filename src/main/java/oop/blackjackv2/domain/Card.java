package oop.blackjackv2.domain;

import java.util.LinkedList;
import java.util.List;

public class Card {

    private Pattern pattern;
    private Denomination denomination;

    private final int PATTERNS_SIZE = Pattern.values().length;
    private final int DENOMINATIONS_SIZE = Denomination.values().length;

    public Card(Pattern pattern, Denomination denomination){
        this.pattern = pattern;
        this.denomination = denomination;
    }

    public String getPatternValue() {
        return pattern.getValue();
    }

    public String getDenominationMark() {
        return denomination.getMark();
    }

    public Card(){}

    public List<Card> getCards() {
        List<Card> cards = new LinkedList<>();
        addCards(cards);
        return cards;
    }

    private void addCards(List<Card> cards) {
        for (int i = 0; i < PATTERNS_SIZE * DENOMINATIONS_SIZE ; i++) {
            cards.add(create(i));
        }
    }

    private Card create(int idx) {
        return new Card(choosePattern(idx), chooseDenomination(idx));
    }

    private Denomination chooseDenomination(int idx) {
        return Denomination.values()[idx % DENOMINATIONS_SIZE];
    }

    private Pattern choosePattern(int idx) {
        return Pattern.values()[idx % PATTERNS_SIZE];
    }


    private enum Pattern {
        SPADE("spade"),
        HEART("heart"),
        DIAMOND("diamond"),
        CLUB("club");

        private String value;

        Pattern(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    private enum Denomination {
        A("A", 1),
        TWO("2", 2),
        THREE("3", 3),
        FOUR("4", 4),
        FIVE("5", 5),
        SIX("6", 6),
        SEVEN("7", 7),
        EIGHT("8", 8),
        NINE("9", 9),
        TEN("10", 10),
        JACK("J", 10),
        QUEEN("Q", 10),
        KING("K", 10);

        private String mark;
        private int point;

        Denomination(String mark, int point) {
            this.mark = mark;
            this.point = point;
        }

        public String getMark() {
            return mark;
        }
    }

}
