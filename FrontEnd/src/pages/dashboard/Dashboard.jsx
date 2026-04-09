import { Sidebar } from "../../components/SideBar";
import { Outlet } from "react-router-dom";
import { useNavigate } from "react-router-dom";

export default function Dashboard() {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("isLoggedIn"); // remove o login
    navigate("/login"); // redireciona para login
  };

  return (
    <div className="flex h-screen bg-gray-100">
      <Sidebar onLogout={handleLogout} />
      <main className="flex-1 p-6">
        <Outlet /> {/* Conteúdo das rotas filhas vai aqui */}
      </main>
    </div>
  );
}