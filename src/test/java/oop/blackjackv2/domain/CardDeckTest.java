package oop.blackjackv2.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Stack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CardDeckTest {

     CardDeck cardDeck;
     Card card;

    @BeforeEach
    void setUp() {
        card = new Card();
        cardDeck = new CardDeck(card);
    }


    @Test
    void 카드덱_카드_생성확인() {
        // when
        cardDeck.createCards();
        Stack<Card> cards = cardDeck.getCards();

        // then
        assertThat(cards).isNotNull();
        assertThat(cards).hasSize(52);
    }


}