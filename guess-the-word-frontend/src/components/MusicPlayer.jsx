import { useEffect, useRef, useState } from "react";

import music from "../assets/background-music.mp3";

import "./MusicPlayer.css";

function MusicPlayer() {
    const audioRef = useRef(null);

    const [isPlaying, setIsPlaying] =
        useState(false);

    useEffect(() => {
        audioRef.current.volume = 0.3;
    }, []);

    const toggleMusic = () => {
        if (isPlaying) {
            audioRef.current.pause();
        } else {
            audioRef.current.play();
        }

        setIsPlaying(!isPlaying);
    };

    return (
        <>
            <audio
                ref={audioRef}
                loop
            >
                <source
                    src={music}
                    type="audio/mpeg"
                />
            </audio>

            <button
                className="music-button"
                onClick={toggleMusic}
            >
                {isPlaying
                    ? "🔇 Music Off"
                    : "🎵 Music On"}
            </button>
        </>
    );
}

export default MusicPlayer;