
##TODO
- Externalize api key
- Apparently the MovieDatabase API does not support image size :-(
- Animate favourite
- Could have used data binding, but didn't
- Could have used constraint layout, but didn't

##Features
- Kotlin
- Dagger, RxJava, Architecture components (ViewModel, Room, Paging)
- Repository pattern implementation (DetailRepositoryRetrofit)
- **Api paging** implementation (using **Paging** Architecture Components)
- Use ViewModel to keep presenters across config changes so that we do not needlessly redo
network requests.
- some unit tests (ideally the application should have been developed in TDD)

 /$$$$$$$$ /$$$$$$$$  /$$$$$$  /$$$$$$$$ /$$$$$$  /$$
|__  $$__/| $$_____/ /$$__  $$|__  $$__//$$__  $$| $$
   | $$   | $$      | $$  \__/   | $$  | $$  \__/| $$
   | $$   | $$$$$   |  $$$$$$    | $$  |  $$$$$$ | $$
   | $$   | $$__/    \____  $$   | $$   \____  $$|__/
   | $$   | $$       /$$  \ $$   | $$   /$$  \ $$    
   | $$   | $$$$$$$$|  $$$$$$/   | $$  |  $$$$$$/ /$$
   |__/   |________/ \______/    |__/   \______/ |__/
                                                     
                                                     
                                                     