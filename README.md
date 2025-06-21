## 🌟 InstaCompose
InstaCompose is a minimalist Instagram profile UI clone built using the latest Modern Android Development (MAD) skills. It features a fully responsive Jetpack Compose UI, a story player screen, and implements best practices across architecture, state management, and testing.

This app is designed as a showcase/demo project for GitHub profile, emphasizing clean architecture, modular code, and modern UI/UX patterns.
## ✨ Screenshots
| Profile | Posts |  Story Player |
|:-:|:-:|:-:|
| ![Fist](media/profile.png?raw=true) | ![3](media/posts.png?raw=true) | ![3](media/story.png?raw=true) |

## 🚀 Key Highlights

✅ 100% Jetpack Compose UI — fully declarative and lifecycle-aware                                                                                                                           
✅ Clean architecture with separation of concerns (UI, Domain, Data)                                                                                                
✅ Offline-first support using Room for local persistence                                                                                                              
✅ Infinite scrolling with Paging 3                                                                      
✅ Dependency injection using Hilt                                                                                                   
✅ ViewModel with StateFlow and collectAsStateWithLifecycle                                                                                                    
✅ Fully covered with Unit Tests and UI Tests (ComposeTestRule)                                                                                                   
✅ Followed Material Design                                                                                                                                                     
✅ Easily scalable and production-ready structure

## ✨ Features
🎨 Instagram-style Profile Screen                                                                                        
Profile image, bio, follower/following/post count                                                                    
Story highlights carousel                                                                                                 
Sticky tab bar with posts grid                                                                                                   
Tabs with swipeable HorizontalPager behavior                                                                                   
## 🎥 Story Player Screen                                                                                
Auto-playing story with progress bars                                                                                      
## 🧱 Built with Modern Android Components                                                                             
Jetpack Compose (Material 3)                                                                                                                          
Paging 3 for post grid                                                                                                                     
Room local caching                                                                                                                                               
Hilt for dependency injection                                                                                                                
Navigation-Compose                                                                                                                  
StateFlow, collectAsStateWithLifecycle                                                                                                          
## ✅ Testing                                                                                                        
Unit tests for ViewModel                                                                                                           
UI tests for Composables and navigation                                                                                                                   
## 🏗️ Architecture                                                                                                                                     
Follows Clean Architecture principles                                                                                                                                  
Clear separation of UI, domain, and data layers                                                                                            
Uses RemoteMediator and offline-first strategy with Room                                                                                                                


## 📃 Libraries Used                                  
**Kotlin** – Modern, concise, and expressive programming language for Android.                                                                                 
**Jetpack Compose** – Android’s modern UI toolkit for building declarative UIs.                                                                                               
**Material 3** – Implements Material You design components in Compose.                                                                                                            
**Navigation-Compose** – Type-safe and composable navigation system for Compose.                                                                                                  
**Paging 3** – Efficiently loads and displays large data sets with support for RemoteMediator and LazyPagingItems.                                                                               
**Room** – SQLite abstraction layer with compile-time verification and coroutine support.                                                         
**Hilt** – Android’s recommended dependency injection library backed by Dagger.                                                                                  
**Kotlin Coroutines** – Simplifies asynchronous programming with structured concurrency.                                                  
**Kotlin Flow** – Cold, asynchronous, and reactive data streams with backpressure support.                                                                      
**Accompanist** – Jetpack Compose utility libraries including SwipeRefresh, Pager, etc.                                                                         
**Coil-Compose** – Fast, lightweight image loading for Compose using Kotlin Coroutines.                                                                                               
**Turbine** – A small testing library for collecting Flow emissions in unit tests.                                                                                       
**MockK** – Mocking library for Kotlin, used in unit tests.                                                                                              
**JUnit** – Framework for writing and running unit tests.                                                                    
**AndroidX Test** – Includes Compose UI testing APIs like createComposeRule.                      

## 🚀 Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites
*   Android Studio 
*   Java JDK

### Installing
Follow these steps if you want to get a local copy of the project on your machine.

#### 1. Clone or fork the repository by running the command below	
```
git [https://github.com/shiv219/InstaCompose]
```

#### 2. Import the project in AndroidStudio
1.  In Android Studio, go to File -> New -> Import project
2.  Follew the dialog wizard to choose the folder where you cloned the project and click on open.
3.  Android Studio imports the projects and builds it for you



## 🤝 How to Contribute
1.  Fork it
2.  Create your feature branch (git checkout -b my-new-feature)
3.  Commit your changes (git commit -am 'Add some feature')
4.  Push to the branch (git push origin my-new-feature)
5.  Create new Pull Request
