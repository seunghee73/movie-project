# Movie Project

## Concept

- Main : 영화추천 커뮤니티 사이트
- 설명 : 영화를 좋아하는 사람들을 위한 community 사이트

<br>

## 요구사항

1. 기념일에 따른 영화 추천 → fix data
2. **영화 추천 시스템 개발** (우리의 핵심 기능)
3. 기분 매일 물어보기 → 쌓인 데이터를 축적하여 '영화 일기장' 컨텐츠로 사용
4. 명대사 월드컵 8강 → fix data
5. 회원가입 기능
   1. 영화 덕후력 테스트 진행. → 등급에 따른 티어 지급. 나중에 리뷰를 달 때 해당 계정의 신뢰성 보장
   2. token 기반 인증 방식
6. 커뮤니티
   1. 게시글 작성/조회/수정 기능
   2. 영화 리뷰 작성/조회/수정 기능
   3. 댓글 작성/조회/수정 기능
7. 나머지 화면 구성
   1. 영화 `detail info` 화면
      - 리뷰 작성 가능
   2. `회원가입` 화면
      - `덕후력 테스트` 화면
   3. `home` 화면
      - 오늘의 기분 물어보기
      - (optional) 기념일에 따른 영화 추천
      - (default) `영화 목록` 화면 : 클릭하여 영화 `detail info` 화면으로 이동
   4. `영화 명대사 월드컵` 화면

<br>

## 개발

```text
REST API
Java 11
Spring boot, JPA
TMDB API 사용
```

<br>

## 일정 계획

1. 도메인 DB 설계 + 커뮤니티,리뷰 REST API 개발 5/16-18(수) 까지 완료하기
2. Jwt 로그인 인증 구현 + 회원가입 REST API 개발 ~5/21(토) 까지 완료하기
3. 기념일에 따른 영화추천 REST API 개발 ~5/22(일) 까지 완료하기
4. 명대사 월드컵 REST API 개발 ~5/22(일) 까지 완료하기
5. 덕후력 테스트 REST API 개발 ~5/22(일) 까지 완료하기
6. 기분 매일 물어보게 하고 데이터 쌓는 REST API 개발 ~5/22(일) 까지 완료하기
7. 만든 API 기반으로 front UI/UX 구현 ~5/25(수) 까지 완료하기
8. 5/26 테스트 진행.
9. 5/27 마감일
