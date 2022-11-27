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
        List<Player> initAfterPlayers = initPhase(cardDeck, players);
        List<Player> playingAfterPlayers = playingPhase(sc, cardDeck, initAfterPlayers);


    }


    /**
     * 한 턴씩 진행함으로써, Dealer와 Gamer 모두 만족할때까지 카드를 뽑도록 하는 1개의 책임을 갖고 있다.
     * 하나의 메서드는 하나의 책임만 갖도록 한다.
     *
     * isAllPlayerTurnOff()는 Dealer와 Gamer가 모두 카드를 더 이상 받지 않아도 괜찮은지 확인하는 책임을
     * 메시지를 통해서 위임한다.
     */
    private List<Player> playingPhase(Scanner sc, CardDeck cardDeck, List<Player> players) {
        List<Player> cardReceivedPlayers;

        while(true) {
            cardReceivedPlayers = receiveCardAllPlayers(sc, cardDeck, players);

            if(isAllPlayerTurnOff(cardReceivedPlayers)) {
                break;
            }
        }

        return cardReceivedPlayers;
    }

    /**
     * 한번의 턴에서 Dealer와 Gamer 모두 카드를 뽑을 수 기회를 갖도록 하는 1개의 책임을 갖고 있다.
     * 하나의 메서드는 하나의 책임만 갖도록 한다.
     *
     * 파라미터로 들어온 players는 "Call By Reference"이기 때문에,
     * receiveCardAllPlayers() 메서드 내에서 players의 상태가 변경되면,
     * playingPhase() 메서드 내에서도 players의 상태가 변경된다.
     * 따라서 굳이, return 하지 않아도 괜찮다.
     *
     * 하지만 return 하는 이유는 그 목적을 명확히 하기 위해서이다.
     * "receiveCardAllPlayers는 CardDeck과 List를 인자로 받아 특별한 과정을 통해 변경된 List를 준다."
     * 이것이 receiveCardAllPlayers의 목적이다.
     * 만약 void로 할 경우 List가 변경은 될지언정, 최종적으로 무얼 위함인지 코드상에서 확인하기 어렵고 목적이 모호해지게 됩니다
     * 좋은 메소드란 결국 어떤 인자가 필요하고, 그 인자를 통해 어떤 결과를 뱉어내는지 명확한 것이라고 생각합니다
     */
    private List<Player> receiveCardAllPlayers(Scanner sc, CardDeck cardDeck, List<Player> players) {
        for(Player player : players) {
            if(stillReceiveCard(sc)){
                Card card = cardDeck.draw();
                player.receiveCard(card);
                player.turnOn();
            }else {
                player.turnOff();
            }
        }

        return players;
    }

    /**
     * 카드를 추가적으로 받을지 말지, 입력을 받는 책임을 담당한다.
     */
    private boolean stillReceiveCard(Scanner sc) {
        System.out.println("카드를 뽑으시겠습니까? 종료를 원하시면 0을 입력하세요");
        return !sc.nextLine().equals(STOP_RECEIVE_CARD);
    }

    /**
     * 모든 Player가 카드를 더 이상 받지 않아도 되는지 알려주는 책임을 담당한다.
     */
    private boolean isAllPlayerTurnOff(List<Player> players) {
        for(Player player : players) {
            if(player.isTurn()) {
                return false;
            }
        }
        return true;
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
    private List<Player> initPhase(CardDeck cardDeck, List<Player> players) {
        System.out.println("처음 2장의 카드를 각자 뽑겠습니다.");
        for(int i = 0; i < INITIAL_CARD_COUNT; i++) {
            players.forEach(player -> player.receiveCard(cardDeck.draw()));
        }

        return players;
    }
}
