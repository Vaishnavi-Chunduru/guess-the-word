import { useNavigate } from "react-router-dom";

import "./Navbar.css";

function Navbar() {
    const navigate = useNavigate();

    const role =
        localStorage.getItem("role");

    const logout = () => {
        const confirmLogout = window.confirm(
            "Are you sure you want to log out?"
        );

        if (!confirmLogout) {
            return;
        }

        localStorage.clear();

        navigate("/login");
    };

    return (
        <nav className="navbar">

            <h2>
                Guess The Word
            </h2>

            <div className="nav-links">

                {role === "PLAYER" && (
                    <button
                        onClick={() =>
                            navigate("/dashboard")
                        }
                    >
                        Dashboard
                    </button>
                )}

                <button
                    className="logout-btn"
                    onClick={logout}
                >
                    Logout
                </button>

            </div>

        </nav>
    );
}

export default Navbar;