# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [2.0] - 2020-05-27
### Changed
- Upgrade to Cats 2.1.0
- Switch from `ValidatedNel` to `ValidatedNec` which uses `Chain` as its base data structure. `Chain` allows constant time prepending and appending. This makes it especially efficient when used as a Monoid, e.g. with Validated or Writer.

## [1.0] - 2016-10-27
### Added
- Initial release

[Unreleased]: https://github.com/DanielaSfregola/data-validation
[2.0]: https://github.com/DanielaSfregola/data-validation/releases/v2.0
[1.0]: https://github.com/DanielaSfregola/data-validation/releases/v1.0
