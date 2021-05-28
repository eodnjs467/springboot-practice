# springboot-practice
Project   &emsp; &emsp;  Explain
<br>
ex1 -> Spring boot test(Hello Spring!)
<br>
ex2 -> Entity Class, JpaRepository, 페이징/정렬 처리, 쿼리 메서드 기능과 @Query
<br>
ex3 -> Spring MVC, Thymeleaf, JPA, Layout, LocalDateTime
<br>
guestbook -> Thymeleaf와 SpringData JPA를 Spring MVC와 함께 결합한 미니 프로젝트(Querydsl을 이용해 검색환경 구성)
<br>
board -> N:1(다대일 관계) 처리, @RestController와 JSON 처리
<br>
club -> Boot 와 Spring Security 연동, 소셜 로그인 처리
<br>
AcademyKorea -> 전국의 학원 정보를 얻을 수 있을 수 있음. 개강날짜와 개강과목을 확인 후 신청 가능
<br>
jpashop -> 회원, 상품, 주문 // 회원이 상품을 주문or취소, 배송정보 조회
<br>
[20210505] -> UI설계, Spring Security 적용  <br>
[20210506] -> UI구현(1) - index, event ,default_Page(header, nav, footer) <br>
[20210507] -> 게시판, 댓글 설계 

---
# jpashop project
  
### 	목차
* 프로젝트 환경설정
	* 프로젝트 생성
	* View 환경 설정
	* H2데이터베이스 설치
	* JPA 와 DB 설정, 동작확인
* 	도메인 분석 설계
	* 요구사항 분석
	* 도메인 모델과 테이블 설계
	* 엔티티 클래스 개발
* 애플리케이션 구현준비
	* 구현 요구사항
	* 애플리케이션 아키텍쳐
* 회원 도메인 개발
	* 회원 리포지토리 개발
	* 회원 서비스 개발
	* 회원 기능 테스트

### 	프로젝트 생성
* 스프링 부트 스타터(https://start.spring.io/)
* 사용기능 : web, thymeleaf, jpa, h2, lombok
	* group : jpabook
	* artifactId : jpashop

### 	View 환경 설정
**thymeleaf 템플릿 엔진**
* thymeleaf 공식 사이트 : https://www.thtymeleaf.org/
* 스프링 공식 튜토리얼 : https://spring.io/guides/gs/serving-web-content/
* 스프링부트 매뉴얼 : [Spring Boot Features](https://docs.spring.io/spring-boot/docs/current/reference/html/features.html#features)

### 	H2 데이터베이스 설치

Ver. 1.4.200 다운로드

### 	JPA와 DB 설정, 동작확인
JPA설정, H2 DB 설정 후 동작 확인

## 도메인 분석 설계

### 	요구사항 분석
**기능 목록**
* 회원 기능
	* 회원 등록
	* 회원 조회
* 상품 기능
	* 상품 등록
	* 상품 수정
	* 상품 조회
* 주문 기능
	* 상품 주문
	* 주문 내역 조회
	* 주문 취소
* 기타 요구사항
	* 상품은 재고 관리가 필요하다.
	* 상품의 종류는 도서, 음반, 영화가 있다.
	* 상품을 카테고리로 구분할 수 있다.
	* 상품 주문시 배송 정보를 입력할 수 있다.

### 	도메인 모델과 테이블 설계
![image](https://user-images.githubusercontent.com/24502604/119982254-91deed00-bff9-11eb-9d1a-6f6217aca049.png)


회원, 주문, 상품의 관계 : 회원은 여러 상품을 주문할 수 있다. 한 번 주문할 때 여러 상품을 선택할 수 있으므로 주문과 상품은 다대다 관계이다.
관계형 데이터 베이스는 물론 엔티티에서 거의 사용하지 않아 주문 상품 이라는 엔티티를 추가해 다대다 관계를 일대다, 다대일 관계로 풀어냈다.

**상품 분류 :** 상품은 도서, 음반, 영화로 구분되는데 상품이라는 공통 속성을 사용하므로 상속 구조로 표현했다.

### 회원 엔티티 분석
![image](https://user-images.githubusercontent.com/24502604/119982451-d10d3e00-bff9-11eb-9fc4-54cd70aa1212.png)

**회원(Member) :** 이름과 임베디드 타입인 주소( `Address` ), 그리고 주문( `orders` ) 리스트를 가진다.  
**주문(Order) :** 한 번 주문시 여러 상품을 주문할 수 있으므로 주문과 주문상품( `OrderItem` )은 일대다 관계다. 주문은 상품을 주문한 회원과 배송 정보, 주문 날짜, 주문 상태( `status` )를 가지고 있다.
주문 상태는 열 거형을 사용했는데 주문( `ORDER` ), 취소( `CANCEL` )을 표현할 수 있다.  
**주문상품(OrderItem) :** 주문한 상품 정보와 주문 금액( `orderPrice` ), 주문 수량( `count` ) 정보를 가지고 있다. (보통 `OrderLine` , `LineItem` 으로 많이 표현한다.)  
**상품(Item) :** 이름, 가격, 재고수량( `stockQuantity` )을 가지고 있다. 상품을 주문하면 재고수량이 줄어든 다. 상품의 종류로는 도서, 음반, 영화가 있는데 각각은 사용하는 속성이 조금씩 다르다.  
**배송(Delivery) :** 주문시 하나의 배송 정보를 생성한다. 주문과 배송은 일대일 관계다.  
**카테고리(Category) :** 상품과 다대다 관계를 맺는다. `parent` , `child` 로 부모, 자식 카테고리를 연결한다.  
**주소(Address) :** 값 타입(임베디드 타입)이다. 회원과 배송(`Delivery`)에서 사용한다.  

> 참고 : 회원이 주문을 참조하지 않고, 주문이 회원을 참조하는 것으로 충분하지만 일대다, 다대일의 양방향 연관관계를 알아보기 위해 추가했다. 

### 회원 테이블 분석
![image](https://user-images.githubusercontent.com/24502604/119982522-e8e4c200-bff9-11eb-90c0-69f4f7f34ced.png)


**MEMBER :** * 회원 엔티티의 `Address` 임베디드 타입 정보가 회원 테이블에 그대로 들어갔다. 이것은 `DELIVERY` 테이블도 마찬가지다.  
**ITEM :** * 앨범, 도서, 영화 타입을 통합해서 하나의 테이블로 만들었다. `DTYPE`컬럼으로 타입을 구분한다. 

> 참고: 테이블명이 ORDER 가 아니라 ORDERS 인 것은 데이터베이스가 order by 때문에 예약어로 잡고 있는 경우가 많아 ORDERS 를 사용한다.  

### 엔티티 설계
[springboot-practice/jpashop/src/main/java/jpabook/jpashop/domain at main · eodnjs467/springboot-practice · GitHub](https://github.com/eodnjs467/springboot-practice/tree/main/jpashop/src/main/java/jpabook/jpashop/domain)

## 애플리케이션 구현 준비
### 	구현 요구사항
![image](https://user-images.githubusercontent.com/24502604/119982568-f8640b00-bff9-11eb-81b7-a492cd09ed3d.png)


* 회원 기능
	* 회원 등록
	* 회원 조회
* 상품 기능
	* 상품 등록
	* 상품 수정
	* 상품 조회
* 주문 기능
	* 상품 주문
	* 주문 내역 조회
	* 주문 취소

### 어플리케이션 아키텍처
![image](https://user-images.githubusercontent.com/24502604/119982594-ff8b1900-bff9-11eb-8724-c42073f2d195.png)


**계층형 구조 사용**
* controller, web : 웹 계층
* service : 비즈니스 로직, 트랜잭션 처리
* repository : JPA를 직접 사용하는 계층, 엔티티 매니저 사용
* domain : 엔티티가 모여있는 계층, 모든 계층에서 사용

**패키지 구조**
* jpabook.jpashop
	* domain
	* exception
	* repository
	* service
	* web


**개발 순서 : 서비스 -> 리포지토리 -> 테스트케이스 -> 웹 계층 적용**

## 회원 도메인 개발
**구현 기능**
* 회원 등록
* 회원 목록 조회

**순서**
* 회원 리포지토리 개발
* 회원 서비스 개발
* 회원 기능 테스트
