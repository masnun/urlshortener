## URL Shortener

It's a Spring Boot project that provides basic url shortening service. 

<img src="ScreenShot.png" />


### Setting up

Open `application.properties` and change the database config and the base url. 

The `app.url` should be set to your domain name. For example: `http://masnun.com`. The url must not contain any trailing slash. 

### Running

##### Self Contained Jar

Build the jar: 

```
./gradlew build	
```

And then run it from the jar file:

```
java -jar build/libs/urlshortener-0.0.1-SNAPSHOT.jar
```

##### Using Gradle

```
./gradlew bootRun
```

