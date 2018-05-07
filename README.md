# ATTENTION PLEASE
Use production product flavor **production** for running, and **mock** for Espresso tests  

##Features
- Kotlin
- Dagger, RxJava, Architecture components (ViewModel, Room, Paging)
- Repository pattern implementation (DetailRepositoryRetrofit)
- **Api paging** implementation (using **Paging** Architecture Components)
- Use ViewModel to keep presenters across config changes so that we do not redo unnecessary network requests.
- some unit tests using Mockito (ideally the application should have been developed in TDD)
- enable mocking of Kotlin classes 

##TODO
- Externalize api key
- Apparently the MovieDatabase API does not support image size :-(
- Write UI tests 