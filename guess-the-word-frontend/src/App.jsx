import {
    BrowserRouter,
    Routes,
    Route
} from "react-router-dom";

import MusicPlayer from "./components/MusicPlayer";

import StartPage from "./pages/StartPage";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Dashboard from "./pages/Dashboard";
import Game from "./pages/Game";
import UserReport from "./pages/UserReport";
import AdminReport from "./pages/AdminReport";
import Instructions from "./pages/Instructions";

function App() {
    return (
        <>
            <MusicPlayer />

            <BrowserRouter>
                <Routes>
                    <Route
                        path="/"
                        element={<StartPage />}
                    />

                    <Route
                        path="/login"
                        element={<Login />}
                    />

                    <Route
                        path="/register"
                        element={<Register />}
                    />

                    <Route
                        path="/dashboard"
                        element={<Dashboard />}
                    />

                    <Route
                        path="/game"
                        element={<Game />}
                    />

                    <Route
                        path="/user-report"
                        element={<UserReport />}
                    />

                    <Route
                        path="/admin-report"
                        element={<AdminReport />}
                    />

                    <Route
                        path="/instructions"
                        element={<Instructions />}
                    />
                </Routes>
            </BrowserRouter>
        </>
    );
}

export default App;