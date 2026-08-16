import { useNavigate } from "react-router-dom";

import coverImage from "../assets/Cover_Page_image.png";

import "./StartPage.css";

function StartPage() {
    const navigate = useNavigate();

    return (
        <div className="start-page">

            <img
                src={coverImage}
                alt="Guess the Word"
                className="cover-image"
            />

            <button
                className="start-button"
                onClick={() => navigate("/login")}
            >
                Get Started
            </button>

        </div>
    );
}

export default StartPage;