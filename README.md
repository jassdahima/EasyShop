## Easy Shop - Ecommerce App
Easy Shop is a modern e-commerce application for Android, built with Jetpack Compose and integrated with Firebase and Razorpay Payment System. It provides a seamless shopping experience, allowing users to browse products, manage a shopping cart, handle payments, and view their order history.
## 🌟 Key Features
- **User Authentication**: Secure sign-up and sign-in functionality using Firebase Authentication.
- **Product Browsing**: A clean interface for users to view products.
- **Shopping Cart**:Add items to the cart.Increment or decrement item quantities,Remove items individually or clear the entire cart.
- **Favorites List**: Users can mark products as favorites, which are saved locally on the device using SharedPreferences.
- **Order Management**:Place orders by converting the current cart into a new order.View a list of past orders with details like date, items, and status.
- **Online Payments**: Integrated with Razorpay to handle real-time payments securely.
- **User Profile**:View and update personal information, such as shipping address.A dedicated screen to view order history and sign out.
- **Dynamic Data**: All user data, cart contents, and order information are stored and synced with Cloud Firestore, providing a persistent and real-time experience.

- ## Screenshots
 <img src= "https://github.com/jassdahima/EasyShop/blob/main/screenshots/easyshop1.png" width = "250" /> <img src= "https://github.com/jassdahima/EasyShop/blob/main/screenshots/easyshop2.png" width = "250" />
  
  <img src= "https://github.com/jassdahima/EasyShop/blob/main/screenshots/easyshop3.png" width = "250" /> <img src= "https://github.com/jassdahima/EasyShop/blob/main/screenshots/easyshop4.png" width = "250" />
 
  <img src= "https://github.com/jassdahima/EasyShop/blob/main/screenshots/easyshop5.png" width = "250" /> <img src= "https://github.com/jassdahima/EasyShop/blob/main/screenshots/easyshop6.png" width = "250" />
  
  <img src= "https://github.com/jassdahima/EasyShop/blob/main/screenshots/easyshop7.png" width = "250" /> <img src= "https://github.com/jassdahima/EasyShop/blob/main/screenshots/easyshop8.png" width = "250" />

## 🛠️ Tech Stack & Architecture
This project is built using modern Android development tools and libraries.
- **Payment Gateway**: Razorpay Android SDK for processing payments.
- **UI**: Fully built with Jetpack Compose, Android's modern declarative UI toolkit.
- **Architecture**: Follows a modern architecture, likely MVVM (Model-View-ViewModel), with utility functions (AppUtil.kt) handling business logic and data operations.
- **Navigation**: Uses Jetpack Navigation for Compose to manage screen transitions.
- **Backend**:Firebase Authentication for user management.Cloud Firestore as the real-time NoSQL database for storing user data, products, carts, and orders.
- **Asynchronous Operations**: Uses Kotlin Coroutines for managing background tasks and API calls.Image Loading: Coil (Coil-Compose) for efficiently loading and displaying images from URLs.
- **UI Components**:Material 3 for Material Design components.◦DotsIndicator for visually indicating positions in carousels or sliders.

  ## Setup and Installation
  - **Prerequisites**:
   A Google account for Firebase setup
   A Razorpay account for API keys

  - **Clone the Repository :**
     git clone https://github.com/your-username/EasyShop.git
cd EasyShop
  - Firebase Integration

  - Razorpay Integration
