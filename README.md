# 도서관리 시스템 개발 

# 기술 스택 
* 언어: Java
* 프레임워크: Spring Boot
* 데이터 베이스 연동: Spring JPA
* 기타: Git

# 프로젝트 구조 (백엔드) 
```text
├── backend
    │   ├── src/ main/ java/ com/ example/ mini_project_04_back/
    │   │   ├── controllers/                       # HTTP 요청을 처리하는 컨트롤러 클래스들을 모아둔 패키지
    │   │   │   ├─ BookController.java             # 책(Book) 관련 API 요청을 처리하는 컨트롤러
    |   |   |   ├─ CommentController.java          # 댓글(Comment) 관련 API 요청을 처리하는 컨트롤러
    │   │   ├── domain/                            # 엔티티 클래스들을 모아둔 패키지 
    │   │   │   ├─ Book.java                       # 책 엔티티 클래스 
    |   |   |   ├─ Comment.java                    # 댓글 엔티티 클래스
    │   │   ├── dto/                               # 데이터 전송 객체(Data Transfer Object) 클래스들을 모아둔 패키지
    │   │   │   ├─ BookDTO.java                    # 책 관련 데이터 전송 시 사용되는 DTO
    |   |   |   ├─ CommentDTO.java                 # 댓글 관련 데이터 전송 시 사용되는 DTO
    │   │   ├── repository/                        # 데이터베이스와 상호작용하는 리포지토리 인터페이스들을 모아둔 패키지
    │   │   │   ├─ BookRepository.java             # 책 엔티티에 대한 데이터 접근 로직을 담은 리포지토리
    |   |   |   ├─ CommentRepository.java          # 댓긓 엔티티에 대한 데이터 접근 로직을 담은 리포지토리
    │   │   ├── service/                           # 비즈니스 로직을 처리하는 서비스 클래스들을 모아둔 패키지
    │   │   │   ├─ BookService.java                # 책 관련 비즈니스 로직을 정의하는 서비스 인터페이스
    │   │   │   ├─ BookServiceImpl.java            # BookService 인터페이스의 구현체
    │   │   │   ├─ CommentService.java             # 댓글 관련 비즈니스 로직을 정의하는 서비스 인터페이스
    |   |   |   ├─ CommentServiceImpl.java         # CommentService 인터페이스의 구현체
    │   │   └── MiniProject4Application.java       # 메인 애플리케이션 파일
```
# API 정의서
## 기본 정보
**API 이름**: Book Management System <br>
**기본 URL**: localhost:8080 <br>
**버전**: V1.0 <br>
**소개**: 도서를 정리하고, 조회하고, 추가하고 커버 이미지를 AI로 생성해 주는 시스템 입니다.
,
## 엔드포인트
### Book 
| 이름 | HTTP 명령어 | URL | 요청 형식 | 응답 형식 |
| --- | --- | --- | --- | --- |
| 신규 도서 등록 | POST | /books | `BookCreateRequest`<br> { <br> &nbsp; title, <br> &nbsp; content, <br> &nbsp; coverImageUrl <br>} | `BookDetailedResponse` <br> { <br> &nbsp; id, <br> &nbsp; title, <br> &nbsp; content, <br> &nbsp; coverImageUrl, <br> &nbsp; [ <br> &nbsp; &nbsp; `CommentDetailedResponse`, <br> &nbsp; &nbsp; `CommentDetailedResponse` <br> &nbsp; ] <br> &nbsp;  createdAt,<br> &nbsp; updatedAt <br> }  |
| 도서 목록 확인 | GET | /books | - | [`BookSimpleResponse`] <br> [ <br> &nbsp; { <br> &nbsp; &nbsp; id, <br> &nbsp; &nbsp; title, <br> &nbsp; &nbsp; createdAt, <br> &nbsp; &nbsp; coverImageUrl  <br> &nbsp; &nbsp; }, { <br> &nbsp; &nbsp; id, <br> &nbsp; &nbsp; title, <br> &nbsp; &nbsp; createdAt, <br> &nbsp; &nbsp; coverImageUrl  <br> &nbsp; &nbsp; } <br> ] |
| 도서 상세 정보 조회 | GET | /books/{id} | - | `BookDetailedResponse` <br> { <br> &nbsp; id, <br> &nbsp; title, <br> &nbsp; content, <br> &nbsp; coverImageUrl, <br> &nbsp; [ <br> &nbsp; &nbsp; `CommentDetailedResponse`, <br> &nbsp; &nbsp; `CommentDetailedResponse` <br> &nbsp; ] <br> &nbsp;  createdAt,<br> &nbsp; updatedAt <br> }  |
| 도서 수정 | PATCH | /books/{id} | `BookUpdateRequest` <br> { <br> &nbsp; title, <br> &nbsp; content, <br> &nbsp; coverImageUrl <br> } | `BookDetailedResponse` <br> { <br> &nbsp; id, <br> &nbsp; title, <br> &nbsp; content, <br> &nbsp; coverImageUrl, <br> &nbsp; [ <br> &nbsp; &nbsp; `CommentDetailedResponse`, <br> &nbsp; &nbsp; `CommentDetailedResponse` <br> &nbsp; ] <br> &nbsp;  createdAt,<br> &nbsp; updatedAt <br> } |
| 도서 삭제 | DELETE | /books/{id} | - | - |

### Comment
| 이름 | HTTP 명령어 | URL | 요청 형식 | 응답 형식 |
| --- | --- | --- | --- | --- |
| 신규 댓글 등록 | POST | /books/{id}/comments | `CommentCreateRequest` <br> { <br> &nbsp; content <br> } | `CommentDetailedResponse` <br> { <br> &nbsp; id, <br> &nbsp; bookId, <br> &nbsp; content,<br> &nbsp; createdAt, <br> &nbsp; updatedAt <br>} |
| 도서의 댓글 목록 | GET | /books/{id}/comments | - | `CommentDetailedResponse` <br> { <br> &nbsp; id, <br> &nbsp; bookId, <br> &nbsp; content,<br> &nbsp; createdAt, <br> &nbsp; updatedAt <br>} |
| 댓글 수정 | PATCH | /comments/{id} | `CommentUpdateRequest`<br> { <br> &nbsp; content <br> } | `CommentDetailedResponse` <br> { <br> &nbsp; id, <br> &nbsp; bookId, <br> &nbsp; content,<br> &nbsp; createdAt, <br> &nbsp; updatedAt <br>} |
| 댓글 삭제 | DELETE | /comments/{id} | - | - |
