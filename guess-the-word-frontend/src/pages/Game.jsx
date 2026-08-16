import { useState } from "react";
import Navbar from "../components/Navbar";
import api from "../services/api";

import "./Game.css";

function Game() {
    const [gameId, setGameId] = useState(null);

    const [guess, setGuess] = useState("");

    const [result, setResult] = useState(null);

    const [history, setHistory] = useState([]);

    const [gameOver, setGameOver] = useState(false);

    const [dailyLimitReached, setDailyLimitReached] =
        useState(false);

    const [guessesUsed, setGuessesUsed] =
        useState(0);

    const [verdict, setVerdict] =
        useState("");

    const [correctWord, setCorrectWord] =
        useState("");

    const [limitMessage, setLimitMessage] =
        useState("");

    const token =
        localStorage.getItem("token");

    const startGame = async () => {
        try {
            const response = await api.post(
                "/api/game/start",
                {},
                {
                    headers: {
                        Authorization:
                            `Bearer ${token}`
                    }
                }
            );

            const message =
                response.data;

            const id =
                message.match(/\d+/)[0];

            setGameId(id);

            setHistory([]);

            setResult(null);

            setGuess("");

            setGuessesUsed(0);

            setGameOver(false);

            setVerdict("");

            setLimitMessage("");

            // alert(message);

        } catch (error) {
            const message =
                error.response?.data;

            if (
                message?.includes(
                    "maximum of 3 games"
                )
            ) {
                setDailyLimitReached(true);

                setLimitMessage(
                    "You have reached the maximum limit of 3 games for today. Come back tomorrow."
                );

                return;
            }

            alert(message);
        }
    };

    const submitGuess = async () => {
        if (guess.length !== 5) {
            alert(
                "Please enter exactly 5 letters."
            );

            return;
        }

        try {
            const response = await api.post(
                `/api/game/${gameId}/guess`,
                {
                    guess:
                        guess.toUpperCase()
                },
                {
                    headers: {
                        Authorization:
                            `Bearer ${token}`
                    }
                }
            );

            setResult(response.data);

            setHistory((previous) => [
                ...previous,
                response.data
            ]);

            setGuessesUsed(
                (previous) =>
                    previous + 1
            );

            if (response.data.gameOver) {
                setGameOver(true);

                setVerdict(
                    response.data.message
                );

                setCorrectWord(
                    response.data.correctWord
                );
            }

            setGuess("");

        } catch (error) {
            alert(
                error.response?.data ||
                "Unable to submit the guess"
            );
        }
    };

    return (
        <div className="game-page">
            <Navbar />

            <div className="game-container">

                {/*<h1>Guess The Word</h1>*/}

                {!gameId && !dailyLimitReached && (

                    <div className="welcome-card">

                        <h2>Ready to Play?</h2>

                        <p>
                            Guess the hidden 5-letter word using
                            the color hints.
                        </p>

                        {/*<div className="game-info">*/}

                        {/*    <div className="info-box">*/}
                        {/*        <h3>5</h3>*/}
                        {/*        <p>Attempts</p>*/}
                        {/*    </div>*/}

                        {/*    <div className="info-box">*/}
                        {/*        <h3>3</h3>*/}
                        {/*        <p>Games / Day</p>*/}
                        {/*    </div>*/}

                        {/*    <div className="info-box">*/}
                        {/*        <h3>5</h3>*/}
                        {/*        <p>Letters</p>*/}
                        {/*    </div>*/}

                        {/*</div>*/}

                        <button
                            className="start-game-btn"
                            onClick={startGame}
                        >
                            Start Game
                        </button>

                    </div>

                )}



                {gameOver &&
                    !dailyLimitReached && (
                        <button
                            className="start-game-btn"
                            onClick={startGame}
                        >
                            Play Again
                        </button>
                    )}

                {gameId && (
                    <>
                        {/*<p>*/}
                        {/*    Game ID: {gameId}*/}
                        {/*</p>*/}

                        <p>
                            Remaining Guesses:
                            {" "}
                            {5 - guessesUsed}
                        </p>

                        <input
                            className="guess-input"
                            type="text"
                            maxLength={5}
                            value={guess}
                            placeholder="Enter a 5-letter word"
                            onChange={(e) =>
                                setGuess(
                                    e.target.value
                                        .toUpperCase()
                                        .replace(
                                            /[^A-Z]/g,
                                            ""
                                        )
                                )
                            }
                            disabled={gameOver}
                        />

                        <button
                            className="submit-btn"
                            onClick={
                                submitGuess
                            }
                            disabled={
                                gameOver
                            }
                        >
                            Submit Guess
                        </button>
                    </>
                )}

                {history.length > 0 && (
                    <div className="history">

                        {history.map(
                            (
                                attempt,
                                attemptIndex
                            ) => (
                                <div
                                    key={
                                        attemptIndex
                                    }
                                    className="attempt"
                                >
                                    <div className="tiles">
                                        {attempt.result.map(
                                            (
                                                color,
                                                index
                                            ) => (
                                                <div
                                                    key={
                                                        index
                                                    }
                                                    className={`tile ${color.toLowerCase()}`}
                                                >
                                                    {
                                                        attempt.guess[
                                                            index
                                                            ]
                                                    }
                                                </div>
                                            )
                                        )}
                                    </div>
                                </div>
                            )
                        )}

                    </div>
                )}

                {gameOver && (
                    <div className="verdict">

                        <h2>{verdict}</h2>

                        {correctWord && (
                            <p>
                                The correct word was:
                                <strong>
                                    {" "}
                                    {correctWord}
                                </strong>
                            </p>
                        )}

                    </div>
                )}
                {dailyLimitReached && (
                    <div className="limit-message">
                        <h3>{limitMessage}</h3>
                    </div>
                )}

            </div>
        </div>
    );
}

export default Game;