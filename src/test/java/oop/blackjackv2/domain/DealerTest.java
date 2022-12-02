package oop.blackjackv2.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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


    @Test
    @DisplayName("딜러가 16점 이상이면 카드를 더 뽑지 않는다.")
    void dealerCanDraw(){
        //given
        cardDeck.createCards();

        //when
        dealer.initDraw();
        dealer.initDraw();
        dealer.draw();
        dealer.draw();
        dealer.draw();
        dealer.draw();

        //then
        assertThat(dealer.getMyCards().stream().mapToInt(Card::getPoint).sum()).isGreaterThan(16);
    }


}