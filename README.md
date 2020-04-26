# SingleSignOn
Single Sign On

1. check out(clone) to local file system. lets say in D:\work\
2. open commond prompt and navigate to D:\work\SingleSignOn\singlesignon
3. run command
    mvn spring-boot:run
4. application(tomcat) will be listening on http://localhost:8081/
5. H2 database console : http://localhost:8081/h2-console
   use below JSBC URL on h2 console login page
   
   jdbc:h2:file:D:\work\SingleSignOn\singlesignon\database/singlesignondb
   
6. Generate JWT using below endpoint

   a) POST on http://localhost:8081/authenticate
   
      with body 
      {
          "userId": "1234567890",
          "password": "abc"
      }
   
   b) access GET http://localhost:8081/secure/test
   
      add "Authorization" header with JWT token value received in previous endpoint.
      
