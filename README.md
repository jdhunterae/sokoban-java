# Sokoban Java

![Build & Test](https://github.com/jdhunterae/sokoban-java/actions/workflows/gradle-test.yml/badge.svg)

A Java implementation of the classic puzzle game **Sokoban**, built using `JFrame` and `Swing`. Navigate a player through tightly packed maps, pushing crates onto designated goal tiles with minimal moves.

---

## Features

- Full Sokoban puzzle logic with wall, box, goal, and player mechanics
- Keyboard controls (`WASD` or arrow keys)
- Level completion detection and overlays
- Level pack system with dynamic loading
- Custom tool to validate and grade level difficulty via automated solving

---

## Requirements

- Java 21+
- Gradle (or use `./gradlew` wrapper)

---

## Running the Game

```bash
./gradlew run
````

Or build a runnable JAR:

```bash
./gradlew jar
java -jar build/libs/sokoban-java.jar
```

---

## Running Tests

```bash
./gradlew test
```

---

## Level Analysis Tool

Run the level grader to validate solvability and difficulty of all `.txt` maps in `src/main/resources/levels/`:

```bash
./gradlew runAnalyzer
```

Generates: `level_analysis_report.txt`

---

## Folder Structure

```
src/
├── main/
│   ├── java/          # Game logic, models, UI
│   └── resources/     # Level files (.txt)
├── test/              # JUnit tests
build.gradle
```

---

## 📜 License

MIT License
