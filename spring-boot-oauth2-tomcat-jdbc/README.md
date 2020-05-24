# OAuth 2 서버 구축

### 1. DB에 User 정보 저장해서 조회
    in memory to database

### 2. JWT 전환
    endpoints: EndpointsConfigurer
    jwt tokenStore 

### 3. refresh_token 추가
    CustomUserDetailService
    signKey
    client 쪽 /token/refresh Mapping 추가
  
    
- 퍼시스턴스를 등록하지 않아 schema.sql 테이블을 직접 생성
- Server 쪽 OAuth2ServerConfigTest 를 통해 PasswordEncoder 를 생성하여 db에 직접 insert 함.
- Server 쪽 TestCase에 있는 MyUserTest 를 통해 임시 유저 생성


--- 

* server port 8081
* start URI : oauth

> http://localhost:8081/oauth/authorize?client_id=testClientId&redirect_uri=http://localhost:8090/oauth2/token&response_type=code&scope=read


* client port 8090
* start URI : oauth2

> http://localhost:8090/oauth2/token?code=53xUYQ
>
> http://localhost:8090/oauth2/token/refresh?refreshToken={refreshToken}
