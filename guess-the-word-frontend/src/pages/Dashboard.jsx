import { useNavigate } from "react-router-dom";

import Navbar from "../components/Navbar";

import "./Dashboard.css";

function Dashboard() {
    const navigate = useNavigate();

    const role = localStorage.getItem("role");

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
        <div className="dashboard">

            <Navbar />

            <h1>Dashboard</h1>

            <div className="card-container">

                <div
                    className="dashboard-card"
                    onClick={() =>
                        navigate("/game")
                    }
                >
                    <h2> Play Game</h2>
                </div>

                <div
                    className="dashboard-card"
                    onClick={() =>
                        navigate("/user-report")
                    }
                >
                    <h2>My Report</h2>
                </div>

                <div
                    className="dashboard-card"
                    onClick={() =>
                        navigate("/instructions")
                    }
                >
                    <h2>Instructions</h2>
                </div>

                {role === "ADMIN" && (

                    <div
                        className="dashboard-card"
                        onClick={() =>
                            navigate("/admin-report")
                        }
                    >
                        <h2> Admin Report</h2>
                    </div>

                )}

            </div>

        </div>
    );
}

export default Dashboard;