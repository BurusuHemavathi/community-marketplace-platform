import { Link } from "react-router-dom";

function AdminDashboard() {

  return (
    <div className="container mt-5">

      <h1 className="text-center mb-4">
        Admin Dashboard
      </h1>

      <div className="row">

        <div className="col-md-3 mb-4">

          <div className="card shadow">

            <div className="card-body text-center">

              <h3>📦</h3>

              <h5>Products</h5>

              <p>
                Manage marketplace products
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

        <div className="col-md-3 mb-4">

          <div className="card shadow">

            <div className="card-body text-center">

              <h3>➕</h3>

              <h5>Add Product</h5>

              <p>
                Add new products
              </p>

              <Link
                to="/add-product"
                className="btn btn-success"
              >
                Add Product
              </Link>

            </div>

          </div>

        </div>

        <div className="col-md-3 mb-4">

          <div className="card shadow">

            <div className="card-body text-center">

              <h3>❤️</h3>

              <h5>Wishlist</h5>

              <p>
                View wishlist items
              </p>

              <Link
                to="/wishlist"
                className="btn btn-danger"
              >
                Wishlist
              </Link>

            </div>

          </div>

        </div>

        <div className="col-md-3 mb-4">

          <div className="card shadow">

            <div className="card-body text-center">

              <h3>👤</h3>

              <h5>Profile</h5>

              <p>
                View admin profile
              </p>

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

    </div>
  );
}

export default AdminDashboard;