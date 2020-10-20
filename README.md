# Farmz
A simple android application to fetch, update farmers and register their farms
Fully compatible with [Android 11][android-q], supporting light and [dark theme][dark-theme] (see screenshots)

## API Key
The app uses google maps to display location of farms. Get an api key [here](https://developers.google.com/maps/documentation/android-sdk/get-api-key) <br/>

* Create the file `secure.properties` in your project level directory, and then add the following code to the file. Replace `YOUR_API_KEY` with your API key.
```
    MAPS_API_KEY=YOUR_API_KEY

```

* Place it in `app/build.gradle`
```
android {
    defaultConfig {
       ...
        def secureProps = new Properties()
        if (file("../secure.properties").exists()) {
            file("../secure.properties")?.withInputStream { secureProps.load(it) }
        }
        resValue "string", "maps_api_key", (secureProps.getProperty("MAPS_API_KEY") ?: "")
       ...
    }
    ...
}
```

## Download APK
You can download the APK from [releases](https://github.com/KryptKode/Farmz/releases/tag/0.0.1) <br/>
Use email `a@b.c` and password `123456` to log in


## What you can learn
* [Material Components][material]
* [Constraint Layout][constraint-layout]
* [Retrofit][retrofit] for REST api communication
* [Glide][glide] for image loading
* [Mockk][mockk] for mocking in tests
* [Dagger2][dagger2] for dependency injection
* [Room][room] for database
* [Kotlin Flow][flow] for concurrency
* [ViewModel][viewmodel] & [LiveData][livedata]
* [Navigation Architecture Component][nav]
* [Clean Architecture][clean-arch]
* [ViewBinding][viewbinding]
* [Kotlin Delegates][delegates]
* [DataStore][datastore]
* [Paging 3.0][paging-3]
* [Google Maps][maps]
* [Activity Results API][results]




[mockwebserver]: https://github.com/square/okhttp/tree/master/mockwebserver
[androidx]: https://developer.android.com/jetpack/androidx
[arch]: https://developer.android.com/arch
[espresso]: https://google.github.io/android-testing-support-library/docs/espresso/
[retrofit]: http://square.github.io/retrofit
[glide]: https://github.com/bumptech/glide
[mockk]: https://github.com/mockk/mockk
[dagger2]: https://github.com/google/dagger
[kotlin]: https://developer.android.com/kotlin
[material]: https://github.com/material-components/material-components-android/
[android-q]: https://developer.android.com/preview
[dark-theme]: https://developer.android.com/preview/features/darktheme
[constraint-layout]: https://developer.android.com/reference/android/support/constraint/ConstraintLayout
[rxjava2]: https://github.com/ReactiveX/RxJava
[room]: https://developer.android.com/topic/libraries/architecture/room
[paging-3]:https://developer.android.com/topic/libraries/architecture/paging/v3-overview
[maps]:https://developers.google.com/maps/documentation/android-sdk/overview
[livedata]:https://developer.android.com/topic/libraries/architecture/livedata
[viewmodel]:https://developer.android.com/topic/libraries/architecture/viewmodel
[datastore]:https://developer.android.com/topic/libraries/architecture/datastore
[flow]:https://kotlinlang.org/docs/reference/coroutines/flow.html
[clean-arch]:https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html
[nav]:https://developer.android.com/guide/navigation/navigation-getting-started
[viewbinding]:https://developer.android.com/topic/libraries/view-binding
[delegates]:https://kotlinlang.org/docs/reference/delegated-properties.html
[results]:https://developer.android.com/training/basics/intents/result


## Possible Improvements
- Add logout funtionality
- Change theme
- Improve pagination
- Use multiple back stacks on bottom navigation
- Improve UI/UX


## Screenshots
### Dark
<img src="https://user-images.githubusercontent.com/25648077/96524630-b4917200-1270-11eb-8d97-68abf65d5619.gif" width="302" alt="Screenshot">

### Light
<img src="https://user-images.githubusercontent.com/25648077/96524147-5fa12c00-126f-11eb-9cc0-803126c99647.gif" width="302" alt="Screenshot">


### Lines of code
<img src="https://user-images.githubusercontent.com/25648077/96524965-911af700-1271-11eb-8bbf-ea788f53f1ba.png" alt="Lines of code">