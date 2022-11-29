package oop.blackjackv2.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CardDeckTest {

     static CardDeck cardDeck;
     static Card card;

    @BeforeAll
    static void setUp() {
        card = new Card();
        cardDeck = new CardDeck(card);
    }


    @Test
    void 카드덱_카드_생성확인() {
        // when
        cardDeck.createCards();

        // then
        assertThat(cardDeck.createCards()).isNotNull();
        assertThat(cardDeck.createCards().size()).isEqualTo(52);

        assertThat(cardDeck.createCards().get(0).getPatternValue()).isEqualTo("spade");
        assertThat(cardDeck.createCards().get(0).getDenominationMark()).isEqualTo("A");

        assertThat(cardDeck.createCards().get(51).getPatternValue()).isEqualTo("club");
        assertThat(cardDeck.createCards().get(51).getDenominationMark()).isEqualTo("K");
    }


}