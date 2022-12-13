# News 9 App

## Project Features
The features of this project includes:
* On the list of article, it is showing the following fields:
  * headline
  * theAbstract
  * byLine
* Display the latest article first in the list sorted by "timeStamp"
* Display the smallest image
* Images loaded asynchronously and cached
* Use Activities adaptable to screen sizes / rotation
* Unit test

## Project Description
![](../../Desktop/Screen Shot 2022-12-13 at 3.18.35 am.png)

### 1. Project Structure

#### 1.1 core
that defined base interfaces, classes and shared widgets

#### 1.2 news
- package api. it handles network requests
- package di. it deals with dependency injection for database and service
- package domain - repository that:
  * requests new data from remote server and update local data
  * interacts with viewModel
- package storage that manage local data by using Room
- package ui that manages UI interface and viewModel

### 2. Libraries and tools 🛠

- Android Studio Dolphin
- plugins and dependency management organized in the project build.gradle file
- Kotlin
- Coroutines and Flow
- Architecture components [Room, ViewModel, DataBinding, LiveData]
- Dagger2 + Hilt. Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project
- Retrofit, OkHttp used to manage the process of receiving, sending, and creating HTTP requests and responses.
- Coil used for image loading
- Timber. This is a logger with a small, extensible API which provides utility on top of Android's normal Log class.
- Unit Testing [JUnit, Mockito, Espresso]


### 3. Architecture

The project uses MVVM [Model-View-ViewModel] architecture to have a unidirectional flow of data, separation of concern, testability, and a lot more.

![](../../../../var/folders/7s/6qgpvv_j0xn20083t_zs7pz40000gn/T/TemporaryItems/NSIRD_screencaptureui_wlbdvw/Screen Shot 2022-12-13 at 2.38.58 am.png)

### Note:
In data model, in order to make a better user experience, this project always retrieves data from local database [cache is always source of truth], which can always show some articles for users, regardless of with or without internet.
And the data from remote server will be saved into database first. 

--------------------


