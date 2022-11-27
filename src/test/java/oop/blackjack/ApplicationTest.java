package oop.blackjack;



import static org.assertj.core.api.Assertions.*;

import java.util.List;
import oop.blackjack.domain.Card;
import oop.blackjack.domain.CardDeck;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


class ApplicationTest {

    private static CardDeck cardDeck;
    private static List<Card> cards;

    @BeforeAll
    public static void setUp() {
        cardDeck = new CardDeck();
        cards = cardDeck.getCards();
    }

    @Test
    public void test_List를Stack으로변환() {
        assertThat(cardDeck.getCards().size()).isEqualTo(52);
        cardDeck.draw();
        assertThat(cardDeck.getCards().size()).isEqualTo(51);
        cardDeck.draw();
        assertThat(cardDeck.getCards().size()).isEqualTo(50);
    }
}