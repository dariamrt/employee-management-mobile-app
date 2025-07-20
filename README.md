# Employee Management solution for Android

## Overview
This app is a comprehensive Android solution designed to streamline employee management across multiple departments and locations. Built with modern Android development practices, this app provides a complete solution for managing staff information, department coordination and organizational structure.

## Key Features

### User Authentication & Management
- Secure login system with email validation
- User profile management with personalized greetings
- Role-based access control for different departments
- Account creation and user registration system

### Department Management
- Multi-department support 
- Department-specific employee tracking
- Task assignment and management by department
- Admin panel for department oversight
- Seamless switching between organizational units

### Task Management System
- Comprehensive task creation and assignment
- Task tracking and progress monitoring
- Department-based task organization
- Task manager interface for supervisors

### Employee Management
- Add new employees to the system
- Edit and update employee information
- Comprehensive employee database
- User details management and updates
- Advanced search and filtering capabilities

### Location & Office Management
- Firebase-based location tracking
- CRUD operations for location management
- JSON data integration for flexible data handling
- Real-time location updates

### Real-time Synchronization
- Firebase integration for cloud-based data storage
- Real-time updates across multiple devices
- Offline capability with automatic sync when online
- JSON-based data exchange for seamless integration

## Technical Stack

- **Platform**: Android (Native)
- **Language**: Java
- **Build System**: Gradle with Groovy
- **Backend**: Firebase Realtime Database
- **Authentication**: Firebase Auth
- **Data Format**: JSON
- **UI Framework**: Material Design Components

## Application Architecture

The app features a modular architecture with dedicated activities for:
- **MainActivity**: Main dashboard and navigation
- **LoginPageActivity**: Secure authentication system
- **CreateAccountActivity**: New user registration
- **AdminPanel**: Administrative controls and oversight
- **AddUserActivity**: Employee addition interface
- **EditUserActivity**: Employee information updates
- **UserDetailsActivity**: Detailed employee profiles
- **TaskManagerActivity**: Task assignment and tracking
- **AddTaskActivity**: Task creation interface
- **FirebaseCrudActivity**: Database operations
- **AddToJsonActivity**: JSON data handling

## Getting Started

### Prerequisites
- Android Studio Arctic Fox or newer
- Android SDK API level 21+
- Firebase project setup
- Google Services configuration

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/dariamrt/employee-management-mobile-app.git
   cd employee-management-mobile-app
   ```

2. **Configure Firebase**
    - Create a new Firebase project
    - Add your Android app to the Firebase project
    - Download `google-services.json` and place it in the `app/` directory

3. **Build and run**
   ```bash
   ./gradlew assembleDebug
   ```

### Configuration

Update the Firebase configuration in your project:
- Enable Authentication (Email/Password)
- Set up Realtime Database with appropriate rules
- Configure security rules for your use case