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
