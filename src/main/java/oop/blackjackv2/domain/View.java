package oop.blackjackv2.domain;

import java.util.List;
import java.util.Scanner;

public class View {

    private List<Person> persons;

    Scanner scanner = new Scanner(System.in);

    public View (Person ... persons) {
        this.persons = List.of(persons);
    }

    public void showInitialDrawInfo() {
        persons.forEach(person -> {
            System.out.println(person.getName() + "의 카드는 " + person.showCards() + "이며, 점수는 " + person.getPoints() + "입니다.\n");
        });
    }

    public String getInputYesOrNo(Person person) {
        System.out.println(person.getName() + " 님, 카드를 더 뽑으시겠습니까? (y/n)");
        return scanner.nextLine();
    }

    public void show(String message) {
        System.out.println(message);
    }

    public void showWinner(Person winner) {
        System.out.println(winner.getName() + " 님이 이겼습니다.");
    }
}
