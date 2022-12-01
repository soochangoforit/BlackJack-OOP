package oop.blackjackv2.domain;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RuleTest {

    Card card;
    CardDeck cardDeck;
    Dealer dealer;
    Gamer gamer;
    Rule rule;

    @BeforeEach
    void setUp() {
        card = new Card();
        cardDeck = new CardDeck(card);
        dealer = new Dealer(cardDeck);
        gamer = new Gamer(cardDeck);
        rule = new Rule(gamer, dealer);
    }

    @Test
    void 초기카드를_딜러와_게이머가_잘_받았는지_확인() {
        //given
        cardDeck.createCards();

        // when
        rule.initialDraw();

        // then
        assertThat(gamer.getMyCards()).hasSize(2);
        assertThat(dealer.getMyCards()).hasSize(2);
    }

}