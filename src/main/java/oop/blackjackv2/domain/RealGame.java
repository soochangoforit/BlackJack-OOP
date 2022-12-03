package oop.blackjackv2.domain;

public class RealGame {

    public void play(){
        Card card = new Card();
        CardDeck cardDeck = new CardDeck(card);
        cardDeck.createCards();

        Gamer gamer = new Gamer(cardDeck,"게이머1");
        Dealer dealer = new Dealer(cardDeck);

        Rule rule = new Rule(gamer, dealer);
        rule.initialDraw();

        View view = new View(gamer, dealer);
        view.showInitialDrawInfo();

        // 입력값에 따라 더 뽑을지 말지 결정
        boolean isGamerTurn = true;
        boolean isDealerTurn = true;

        while (isGamerTurn || isDealerTurn) {
            isGamerTurn = GamerTurn(gamer, rule, view);
            view.showInitialDrawInfo();
            
            isDealerTurn = DealerTurn(dealer, rule, view);
            view.showInitialDrawInfo();
        }

        Person winner = rule.findWinner();
        view.showWinner(winner);
        
    }

    private boolean DealerTurn(Dealer dealer, Rule rule, View view) {
        if (rule.isContinue(view.getInputYesOrNo(dealer))){
            return draw(dealer, view);
        }
        return !checkCanStop(dealer, view);
    }

    private boolean checkCanStop(Dealer dealer, View view) {
        if (isUnderMinLimit(dealer, view)) {
            dealer.draw();
            return false;
        }
        return true;
    }

    private boolean draw(Dealer dealer, View view) {
        if (isOverMaxLimit(dealer, view)) {
            return false;
        }
        dealer.draw();
        return true;
    }

    private boolean isUnderMinLimit(Dealer dealer, View view) {
        if (dealer.isUnderLimit()){
            view.show("딜러의 점수가 16점 이하이기 때문에, 카드를 한 장 더 뽑습니다.");
            return true;
        }
        return false;
    }

    private boolean isOverMaxLimit(Dealer dealer,View view) {
        if (dealer.isMaxLimit()) {
            view.show("딜러는 17점 이상이기 때문에, 더 이상 카드를 뽑을 수 없습니다.");
            return true;
        }
        return false;
    }

    private boolean GamerTurn(Gamer gamer, Rule rule, View view) {
        if (rule.isContinue(view.getInputYesOrNo(gamer))){
            gamer.draw();
            return true;
        }
        return false;
    }


}
