# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

> ⚠️ **Warning:** As of 2025-07-27 this repository is archived and substituted by [openapi-doc-tool](https://github.com/fugerit-org/openapi-doc-tool) (see [fugerit-org/openapi-doc-tool#1](https://github.com/fugerit-org/openapi-doc-tool/issues/1)).

## [1.0.3] - 2025-04-18

### Changed

- SnakeYAML version 2.4
- parent changed from fj-bom to fj-universe-tool 2.4.5

### Removed

- [workflow sonar cloud](.github/workflows/sonarcloud-maven.yml)

### Fixed

- fix for simple type array documentation <https://github.com/fugerit-org/yaml-doc-tool/issues/8>

## [1.0.2] - 2024-10-25

### Changed

- parent changed from fj-bom to fj-universe-tool 2.4.0

## [1.0.1] - 2024-06-02

### Changed

- parent changed from fj-bom to fj-universe-tool 2.3.3

### Fixed

- Handling $ref to other object in schemas [#5](https://github.com/fugerit-org/yaml-doc-tool/issues/5)

## [1.0.0] - 2023-11-30

### Added

- handling of template version (currently 0 - legacy, 1, this release)
- handling of constraints minLength, maxLength, minimum and maximum for [OpenAPI data types](https://swagger.io/docs/specification/data-models/data-types/)

### Changed

- parent changed from fj-bom to fj-universe-tool 0.5.6

## [0.7.0] - 2023-10-08

### Added

- added issue management section in pom

### Changed

- parent changed from fj-bom to fj-universe-tool 0.5.2

## [0.6.1] - 2023-10-07

### Added

- outputFile param to produce report on file
- code of conduct
- build and run badges

### Changed

- markdwon heading on report

## [0.6.0] - 2023-10-07

### Added

- new mode : schema check vs implementation test (#1)

## [0.5.0] - 2023-09-21

### Added

- [workflow deploy on branch deploy](.github/workflows/deploy_maven_package.yml)
- [workflow maven build](.github/workflows/build_maven_package.yml)
- keep a changelog and coverage badge

### Changed

- [workflow sonar cloud](.github/workflows/sonarcloud-maven.yml)
- fj-bom version set to 1.4.0
- fj-core version set to 8.3.7
- fj-doc version set to 3.0.7

## [0.4.2] - 2023-08-29

### Removed

- unused class org.fugerit.java.yaml.doc/FjDocFacade

### Changed

- test coverage

## [0.4.1] - 2023-08-29

### Changed

- setup for inner fj-doc facade through the new configuration model.

## [0.4.0] - 2023-08-29

### Added

- support for deprecated openapi attributes (will be added in the description)

### Changed

- Updated fj-core version to 8.2.0
- Updated fj-doc version to 1.5.3
- Updated snakeyaml to 2.2
- Updated fj-bom to to 1.2.5

## [0.3.2] - 2022-12-01

### Changed

- Updated fj-bom to to 0.2.3

### Fixed

- version vulnerability in pom dependencies

## [0.3.1] - 2022-12-01

### Added

- junit for YamlDocConfig
- [yaml-doc-config sample](src/test/resources/yaml-doc-config.xml)

### Changed

- Updated fj-core version to 0.8.3
- Updated fj-doc version to 0.5.5
- Updated snakeyaml to 1.33
- Better logging of info and errors

## [0.3.0] - 2022-11-27

### Changed

- Updated fj-bom to to 0.2.2
- Updated documentation
- Updated test case
