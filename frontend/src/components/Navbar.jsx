import { Link, useNavigate } from "react-router-dom";

function Navbar() {

  const navigate = useNavigate();

  const role =
    localStorage.getItem("role");

  const dashboardPath =
    role === "ADMIN"
      ? "/admin"
      : "/dashboard";

  const logout = () => {

    localStorage.removeItem("token");
    localStorage.removeItem("role");

    navigate("/login");
  };

  return (

    <nav
      className="navbar navbar-expand-lg navbar-dark"
      style={{
        background:
          "linear-gradient(90deg,#4f46e5,#7c3aed,#ec4899)"
      }}
    >

      <div className="container">

        <Link
          className="navbar-brand fw-bold fs-4"
          to={dashboardPath}
        >
          🛒 Community Marketplace
        </Link>

        <div
          className="navbar-nav d-flex flex-row gap-3 align-items-center"
        >

          <Link
            className="nav-link text-white"
            to={dashboardPath}
          >
            Dashboard
          </Link>

          <Link
            className="nav-link text-white"
            to="/products"
          >
            Products
          </Link>

          <Link
            className="nav-link text-white"
            to="/add-product"
          >
            Add Product
          </Link>

          <Link
            className="nav-link text-white"
            to="/wishlist"
          >
            Wishlist
          </Link>

          <Link
            className="nav-link text-white"
            to="/profile"
          >
            Profile
          </Link>

          <button
            className="btn btn-warning fw-bold"
            onClick={logout}
          >
            Logout
          </button>

        </div>

      </div>

    </nav>

  );
}

export default Navbar;