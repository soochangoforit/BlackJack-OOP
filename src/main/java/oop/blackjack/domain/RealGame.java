package oop.blackjack.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * blackjack 게임을 실행시킬 객체
 */
public class RealGame {

    /**
     * 초기에 Dealer와 Gamer 모두 2장의 카드를 받는다.
     */
    private final int INITIAL_CARD_COUNT = 2;
    private static final String STOP_RECEIVE_CARD = "0";

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

        List<Player> players = Arrays.asList(gamer, dealer);

        this.initPhase(cardDeck, players);
        this.playingPhase(sc, cardDeck, players);


    }


    /**
     * Dealer와 Gamer 모두 자유롭게 카드를 받는다.
     * Dealer와 Gamer, 번갈아 가면서 카드를 받는다.
     * 2명이 모두 카드를 다 받았다고 판단되면 종료하는 책임을 담당한다.
     */
    private void playingPhase(Scanner sc, CardDeck cardDeck, List<Player> players) {
        while(true) {
            boolean isAllPlayerTurnOff = receiveCardAllPlayers(sc, cardDeck, players);

            if(isAllPlayerTurnOff) {
                break;
            }
        }
    }

    /**
     * 매 턴마다, Dealer와 Gamer 모두 카드를 받을지 말지를 알여주는 책임을 담당한다.
     *
     * 문제점은 receiveCardAllPlayers 메서드가 2가지 일을 하고 있다는 것이다.
     * 1. Dealer와 Gamer 모두 카드를 받도록 한다.
     * 2. Dealer와 Gamer 모두 카드를 받을지 말지를 결정한 신호를 외부로 보내는 것
     */
    private boolean receiveCardAllPlayers(Scanner sc, CardDeck cardDeck, List<Player> players) {
        boolean isAllPlayerTurnOff = true;

        for(Player player : players) {
            if(isReceiveCard(sc)) {
                Card card = cardDeck.draw();
                player.receiveCard(card);
                isAllPlayerTurnOff = false;
            } else {
                isAllPlayerTurnOff = true;
            }
        }

        return isAllPlayerTurnOff;
    }

    /**
     * 카드를 추가적으로 받을지 말지, 입력을 받는 책임을 담당한다.
     */
    private boolean isReceiveCard(Scanner sc) {
        System.out.println("카드를 뽑으시겠습니까? 종료를 원하시면 0을 입력하세요");
        return !sc.nextLine().equals(STOP_RECEIVE_CARD);
    }

    /**
     * 블랙잭 규칙에 따라 처음 시작시 Dealer와 Gamer가 2장씩 카드를 받는 역할을 담당할 예정이다.
     * Dealer 같은 경우는 추후에 추가할 예정.
     *
     * 여기서 눈여겨 보셔야할 것은 for문의 반복횟수인 2회를 static 상수로 선언한 것입니다.
     * i<2로 작성해도 똑같은 기능이 작동될 것입니다. 그럼에도 이렇게 상수로 선언한 이유는 매직넘버를 피하기 위함입니다.
     *
     * 매직넘버란, 코드를 작성할 때, 그 의미를 알 수 없는 숫자를 사용하는 것을 말합니다.
     * 여기서는 처음 시작시 카드를 받는 횟수인 2를 변수나 상수에 담지 않고, 코드에서 그래도 사용하게 되면 매직넘버가 된다.
     *
     * 매직 넘버를 피해야 하는 이유는
     * 1. 의미가 모호하다.
     *    - 단순히 2라는 숫자만 있으면 어떤 의미인지 알 수가 없다. 이로 인해 다른 개발자는 전체 맥락과 코드를
     *      읽어야 하는 상황이 발생한다.
     *    - 상수 혹은 변수명으로 의도를 명확히 하는 것이 좋다.
     * 2. 변경 범위를 확인하기 어렵다.
     *    - 똑같이 2를 사용하는 A라는 메서드가 하나 더 있다고 생각해보자
     *    - 초반 카드 뽑기 횟수가 2->3으로 늘어날 경우 A메소드의 2도 3으로 변경해야 할까? 변경하는 것은 확실한가?
     *    - 특히나, 0,1,10 등 빈번하게 사용되는 숫자를 전부 매직넘버로 처리할 경우 히스토리를 알지 못하면 변겨이 치명
     *      적인 버그를 발생시킬 수 있다.
     *
     * 수정) Player라는 추상화를 통해 같은 코드를 중복해서 작성하지 않고, Dealer와 Gamer를 모두 처리할 수 있도록
     *     리팩토링을 진행하였습니다.
     */
    private void initPhase(CardDeck cardDeck, List<Player> players) {
        System.out.println("처음 2장의 카드를 각자 뽑겠습니다.");
        for(int i = 0; i < INITIAL_CARD_COUNT; i++) {
            players.forEach(player -> player.receiveCard(cardDeck.draw()));
        }
    }
}
