# Changelog

All significant changes to this project will be documented in this file.

## [0.3.4] - 2024-12-11

### Enhancements
- Introduced detailed logging in key classes to improve traceability and debugging.
- Implemented `equals()`, `hashCode()`, and `toString()` methods in certain classes to ensure consistent behavior, improve readability, and facilitate easier object comparison and logging.

### Dependencies Added
- **org.apache.logging.log4j:log4j-api:2.24.2** - Provides the Log4j API, offering interfaces for logging (e.g., Logger, Appender) to abstract the logging implementation and enable integration with other logging frameworks.
- **org.apache.logging.log4j:log4j-core:2.24.2** - The core module of Log4j, implementing the actual logging logic, including log levels, appenders, and configurations.
- **org.apache.logging.log4j:log4j-slf4j2-impl:2.24.2** - Acts as a bridge between SLF4J and Log4j 2, allowing SLF4J users to leverage Log4j 2 as the underlying logging framework.

## [0.3.3] - 2024-10-28

### Fixed
- Previously, `connection.getInputStream()` would throw an `IOException` on error responses, causing the process to fail. Updated to use `connection.getErrorStream()` to handle error streams properly.

### Dependencies Updated
- Updated `org.junit.jupiter:junit-jupiter` from **5.11.2** to **5.11.3**
- Updated `org.junit.platform:junit-platform-suite-engine` from **1.11.2** to **1.11.3**

## [0.3.2] - 2024-10-21

### Added
- The `byte[] getBodyAsRaw()` has been added to retrieve the raw body of a response

### Dependencies Updated
- Updated `com.fasterxml.jackson.core:jackson-databind` from **2.17.2** to **2.18.0**
- Updated `com.fasterxml.jackson.dataformat:jackson-dataformat-xml` from **2.17.2** to **2.18.0**
- Updated `org.junit.jupiter:junit-jupiter` from **5.11.0** to **5.11.2**
- Updated `org.junit.platform:junit-platform-suite-engine` from **1.11.0** to **1.11.2**

## [0.3.1] - 2024-08-29

### Fixed
- The `<T> T getBody(Class<T> bodyType)` method does not work for deserializing List types. A new method, `<T> List<T> getBodyAsList(Class<T> bodyType)`, has been provided that returns a List of objects.

### Dependencies Updated
- Updated `com.fasterxml.jackson.core:jackson-databind` from **2.17.1** to **2.17.2**
- Updated `com.fasterxml.jackson.dataformat:jackson-dataformat-xml` from **2.17.1** to **2.17.2**
- Updated `org.junit.jupiter:junit-jupiter` from **5.10.2** to **5.11.0**
- Updated `org.junit.platform:junit-platform-suite-engine` from **1.10.2** to **1.11.0**

## [0.3.0] - 2024-05-26

### Added
- Support for XML data with different media types.
- Support for JSON data with `application/vnd.api+json` media type.
- New methods in `HeadersBuilder` to support additional HTTP headers

### Dependencies Added
- **com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.17.1** - Jackson Databind library for XML processing.

### Dependencies Updated
- Updated `com.fasterxml.jackson.core:jackson-databind` from **2.17.0** to **2.17.1**

## [0.2.0] - 2024-05-04

### Added
- Support for HTTP PATCH requests.
- Support for HTTP HEAD requests.
- Support for HTTP OPTIONS requests.
- Support for query parameters and path variables.

### Fixed
- Fixed an issue where invoking the `retrieve()` method without calling the `headers()` method would result in an error.
- Improved error handling: Now, if the `uri()` method is not called before invoking the `retrieve()` method, an exception with a more descriptive message is thrown.

## [0.1.0] - 2024-04-20

### Added
- Initial implementation of the Java REST client library.
- Support for HTTP GET requests.
- Support for HTTP POST requests.
- Support for HTTP PUT requests.
- Support for HTTP DELETE requests.
- Ability to add headers to requests.
- Support for JSON data with `application/json` media type.

### Dependencies Added
- **com.fasterxml.jackson.core:jackson-databind:2.17.0** - Jackson Databind library for JSON processing.
- **org.junit.jupiter:junit-jupiter:5.10.2** - JUnit Jupiter for unit testing.
- **org.junit.platform:junit-platform-suite-engine:1.10.2** - JUnit Platform Suite Engine for organizing tests.