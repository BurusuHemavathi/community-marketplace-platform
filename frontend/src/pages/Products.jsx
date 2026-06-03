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

    <h1 className="text-center mb-4">
      Products
    </h1>

    <h4 className="mb-4">
      Total Products: {products.length}
    </h4>
    <div className="mb-4">

  <input
    type="text"
    className="form-control"
    placeholder="Search Products..."
    value={searchTerm}
    onChange={(e) =>
      setSearchTerm(e.target.value)
    }
  />

</div>

    <div className="row">

      {filteredProducts.map((product) => (

        <div
          className="col-md-4 mb-4"
          key={product.id}
        >

          <div className="card shadow h-100">

            <img
              src={product.imageUrl}
              alt={product.productName}
              className="card-img-top"
              style={{
                height: "250px",
                objectFit: "cover"
              }}
            />

            <div className="card-body">

              <h5 className="card-title">
                {product.productName}
              </h5>

              <p className="card-text">
                {product.description}
              </p>

              <h5 className="text-success">
                ₹ {product.price}
              </h5>

              <button
                className="btn btn-primary w-100"
                onClick={() => addToWishlist(product.id)}
              >
                Add To Wishlist
              </button>

            </div>

          </div>

        </div>

      ))}

    </div>

  </div>
  
);
}

export default Products;