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
            return dealer.canDraw(view);
        }
        return !dealer.canStop(view);
    }

    private boolean GamerTurn(Gamer gamer, Rule rule, View view) {
        if (rule.isContinue(view.getInputYesOrNo(gamer))){
            gamer.draw();
            return true;
        }
        return false;
    }


}
