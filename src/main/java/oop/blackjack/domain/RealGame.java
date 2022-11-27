package oop.blackjack.domain;

import java.util.Scanner;

/**
 * blackjack 게임을 실행시킬 객체
 */
public class RealGame {

    /**
     * 초기에 Dealer와 Gamer 모두 2장의 카드를 받는다.
     */
    private final int INITIAL_CARD_COUNT = 2;

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
     */
    private void initPhase(CardDeck cardDeck, Gamer gamer) {
        System.out.println("처음 2장의 카드를 각자 뽑겠습니다.");
        for(int i = 0; i < INITIAL_CARD_COUNT; i++) {
            Card card = cardDeck.draw();
            gamer.receiveCard(card);
        }
    }
}
