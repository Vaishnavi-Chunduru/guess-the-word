# 🎮 Guess The Word

A full-stack **word puzzle game** inspired by Wordle, developed using **React, Spring Boot, Spring Security, JWT, and MySQL**.

Players must identify a hidden **5-letter word** by using color-based clues. The game provides a limited number of attempts and restricts the number of games that can be played each day.

The application supports two different user roles:

- **Player**
- **Admin**

---
## Project Demo Video
▶️ [Watch the gameplay video](https://drive.google.com/file/d/1InlNqfmkJTfI_dpcuS6stewNofAje-uT/view?usp=drive_link)

# 📌 Features

## 👤 Player Features

- User registration
- User login
- JWT-based authentication
- Play a word-guessing game
- Three games per day
- Five attempts per game
- Color-based feedback after every guess
- Automatic word reveal after losing
- Personal game reports
- Background music with an option to enable or disable it
- Instructions page explaining the game rules

---

## 👨‍💼 Admin Features

- View daily reports
- View the number of users who played on a selected date
- View the total number of games played
- View the number of correct guesses
- View the names of players who played on a selected date
- Download reports as a text file

---

# 🛠️ Technology Stack

## Frontend

- React.js
- React Router DOM
- Axios
- CSS
- Vite

---

## Backend

- Spring Boot
- Spring Security
- JWT Authentication
- Maven
- Jakarta Validation

---

## Database

- MySQL

---

# 📂 Project Structure

```text
guess-the-word
│
├── guess-the-word-backend
│
│── src/main/java/com/example/guess_the_word
│   │
│   ├── config
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── exception
│   ├── repository
│   ├── security
│   ├── service
│   └── GuessTheWordApplication.java
│
└── guess-the-word-frontend
    │
    ├── src
    │
    ├── assets
    ├── components
    ├── pages
    ├── services
    ├── App.jsx
    └── main.jsx
```

---

# 🗄️ Database Setup

Create the database:

```sql
CREATE DATABASE guess_the_word;

USE guess_the_word;
```

---

## Users Table

Stores information about players and administrators.

```sql
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('PLAYER', 'ADMIN') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

## Words Table

Stores the words used in the game.

```sql
CREATE TABLE words (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    word VARCHAR(5) NOT NULL UNIQUE
);
```

---

## Insert Words

```sql
INSERT INTO words (word)
VALUES
('HOUSE'),
('TIGER'),
('MOUSE'),
('WATER'),
('PLANT'),
('GRAPE'),
('STONE'),
('LIGHT'),
('BREAD'),
('CLOUD'),
('TRAIN'),
('CHAIR'),
('WORLD'),
('SMILE'),
('HEART'),
('TABLE'),
('MUSIC'),
('BRICK'),
('SHARK'),
('FLAME');
```

---

## Games Table

Stores complete game sessions.

```sql
CREATE TABLE games (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    word_id BIGINT NOT NULL,
    game_date DATE NOT NULL,
    status ENUM(
        'IN_PROGRESS',
        'WON',
        'LOST'
    ) NOT NULL,
    guesses_used INT DEFAULT 0,

    FOREIGN KEY (user_id)
        REFERENCES users(id),

    FOREIGN KEY (word_id)
        REFERENCES words(id)
);
```

---

## Guesses Table

Stores individual attempts made during a game.

```sql
CREATE TABLE guesses (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    game_id BIGINT NOT NULL,
    guess_word VARCHAR(5) NOT NULL,
    guess_number INT NOT NULL,
    guess_date DATE NOT NULL,

    FOREIGN KEY (game_id)
        REFERENCES games(id)
);
```

---

# 🔐 Authentication

The application uses **JWT-based authentication**.

Authentication flow:

```text
User Login
     ↓

Spring Security
     ↓

JWT Token Generation
     ↓

Token Stored in Browser
     ↓

Authenticated API Requests
```

---

# 🎮 Game Rules

## How to Play

1. Click **Start Game**.
2. Enter a 5-letter word.
3. Click **Submit Guess**.
4. Use the color hints.
5. Guess the hidden word within 5 attempts.

---

## 🎨 Color Guide

| Color | Meaning |
| --- | --- |
| 🟩 Green | Correct letter in the correct position |
| 🟨 Yellow | Correct letter in the wrong position |
| ⬜ Gray | Letter is not present in the word |

---

## 📋 Constraints

- Every word contains exactly **5 letters**.
- Every word contains **unique characters**.
- Players have **5 attempts** per game.
- Players can play **3 games per day**.
- The game ends immediately after winning.
- The hidden word is revealed after losing.

---

# 🔌 REST API Endpoints

## Authentication APIs

### Register

```http
POST /api/auth/register
```

### Login

```http
POST /api/auth/login
```

---

## Game APIs

### Start a Game

```http
POST /api/game/start
```

### Submit a Guess

```http
POST /api/game/{gameId}/guess
```

---

## User Report API

```http
GET /api/user/report
```

---

## Admin Report API

```http
GET /api/admin/reports/daily?date=YYYY-MM-DD
```

---

# 📊 Reports

## Player Report

Displays:

- Date
- Number of words tried
- Number of correct guesses

---

## Admin Report

Displays:

- Number of users who played
- Number of games played
- Number of correct guesses
- Report date
- Names of players

---

# 🚀 Running the Backend

Move to the backend directory:

```bash
cd guess-the-word-backend
```

Install dependencies:

```bash
mvn clean install
```

Run the application:

```bash
mvn spring-boot:run
```

Backend server:

```text
http://localhost:8080
```

---

# 🚀 Running the Frontend

Move to the frontend directory:

```bash
cd guess-the-word-frontend
```

Install dependencies:

```bash
npm install
```

Start the application:

```bash
npm run dev
```

Frontend server:

```text
http://localhost:5173
```

---

# 🎵 User Interface

The application includes:

- Animated game interface
- Glassmorphism-based design
- Blue-purple theme
- Responsive layout
- Background image
- Background music
- Music enable/disable button

---

# 🔮 Future Enhancements

- Difficulty levels
- Leaderboards
- Multiplayer mode
- Hint system
- Achievement badges
- Time-based challenges
- User rankings

---

# 👩‍💻 Author

**Vaishnavi Chunduru**

**B.Tech Computer Science and Engineering**

**BVRIT Hyderabad College of Engineering for Women**
