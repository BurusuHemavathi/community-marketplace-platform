import { useEffect, useState } from "react";
import api from "../services/api";

function Wishlist() {

  const [wishlist, setWishlist] = useState([]);

  useEffect(() => {
    fetchWishlist();
  }, []);

  const fetchWishlist = async () => {

    try {

      const token = localStorage.getItem("token");

      const response = await api.get(
        "/wishlist",
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );

      console.log(response.data);

      setWishlist(response.data);

    } catch (error) {

      alert(
        error.response?.status ||
        error.message
      );

      console.log(error);
    }
  };
  const removeFromWishlist = async (productId) => {

  try {

    const token = localStorage.getItem("token");

    await api.delete(
      `/wishlist/${productId}`,
      {
        headers: {
          Authorization: `Bearer ${token}`
        }
      }
    );

    alert("Removed from Wishlist");

    fetchWishlist();

  } catch (error) {

    alert(
      error.response?.status ||
      error.message
    );
  }
};
 return (
  <div className="container mt-4">

    <h1 className="text-center mb-4">
      Wishlist
    </h1>

    <h4 className="mb-4">
      Total Items: {wishlist.length}
    </h4>

    <div className="row">

      {wishlist.map((item) => (

        <div
          className="col-md-4 mb-4"
          key={item.id}
        >

          <div className="card shadow h-100">

            <img
              src={item.product?.imageUrl}
              alt={item.product?.productName}
              className="card-img-top"
              style={{
                height: "250px",
                objectFit: "cover"
              }}
            />

            <div className="card-body">

              <h5 className="card-title">
                {item.product?.productName}
              </h5>

              <p className="card-text">
                {item.product?.description}
              </p>

              <h5 className="text-success">
                ₹ {item.product?.price}
              </h5>

              <button
                className="btn btn-danger w-100"
                onClick={() =>
                  removeFromWishlist(
                    item.product.id
                  )
                }
              >
                Remove From Wishlist
              </button>

            </div>

          </div>

        </div>

      ))}

    </div>

  </div>
);
}

export default Wishlist;