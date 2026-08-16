import { useEffect, useState } from "react";
import api from "../services/api";
import Navbar from "../components/Navbar";
import "./UserReport.css";

function UserReport() {
    const [reports, setReports] = useState([]);

    useEffect(() => {
        fetchReport();
    }, []);

    const fetchReport = async () => {
        try {
            const token = localStorage.getItem("token");

            const response = await api.get(
                "/api/user/report",
                {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                }
            );

            setReports(response.data);

        } catch (error) {
            alert("Unable to load the report.");
        }
    };

    return (
        <div className="user-report-page">

            <Navbar />

            <div className="user-report-container">

                <h1>
                    My Report
                </h1>

                {reports.length > 0 ? (

                    <table className="report-table">

                        <thead>

                        <tr>
                            <th>Date</th>
                            <th>Words Tried</th>
                            <th>Correct Guesses</th>
                        </tr>

                        </thead>

                        <tbody>

                        {reports.map(
                            (report, index) => (

                                <tr key={index}>

                                    <td>
                                        {report.date}
                                    </td>

                                    <td>
                                        {report.wordsTried}
                                    </td>

                                    <td>
                                        {report.correctGuesses}
                                    </td>

                                </tr>
                            )
                        )}

                        </tbody>

                    </table>

                ) : (

                    <p className="no-data">
                        No report data available.
                    </p>

                )}

            </div>

        </div>
    );
}

export default UserReport;