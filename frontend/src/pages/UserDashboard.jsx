import { Link } from "react-router-dom";

function UserDashboard() {

  return (
    <div className="container mt-5">

      <h1 className="text-center mb-4">
        Welcome to Community Marketplace
      </h1>

      <div className="row">

        <div className="col-md-4 mb-4">
          <div className="card shadow">
            <div className="card-body text-center">
              <h3>📦 Products</h3>
              <p>Browse available products</p>

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
          <div className="card shadow">
            <div className="card-body text-center">
              <h3>❤️ Wishlist</h3>
              <p>View saved products</p>

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
          <div className="card shadow">
            <div className="card-body text-center">
              <h3>👤 Profile</h3>
              <p>Manage your account</p>

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

    </div>
  );
}

export default UserDashboard;