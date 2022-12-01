package oop.blackjackv2.domain;

import java.util.List;

public class Rule {

    private final List<Person> persons;
    private final int initCardsCount = 2;

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

}
