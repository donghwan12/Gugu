# 구인구직웹 프로젝트

DEVELOPMENT MOTIVATION
---
> 구인구직싸이트 -
> > -- 개발동기 적는부분<br> 
> > -- 에 의 불편함에 의해 ~~ <br> 
 

HISTORY
---
[TOTAL](DOCUMENT/HISTORY/TOTAL) | [나재현](DOCUMENT/HISTORY/나재현) | [이동환](DOCUMENT/HISTORY/이동환) | [박정현](DOCUMENT/HISTORY/박정현) |  [최원준](DOCUMENT/HISTORY/최원준) | 


PLANS
---
 |LANGUAGE|PLAN|IMPLEMENT|DESCRIPTION|
 |-|-|-|-|
 |JAVA|-|-|-|
 |JSP/SERVLET|2024/04/24 - 2024/04/28|-|-|
 |SPRING STS3|-|-|-|
 |SPRING BOOT|2024/06/01 - 2024/06/30|-|-|
 

MEMBERERS
--- 
|NAME|ROLE|DETAILS|DESCRIPTION| 
|---|---|---|---|
|정우균|FN| FrontController / Docment 관리 / Dependencies  관리 |---|
|나재현|BN| OFFER - 회원가입 / 회원탈퇴 / 기업정보 CRUD|---|
|이동환|BN| 유저공통 - 로그인 / 로그아웃|이력서 CRUD/공지사항 CRUD/입사지원서 CRUD---|
|박정현|BN| SEEKER 회원가입 회원탈퇴 이력서 CRUD|---|
|최원준|FN| 모든 PAGE와 서버간 REQ / RESP |---|

SKILLS
---

#### FN
---
<img src="https://img.shields.io/badge/HTML5-3366CC?style=for-the-badge&logo=htmlacademy&logoColor=white"> <img src="https://img.shields.io/badge/CSS-1572B6?style=for-the-badge&logo=css3&logoColor=white"> <img src="https://img.shields.io/badge/JAVASCRIPT-F7DF1E?style=for-the-badge&logo=javascript&logoColor=white"> <img src="https://img.shields.io/badge/JQUERY-0769AD?style=for-the-badge&logo=jquery&logoColor=white"> 


#### BN
---
<img src="https://img.shields.io/badge/JAVA-005571?style=for-the-badge&logo=doubanread&logoColor=white"> <img src="https://img.shields.io/badge/JSP-1E8CBE?style=for-the-badge&logo=doubanread&logoColor=white"> <img src="https://img.shields.io/badge/SERVLET-4B4B77?style=for-the-badge&logo=doubanread&logoColor=white"> <img src="https://img.shields.io/badge/SPRING-STS3-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/SPRINGBOOT-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> 

#### DATABASE
---
<img src="https://img.shields.io/badge/MYSQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white">  


#### DEVOPS
---
<img src="https://img.shields.io/badge/GIT-F05032?style=for-the-badge&logo=git&logoColor=white"> <img src="https://img.shields.io/badge/GITHUB-181717?style=for-the-badge&logo=github2&logoColor=white"> <img src="https://img.shields.io/badge/AWS-EC2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white"> <img src="https://img.shields.io/badge/DOCKER-2496ED?style=for-the-badge&logo=docker&logoColor=white"> <img src="https://img.shields.io/badge/DOCKERHUB-2496ED?style=for-the-badge&logo=docker&logoColor=white"> <img src="https://img.shields.io/badge/JENKINS-D24939?style=for-the-badge&logo=jenkins&logoColor=white"> 


END POINT DOC
---
|URI|REQUEST METHOD|REQUEST PARAMETER TYPE|RESPONSE VALUE TYPE|DESCRIPTION|
|---|---|---|---|---|
|/user/join|POST|---|---|---|
|/user/login|GET/POST|---|---|---|
|/user/logout|POST|---|---|---|
|---|---|---|---|---|
|/seeker/join|---|---|---|---|
|/seeker/remove|---|---|---|---|
|/seeker/resume/add|---|---|---|---|
|/seeker/resume/list|---|---|---|---|
|---|---|---|---|---|
|/offer/join|---|---|---|---|
|/offer/remove|---|---|---|---|
|/offer/company/read|---|---|---|---|
|---|---|---|---|---|

DEPENDENCIES LIST
---
|CAT|NAME|DESCRIPTION|LINK|-|-|
|-|-|-|-|-|-|
|FN|-|-|-|-|-|
|FN|-|-|-|-|-|
|BN|-|-|-|-|-|
|BN|-|-|-|-|-|
|DB|-|-|-|-|-|
|DEVOPS|-|-|-|-|-|


ERD[KoreaJobDb]
---
![20240425155554](https://github.com/jungwoogyun/EM-01-PROJECTS/assets/84259104/8631169d-3c85-4be4-a097-613bf1e5b7e0)


FILE TREES[JSP/SERVLET]
--- 
```
C:.
│  .classpath
│  .gitignore
│  .project
│  정리.txt
│
├─.settings
│      .jsdtscope
│      org.eclipse.core.resources.prefs
│      org.eclipse.jdt.core.prefs
│      org.eclipse.wst.common.component
│      org.eclipse.wst.common.project.facet.core.xml
│      org.eclipse.wst.jsdt.ui.superType.container
│      org.eclipse.wst.jsdt.ui.superType.name
│
└─src
    └─main
        ├─java
        │  └─com
        │      └─example
        │          └─app
        │              ├─controller
        │              │  │  FrontController.java
        │              │  │  HomeController.java
        │              │  │  SubController.java
        │              │  │
        │              │  └─user
        │              │      │  UserJoinController.java
        │              │      │  UserLoginController.java
        │              │      │  UserLogoutController.java
        │              │      │
        │              │      ├─offer
        │              │      │      OfferCompanyReadController.java
        │              │      │      OfferJoinController.java
        │              │      │      OfferRemoveController.java
        │              │      │
        │              │      └─seeker
        │              │              SeekerJoinController.java
        │              │              SeekerRemoveController.java
        │              │              SeekerResumeAddController.java
        │              │              SeekerResumeListController.java
        │              │
        │              ├─domain
        │              │  ├─common
        │              │  │  ├─dao
        │              │  │  │  │  DaoFunctionHeaderNaming.java
        │              │  │  │  │  SessionDao.java
        │              │  │  │  │  SessionDaoImpl.java
        │              │  │  │  │  UserDao.java
        │              │  │  │  │  UserDaoImpl.java
        │              │  │  │  │
        │              │  │  │  └─connectionPool
        │              │  │  │          CommonDao.java
        │              │  │  │          ConnectionPool.java
        │              │  │  │
        │              │  │  ├─dto
        │              │  │  │      SessionDto.java
        │              │  │  │      UserDto.java
        │              │  │  │
        │              │  │  └─service
        │              │  │          UserService.java
        │              │  │          UserServiceImpl.java
        │              │  │
        │              │  ├─offer
        │              │  │  ├─dao
        │              │  │  │      OfferDaoImpl.java
        │              │  │  │
        │              │  │  ├─dto
        │              │  │  │      OfferDto.java
        │              │  │  │
        │              │  │  └─service
        │              │  │          OfferServiceImpl.java
        │              │  │
        │              │  └─seeker
        │              │      ├─dao
        │              │      │      SeekerDaoImpl.java
        │              │      │
        │              │      ├─dto
        │              │      │      SeekerDto.java
        │              │      │
        │              │      └─service
        │              │              SeekerServiceImpl.java
        │              │
        │              ├─filter
        │              │      RememberMeFilter.java
        │              │      UTF_8_EncodingFilter.java
        │              │
        │              └─type
        │                      ROLE.java
        │
        └─webapp
            ├─META-INF
            │      context.xml
            │      MANIFEST.MF
            │
            ├─resources
            │  └─static
            │      ├─assets
            │      │      logo.gif
            │      │
            │      ├─css
            │      │  │  common.css
            │      │  │
            │      │  └─user
            │      │          join.css
            │      │          login.css
            │      │
            │      ├─js
            │      │  │  common.js
            │      │  │  index.js
            │      │  │
            │      │  └─user
            │      │          join.js
            │      │          login.js
            │      │
            │      └─jsp
            │              footer.jsp
            │              link.jsp
            │              nav.jsp
            │              topHeader.jsp
            │
            └─WEB-INF
                │  web.xml
                │
                ├─lib
                │      commons-logging-1.3.1.jar
                │      gson-2.10.1.jar
                │      HikariCP-5.1.0.jar
                │      jackson-databind-2.17.0.jar
                │      jackson-dataformat-xml-2.17.0.jar
                │      json-simple-1.1.1.jar
                │      jstl-1.2.jar
                │      logback-classic-1.5.3.jar
                │      mysql-connector-j-8.3.0.jar
                │      slf4j-api-2.0.12.jar
                │      spring-security-crypto-3.2.10.RELEASE.jar
                │
                └─view
                    │  index.jsp
                    │  template.jsp
                    │  template_aside.jsp
                    │
                    ├─offer
                    │      companyRead.jsp
                    │
                    ├─seeker
                    │  └─resume
                    │          add.jsp
                    │
                    └─user
                            join.jsp
                            login.jsp
```

