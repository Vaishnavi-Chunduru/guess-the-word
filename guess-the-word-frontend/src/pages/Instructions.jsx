import Navbar from "../components/Navbar";

import "./Instructions.css";

function Instructions() {
    return (
        <div className="instructions-page">

            <Navbar />

            <div className="instructions-container">

                <h1>How to Play</h1>

                <div className="instructions-grid">

                    <div className="instruction-card">

                        <h2>🎮 How to Play</h2>

                        <ol>
                            <li>Click the Start Game button.</li>

                            <li>Enter a 5-letter word.</li>

                            <li>Click Submit Guess.</li>

                            <li>Use the color hints to identify the hidden word.</li>

                            <li>Guess the word within 5 attempts.</li>
                        </ol>

                    </div>

                    <div className="instruction-card">

                        <h2>🎨 Color Guide</h2>

                        <div className="color-item">

                            <span className="color-box green"></span>

                            <p>
                                Correct letter in the correct position.
                            </p>

                        </div>

                        <div className="color-item">

                            <span className="color-box yellow"></span>

                            <p>
                                Correct letter in the wrong position.
                            </p>

                        </div>

                        <div className="color-item">

                            <span className="color-box gray"></span>

                            <p>
                                Letter does not exist in the word.
                            </p>

                        </div>

                    </div>

                    <div className="instruction-card">

                        <h2>📋 Rules</h2>

                        <ul>
                            <li>Every word contains exactly 5 letters.</li>

                            <li>You have 5 attempts per game.</li>

                            <li>You can play only 3 games per day.</li>

                            <li>The game ends after winning or using all attempts.</li>

                            <li>The correct word is revealed after losing.</li>
                        </ul>

                    </div>

                    <div className="instruction-card">

                        <h2>💡 Tips</h2>

                        <ul>
                            <li>Start with common words.</li>

                            <li>Pay attention to color hints.</li>

                            <li>Reuse green letters.</li>

                            <li>Reposition yellow letters.</li>

                            <li>Avoid gray letters.</li>
                        </ul>

                    </div>

                </div>

            </div>

        </div>
    );
}

export default Instructions;