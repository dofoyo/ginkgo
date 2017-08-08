# ginkgo

# install dependencies
mvn install

# serve with hot reload at localhost:8080
mvn spring-boot:run

# package for dev
# before pakcage, update application.yml, set active: dev
mvn clean package

# package for production
# before pakcage, update application.yml, set active: prod
mvn clean package -Dmaven.test.skip=true 

