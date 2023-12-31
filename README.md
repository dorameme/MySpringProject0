#  스프링 프로젝트 배포하면서 배운것들 정리
<출처 : 인프런 강의 -자바와 스프링 부트로 생애 최초 서버 만들기, 누구나 쉽게 개발부터 배포까지! [서버 개발 올인원 패키지]>

공부일지

| 날짜   | 정리 | 커리큘럼 내용                                         |
|------|----|-------------------------------------------------|
| 9/10 | ☑️ | [자바의 기초](#자바와-기초)                               |
| 9/11 | ☑️️ | [API 만들기](#API-만들기)                             |
| 9/12 | ☑️️ | [JPA 이해](#JPA-이해)                               |
| 9/14 | ☑️️ | [트랜잭션과 JPA](#트랜젝션과-JPA)                         |
| 9/16 | ☑️| [조금 더 객체지향적으로 개발할 수 없을까?](#JPA-연관관계)            |
|      |    | [스프링과 문제 해결 - 예외 처리, 반복](#스프링과-문제-해결--예외 처리,반복) |


# 자바의 기초
> ### 자바와 기초

프로젝트 만들기 시작 준비물      
1. 자바 -프로그래밍 언어
2. 인텔리제이-통합개발도구
3. 포스트맨-API 테스트 도구
4. 데이터베이스-mysql    
5. git-버전관리 프로그램    


>### 중앙집중형 시스템  vs 분산형 시스템
 중앙집중형
- 저장소가 서버에 있음
- 서버와 연결이 끊어지면 기존 받아둔 소스 수정 이외의 일을 못함
- Ex : Subversion(SVN)

분산형
- 모든 개발자가 각자의 저장소를 가지고, 각자 저장소에서 여러가지 작업을 한 뒤 한번에 서버에 반영 가능.
- 오프라인 환경에서도 로컬저장소에 commit하면 되고 로그를 볼 수 있음.
- Ex : Git, Mercurial

> ### 자바에 대한 기초 상식 !

코드-(컴파일러)컴파일 -바이트코드(바이너리 코드)

c언어는 운영체제마다 컴파일러가 다르다.   
따라서 c언어의 경우 윈도우용 리눅스용 c언어 컴파일러 필요

Java 같은경우는 jvm을 거쳐가기 떄문에 자바컴파일러가 운영체제에 종속적이지 않고 하나인게 가능!             
jvm이 컴파일러와 운영체제 사이에 있음.

다시말해 **jvm**은 똑같은 java 바이트코드를 os 마다 다르게 해석해주는 친구

> ### JKD JRE JVM ?

### JVM 자바 가상 머신
Java Virtual Machine    
java의 바이트코드를 os 마다 다르게 해석해주는 역할(운영체제 별로 존재)
### JRE 자바 실행 환경
Java Runtime Environment         
JRE=JVM+자바 프로그램에 필요한 라이브러리등을 포함        
컴파일러, 디버그 도구등이 포함됨      
### JDK 자바 개발 키트
Java Development Kit     
JDK=JRE+개발을 위한 도구 (컴파일러 디버그 도구등이 포함)
   
> ### JDK는 버전과 종류가 있다.

### JDK 버전    
LTS(long time support)버전은 오래사용할 수 있는 버전    
(jdk11을 선택하는 일반적인 이유도  LTS라 오래사용할 수 있기때문)     

### JDK 종류      
oracle jdk(오라클에서 만듬)-> 개인에겐 무료/기업은 유로     
openjdk ->오픈JDK는 오라클 JDK와 성능은 비슷한데 언제나무료(프로그램을 상업용으로 쓸땐 이걸 써야 무료다.)           

JDK는 기능이 추가되거나 기존 기능사라진 버전이 있고,     
JDK는 기능은 동일하지만 성능 비용이 다른 종류가 있다.


> ### 빌드와 실행

빌드와 실행은 다르다~!

### 빌드 -> 소스코드파일을 컴퓨터로 실행가능하도록 변환하는 과정

1. 소스코드 컴파일    
2. 테스트코드 컴파일  (테스트코드의 중요성!! 빌드에 포함 개발 속도및 안정성 품질을 지켜줌)  
3. 테스트코드 실행   
4. 테스트코드 리포트 작성
5. 기타 추가 설정한 작업 진행
6. 패키징 수행 (내가만든 코드와 사용한 오픈소스들 (예를들어 스프링 부트?) 묶는다)
최종 sw 결과물을 만든다.


### 실행-> 내가 작성한 코드를 컴파일 거쳐 작동!시켜보는것

(빌드 될수도있고 아닐 수 도 있다.)
주의 인터프리터는 컴파일이 필요없다.

> ### 자바의 빌드툴

소스코드의 빌드과정을 자동으로 처리해주는 프로그램
외부 소스코드 자동 추가 관리를 해준다.

빌드툴 종류 = 엔트 메이븐 그래들

1. (오래됨)엔트    
설정을 위해 xml을 이용한다.   
간단하고 사용하기쉽단다.    
근데 복잡한 처리가 어렵고 외부 라이브러리를 관리하는 구조가 없다.


2. 메이븐 
xml로 설정관리특정경우 xml이 복잡해진다 외부라이브러리 관리 해줌    
Ant 단점을 대부분 극복했지만 신규project 에선 지양하는 편      


3. (가장 최신에 나옴)그래들
groovy를 설정하는데 사용한다.
외부라이버리를 관리가능
유연하게 빌드 스크립트 작성가능
성능이 뛰어남      
  특히 신규 project에 많이 사용된다.


### >스프링 프로젝트 시작
1. 이미만들어진 프로젝트 다운받기
2. Spring initializer 사용

스프링부트 알파벳 베타 -> 알파벳 없음-> 안정화된 버전    
버전x.x.x -> 잘안바뀜.몇달정도 거쳐서바뀜.잘바뀜        

1. Group 프로잭트 팀
2. Artifact 최종 결과물 이름
3. Name 프로젝트 이름 
4. Description 설명
5. Package Name 최상단 페키지 이름 


스프링 부트는 톰캣이 내장이 되어있어 jar을 선택하면 된다.      
자바의 버전은 java11 -> jva8 순으로 제일 많다.    
최신 버전을 바로쓰는건 안정적이지않아 안정적인(LST 이기함)11을 제일 많이 사용     
가장 초기버전은 오류가 많을 수 있기에.   

> ### 의존성-> 프로젝트에서 사용하는 라이브러리나 프레임워크를 말함

1. 라이브러리-> 미리만들어 져있는 기능을 사용하는경우 사용
김치찌개 -> 김치를 마트에서 사용

2. 프레임워크-> 미리 만들어져있는 구조에 코드를 끼워넣는것
원데이 클라스 모든게 준비되어있고 상황에 맞는 코드를 끼워넣음 

> ### 어노테이션-> 기본적으로 설정을 자동으로 해준다.


> ### 네트워크란 무엇인가?
고유한ip가 각각있다.      
인터넷을 통해 컴퓨터가데이터를 주고받울 수 있다.        
ip (어떤 컴퓨터인지),port(특정 컴퓨터 내 프로그램중 어떤건지)      

> ### Domain name 
아이피 대신 외우기 쉬운 이름을 넣는것.       
이러한 시스템을 domain name system dns 라고 부른다

> ### http와 api 무엇인가?!

데이터를 주고 받는 표준이 있다 이것이 바로 http = Hyper text transfer protocol

Get
http method(GET) and path(naver)  원하는 조건들은(name=dora) 쿼리라고 한다.

Post     
바디 -> 데이터를  담아둔다.

데이터를 보내는 두가지 방법   
쿼리(데이터를 달라) 바디(데이터 넣어보냄 (저장또는 수정!))     

> ### API application programming interface
정해진 약속을 하여 특정 기능을 수행하는 것을 api라고 한다.


### -http 요청-

http 요청 문법
Get메소드/패스/쿼리(한줄)
헤더(여러줄가능)

post메소드/패스 ￼
헤더 (여러줄가능)
한줄 띄고
바디(저장 수정하고 싶은 데이터 저장 (여러줄 가능))

http://spring.com:3000/portion?color=red&count=2       
Http프로토콜://도메인이름(ip로 써도 됨),자원,경로/쿼리

### -http 응답-

상태코드          
1XX: Informational(정보 제공)        
임시 응답으로 현재 클라이언트의 요청까지는 처리되었으니 계속 진행하라는 의미입니다. HTTP 1.1 버전부터 추가됨.


2XX: Success(성공)        
클라이언트의 요청이 서버에서 성공적으로 처리되었다는 의미.


3XX: Redirection(리다이렉션)       
완전한 처리를 위해서 추가 동작이 필요한 경우입니다. 주로 서버의 주소 또는 요청한 URI의 웹 문서가 이동되었으니 그 주소로 다시 시도하라는 의미.


4XX: Client Error(클라이언트 에러)      
없는 페이지를 요청하는 등 클라이언트의 요청 메시지 내용이 잘못된 경우.     


5XX: Server Error(서버 에러)          
서버 사정으로 메시지 처리에 문제가 발생한 경우입니다. 서버의 부하, DB 처리 과정 오류, 서버에서 익셉션이 발생하는 경우.   

응답에는 추가정보를 바디에 담을 수 있다.     

첫쨰줄 상태코드          
여러줄- 헤더     
한줄 띄고     
여러줄- 바디     

# API 만들기

> ### API 명세서(스펙)

1. http method  ex)GET
2. http path   ex)/add
3. http body 쿼리 (key/value)  ex) int number1 /int number2
4. API 반환결과  ex) 두숫자의 덧셈결과

> ### @RequestBody and @ResponseBody
> 클라이언트 -> 서버 요청 : @RequestBody    
서버 -> 클라이언트 응답 : @ResponseBody

클라이언트에서 서버로  요청 본문(request body)에 담긴 값을 자바객체로 변환.    
서버에서 클라이언트로 응답 데이터를 전송하기 위해 @ResponseBody 어노테이션을 사용하여 자바 객체를 HTTP 응답 본문의 객체로 변환하여 클라이언트로 전송한다. 

> ### GET vs POST
final키워드를 필드에 붙이면 변경이 불가능하고, DTO는 최초 객체 생성 이후 필드가 변경될 일이 없기 때문에 붙여주는게 좋다!
 

GET API - 생성자를 없애면 값이 0으로 바인딩되던데 왜그럴까?
* GET API에서 사용되는 DTO의 경우, POST와 달리 생성자를 통해 값이 바인딩 되기 때문!
* 만약 생성자가 없다면, API를 통해 들어온 쿼리가 바인딩 되는게 아니라, 기본값인 0이 필드에 들어가게 됨.
  public class Data {
  private int number;
  }    

POST API - CalculatorMultiplyRequest에서는 왜 생성자가 없어도 값이 바인딩되는 걸까? @RequestBody 어노테이션 때문?
* YES!! POST API에서 사용되는 DTO의 경우 getter를 통해 값이 바인딩 되기 때문에 
* 생성자가 없더라도 필드에 값을 넣을 수 있는데
* @RequestBody를 붙이게 되면 바인딩 되는 로직이 달라지기 때문!

# JPA 이해

> ### 컴퓨터 핵심부품 세개
Cpu연산 RAM임시기억장치 disk장기기억장치


>### DLL(데이터 테이블 제작)

>### DML(데이터 조작)
1. Create 생성 insert into (,,,) values (,,)  -update() 메서드
2. Read 읽기,조회   -query() 메서드
3. Update 업데이트 -update() 메서드
4. Delete 제거 -update() 메서드

>### Controller / Service / Repository 
> 3가지 역할로 구분된 구조를 Layered Architecture라고 한다.
Repository 쿼리날림 /service 서비스작업 /controller 맵핑

>### 스프링 컨테이너를 사용하면 
컨테이너가 선택해 의존성 주입(DI - dependency injection)을 해준다.
이런방식을 제어의 역전(IoC inversion of control)이라고함

>### 의존성 주입방식 
> 1. 생성자주입 (제일권장)  
> -순환참조가 방지됨        
> -final선언이 가능함         
> -테스트코드 작성이 용이함
> 2. 필드주입
> 3. 수정자주입

>### 생성자 주입 vs 필드주입 & 수정자주입        
### 생성자주입          
1. 빈을 만들기전에 주입하려는 빈을 먼저 찾음       
 따라서 찾는 과정에서 서로 객체가 생성되기전에 순환참조가 발생하면서 컴파일 오류를 냄.       

2. final선언으로 객체 불변성을 보장.

### 필드주입         

1. 수정자주입은 빈을 먼저 만들고 주입하려는 빈을 찾음        
 메소드 실행시점(런타임)까지 순환참조가 있더라도 알 수 없다. 
2. 필드를 final로 둘 수 없다. 나중에 변경될 수 있음.
3. mockito 를 이용해 테스트 해야함.
>### 스프링빈
 스프링 빈은       
서버가 시작하면 스프링 컨테이너가 생기고 컨테이너 안에 클래스가 들어간다.       
 들어간 클래스를 스프링 빈이라고 한다.            
jdbctemplete도 스프링빈으로 미리들어가있어서 우리가 따로 인스턴스화 할 필요없다.      
우리가 가져온 gradle 의존성에( dependency )의해 들어있다.            


>### 스프링빈을 등록하는 방법
>가장 권장되는 방식은 생성자를 이용해 주입받는 방식
>스프링 시작 -> 컨테이너 만듬 -> 스프링빈자동등록        
1. 외부 라이브러리, 프레임워크에서 만든 클래스를 등록할때 사용       
1) @Configuration      
클래스에 붙이는 어노테이션    
2) @Bean       
메소드에 붙이는 어노테이션      
메소드에서 반환된 객체를 스프링빈에 등록

2. 개발자가 직접만든 클래스를 스프링빈으로 등록할떄
1) @Service(@Component 자동감지)
2) @Repository(@Component 자동감지)
3) @Controller(@Component 자동감지)

4. 컨트롤러도 서비스도 리포지토리도 아니고 , 개발자가 직접작성한 클래스       
1)@Comtonent 사용

>### @Primary < @Qualifier 

>### jpa 등장배경 
> 1. sql 직접작성하면 실수, 인지느리고 ..
> 2. 컴파일시점이아닌 런타임시점에 발견됨
> 3. 특정 데이터베이스에 종속적을 됨
> 4. 반복작업이 많다.
> 5. 객체와 테이블은 패러다임이 다르다.
   ex)상속… 표현하기 애매하다

Jpa (java persistence(영속성-영구적 저장) api(정해진규칙)) = 자바 진영의 orm     
객체와 관계형db를 짝지어 줌  =orm      
Hibernate가 JPA(interface)를 구현한 클래스고 jdbc를 사용함       

정확히는 우리는 JPA를 쉽게
쓸 수 있게 도와주는 Spring Date JPA라는 라이브러리를 사용한다.
( jpa를 사용하려면 기본생성자가 꼭 필요하다. )


# 트랜젝션과 JPA


>### 쪼갤수없는 업무의 단위! 트랜잭션 특징

영속성컨택스트는 트랜잭션이 시작할때 시작하고 종료될때 함께 종료됨
영속성 컨텍스트 특징

1. 변경감지 (dirty check)
   변경이 일어나면  명시적으로 Save 하지않더라도 자동저장

2. 쓰기지연
   Insert /update /delete sql을 마지막에 한번에 날린다.
   스프링과 db사이 통신이 한번만 일어나게함!-> 통신횟수가 줄어드므로 시간이 오래걸리지않도록함.

3.  1차캐싱
    아이디를 기준으로 객체(하나의 같은 객체)를  캐시에 저장하도록함 그래서 다시 조회하거나하면 캐시에 있는거 알려줌.(최초한번만 조회)


JPA에서 엔티티 클래스와 데이터베이스 테이블 간의 기본 규칙은 다음과 같습니다:
* 		엔티티 클래스 이름과 데이터베이스 테이블 이름은 일치해야 합니다.
*       대소문자는 무시됩니다. 즉, 엔티티 클래스 이름이 "Book"이고 데이터베이스 테이블 이름이 "book"이라면 자동으로 매핑됩니다.


# JPA 연관관계

1:1 관계 (@OneToOne)
1. 주도권을 가지고있는 쪽=주인=상대테이블을 참조하는 쪽
2. 주인이 아닌쪽에 (mappedBy=“~”)를 써준다.
3. 연관관계 주인효과 -> 연결되는 기준이 된다.
4. 주인쪽에서 연결이 되어야 DB에서 연결이 된채 발견된다.
   연관관계의 주인의 setter가 사용되어야 db연결
5. Setter는 한번에 두쪽 다 설정되도록 하기!
   ￼

1:N관계
1. @ManyToOne->N 쪽이 무조건 주인 @OneToMany
2. @OneToMany(mappedBy=“~”)는 생략가능하다.
3. @JoinColumn 연관관계 주인이 활용가능한 어노테이션 필드의 이름 null여부 , 유일성 여부, 업데이트 여부지정

N:M관계 -> 1:N관계로 풀어내기      
Ex) 학생 대 동아리      
구조가복잡하고테이블이직관적으로 매핑되지 않음.     

cascade옵션      
-> 한 객체가 저장 삭제될때 연결객체도 함께 영향을 받으록 함

orphanRemoval옵션       
-> 객체간 연결이 끊어진것 만으로도 db에서 사라지도록 하는 옵션

영속성컨텍스트의 특징->  지연로딩  (OneToMany 의 fetch옵션-> 원하는 정보를 꼭 필요한 순간에 가져옴)
