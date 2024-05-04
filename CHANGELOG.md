# Changelog

All significant changes to this project will be documented in this file.

## [0.2.0] - 2024-05-04

### Adeed

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