import { Link } from "react-router-dom";

function AdminDashboard() {

  return (

    <div className="container mt-4">

      {/* HERO SECTION */}

      <div
        className="text-white p-5 rounded-4 shadow-lg mb-5"
        style={{
          background:
            "linear-gradient(135deg,#0f172a,#1e293b,#7c3aed)"
        }}
      >

        <h1 className="display-4 fw-bold">
          🛡 Admin Control Center
        </h1>

        <p className="lead mt-3">
          Manage products, monitor marketplace activity
          and control platform operations.
        </p>

        <div className="mt-4">

          <Link
            to="/products"
            className="btn btn-light btn-lg me-3"
          >
            Manage Products
          </Link>

          <Link
            to="/add-product"
            className="btn btn-warning btn-lg"
          >
            Add Product
          </Link>

        </div>

      </div>

      {/* ADMIN ACTION CARDS */}

      <div className="row mb-5">

        <div className="col-md-4 mb-4">

          <Link
            to="/products"
            style={{
              textDecoration: "none"
            }}
          >

            <div className="card shadow h-100">

              <div className="card-body text-center p-4">

                <h1>📦</h1>

                <h3>Products</h3>

                <p>
                  View and manage all marketplace products.
                </p>

                <button className="btn btn-primary">
                  Open Products
                </button>

              </div>

            </div>

          </Link>

        </div>

        <div className="col-md-4 mb-4">

          <Link
            to="/users"
            style={{
              textDecoration: "none"
            }}
          >

            <div className="card shadow h-100">

              <div className="card-body text-center p-4">

                <h1>👥</h1>

                <h3>Users</h3>

                <p>
                  View all registered users.
                </p>

                <button className="btn btn-success">
                  View Users
                </button>

              </div>

            </div>

          </Link>

        </div>

        <div className="col-md-4 mb-4">

          <Link
            to="/wishlist"
            style={{
              textDecoration: "none"
            }}
          >

            <div className="card shadow h-100">

              <div className="card-body text-center p-4">

                <h1>❤️</h1>

                <h3>Wishlist</h3>

                <p>
                  Track user wishlist activity.
                </p>

                <button className="btn btn-danger">
                  Open Wishlist
                </button>

              </div>

            </div>

          </Link>

        </div>

      </div>

      {/* QUICK ACTIONS */}

      <div className="card shadow-lg p-4 mb-5">

        <h2 className="text-center mb-4">
          ⚡ Quick Actions
        </h2>

        <div className="d-flex justify-content-center gap-3 flex-wrap">

          <Link
            to="/products"
            className="btn btn-primary"
          >
            Products
          </Link>

          <Link
            to="/users"
            className="btn btn-dark"
          >
            Users
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
            Wishlist
          </Link>

        </div>

      </div>

      {/* STATUS */}

      <div className="row">

        <div className="col-md-6 mb-4">

          <div className="card shadow p-4">

            <h3>🚀 Marketplace Status</h3>

            <p>
              Platform is active and ready for users.
            </p>

          </div>

        </div>

        <div className="col-md-6 mb-4">

          <div className="card shadow p-4">

            <h3>🔒 Security</h3>

            <p>
              JWT Authentication and Role-Based Access
              Control are enabled.
            </p>

          </div>

        </div>

      </div>

    </div>

  );
}

export default AdminDashboard;