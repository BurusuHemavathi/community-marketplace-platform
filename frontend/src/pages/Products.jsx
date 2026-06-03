import { useEffect, useState } from "react";
import api from "../services/api";

function Products() {

  const [products, setProducts] = useState([]);

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

  return (
    <div>

      <h1>Products Page</h1>

      <h2>Total Products: {products.length}</h2>

      {products.map((product) => (
        <div key={product.id}>

          <h3>{product.productName}</h3>

          <p>{product.description}</p>

          <p>₹ {product.price}</p>

          <button
            onClick={() => addToWishlist(product.id)}
          >
            Add To Wishlist
          </button>

          <hr />

        </div>
      ))}

    </div>
  );
}

export default Products;