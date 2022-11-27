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
    public void test_카드패턴비교() {
        assertThat(cards.get(0).getPattern()).isEqualTo(Card.Pattern.SPADE);
        assertThat(cards.get(13).getPattern()).isEqualTo(Card.Pattern.HEART);
    }

    @Test
    public void test_카드끗수비교() {
        assertThat(cards.get(0).getDenomination()).isEqualTo(Card.Denomination.ACE);
        assertThat(cards.get(12).getDenomination()).isEqualTo(Card.Denomination.KING);
    }
}