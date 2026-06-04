import { BrowserRouter, Routes, Route } from "react-router-dom";

import Login from "./pages/Login";
import Register from "./pages/Register";
import Products from "./pages/Products";
import AddProduct from "./pages/AddProduct";
import Wishlist from "./pages/Wishlist";
import Profile from "./pages/Profile";
import AdminDashboard from "./pages/AdminDashboard";
import UserDashboard from "./pages/UserDashboard";

import Navbar from "./components/Navbar";
import Footer from "./components/Footer";

function App() {

  return (

    <BrowserRouter>

      <Navbar />

      <Routes>

        <Route
          path="/"
          element={<Login />}
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
          element={<UserDashboard />}
        />

        <Route
          path="/admin"
          element={<AdminDashboard />}
        />

        <Route
          path="/products"
          element={<Products />}
        />

        <Route
          path="/add-product"
          element={<AddProduct />}
        />

        <Route
          path="/wishlist"
          element={<Wishlist />}
        />

        <Route
          path="/profile"
          element={<Profile />}
        />

      </Routes>

      <Footer />

    </BrowserRouter>

  );
}

export default App;