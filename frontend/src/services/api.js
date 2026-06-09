import axios from "axios";

const api = axios.create({
  baseURL:"https://community-marketplace-backend-u5ms.onrender.com"
});

export default api;