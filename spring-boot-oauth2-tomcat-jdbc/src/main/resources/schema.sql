DROP TABLE IF EXISTS oauth_client_token CASCADE;
DROP TABLE IF EXISTS oauth_access_token CASCADE;
DROP TABLE IF EXISTS oauth_refresh_token CASCADE;
DROP TABLE IF EXISTS oauth_code CASCADE;
DROP TABLE IF EXISTS oauth_approvals CASCADE;

create table IF NOT EXISTS oauth_client_token
(
    token_id          VARCHAR(256),
    token             bytea,
    authentication_id VARCHAR(256) PRIMARY KEY,
    user_name         VARCHAR(256),
    client_id         VARCHAR(256)
);

create table IF NOT EXISTS oauth_access_token
(
    token_id          VARCHAR(256),
    token             bytea,
    authentication_id VARCHAR(256) PRIMARY KEY,
    user_name         VARCHAR(256),
    client_id         VARCHAR(256),
    authentication    bytea,
    refresh_token     VARCHAR(256)
);

create table IF NOT EXISTS oauth_refresh_token
(
    token_id       VARCHAR(256),
    token          bytea,
    authentication bytea
);

create table IF NOT EXISTS oauth_code
(
    code           VARCHAR(256),
    authentication bytea
);

create table IF NOT EXISTS oauth_approvals
(
    userId         VARCHAR(256),
    clientId       VARCHAR(256),
    scope          VARCHAR(256),
    status         VARCHAR(10),
    expiresAt      TIMESTAMP,
    lastModifiedAt TIMESTAMP
);

create table IF NOT EXISTS my_user
(
    msrl     bigserial    not null,
    name     varchar(100) not null,
    password varchar(100),
    provider varchar(100),
    uid      varchar(50)  not null,
    primary key (msrl)
);