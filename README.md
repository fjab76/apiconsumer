## Simple App to consume third parties' APIs
The application has been developed using Spring Boot to run a command line application that
retrieves info about movies or songs accordingly to the system properties specified.

### How to run the app
1. Clone the repository and cd into the downloaded folder
2. Run `mvn clean install`
3. Run `java -jar target/apiconsumer-0.0.1-SNAPSHOT.jar` specifying the required system properties.

When you simply run `java -jar target\apiconsumer-0.0.1-SNAPSHOT.jar`, you get a message to indicate
how to use the application:

> "It is necessary to provide the following properties.
Property 'api' must specify the API to use ('movies' for movies and 'songs' for songs).
If movies, a property 'title' must specify the movie title.
If songs, a property 'artist' must specify the artist name and a property 'title' the song title"

See some examples below.

#### Movies

Movies are retrieved using the API by omdbapi.

Run a command like:

`java -Dapi=movies -Dtitle=batman -jar target\apiconsumer-0.0.1-SNAPSHOT.jar`

It will retrieve a list like:

> "Movie{title='Batman Begins', year='2005'} <br/>
   Movie{title='Batman', year='1989'} <br/>
   Movie{title='Batman Returns', year='1992'} <br/>
   Movie{title='Batman Forever', year='1995'} <br/>
   Movie{title='Batman & Robin', year='1997'} <br/>
   Movie{title='Batman: The Animated Series', year='1992?1995'} <br/>
   Movie{title='Batman: Under the Red Hood', year='2010'} <br/>
   Movie{title='Batman: The Dark Knight Returns, Part 1', year='2012'} <br/>
   Movie{title='Batman: Mask of the Phantasm', year='1993'} <br/>
   Movie{title='Batman: The Dark Knight Returns, Part 2', year='2013'}"
   
In case the value of the system property contains blank spaces, it should be put in between quotes.

#### Songs

Songs are retrieved using the API by echonest.

Run a command like:

`>java -Dapi=songs -Dartist=beatles  -Dtitle=jude -jar target/apiconsumer-0.0.1-SNAPSHOT.jar`

It will retrieve a list like:

> "Song{artistName='The Beatles', title='Hey Jude'} <br/>
   Song{artistName='The Beatles', title='Hey Jude'} <br/>
   Song{artistName='The Beatles', title='Hey Jude - Remastered 2015'} <br/>
   Song{artistName='The Beatles', title='21. Hey Jude'} <br/>
   Song{artistName='The Beatles', title='24 Hey Jude'} <br/>
   Song{artistName='The Beatles', title='07 - Hey Jude'} <br/>
   Song{artistName='Bornagen Beatles', title='Hey Jude'} <br/>
   Song{artistName='Beatles Chillout', title='Hey Jude'} <br/>
   Song{artistName='The Beatles Revival Band', title='Hey Jude'} <br/>
   Song{artistName='Re Beatles', title='Hey Jude'} <br/>
   Song{artistName='The Beatles Sing Alongs', title='Hey Jude'} <br/>
   Song{artistName='A Gold Tribute To Beatles', title='Hey Jude'} <br/>
   Song{artistName='The Beatles Symphony Orchestra', title='Hey Jude'} <br/>
   Song{artistName='The Beatles Acoustic Trio', title='Hey Jude'} <br/>
   Song{artistName='The Beatles Revival Band', title='Hey Jude (Live)'}"
 

In case the value of the system property contains blank spaces, it should be put in between quotes.
