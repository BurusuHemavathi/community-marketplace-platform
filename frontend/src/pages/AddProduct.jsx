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

      await api.post(
        "/products",
        product,
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );

      alert("✅ Product Added Successfully");

      setTitle("");
      setDescription("");
      setPrice("");
      setCategory("");
      setImageUrl("");

    } catch (error) {

      alert(
        error.response?.data?.message ||
        "Failed To Add Product"
      );

      console.log(error);
    }
  };

  return (

    <div className="container py-5">

      <div className="row justify-content-center">

        <div className="col-lg-8">

          <div className="text-center mb-4">

            <h1 className="fw-bold">
              📦 Add Product
            </h1>

            <p className="text-light">
              Create a new product listing
            </p>

          </div>

          <div
            className="card shadow-lg border-0"
            style={{
              borderRadius: "25px"
            }}
          >

            <div className="card-body p-5">

              <form onSubmit={handleSubmit}>

                <div className="mb-4">

                  <label className="form-label fw-bold">
                    Product Title
                  </label>

                  <input
                    type="text"
                    className="form-control"
                    placeholder="Enter Product Title"
                    value={title}
                    onChange={(e) =>
                      setTitle(e.target.value)
                    }
                    required
                  />

                </div>

                <div className="mb-4">

                  <label className="form-label fw-bold">
                    Description
                  </label>

                  <textarea
                    className="form-control"
                    rows="4"
                    placeholder="Enter Product Description"
                    value={description}
                    onChange={(e) =>
                      setDescription(e.target.value)
                    }
                    required
                  />

                </div>

                <div className="row">

                  <div className="col-md-6 mb-4">

                    <label className="form-label fw-bold">
                      Price
                    </label>

                    <input
                      type="number"
                      className="form-control"
                      placeholder="Enter Price"
                      value={price}
                      onChange={(e) =>
                        setPrice(e.target.value)
                      }
                      required
                    />

                  </div>

                  <div className="col-md-6 mb-4">

                    <label className="form-label fw-bold">
                      Category
                    </label>

                    <input
                      type="text"
                      className="form-control"
                      placeholder="Electronics, Mobile..."
                      value={category}
                      onChange={(e) =>
                        setCategory(e.target.value)
                      }
                      required
                    />

                  </div>

                </div>

                <div className="mb-4">

                  <label className="form-label fw-bold">
                    Product Image URL
                  </label>

                  <input
                    type="text"
                    className="form-control"
                    placeholder="Paste Image URL"
                    value={imageUrl}
                    onChange={(e) =>
                      setImageUrl(e.target.value)
                    }
                  />

                </div>

                <button
                  type="submit"
                  className="btn btn-success w-100 py-3 fw-bold"
                >
                  🚀 Add Product
                </button>

              </form>

            </div>

          </div>

        </div>

      </div>

    </div>

  );
}

export default AddProduct;