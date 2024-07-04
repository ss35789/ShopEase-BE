# Shopease 백엔드

## 개요
Shopease는 온라인 쇼핑몰을 위한 백엔드 시스템입니다. 이 프로젝트는 상품 관리, 주문 처리, 사용자 관리 등을 포함하여 쇼핑몰의 핵심 기능을 제공합니다.

## 주요 기능
- **사용자 관리**
  - 회원가입
  - 로그인
  - 사용자 정보 수정

- **상품 관리**
  - 상품 등록
  - 상품 수정
  - 상품 삭제
  - 상품 조회

- **주문 관리**
  - 주문 생성
  - 주문 조회
  - 주문 수정
  - 주문 삭제

- **카테고리 관리**
  - 상품 카테고리 등록
  - 카테고리 수정
  - 카테고리 삭제

- **장바구니**
  - 장바구니 아이템 추가
  - 장바구니 아이템 수정
  - 장바구니 아이템 삭제

## 기술 스택
- **언어**: Java
- **프레임워크**: Spring Boot
- **빌드 도구**: Maven
- **데이터베이스**: MySQL
- **ORM**: Hibernate (JPA)
- **보안**: Spring Security

## 설치 및 실행 방법

### 필수 조건
- Java 11 이상
- Maven 3.6 이상
- MySQL 데이터베이스

### 설치 방법
1. 저장소 클론:
    ```sh
    git clone https://github.com/your-repo/shopease-backend.git
    cd shopease-backend
    ```

2. 데이터베이스 설정:
    - `src/main/resources/application.properties` 파일에서 데이터베이스 설정을 수정합니다.
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/shopease
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=update
    ```

3. 빌드 및 실행:
    ```sh
    mvn clean install
    mvn spring-boot:run
    ```

## API 문서
API 문서는 [Swagger UI](http://localhost:8080/swagger-ui.html)에서 확인할 수 있습니다. (기본 설정에 따라 달라질 수 있습니다)

## 기여 방법
1. 포크(Fork)합니다.
2. 새 브랜치(Feature)를 만듭니다.
    ```sh
    git checkout -b feature/AmazingFeature
    ```
3. 변경 사항을 커밋합니다.
    ```sh
    git commit -m 'Add some AmazingFeature'
    ```
4. 브랜치에 푸시(Push)합니다.
    ```sh
    git push origin feature/AmazingFeature
    ```
5. 풀 리퀘스트(Pull Request)를 생성합니다.

## 라이센스
이 프로젝트는 MIT 라이센스를 따릅니다. 자세한 내용은 [LICENSE](LICENSE) 파일을 참고하세요.

## 문의
프로젝트에 대한 문의는 [이메일](mailto:your-email@example.com)로 연락 주세요.
