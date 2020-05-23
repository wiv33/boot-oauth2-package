create table IF NOT EXISTS oauth_client_details
(
    client_id               VARCHAR(256) PRIMARY KEY,
    resource_ids            VARCHAR(256),
    client_secret           VARCHAR(256),
    scope                   VARCHAR(256),
    authorized_grant_types  VARCHAR(256),
    web_server_redirect_uri VARCHAR(256),
    authorities             VARCHAR(256),
    access_token_validity   INTEGER,
    refresh_token_validity  INTEGER,
    additional_information  VARCHAR(4096),
    autoapprove             VARCHAR(256)
);

create table IF NOT EXISTS my_user
(
    msrl     bigserial    not null,
    name     varchar(100) not null,
    password varchar(100),
    provider varchar(100),
    uid      varchar(50)  not null,
    primary key (msrl)
)