insert into oauth_client_details(client_id, resource_ids,client_secret,scope,authorized_grant_types,web_server_redirect_uri,authorities,access_token_validity,refresh_token_validity,additional_information,autoapprove)
values('testClientId',null,'{bcrypt}$2a$10$GN3u89QzeNJfOiWUeMT5i.UOKpWFRrenRLIq2gLDW3Pc.dwbSnR6q','read,write','authorization_code,refresh_token','http://localhost:8090/oauth2/token','ROLE_USER',36000,50000,null,null);

SELECT * FROM oauth_client_details

UPDATE oauth_client_details
SET client_id = 'psk@gamil.com'

DELETE FROM my_user
WHERE uid = 'psk@gmail.com'

SELECT * FROM my_user
WHERE uid = 'psk2@gmail.com'