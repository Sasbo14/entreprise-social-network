import { Navigate } from "react-router-dom";
import { useAuthStore } from "./useAuthStore";

export default function AuthProvider({ children }) {
  const token = useAuthStore((state) => state.token);
  return token ? children : <Navigate to="/login" />;
}
