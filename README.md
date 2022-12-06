# BLACK JACK - OOP


### 목차

- 프로젝트를 시작하게 된 계기
- 블랙잭 규칙
- 코드 작성 규c칙
- 설계 원칙
- 주요 객체
- 도메인 모델
- 진행 하면서 느꼈던 점 (회고)
 
 
<br>
 
<aside>
✅ 반드시 기억하자

---

1. 협력을 기점으로, 메시지를 생각하고, 메시지를 수행할 객체를 선택하라.
2. 객체가 가지는 책임을 수행하는데 있어서 행위를 먼저 생각하라.
3. 행위를 수행함에 따라 필요한 상태를 결정하라.
</aside>
 
<br>
 
 
<br>
 

### 프로젝트를 시작하게 된 계기

---

[기존의 문제점]

- 프로젝트 개발시, Java 언어와 Spring 프레임 워크 숙달을 위한 **“구현”**에 **초점**이 맞춰진 개발
- 다른 지인과 나의 코드를 비교하는 과정에서 너무 **“절차지형적”으로 개발**했던 문제점
- 절차 지향적인 개발로 인해서 프로젝트 **확장성과 유연성이 떨어지는 문제점**
 
<br>
 

**[성장 하고자 하는 방향]**

- **객체 간의 의사소통**을 중점으로 생각하는 사고력.
- 수 없이 변하는 요구사항에 대처하기 위한 **유연성**과 **확장성**까지 고려 해보는 경험.
- 구현에만 집중하지 않고, 설계에 많은 시간을 투자함으로써 **“객체 지향 사고 방식”**.
- 책을 읽으면서 느겼던 부분들을 실제 코드로 작성해보고 느껴보는 **경험**
    - 함께 읽었던 책
    - 조용호 님의 “객체지향의 사실과 오해”
    - 조용호 님의 “Object(오브젝트)”
 
<br>
 

### 블랙잭 규칙

---

- 딜러와 게이머 단 2명만 존재한다.
- 카드는 조커를 제외한 52장이다. (즉, 카드는 다이아몬드,하트,스페이드,클럽 무늬를 가진 A,2~10,K,Q,J 으로 이루어져있다.)
- 2~10은 숫자 그대로 점수를, K/Q/J는 10점으로, **A는 1로** 계산한다. (기존 규칙은 A는 1과 11 둘다 가능하지만 여기선 1만 허용하도록 스펙아웃)
- 딜러와 게이머는 처음에 순차적으로 카드를 하나씩 뽑아 각자 2개의 카드를 소지한다.
- 게이머는 얼마든지 카드를 추가로 뽑을 수 있다.
- 딜러는 2카드의 합계 점수가 16점 이하이면 반드시 1장을 추가로 뽑고, 17점 이상이면 추가할 수 없다.
- 양쪽다 추가 뽑기 없이, 카드를 오픈하면 딜러와 게이머 중 소유한 카드의 합이 21에 가장 가까운 쪽이 승리한다.
- 게이머는 점수 총합이  21을 초과하면 더 이상 뽑지 못한다.
 
<br>
 

### 코드 작성 규칙

---

- 함수(또는 메서드)의 길이가 15라인을 넘어가지 않도록 구현한다.
    - **함수(또는 메서드)가 한 가지 일만 잘 하도록 구현한다.**
    - 함수(또는 메서드)가 한 가지 일만 하도록 최대한 작게 만들어라.
- else 예약어를 쓰지 않는다.
    - **if 조건절에서 값을 return하는 방식으로 구현하면 else를 사용하지 않아도 된다.**
- **Java Enum이 필요한 경우, 적극 활용**
- 도메인 로직에 단위 테스트를 구현해야 한다. 단, UI(System.out, System.in, Scanner) 로직은 제외한다.
    - 핵심 로직을 구현하는 코드와 UI를 담당하는 **로직을 분리해 구현**한다.
- **indent(인덴트, 들여쓰기) depth를 3이 넘지 않도록 구현한다. 2까지만 허용한다.**
    - 예를 들어 while문 안에 if문이 있으면 들여쓰기는 2이다.
    - **indent(인덴트, 들여쓰기) depth를 줄이는 좋은 방법은 함수(또는 메서드)를 분리하면 된다.**
    - 연습이 이루어지면 depth를 2가 넘지 않도록 한다.
        - 최대한 1을 유지하기 위해 노력하고, 정말 힘든 경우 2까지 허용한다.
- 3항 연산자를 쓰지 않는다.
- JUnit 5와 AssertJ를 이용하여 본인이 정리한 기능 목록이 정상 동작함을 테스트 코드로 확인한다.
- 메서드의 파라미터 개수는 최대 3개까지만 허용한다.
 
<br>
 

### 설계 원칙

---

- 클래스 우선이 아닌, **객체의 속성**과 **행위**가 우선이다.
    - 클래스는 객체를 추상화하는 도구일 뿐이다.
- 데이터가 아닌 **메세지(행위)**를 중심으로 객체를 설계해라.
    - 객체는 혼자 있을 수 없다. 다른 객체와의 **협력** 안에서만 존재할 수 있다.
    - **메세지를 중심**으로, 해당 메세지가 어떤 객체를 필요로 하는지를 생각하자.
- 하나하나 지시하지 말고 **요청해라.**
    - 예를들어, 판사가 증인에게 1) 목격했던 장면을 떠올리고, 2) 떠오르는 시간을 순서대로 구성하고, 3) 말로 간결하게 표현해라 라고 요청하지 않는다. 그냥 "증언하라" 라고 요청한다.
    - 마찬가지로 객체의 설계단계에서도 책임이 있는 객체에 요청만 하도록 설계한다.
- **하나의 메소드는 하나의 일**만 해야한다.
- 처음부터 완벽한 설계는 없다.
    - 설계를 코드로 구현해가는 과정에서 수정이 필요하다면 **설계를 수정**한다.
 
<br>
 
 
<br>
 

### 주요 객체

---

- 카드덱 (카드 뭉치)
- 카드
- 규칙
- 딜러
- 게이머
- 게임
 
<br>
 
 
<br>
 

### 도메인 모델

---

![Untitled](https://user-images.githubusercontent.com/91618389/205496246-3e9e7c2d-a08c-4a1a-bd1f-7c7a793650e0.png)

 
<br>
 

**도메인 모델이란?**

- 기본적으로 도메인 모델은 특정 도메인을 개념적으로 표현한 것
    - 도메인 모델을 사용하면 여러 관계자들이 동일한 모습으로 도메인을 이해하고 도메인 지식을 공유하는데 도움이 된다.
- 도메인을 이해하기 위한 준비
    - 도메인이 제공하는 기능
    - 도메인의 주요 데이터 구성
- 기능과 데이터를 함께 보여주는 객체 모델은 도메인을 모델링하기 적합..
- 도메인 모델은 기본적으로 도메인 자체를 이해하기 위한 개념 모델이다.
    - 개념 모델로 바로 코드를 작성할 수 있는 것은 아니기에 구현 기술에 맞는 구현 모델이 따로 필요하다.
    - 객체 기반 모델을 이용해 도메인을 표현했다면 객체 지향 언어를 이용해 개념 모델에 가깝게 구현 모델을 구현할 수 있다.
 
<br>
 

### 진행 하면서 느꼈던 점 (회고)
[노션 링크](https://misty-birthday-b8e.notion.site/BLACK-JACK-OOP-a83472dd11da4e3da127a846c189237c#c712cda9a70542b89768e5a824d6f368)
