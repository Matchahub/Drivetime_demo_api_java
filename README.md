# Drive Time API (Java + Spring Boot)

A simple Spring Boot API that asks **Google Maps Routes API** for drive time + distance between two addresses.
Includes a tiny HTML page to test it in the browser, and some Terraform files for deployment to Google Cloud Run.

---

## What this project does

- Accepts locations for **origin** and **destination**
- Calls the Google Maps Routes API
- Returns the below data:
  - `duration` (raw seconds)
  - `formattedDuration` (e.g., `1h 23m`)
  - `distanceMeters`, `distanceKilometers`, `distanceMiles`

---

## Tech Stack

- **Java 17**
- **Spring Boot** (REST API)
- **Maven** (Build automation)
- **Google Maps Routes API**
- **Terraform**

---

## 🚀 How to Run locally

### 1) Clone Repo

### 2) Add your Google Maps API key
Create file (`src/main/resources/application.properties`) 

### 2) Run the development server 
`./mvnw spring-boot:run`
`localhost:8080`
