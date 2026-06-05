import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import api from "../services/api";

function Login() {

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const navigate = useNavigate();

  const ADMIN_EMAIL = "admin@gmail.com";

  const handleLogin = async (e) => {

    e.preventDefault();

    try {

      const response = await api.post(
        "/authenticate",
        {
          email,
          password
        }
      );

      localStorage.setItem(
        "token",
        response.data
      );
      console.log(response.data);

      if (
        email.toLowerCase() ===
        ADMIN_EMAIL.toLowerCase()
      ) {

        localStorage.setItem(
          "role",
          "ADMIN"
        );

        navigate("/admin");

      } else {

        localStorage.setItem(
          "role",
          "USER"
        );

        navigate("/dashboard");

      }

    } catch (error) {

      alert(
        error.response?.data ||
        error.message
      );
    }
  };

  return (
    <div className="container mt-5">

      <div className="row justify-content-center">

        <div className="col-md-5">

          <div className="card shadow">

            <div className="card-body">

              <h2 className="text-center mb-4">
                Community Marketplace
              </h2>

              <form onSubmit={handleLogin}>

                <div className="mb-3">

                  <label className="form-label">
                    Email
                  </label>

                  <input
                    type="email"
                    className="form-control"
                    placeholder="Enter Email"
                    value={email}
                    onChange={(e) =>
                      setEmail(e.target.value)
                    }
                    required
                  />

                </div>

                <div className="mb-3">

                  <label className="form-label">
                    Password
                  </label>

                  <input
                    type="password"
                    className="form-control"
                    placeholder="Enter Password"
                    value={password}
                    onChange={(e) =>
                      setPassword(e.target.value)
                    }
                    required
                  />

                </div>

                <button
                  type="submit"
                  className="btn btn-primary w-100"
                >
                  Login
                </button>

              </form>

              <div className="text-center mt-3">

                <p>
                  Don't have an account?
                </p>

                <Link
                  to="/register"
                  className="btn btn-outline-success"
                >
                  Register
                </Link>

              </div>

            </div>

          </div>

        </div>

      </div>

    </div>
  );
}

export default Login;