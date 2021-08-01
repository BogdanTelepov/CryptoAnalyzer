
![GitHub Cards Preview](https://github.com/BogdanTelepov/CryptoAnalyzer/blob/main/screenshots/main_screen.png?raw=true)

# 🗞 Crypto Analyzer
**Crypto Analyzer** is a Minimal Crypto Analyzer 🗞 Android application with Modern Android development tools.


## Built With 🛠
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
  - [Room](https://developer.android.com/topic/libraries/architecture/room) - SQLite object mapping library.
  - [Jetpack Navigation](https://developer.android.com/guide/navigation) - Navigation refers to the interactions that allow users to navigate across, into, and back out from the different pieces of content within your app
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.


# Package Structure
    
    www.thecodemonks.techbytes   # Root Package
    .
    ├── data                              # For data handling.
    │   ├── database                      # Local Persistence Database. Room (SQLite) database
    |   │   ├── MainDao                   # Data Access Object for Room   
    |   |   |── MainDatabase              # Database Instance
    |   |   |── CustomTypeConverter       # Type Converters to objectc for save in DB
    |   |   |
    |── network
        |──ConnectionListener             # Listener connection changing
        |──CryptoApi                      # Interface for Api calls
        |──NetworkHandler                 # Handler network connection
        |──NetworkResult                  # Handle Network Responses
    |── repository
        |── LocalDataSource               # Save data to DB. Caching
        |── RemoteDataSource              # Fetch data from Api
        |── Respository                   # Class contains links to local and remote repositories
    |── di                                # Dipendency Injection
    |
    |── extensions                        # Extensions
    ├── model                             # Model classes
    |
    |
    ├── ui                                # Activity/View layer
    │   |───|
    |   │   ├── adapter                   # Adapter for RecyclerView
    |   │   └── viewmodel                 # Viewmodels   
    |   │   ├── fragments                 # Home Fragment
    |   │   ├── activities                # Main Activity
    |   
    |
    |
    |── utils               # Constants




        
    
    
    ## Architecture
    
This app uses [***MVVM (Model View View-Model)***](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) architecture.

![](https://github.com/TheCodeMonks/Notes-App/blob/master/screenshots/ANDROID%20ROOM%20DB%20DIAGRAM.jpg)


