# top-three-words-test
Find Top 3 Occurrences in desending order for a given text or a input stream

## Prerequisites
- Java 11 & Maven 3

## How to build + run all tests
- Run `mvn clean install`

## How to Run all tests
- Run `mvn test`

## Other notes
* extra large file inputs performance can be improved using a BufferedReader. Optionally file can be split into smaller files and find the 'Word' Vs 'Occurrence Count' in each file parallelly. Finally those results from parallel processes can be examined and find the final top 3 words.

* project was generated using https://start.spring.io/
