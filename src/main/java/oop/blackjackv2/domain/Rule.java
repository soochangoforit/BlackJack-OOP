package oop.blackjackv2.domain;

import java.util.List;

public class Rule {

    private final List<Person> persons;
    private static final int initCardsCount = 2;

    private static final int BLACKJACK = 21;

    public Rule(Person ... persons) {
        this.persons = List.of(persons);
    }

    /**
     * Rule의 책임을 딜러와 게이머 각각의 객체에 카드를 뽑도록 지시하고,
     * 순차적으로 뽑기 위해 순서를 관리하는 책임을 갖는다.
     */
    public void initialDraw(){
        for (int i = 0; i < initCardsCount; i++) {
            persons.forEach(Person::initDraw);
        }
    }

    public Person findWinner() {
       return persons.stream().min((p1, p2) -> {
            int p1PointsGap = getGap(p1);
            int p2PointsGap = getGap(p2);

            return Integer.compare(p1PointsGap, p2PointsGap);
        }).orElseThrow(() -> new RuntimeException("승자가 없습니다."));
    }

    private static int getGap(Person p) {
        return Math.abs(BLACKJACK - p.getPoints());
    }

    public boolean isContinue(String inputYesOrNo) {
        return inputYesOrNo.equals("y");
    }
}
