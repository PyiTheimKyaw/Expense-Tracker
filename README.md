# Personal Expense Tracker

A modern, lightweight Personal Expense Tracker Android application built with Jetpack Compose. This project includes Clean Architecture, Unidirectional Data Flow, and robust manual Dependency Injection.

## 🚀 Key Features

- **Live Summary Dashboard**: Real-time calculation of total expenses shown at the top of the list.
- **Detailed Expense Management**:
    - **Add Expense**: Form with title, amount, category, note, and date selection.
    - **Delete Expense**: Ability to remove individual entries with smooth animations.
    - **Clear All**: Quick action to reset the database.
- **Material 3 UI**: Modern components including `DatePicker`, `TopAppBar`, and `FloatingActionButton`.
- **Offline-First**: Powered by Room DB for persistent local storage.
- **Input Validation**: Robust handling of invalid inputs (empty titles, non-numeric amounts) with inline error feedback.
- **Type-Safe Navigation**: Secure screen transitions using Jetpack Navigation with Kotlin Serialization.

## 🏛 Architectural Design

The app strictly follows **Clean Architecture** principles to ensure a decoupled and maintainable codebase.

### Layers:
1.  **Domain Layer**: Pure Kotlin. Contains the `Expense` model and the `ExpenseRepository` interface. Zero dependencies on external frameworks.
2.  **Data Layer**: Implements the repository. Handles `Room` database operations and data mapping (`ExpenseEntity` <-> `Expense`).
3.  **Presentation Layer**: Built with `Jetpack Compose` and `MVVM`.
    - **UDF (Unidirectional Data Flow)**: UI emits events to the ViewModel, which updates the UI state.
    - **State Management**: ViewModels use `StateFlow` to emit immutable UI states.

## 💉 Dependency Injection

This project intentionally uses **Manual Dependency Injection**.

**Rationale:**
- **Performance**: Zero annotation processing (KAPT/KSP) overhead for DI, resulting in faster build times.
- **Transparency**: The object graph is explicitly defined in `ExpenseTrackerApp` and `ViewModelFactory`, making it easy to trace dependencies.
- **Testability**: Dependencies are injected through constructors, allowing for easy mocking during unit tests.

## 🧪 Testing

The project includes basic unit tests to demonstrate testing proficiency:
- **ViewModel Tests**: Validating business logic and state transitions in `AddExpenseViewModel`.
- **Mapper Tests**: Ensuring data integrity during layer-to-layer transformations in the Data layer.

## 🛠 Tech Stack

- **UI**: Jetpack Compose (Material 3)
- **Database**: Room Persistence Library
- **Architecture**: Clean Architecture + MVVM + UDF
- **Navigation**: Jetpack Navigation Compose (Type-Safe)
- **Asynchronous Logic**: Kotlin Coroutines & Flow
- **Testing**: JUnit 4, Mockito-Kotlin, Turbine (Flow testing)

## 🏃 How to Run

1.  **Clone the repository**:
    ```bash
    git clone https://github.com/your-username/Expense-Tracker.git
    ```
2.  **Open in Android Studio**:
    Open the root directory.
3.  **Sync Gradle**:
    Wait for dependencies to download.
4.  **Run**:
    Select an emulator or device (API 24+) and hit **Run**.

---
*Developed as a demonstration of modern Android development standards.*
