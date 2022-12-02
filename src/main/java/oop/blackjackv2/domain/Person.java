package oop.blackjackv2.domain;

import java.util.List;

public interface Person {

    void initDraw();

    void draw();

    List<Card> openCards();
}
