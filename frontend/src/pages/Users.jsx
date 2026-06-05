import { useEffect, useState } from "react";
import api from "../services/api";

function Users() {

  const [users, setUsers] = useState([]);

  useEffect(() => {
    fetchUsers();
  }, []);

  const fetchUsers = async () => {

    try {

      const token =
        localStorage.getItem("token");

      const response =
        await api.get(
          "/admin/users",
          {
            headers: {
              Authorization:
                `Bearer ${token}`
            }
          }
        );

      setUsers(response.data);

    } catch (error) {

      alert(
        error.response?.data ||
        error.message
      );
    }
  };

  const deleteUser = async (id) => {

    const confirmDelete =
      window.confirm(
        "Are you sure you want to delete this user?"
      );

    if (!confirmDelete) {
      return;
    }

    try {

      const token =
        localStorage.getItem("token");

      await api.delete(
        `/admin/users/${id}`,
        {
          headers: {
            Authorization:
              `Bearer ${token}`
          }
        }
      );

      alert("User Deleted Successfully");

      fetchUsers();

    } catch (error) {

      alert(
        error.response?.data ||
        error.message
      );
    }
  };

  return (

    <div className="container mt-4">

      <div className="card shadow-lg border-0">

        <div
          className="card-header text-white"
          style={{
            background:
              "linear-gradient(90deg,#4f46e5,#7c3aed,#ec4899)"
          }}
        >

          <h2 className="mb-0">
            👥 Registered Users
          </h2>

        </div>

        <div className="card-body">

          <h5 className="mb-3">
            Total Users: {users.length}
          </h5>

          <table className="table table-hover table-bordered">

            <thead className="table-dark">

              <tr>

                <th>ID</th>

                <th>Name</th>

                <th>Email</th>

                <th>Role</th>

                <th>Action</th>

              </tr>

            </thead>

            <tbody>

              {users.map((user) => (

                <tr key={user.id}>

                  <td>{user.id}</td>

                  <td>{user.name}</td>

                  <td>{user.email}</td>

                  <td>

                    <span
                      className={
                        user.role === "ADMIN"
                          ? "badge bg-danger"
                          : "badge bg-success"
                      }
                    >
                      {user.role}
                    </span>

                  </td>

                  <td>

                    <button
                      className="btn btn-danger btn-sm"
                      onClick={() =>
                        deleteUser(user.id)
                      }
                    >
                      Delete
                    </button>

                  </td>

                </tr>

              ))}

            </tbody>

          </table>

        </div>

      </div>

    </div>

  );
}

export default Users;