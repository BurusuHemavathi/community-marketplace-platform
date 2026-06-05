import { Link } from "react-router-dom";

function UserDashboard() {

  return (

    <div className="container mt-4">

      {/* HERO SECTION */}

      <div
        className="text-white p-5 rounded-4 shadow-lg mb-5"
        style={{
          background:
            "linear-gradient(135deg,#4f46e5,#7c3aed,#ec4899)"
        }}
      >

        <h1 className="display-3 fw-bold">
          🚀 Community Marketplace
        </h1>

        <p className="lead mt-3">
          Buy, Sell and Discover Amazing Products
          from trusted sellers around the community.
        </p>

        <div className="mt-4">

          <Link
            to="/products"
            className="btn btn-light btn-lg me-3"
          >
            Explore Products
          </Link>

          <Link
            to="/add-product"
            className="btn btn-warning btn-lg"
          >
            Sell Product
          </Link>

        </div>

      </div>

      {/* DASHBOARD CARDS */}

      <div className="row mb-5">

        <div className="col-md-4 mb-4">

          <div className="card shadow h-100">

            <div className="card-body text-center p-4">

              <h1>📦</h1>

              <h3>Products</h3>

              <p>
                Browse all available marketplace products.
              </p>

              <Link
                to="/products"
                className="btn btn-primary"
              >
                View Products
              </Link>

            </div>

          </div>

        </div>

        <div className="col-md-4 mb-4">

          <div className="card shadow h-100">

            <div className="card-body text-center p-4">

              <h1>❤️</h1>

              <h3>Wishlist</h3>

              <p>
                View and manage your saved items.
              </p>

              <Link
                to="/wishlist"
                className="btn btn-danger"
              >
                Open Wishlist
              </Link>

            </div>

          </div>

        </div>

        <div className="col-md-4 mb-4">

          <div className="card shadow h-100">

            <div className="card-body text-center p-4">

              <h1>👤</h1>

              <h3>Profile</h3>

              <p>
                Manage your account details.
              </p>

              <Link
                to="/profile"
                className="btn btn-success"
              >
                View Profile
              </Link>

            </div>

          </div>

        </div>

      </div>

      {/* QUICK ACTIONS */}

      <div className="card shadow-lg mb-5">

        <div className="card-body text-center p-4">

          <h2 className="mb-4">
            ⚡ Quick Actions
          </h2>

          <div className="d-flex justify-content-center gap-3 flex-wrap">

            <Link
              to="/products"
              className="btn btn-primary"
            >
              Browse Products
            </Link>

            <Link
              to="/add-product"
              className="btn btn-success"
            >
              Add Product
            </Link>

            <Link
              to="/wishlist"
              className="btn btn-danger"
            >
              My Wishlist
            </Link>

            <Link
              to="/profile"
              className="btn btn-warning"
            >
              Profile
            </Link>

          </div>

        </div>

      </div>

    </div>

  );
}

export default UserDashboard;