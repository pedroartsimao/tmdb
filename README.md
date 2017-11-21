Fleetmatics Code Challenge: The Movie App
===================


Simple Android App to check a list of popular movies via **The Movie Database (TMDb)** and its respective details.  It contains two screen:

* The first one is an infinite scroll list of movie's poster thumbnail and title, sorted by movie popularity;
* The second screen is the movie details. It shows the movie backdrop image, title, duration, release date, genres, overview, rating score, rating count, website and an option to favorite the movie.

The project structure is based in clean architectures with some small modifications to make it simpler, MVP pattern for presentation layer, and some modern technologies as RxJava, Dagger 2, Retrofit, Retrolambda and Butterknife.

Unfortunately, I was not able to create automatized unit/instrumented tests due to the short time I had this week.

----------

What's left to be done
-------------

* Unit/Instrumented tests;
* Better solution to store favorite movies;
* Use of different models in external layer (webservice/storage). It's currently using the same model as entity layer;
* Separate layer by module instead of only package;
* Offline caching;
* Share movies option;
* Add more filtering/sorting options;
* Use shared elements approach for activity transition;