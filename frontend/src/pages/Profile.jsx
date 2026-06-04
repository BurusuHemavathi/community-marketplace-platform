import { useEffect, useState } from "react";
import api from "../services/api";

function Profile() {

  const [profile, setProfile] = useState({});

  useEffect(() => {
    fetchProfile();
  }, []);

  const fetchProfile = async () => {

    try {

      const token = localStorage.getItem("token");

      const response = await api.get(
        "/profile",
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );

      setProfile(response.data);

    } catch (error) {

      console.log(error);

      alert(
        error.response?.data ||
        error.message
      );
    }
  };

  return (

    <div className="container py-5">

      <div className="row justify-content-center">

        <div className="col-lg-8">

          <div className="card shadow-lg border-0">

            <div className="card-body text-center p-5">

              <img
                src="https://cdn-icons-png.flaticon.com/512/3135/3135715.png"
                alt="Profile"
                width="120"
                className="mb-4"
              />

              <h2 className="fw-bold">
                {profile.name}
              </h2>

              <p className="text-muted">
                {profile.email}
              </p>

              <span className="badge bg-primary fs-6 p-2">
                {profile.role}
              </span>

              <hr className="my-4" />

              <div className="row">

                <div className="col-md-6">

                  <div className="card shadow-sm">

                    <div className="card-body">

                      <h3>📦</h3>

                      <h5>
                        Products
                      </h5>

                      <p>
                        Marketplace Listings
                      </p>

                    </div>

                  </div>

                </div>

                <div className="col-md-6">

                  <div className="card shadow-sm">

                    <div className="card-body">

                      <h3>❤️</h3>

                      <h5>
                        Wishlist
                      </h5>

                      <p>
                        Saved Products
                      </p>

                    </div>

                  </div>

                </div>

              </div>

            </div>

          </div>

        </div>

      </div>

    </div>

  );
}

export default Profile;