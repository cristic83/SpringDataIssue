This project demos an issue with a Spring Boot & Spring Data project, when the application context uses a class loader that is different from the current thread (= the thread that is starting up the application context) class loader.

In the above case, a "java.lang.IllegalArgumentException: interface org.springframework.transaction.interceptor.TransactionalProxy is not visible from class loader" is thrown when refreshing the application context.

The demo consists of the following classes:

The Main class create a dedicated class loader that is used for creating the application context and instantiating the Spring beans. Then, using this class loader it loads a Runner class that creates the Spring application context, registers the Spring beans and refreshed the application context. 

The application context contains a Spring Data repository interface, a basic JPA entity, an entity factory bean and a data source bean.

To run this project:

./gradlew clean build.

Then run the Main.main() method.


