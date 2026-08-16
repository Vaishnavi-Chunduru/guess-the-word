import { useEffect, useState } from "react";
import Navbar from "../components/Navbar";
import api from "../services/api";

import "./AdminReport.css";

function AdminReport() {
    const [report, setReport] =
        useState(null);

    const [date, setDate] =
        useState(
            new Date()
                .toISOString()
                .split("T")[0]
        );

    const token =
        localStorage.getItem("token");

    useEffect(() => {
        getReport();
    }, []);

    const getReport = async () => {
        try {
            const response =
                await api.get(
                    `/api/admin/reports/daily?date=${date}`,
                    {
                        headers: {
                            Authorization:
                                `Bearer ${token}`
                        }
                    }
                );

            setReport(response.data);

        } catch (error) {
            alert(
                error.response?.data ||
                "Unable to load the report."
            );
        }
    };

    const downloadReport = () => {
        if (!report) {
            return;
        }

        const players =
            report.users && report.users.length > 0
                ? report.users
                    .map(
                        (user, index) =>
                            `${index + 1}. ${user}`
                    )
                    .join("\n")
                : "No players found";

        const content =
            `Date: ${report.date}

Users Played: ${report.numberOfUsers}
Games Played: ${report.gamesPlayed}
Correct Guesses: ${report.correctGuesses}

Players Who Played: 
${players}`;

        const file =
            new Blob(
                [content],
                {
                    type: "text/plain"
                }
            );

        const link =
            document.createElement("a");

        link.href =
            URL.createObjectURL(file);

        link.download =
            "daily-report.txt";

        link.click();
    };

    return (
        <div className="admin-page">
            <Navbar />

            <div className="admin-container">

                <h1>
                    Admin Dashboard
                </h1>

                <div className="controls">

                    <input
                        type="date"
                        value={date}
                        onChange={(e) =>
                            setDate(
                                e.target.value
                            )
                        }
                    />

                    <button
                        onClick={getReport}
                    >
                        Get Report
                    </button>

                    <button
                        onClick={
                            downloadReport
                        }
                    >
                        Download
                    </button>

                </div>

                {report && (

                    <div className="cards">

                        <div className="card">

                            <h2>
                                {report.numberOfUsers}
                            </h2>

                            <p>
                                Users Played
                            </p>

                        </div>

                        <div className="card">

                            <h2>
                                {report.gamesPlayed}
                            </h2>

                            <p>
                                Games Played
                            </p>

                        </div>

                        <div className="card">

                            <h2>
                                {report.correctGuesses}
                            </h2>

                            <p>
                                Correct Guesses
                            </p>

                        </div>

                        <div className="card">

                            <h2>
                                {report.date}
                            </h2>

                            <p>
                                Report Date
                            </p>

                        </div>

                    </div>

                )}

                {report &&
                    report.users &&
                    report.users.length > 0 && (
                        <div className="users-list">

                            <h2>Players Who Played</h2>

                            <div className="players-container">

                                {report.users.map(
                                    (user, index) => (
                                        <div
                                            key={index}
                                            className="player-card"
                                        >
                                            {user}
                                        </div>
                                    )
                                )}

                            </div>

                        </div>
                    )}

            </div>

        </div>
    );
}

export default AdminReport;