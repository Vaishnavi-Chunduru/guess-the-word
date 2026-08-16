import { useState } from "react";
import { useNavigate } from "react-router-dom";
import api from "../services/api";

import "./Auth.css";

function Register() {
    const navigate = useNavigate();

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const [showPassword, setShowPassword] =
        useState(false);

    const register = async () => {

        if (!username || !password) {
            alert("Please fill in all fields.");
            return;
        }

        if (username.length < 5) {
            alert(
                "Username must have at least 5 characters."
            );
            return;
        }

        if (
            !/(?=.*[a-z])(?=.*[A-Z])[A-Za-z]+$/.test(
                username
            )
        ) {
            alert(
                "Username must contain both uppercase and lowercase letters and only letters are allowed."
            );
            return;
        }

        if (password.length < 5) {
            alert(
                "Password must have at least 5 characters."
            );
            return;
        }

        if (
            !/(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$%*])[A-Za-z0-9$%*]+$/.test(
                password
            )
        ) {
            alert(
                "Password must contain a letter, a number, and one special character ($, %, *)."
            );
            return;
        }

        try {

            const response = await api.post(
                "/api/auth/register",
                {
                    username,
                    password
                }
            );

            alert(response.data);

            navigate("/login");

        } catch (error) {

            alert(
                error.response?.data ||
                "Registration failed"
            );
        }
    };

    return (
        <div className="auth-page">

            <div className="auth-card">

                <h1>Register</h1>

                <input
                    className="auth-input"
                    type="text"
                    placeholder="Username"
                    value={username}
                    onChange={(e) =>
                        setUsername(e.target.value)
                    }
                />

                <div className="password-wrapper">

                    <input
                        className="auth-input"
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

                <button
                    className="auth-button"
                    onClick={register}
                >
                    Create Account
                </button>

                <p className="new-user">


                    <button
                        className="create-account"
                        onClick={() =>
                            navigate("/login")
                        }
                    >
                        Already have an account? Login
                    </button>

                </p>

            </div>

        </div>
    );
}

export default Register;