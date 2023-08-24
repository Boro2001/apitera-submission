# Project Submission - Github Repository Fetching Api

This is the project submission for the Atipera preliminary recruitment process.

## Project Overview

Api fetching github repositories for given user.

## Technologies Used

Java 17, Spring Boot

## Setup and Installation

1. Clone the repository
2. Package the project using Maven `mvn clean package`
3.  Run jar file or  use `mvn spring-boot:run`
4. Format of the request should look like this: `http://localhost:8080/api/v1/{username}/repositories`

## Usage

I also hosted this tiny app on Oracle, for it allows to try this application without building project.
The endpoint is: `https://lammaat.pl/api/v1/{username}/repositories`

## Challenges Faced

I had never used Spring Boot before as a client for another api. 
I think processing of the data should be parallelized if it is possible, but
it was more difficult than synchronous processing, as well as exception management.

## 

---

Thank you for considering my project submission.

Mikołaj Borowicz

mikolajborowicz7@gmail.com
