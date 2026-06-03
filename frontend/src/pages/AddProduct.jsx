import { useState } from "react";
import api from "../services/api";

function AddProduct() {

  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [price, setPrice] = useState("");
  const [category, setCategory] = useState("");
  const [imageUrl, setImageUrl] = useState("");

  const handleSubmit = async (e) => {

    e.preventDefault();

    try {

      const token = localStorage.getItem("token");

      const product = {
        title,
        description,
        price,
        category,
        imageUrl
      };
      alert(JSON.stringify(product));

      await api.post(
        "/products",
        product,
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );

      alert("Product Added Successfully");

    } catch (error) {

      alert(
        error.response?.data?.message ||
        "Failed To Add Product"
      );

      console.log(error);
    }
  };

  return (
    <div>

      <h1>Add Product</h1>

      <form onSubmit={handleSubmit}>

        <input
          type="text"
          placeholder="Title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />

        <br /><br />

        <textarea
          placeholder="Description"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
        />

        <br /><br />

        <input
          type="number"
          placeholder="Price"
          value={price}
          onChange={(e) => setPrice(e.target.value)}
        />

        <br /><br />

        <input
          type="text"
          placeholder="Category"
          value={category}
          onChange={(e) => setCategory(e.target.value)}
        />

        <br /><br />

        <input
          type="text"
          placeholder="Image URL"
          value={imageUrl}
          onChange={(e) => setImageUrl(e.target.value)}
        />

        <br /><br />

        <button type="submit">
          Add Product
        </button>

      </form>

    </div>
  );
}

export default AddProduct;