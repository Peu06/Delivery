import { Navigate } from "react-router-dom";

export function ProtectedRoute({ children }) {
  const isLoggedIn = localStorage.getItem("isLoggedIn");

  if (!isLoggedIn) {
    return <Navigate to="/login" replace />; // redireciona para login
  }

  return children; // se estiver logado, mostra o conteúdo
}