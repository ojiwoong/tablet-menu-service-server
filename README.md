# 태블릿 메뉴 서비스

## ERD 설계

![https://tablet-bucket.s3.ap-northeast-2.amazonaws.com/readme_image/ERD+%EC%84%A4%EA%B3%84.PNG](https://tablet-bucket.s3.ap-northeast-2.amazonaws.com/readme_image/ERD+%EC%84%A4%EA%B3%84.PNG)

## DDL

```bash
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS order_menus;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS menus;
DROP TABLE IF EXISTS payments;
DROP TABLE IF EXISTS payment_method;

CREATE TABLE `users` (
   `id`   bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
   `login_id` varchar(20)    NOT NULL UNIQUE,
   `password` varchar(255)   NOT NULL,
   `name` varchar(255)   NOT NULL,
   `phone_number` varchar(11)    NOT NULL,
   `created_at`   VARCHAR(255)   NULL DEFAULT now(),
   `updated_at`   VARCHAR(255)   NULL DEFAULT now()
);

CREATE TABLE `orders` (
   `id`   bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
   `user_id`  VARCHAR(255)   NOT NULL,
   `total_amount` int    NOT NULL,
   `created_at`   VARCHAR(255)   NULL DEFAULT now(),
   `updated_at`   VARCHAR(255)   NULL DEFAULT now()
);

CREATE TABLE `menus` (
   `id`   bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
   `name` varchar(50)    NOT NULL,
   `price`    int    NOT NULL,
   `description`  varchar(255)   NULL,
   `image_url`    varchar(255)   NULL,
   `created_at`   datetime   NULL DEFAULT now(),
   `updated_at`   datetime   NULL DEFAULT now()
);

CREATE TABLE `order_menus` (
   `id`   bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
   `order_id` bigint NULL,
   `menu_id`  bigint NOT NULL,
   `quantity` int    NOT NULL,
   `amount`   int    NOT NULL,
   `created_at`   VARCHAR(255)   NULL DEFAULT now(),
   `updated_at`   VARCHAR(255)   NULL DEFAULT now(),
   CONSTRAINT FK_order_menus_orders FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE ON UPDATE CASCADE,
   CONSTRAINT FK_order_menus_menus FOREIGN KEY (menu_id) REFERENCES menus(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `payment_method` (
   `id`   int    NOT NULL PRIMARY KEY AUTO_INCREMENT,
   `name` varchar(100)   NOT NULL,
   `created_at`   VARCHAR(255)   NULL DEFAULT now(),
   `updated_at`   VARCHAR(255)   NULL DEFAULT now()
);

CREATE TABLE `payments` (
   `id`   bigint NOT NULL PRIMARY KEY AUTO_INCREMENT,
   `payment_method_id`    int    NOT NULL,
   `total_amount` int    NOT NULL,
   `created_at`   VARCHAR(255)   NULL DEFAULT now(),
   `updated_at`   VARCHAR(255)   NULL DEFAULT now(),
   CONSTRAINT FK_payment_method_payments FOREIGN KEY (payment_method_id) REFERENCES payment_method(id)
);
```

## REST API 명세서

- Users
  - `POST` /users : 사용자 등록
  - `POST` /login : 로그인
  - `GET` /users/{id} : 회원 id 검색
  - `GET` /users/search?userId=:userId : 회원 아이디 검색
  - `DELETE` /users : 사용자 삭제
  - `PUT` /users : 사용자 수정
- Menus
  - `GET` /menus : 전체 메뉴 조회
  - `GET` /menus/{id} : 메뉴 id 조회
- Orders

  - `POST` /orders/{userId} : 주문 등록
  - `GET` /orders?userId=:userId : 특정 사용자 주문 조회
  - `DELETE` /orders/{userId}: 특정 사용자 주문 삭제

- Payments
  - `POST` /payments/{paymentMethod} : 결제 등록
  - `GET` /payments : 전체 결제 조회

## 서비스 구성도

![https://tablet-bucket.s3.ap-northeast-2.amazonaws.com/readme_image/%EC%84%9C%EB%B9%84%EC%8A%A4+%EA%B5%AC%EC%84%B1%EB%8F%84.PNG](https://tablet-bucket.s3.ap-northeast-2.amazonaws.com/readme_image/%EC%84%9C%EB%B9%84%EC%8A%A4+%EA%B5%AC%EC%84%B1%EB%8F%84.PNG)

## 로그인, JWT 토큰 인증 시퀀스 다이어그램

![https://tablet-bucket.s3.ap-northeast-2.amazonaws.com/readme_image/%EC%9D%B8%EC%A6%9D+%EB%A1%9C%EC%A7%81+%ED%94%8C%EB%A1%9C%EC%9A%B0.PNG](https://tablet-bucket.s3.ap-northeast-2.amazonaws.com/readme_image/%EC%9D%B8%EC%A6%9D+%EB%A1%9C%EC%A7%81+%ED%94%8C%EB%A1%9C%EC%9A%B0.PNG)

## 결과 화면

- 로그인 페이지

  - 로그인 (Spring Security 사용), 로그아웃 (localStorage aceessToken 값 삭제)
  
    ![https://tablet-bucket.s3.ap-northeast-2.amazonaws.com/readme_image/%EB%A1%9C%EA%B7%B8%EC%9D%B8+%EB%A1%9C%EA%B7%B8%EC%95%84%EC%9B%83.gif](https://tablet-bucket.s3.ap-northeast-2.amazonaws.com/readme_image/%EB%A1%9C%EA%B7%B8%EC%9D%B8+%EB%A1%9C%EA%B7%B8%EC%95%84%EC%9B%83.gif)

- 메뉴 페이지
  - 메뉴 조회

    ![https://tablet-bucket.s3.ap-northeast-2.amazonaws.com/readme_image/%EB%A9%94%EB%89%B4%EC%A1%B0%ED%9A%8C.gif](https://tablet-bucket.s3.ap-northeast-2.amazonaws.com/readme_image/%EB%A9%94%EB%89%B4%EC%A1%B0%ED%9A%8C.gif)
    
  - 메뉴 선택 (선택한 메뉴 로컬스토리지 장바구니에 담기)
  
    ![https://tablet-bucket.s3.ap-northeast-2.amazonaws.com/readme_image/%EB%A9%94%EB%89%B4+%EC%84%A0%ED%83%9D.gif](https://tablet-bucket.s3.ap-northeast-2.amazonaws.com/readme_image/%EB%A9%94%EB%89%B4+%EC%84%A0%ED%83%9D.gif)
- 장바구니 페이지

  - 장바구니 조회. 메뉴 수량 변경, 메뉴 삭제 (삭제한 메뉴 로컬스토리지 장바구니에서 삭제)
  
    ![https://tablet-bucket.s3.ap-northeast-2.amazonaws.com/readme_image/%EC%9E%A5%EB%B0%94%EA%B5%AC%EB%8B%88+%EB%A9%94%EB%89%B4+%EC%88%98%EB%9F%89%EB%B3%80%EA%B2%BD+%EC%82%AD%EC%A0%9C.gif](https://tablet-bucket.s3.ap-northeast-2.amazonaws.com/readme_image/%EC%9E%A5%EB%B0%94%EA%B5%AC%EB%8B%88+%EB%A9%94%EB%89%B4+%EC%88%98%EB%9F%89%EB%B3%80%EA%B2%BD+%EC%82%AD%EC%A0%9C.gif)

  - 주문하기 (주문 시 로컬스토리지 장바구니 전체 삭제)
  
    ![https://tablet-bucket.s3.ap-northeast-2.amazonaws.com/readme_image/%EC%A3%BC%EB%AC%B8%ED%95%98%EA%B8%B0.gif](https://tablet-bucket.s3.ap-northeast-2.amazonaws.com/readme_image/%EC%A3%BC%EB%AC%B8%ED%95%98%EA%B8%B0.gif)

- 계산서 페이지
  - 주문내역 조회, 결제하기 (결제 시 주문내역 삭제)
  
    ![https://tablet-bucket.s3.ap-northeast-2.amazonaws.com/readme_image/%EC%A3%BC%EB%AC%B8+%EC%A1%B0%ED%9A%8C+%EB%B0%8F+%EA%B2%B0%EC%A0%9C%ED%95%98%EA%B8%B0.gif](https://tablet-bucket.s3.ap-northeast-2.amazonaws.com/readme_image/%EC%A3%BC%EB%AC%B8+%EC%A1%B0%ED%9A%8C+%EB%B0%8F+%EA%B2%B0%EC%A0%9C%ED%95%98%EA%B8%B0.gif)
  ## 도커 이미지 주소
  - React-Client : [https://hub.docker.com/repository/docker/ojiwoong/tablet-react-client](https://hub.docker.com/repository/docker/ojiwoong/tablet-react-client)
  - Service-Discovery : [https://hub.docker.com/repository/docker/ojiwoong/tablet-service-discovery](https://hub.docker.com/repository/docker/ojiwoong/tablet-service-discovery)
  - Auth-Service : [https://hub.docker.com/repository/docker/ojiwoong/tablet-auth-service](https://hub.docker.com/repository/docker/ojiwoong/tablet-auth-service)
  - Menu-Service : [https://hub.docker.com/repository/docker/ojiwoong/tablet-menu-service](https://hub.docker.com/repository/docker/ojiwoong/tablet-menu-service)
  - Order-Service : [https://hub.docker.com/repository/docker/ojiwoong/tablet-order-service](https://hub.docker.com/repository/docker/ojiwoong/tablet-order-service)
  ## docker-compose 실행 화면
  ![https://tablet-bucket.s3.ap-northeast-2.amazonaws.com/readme_image/docker-compose+%EC%8B%A4%ED%96%89+%ED%99%94%EB%A9%B4.PNG](https://tablet-bucket.s3.ap-northeast-2.amazonaws.com/readme_image/docker-compose+%EC%8B%A4%ED%96%89+%ED%99%94%EB%A9%B4.PNG)
