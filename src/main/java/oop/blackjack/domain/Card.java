package oop.blackjack.domain;

/**
 * 카드
 */
public class Card {

    private String pattern; // 스페이드, 하트, 다이아몬드, 클로버
    private String denomination; // A, 2~10, K,Q,J


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

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

}
