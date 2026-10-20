# Sparse Table University Course Registration (Java)

A Java-based console application that manages university course registrations using a custom **sparse table** implemented via linked lists. This project demonstrates how to efficiently store and retrieve non-empty student-course relationships without relying on memory-heavy 2D arrays.

## Features

- **Dynamic Storage (Sparse Table):** Uses a doubly linked list for all students and all courses. Each student node contains a linked list of their enrolled courses, and each course node contains a linked list of its enrolled students.
- **Student & Course Management:** Add, remove, and track the last added student/course dynamically.
- **Enrollment Management:** Enroll students in courses and remove enrollments while strictly enforcing university constraints (max 7 courses per student, max 30 students per course).
- **Data Retrieval & Sorting:** 
  - List all courses a student is taking.
  - List all students in a specific course.
  - Sort students in a course by ID (Bubble sort implementation on linked list).
  - Sort courses for a student by ID.
- **Bonus Feature: Undo/Redo:** Uses two Stacks (`undoStack` and `redoStack`) to allow users to undo or redo their latest actions (adds, removes, enrollments).

## Tech Stack

- **Language:** Java (JDK 8+)
- **Data Structures:** Custom Doubly Linked Lists, Singly Linked Lists, Stacks (`java.util.Stack`)
- **Concepts:** Object-Oriented Programming, Pointer manipulation, Time/Space Complexity optimization.

## Getting Started

### Prerequisites
- Java JDK 8 or higher installed (`java -version` to check).

### Compile & Run

Because the source files declare `package dsa;`, they must be compiled from the root folder of the repository.

```bash
# 1. Compile all Java files inside the dsa package
javac dsa/*.java

# 2. Run the application
java dsa.DSA
