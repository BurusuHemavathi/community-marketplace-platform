import { Link, useNavigate } from "react-router-dom";

function Navbar() {

  const navigate = useNavigate();

  const logout = () => {

    localStorage.removeItem("token");

    navigate("/login");
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">

      <div className="container">

        <Link
          className="navbar-brand"
          to="/products"
        >
          Community Marketplace
        </Link>

       <div className="navbar-nav d-flex flex-row gap-3 align-items-center">

          <Link
            className="nav-link"
            to="/products"
          >
            Products
          </Link>

          <Link
            className="nav-link"
            to="/add-product"
          >
            Add Product
          </Link>

          <Link
            className="nav-link"
            to="/wishlist"
          >
            Wishlist
          </Link>

          <Link
            className="nav-link"
            to="/profile"
          >
            Profile
          </Link>

          <button
            className="btn btn-danger ms-3"
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