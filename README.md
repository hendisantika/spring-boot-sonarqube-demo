# spring-boot-sonarqube-demo

### Things todo list:

1. Clone this repository: `git clone https://github.com/hendisantika/spring-boot-sonarqube-demo.git`
2. Navigate to the folder: `cd spring-boot-sonarqube-demo`
3. Run the application: `mvn clean spring-boot:run`
4. Open your favorite browser: http://localhost:9000
5. Login with default username & password: `admin/admin`
6. Create a new project
7. Generate a token
8. Run the sonar-scanner
    ```
   mvn clean verify sonar:sonar \
    -Dsonar.projectKey=SonarQubeDockerDemo \
    -Dsonar.host.url=http://localhost:9000 \
    -Dsonar.login=sqp_ce23330afc9f5ff7733cd2a00325efa3fc7452b5
    ```
9. Check the result
10. Done
