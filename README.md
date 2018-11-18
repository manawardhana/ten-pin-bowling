# ten-pin-bowling

A Clojure library that implements functionality for Ten Pin Bawling Score Card.

## Prerequisites
- [Leiningen](https://leiningen.org)

## Instructions - Running tests
```
lein test
```

## Project Structure
```
.
├── README.md
├── project.clj
├── resources
├── src
│   └── ten_pin_bowling
│       └── core.clj
└── test
    └── ten_pin_bowling
        └── core_test.clj
 ```
 
## Appraoch to the Solution
- Game is a sequence of immutable events
- Everything else (score, completeness etc) can be derived from just from the event history
- A `frame` is the atomic succession unit of state. The alternate would be `rounds`. This is for simplicity.
- No state is stored anywhere, but passed around to functions and returned from functions. This is ok with the amount of data we have to deal with (21 integers per game) and due to the nature of Clojure.

### Score Card Format
```
{:score 15
 :frames [[5 2] 
          [4 4]]
 :completed false}
```
