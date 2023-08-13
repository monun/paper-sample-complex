# PaperMC plugin sample (1.17.1+)

[![Kotlin](https://img.shields.io/badge/java-17-ED8B00.svg?logo=java)](https://www.azul.com/)
[![Kotlin](https://img.shields.io/badge/kotlin-1.8.22-585DEF.svg?logo=kotlin)](http://kotlinlang.org)
[![Gradle](https://img.shields.io/badge/gradle-8.2.1-02303A.svg?logo=gradle)](https://gradle.org)
[![GitHub](https://img.shields.io/github/license/monun/paper-sample-complex)](https://www.gnu.org/licenses/gpl-3.0.html)
[![Kotlin](https://img.shields.io/badge/youtube-각별-red.svg?logo=youtube)](https://www.youtube.com/channel/UCDrAR1OWC2MD4s0JLetN0MA)

## 프로젝트 구성하기

1. 저장소 복제 `git clone https://github.com/monun/paper-sample.git`
2. 프로젝트 이름 변경 (`settings.gradle.kts` 의 `rootProject.name`)
3. 구성 태스크 실행 `./gradlew setupModules`

---

#### API

최상위 계층 인터페이스

---

#### CORE

API의 구현, 실제 실행 코드, `net.minecraft.server` 를 참조하는 코드

하위에 참조할 버전 이름의 프로젝트를 생성 `ex) v1.18`

---

#### PLUGIN

PaperMC 와 상호작용할 JavaPlugin 을 포함한 코드

* `./gradlew devJar` mojang mapped bundler jar
* `./gradlew reobfJar` reobfusecated bundler jar
* `./gradlew clipJar` clip jar

---

#### PUBLISH

배포용 프로젝트

* `./gradlew publishToMavenLocal -Pdev` 로 로컬 저장소에 mojangmapping 버전의 jar 파일을 배포 가능

---

#### 참고

* `api:jar` 태스크를 참조하는 작업 후 `clean` 태스크 실행 불가
    * (gradle daemon이 api.jar 를 잡고 있음)
* SNAPSHOT 버전일 경우 `plugin:clipJar` 태스크를 통한 플러그인이 서버에서 실행되지 않음
    * SNAPSHOT 버전은 항상 mavenCentral 에서 최신 버전을 확인하는데 실제로 서버에 존재하지 않아서 생기는 문제 