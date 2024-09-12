# 💻 Style-Commerce
패션 커머스 서비스

<br>

# 🛠️ 기술 스택
* **Java 17**
* **Spring Boot 3.3.3**
* **MySQL**
* **MongoDB**
* **Redis**
* **Kafka**

<br>

# 🧩 시스템 구조

<br>

# 📄 TABLE
![스크린샷 2024-09-12 오후 5.06.58.png](..%2F%EC%8A%A4%ED%81%AC%EB%A6%B0%EC%83%B7%202024-09-12%20%EC%98%A4%ED%9B%84%205.06.58.png)

<br>

# 📜 API
### 회원
| 기능       | METHOD | URL                  |
|----------|--------|----------------------|
| 회원 정보 조회 | GET    | /member              |
| 회원 가입    | POST   | /member              |
| 회원 수정    | PATCH  | /member              |
| 회원 탈퇴    | DELETE | /member              |
| 주소 등록    | POST   | /member/address      |
| 주소 삭제    | DELETE | /member/address/{id} |

### 인증
| 기능   | METHOD | URL            |
|------|--------|----------------|
| 로그인  | POST   | /auth/sign-in  |
| 로그아웃 | POST   | /auth/sign-out |

### 브랜드
| 기능          | METHOD | URL         |
|-------------|--------|-------------|
| 내 브랜드 목록 조회 | GET    | /brand      |
| 브랜드 등록      | POST   | /brand      |
| 브랜드 수정      | PATCH  | /brand/{id} |
| 브랜드 삭제      | DELETE | /brand{id}  |

### 상품
| 기능           | METHOD | URL                 |
|--------------|--------|---------------------|
| 브랜드 상품 목록 조회 | GET    | /product/brand/{id} |
| 브랜드 등록       | POST   | /product            |
| 브랜드 수정       | PATCH  | /product/{id}       |
| 브랜드 삭제       | DELETE | /product{id}        |

### 주문

### 채팅
