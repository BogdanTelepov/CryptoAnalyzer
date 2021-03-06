
![GitHub Cards Preview](https://github.com/BogdanTelepov/CryptoAnalyzer/blob/main/screenshots/main_screen.png?raw=true)

# π Crypto Analyzer
**Crypto Analyzer** is a Minimal Crypto Analyzer π Android application with Modern Android development tools.


## Built With π 
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
  - [Room](https://developer.android.com/topic/libraries/architecture/room) - SQLite object mapping library.
  - [Jetpack Navigation](https://developer.android.com/guide/navigation) - Navigation refers to the interactions that allow users to navigate across, into, and back out from the different pieces of content within your app
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.


# Package Structure
    
    # Root Package
    .
    βββ data                              # For data handling.
    β   βββ database                      # Local Persistence Database. Room (SQLite) database
    |   β   βββ MainDao                   # Data Access Object for Room   
    |   |   |ββ MainDatabase              # Database Instance
    |   |   |ββ CustomTypeConverter       # Type Converters to objectc for save in DB
    |   |   |
    |ββ network
        |ββConnectionListener             # Listener connection changing
        |ββCryptoApi                      # Interface for Api calls
        |ββNetworkHandler                 # Handler network connection
        |ββNetworkResult                  # Handle Network Responses
    |ββ repository
        |ββ LocalDataSource               # Save data to DB. Caching
        |ββ RemoteDataSource              # Fetch data from Api
        |ββ Respository                   # Class contains links to local and remote repositories
    |ββ di                                # Dipendency Injection
    |
    |ββ extensions                        # Extensions
    βββ model                             # Model classes
    |
    |
    βββ ui                                # Activity/View layer
    β   |βββ|
    |   β   βββ adapter                   # Adapter for RecyclerView
    |   β   βββ viewmodel                 # Viewmodels   
    |   β   βββ fragments                 # Home Fragment
    |   β   βββ activities                # Main Activity
    |   
    |
    |
    |ββ utils                             # Constants




        
    
    
    ## Architecture
    
This app uses [***MVVM (Model View View-Model)***](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) architecture.

![](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png?authuser=2)


