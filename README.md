![Paldex](docs/images/project-header.png)
<p align="center">

  <a href="https://opensource.org/licenses/Apache-2.0">
     <img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-brown.svg"/>
  </a>

  <a href="https://github.com/viethua99">
     <img src="https://img.shields.io/badge/Github-viethua99-blueviolet?logo=github" alt="Github - viethua99">
  </a>

  <a href="https://www.linkedin.com/in/viet-hua-3255a2181/">
     <img src="https://img.shields.io/badge/Linkedin-Viet Hua-0077B5?logo=linkedin&logoColor=" alt="Linkedin - viethua99">
  </a>

</p>

![-----------------------------------------------------](https://raw.githubusercontent.com/andreasbm/readme/master/assets/lines/rainbow.png)

## Features

- All Pal monsters and their information in Palworld.
- Save your favorite Pals.
- Support offline data when you have no internet connection.
- Support Light / Dark mode.

## Showcase
### Android
  <div style="display: flex; width: 100%">
  <img src="docs/images/android_showcase_1.png" width="31%"/>
  <img src="docs/images/android_showcase_2.png" width="31%"/>
  <img src="docs/images/android_showcase_3.png" width="31%"/>
  </div>

### iOS
  <div style="display: flex; width: 100%">
  <img src="docs/images/ios_showcase_1.png" width="31%"/>
  <img src="docs/images/ios_showcase_2.png" width="31%"/>
  <img src="docs/images/ios_showcase_3.png" width="31%"/>
  </div>

## Setup
### Palworld Paldex API
Paldex using the [Palworld Paldex API](https://github.com/mlg404/palworld-paldex-api) for constructing API.<br>

Step 1: Clone and setup [Palworld Paldex API](https://github.com/mlg404/palworld-paldex-api) repository to run server on local host.

Step 2: Sync project and run your app
### Android
Select `composeApp` configuration and run the project.
<img width="120" src="docs/images/composeapp-run.png" />

### iOS
Select `iOSApp` configuration and run the project.
<img width="120" src="docs/images/iosapp-run.png" />


## Technical Dependencies
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- [Composable Multiplatform](https://jb.gg/compose) for building common UI. It simplifies and accelerates UI development on Android, iOS and Desktop.
- [Koin](https://insert-koin.io/) for dependency injection.
- [Ktor & Kotlin Serialization](https://ktor.io/)for constructing the REST APIs and paging network data.
- [SqlDelight](https://github.com/cashapp/sqldelight) for caching data in local database.
- [Kamel](https://github.com/Kamel-Media/Kamel) for loading images from network.
- [Voyager](https://github.com/adrielcafe/voyager) for navigation and screen models.

- Architecture:
  - MVVM Architecture (View - ViewModel - Model)
  - Repository Pattern

## Find this repository useful? :heart:
Support it by joining __[stargazers](https://github.com/viethua99/Paldex/stargazers)__ for this repository. :star: <br>
Also, __[follow me](https://github.com/viethua99)__ on GitHub for my next creations! 🤩

## License

**Paldex** is distributed under the terms of the Apache License (Version 2.0). See the
[license](LICENSE) for more information.