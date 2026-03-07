# SearchArtist App
## 1. Project Architecture
This project follows Clean Architecture combined with the MVVM (Model-View-ViewModel) pattern.

Domain Layer: Contains UseCases and repository interfaces. It is independent of any framework, which facilitates unit testing.

Data Layer: Implements data retrieval logic (using Retrofit for the API and Paging for lists).

Presentation Layer: Contains the ViewModels and the UI developed with Jetpack Compose.

Rationale: This architecture was chosen to ensure the scalability and testability of the code, allowing the business logic to be isolated from the user interface.

## 2. Configuration and Execution
To run the application:

Ensure you have Android Studio Ladybug or higher installed.

Clone the repository.

Sync the project with Gradle.

Build and run on an emulator or device with API 24 or higher.

## 3. Analysis and Development Process
Development: The process began with the definition of the data layer, followed by the UseCases, and finally the UI.

Testing: Local unit tests were implemented in src/test using JUnit 4 and MockK to validate the logic of the UseCases and ViewModels, ensuring that the application state (StateFlow) behaves correctly across different scenarios.

Tools: Hilt was used for dependency injection, reducing boilerplate code and facilitating modularity.

## 4. Static Analysis (Detekt)
To ensure code quality and maintainability, Detekt was integrated into the project.

Configuration: It is configured in the build.gradle.kts file and uses the rule set located at config/detekt/detekt.yml.

Interpretation: Detekt was executed to detect "code smells" and unnecessary complexity. Identified issues (e.g., variable naming, overly long functions) were addressed to adhere to Kotlin coding conventions, thereby improving overall readability.
