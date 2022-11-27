package oop.blackjack;



import static org.assertj.core.api.Assertions.*;

import java.util.List;
import oop.blackjack.domain.Card;
import oop.blackjack.domain.CardDeck;
import org.junit.jupiter.api.Test;


class ApplicationTest {

    @Test
    public void test_카드패턴비교() {
        CardDeck cardDeck = new CardDeck();
        List<Card> cards = cardDeck.getCards();

        assertThat(cards.get(0).getPattern()).isEqualTo(Card.Pattern.SPADE);
        assertThat(cards.get(13).getPattern()).isEqualTo(Card.Pattern.HEART);

    }

}