import { Link } from "react-router-dom";

export function Sidebar({ onLogout }) {
  return (
    <aside className="w-64 bg-white shadow-md flex flex-col p-4">
      <div className="text-2xl font-bold mb-4">Logo</div>
      <nav className="flex flex-col gap-2 flex-grow">
        <Link to="/dashboard" className="py-2 px-4 text-gray-700 hover:bg-gray-200 rounded">Início</Link>
        <Link to="/dashboard/pedidos" className="py-2 px-4 text-gray-700 hover:bg-gray-200 rounded">Pedidos</Link>
        <Link to="/dashboard/estoque" className="py-2 px-4 text-gray-700 hover:bg-gray-200 rounded">Estoque</Link>
        <Link to="/dashboard/financeiro" className="py-2 px-4 text-gray-700 hover:bg-gray-200 rounded">Financeiro</Link>
        <Link to="/dashboard/clientes" className="py-2 px-4 text-gray-700 hover:bg-gray-200 rounded">Clientes</Link>
        <Link to="/dashboard/configuracoes" className="py-2 px-4 text-gray-700 hover:bg-gray-200 rounded">Configurações</Link>
      </nav>
      <button
        onClick={onLogout}
        className="mt-auto bg-red-500 hover:bg-red-600 text-white p-2 rounded"
      >
        Logout
      </button>
    </aside>
  );
}