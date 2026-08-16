import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../services/api";

import "./Auth.css";

function Login() {
    const navigate = useNavigate();

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [showPassword, setShowPassword] = useState(false);

    const login = async () => {
        if (!username || !password) {
            alert("Please enter username and password");
            return;
        }

        try {
            const response = await api.post(
                "/api/auth/login",
                {
                    username,
                    password
                }
            );

            localStorage.setItem(
                "token",
                response.data.token
            );

            localStorage.setItem(
                "role",
                response.data.role
            );

            if (
                response.data.role === "ADMIN"
            ) {
                navigate("/admin-report");
            } else {
                navigate("/dashboard");
            }

        } catch (error) {
            alert(
                error.response?.data ||
                "Login failed"
            );
        }
    };

    return (
        <div className="auth-page">

            <div className="auth-card">

                <h1>Login</h1>

                {/* Username */}
                <input
                    className="auth-input"
                    type="text"
                    placeholder="Username"
                    value={username}
                    onChange={(e) =>
                        setUsername(e.target.value)
                    }
                />

                {/* Password */}
                <div className="password-wrapper">

                    <input
                        className="auth-input password-input"
                        type={
                            showPassword
                                ? "text"
                                : "password"
                        }
                        placeholder="Password"
                        value={password}
                        onChange={(e) =>
                            setPassword(e.target.value)
                        }
                    />

                    <button
                        type="button"
                        className="password-toggle"
                        onClick={() =>
                            setShowPassword(
                                !showPassword
                            )
                        }
                    >
                        {showPassword ? "Hide" : "Show"}
                    </button>

                </div>

                {/* Login button */}
                <button
                    className="auth-button"
                    onClick={login}
                >
                    Login
                </button>

                {/* New user */}
                <p className="new-user">
                    {" "}
                    <button
                        className="create-account"
                        onClick={() =>
                            navigate("/register")
                        }
                    >
                        New user? Create Account
                    </button>
                </p>

            </div>

        </div>
    );
}

export default Login;