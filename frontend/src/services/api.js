import axios from "axios";

const api = axios.create({
  baseURL:"https://community-marketplace-platform.onrender.com"
});

export default api;