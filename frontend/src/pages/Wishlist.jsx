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

  return (
    <div>

      <h1>Wishlist Page</h1>

      <h2>Total Items: {wishlist.length}</h2>

      {
        wishlist.map((item) => (

          <div key={item.id}>

            <h3>{item.product?.productName}</h3>

            <p>{item.product?.description}</p>

            <p>₹ {item.product?.price}</p>

            <hr />

          </div>

        ))
      }

    </div>
  );
}

export default Wishlist;