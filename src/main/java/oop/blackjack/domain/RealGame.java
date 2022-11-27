package oop.blackjack.domain;

import java.util.Scanner;

/**
 * blackjack 게임을 실행시킬 객체
 */
public class RealGame {

    /**
    * 블랙잭 게임을 시작한다.
    */
    public void play(){
        System.out.println("=====BlackJack Game Start=====");
        Scanner sc = new Scanner(System.in);

        // 딜러 생성
        Dealer dealer = new Dealer();

        // 게이머 생성
        Gamer gamer = new Gamer();

        // 카드 뭉치 생성
        CardDeck cardDeck = new CardDeck();

        // 규칙 생성
        Rule rule = new Rule();

        playingPhase(sc, cardDeck, gamer);


    }

    /**
     * 카드 뽑는 단계를 분리하였다.
     * - CardDeck을 통해 카드를 뽑고,
     * - Gamer가 그 카드를 받고,
     * - Gamer의 현재 카드를 확인
     *
     * 여기서 중요한 점은 Gamer는 CardDeck이 어떤 과정을 거쳐서 카드를 뽑아주는지 모른다는 것이다.
     * CardDeck 내부에서 (1) 남아 있는 카드 중 하나를 랜덤으로 뽑고, (2) 뽑은 카드는 목록에서 제거 라는 과정을
     * Gamer가 알 필요는 없다는 것이다.
     * Gamer는 단지 CardDeck에게 카드 하나를 뽑아 달라는 요청만 하면 되는 것이다.
     *
     * 객체는 다른 객체에게 요청을 할때, 이렇게 한뒤에 저렇게 하고 마지막으로 어떻게 해달라 라는 식으로 세세하게 요청해서는 안된다.
     * 객체는 본인의 역할에 충실하면 된다.
     * CardDeck은 카드를 뽑아 주는 것에, Gamer는 CardDeck에게 카드를 받는 것에 충실해야 한다.
     * 만일 각 객체의 책임이 모호하게 구현이 되어 있다면, 차후 변경이 있을 경우 어디까지 수정을 해야하는지 알 수 없는 상황이
     * 올 수도 있다. 그러므로 다른 객체에게 요청을 하는 일은 최대한 해당 객체를 믿고 맡기는 것이 좋다.
     */
    private void playingPhase(Scanner sc, CardDeck cardDeck, Gamer gamer) {
        String gamerInput;
        while(true) {
            System.out.println("카드를 뽑겠습니까?? 종료를 원하시면 0을 입력하세요");
            gamerInput = sc.nextLine();

            if(gamerInput.equals("0")) {
                break;
            }

            Card card = cardDeck.draw();
            gamer.receiveCard(card);
        }
    }
}
