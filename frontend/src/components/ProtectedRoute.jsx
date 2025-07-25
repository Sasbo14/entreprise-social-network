import { Navigate } from "react-router-dom";
import { useAuthStore } from "../auth/useAuthStore";

export default function ProtectedRoute({ children }) {
  const token = useAuthStore((state) => state.token);
  return token ? children : <Navigate to="/login" />;
}
