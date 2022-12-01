package oop.blackjackv2.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DealerTest {

    Dealer dealer;
    CardDeck cardDeck;

    @BeforeEach
    void setUp() {
        cardDeck = new CardDeck(new Card());
        dealer = new Dealer(cardDeck);
    }

    @Test
    void 딜러_초기_카드_뽑았는지_확인() {
        //given
        cardDeck.createCards();

        // when
        dealer.initDraw();

        // then
        assertThat(dealer.getMyCards()).hasSize(1);
    }


}