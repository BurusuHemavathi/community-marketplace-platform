import { useState } from "react";
import api from "../services/api";

function Login() {

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

 const handleLogin = async (e) => {

  e.preventDefault();

  try {

    alert("Before API Call");

    const response = await api.post(
      "/authenticate",
      {
        email: email,
        password: password
      }
    );

alert(JSON.stringify(response.data));

localStorage.setItem(
  "token",
  response.data
);
console.log(response.data);

alert("Login Successful");

  } catch (error) {

  alert(JSON.stringify(error.response?.data || error.message));
}
};

  return (
    <div>

      <h1>Community Marketplace</h1>

      <form onSubmit={handleLogin}>

        <div>
          <label>Email</label>
          <br />

          <input
            type="email"
            placeholder="Enter Email"
            value={email}
            onChange={(e) =>
              setEmail(e.target.value)
            }
          />
        </div>

        <br />

        <div>
          <label>Password</label>
          <br />

          <input
            type="password"
            placeholder="Enter Password"
            value={password}
            onChange={(e) =>
              setPassword(e.target.value)
            }
          />
        </div>

        <br />

        <button type="submit">
          Login
        </button>

      </form>

    </div>
  );
}

export default Login;