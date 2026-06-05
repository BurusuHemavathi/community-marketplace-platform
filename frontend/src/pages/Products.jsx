import { useEffect, useState } from "react";
import api from "../services/api";

function Products() {

  const [products, setProducts] = useState([]);
  const [searchTerm, setSearchTerm] = useState("");

  useEffect(() => {
    fetchProducts();
  }, []);

  const fetchProducts = async () => {

    try {

      const token = localStorage.getItem("token");

      const response = await api.get(
        "/products?page=0&size=20&sortBy=id",
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );

      setProducts(response.data.content);

    } catch (error) {

      alert(
        error.response?.status ||
        error.message
      );
    }
  };

  const addToWishlist = async (productId) => {

    try {

      const token = localStorage.getItem("token");

      await api.post(
        `/wishlist/${productId}`,
        {},
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );

      alert("Added To Wishlist");

    } catch (error) {

      alert(
        error.response?.status ||
        error.message
      );
    }
  };

  const deleteProduct = async (productId) => {

    try {

      const token = localStorage.getItem("token");

      await api.delete(
        `/products/${productId}`,
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );

      alert("Product Deleted");

      fetchProducts();

    } catch (error) {

      alert(
        error.response?.data ||
        error.message
      );
    }
  };

  const filteredProducts = products.filter(
    (product) =>
      product.productName
        ?.toLowerCase()
        .includes(
          searchTerm.toLowerCase()
        )
  );

  return (
    <div className="container mt-4">

      {/* HERO SECTION */}

      <div
        className="text-white p-5 rounded-4 shadow-lg mb-5"
        style={{
          background:
            "linear-gradient(135deg,#2563eb,#7c3aed,#ec4899)"
        }}
      >

        <h1 className="display-4 fw-bold">
          🛍 Products Marketplace
        </h1>

        <p className="lead">
          Discover amazing products from our marketplace.
        </p>

      </div>

      {/* SEARCH */}

      <div className="mb-4">

        <input
          type="text"
          className="form-control shadow-sm"
          placeholder="🔍 Search Products..."
          value={searchTerm}
          onChange={(e) =>
            setSearchTerm(e.target.value)
          }
          style={{
            borderRadius: "15px",
            padding: "12px"
          }}
        />

      </div>

      <h4 className="mb-4 fw-bold">
        Total Products: {filteredProducts.length}
      </h4>

      <div className="row">

        {filteredProducts.map((product) => (

          <div
            className="col-md-4 mb-4"
            key={product.id}
          >

            <div
              className="card h-100 border-0 shadow"
              style={{
                borderRadius: "20px",
                overflow: "hidden"
              }}
            >

              <img
                src={product.imageUrl}
                alt={product.productName}
                className="card-img-top"
                style={{
                  height: "250px",
                  objectFit: "cover"
                }}
              />

<div
  className="card-body"
  style={{
    backgroundColor: "#1f2937",
    color: "white"
  }}
>

  <h4
    className="fw-bold mb-2"
    style={{
      color: "#ffffff"
    }}
  >
    {product.productName}
  </h4>

  <p
    style={{
      color: "#d1d5db",
      marginBottom: "8px"
    }}
  >
    👤 Seller: {product.sellerName}
  </p>

  <span
    className="badge bg-info text-dark mb-2"
  >
    {product.category}
  </span>

  <p
    style={{
      color: "#f3f4f6",
      minHeight: "60px"
    }}
  >
    {product.description}
  </p>

  <h5
    className="fw-bold text-success mb-3"
  >
    ₹ {product.price}
  </h5>

  <div className="d-grid gap-2">

<button
  className="btn btn-warning fw-bold"
  onClick={() =>
    alert(
      `👤 Seller Name: ${product.sellerName}

📧 Seller Email: ${product.sellerEmail}`
    )
  }
>
  👤 Seller Details
</button>

    <button
      className="btn btn-primary fw-bold"
      onClick={() =>
        addToWishlist(product.id)
      }
    >
      ❤️ Add To Wishlist
    </button>

    <button
      className="btn btn-outline-danger fw-bold"
      onClick={() =>
        deleteProduct(product.id)
      }
    >
      🗑 Delete Product
    </button>

  </div>

</div>

            </div>

          </div>

        ))}

      </div>

    </div>
  );
}

export default Products;