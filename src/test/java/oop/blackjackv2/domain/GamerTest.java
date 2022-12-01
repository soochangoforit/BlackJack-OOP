package oop.blackjackv2.domain;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GamerTest {

    CardDeck cardDeck;
    Gamer gamer;


    @BeforeEach
    void setUp() {
        cardDeck = new CardDeck(new Card());
        gamer = new Gamer(cardDeck);
    }

    @Test
    void 게이머_초기_카드_뽑았는지_확인() {
        //given
        cardDeck.createCards();

        // when
        gamer.initDraw();

        // then
        assertThat(gamer.getMyCards()).hasSize(1);
    }

}