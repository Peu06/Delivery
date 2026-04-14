import { useState } from "react";
import { AdminProducts } from "../../components/AdminProducts";
import { AdminProductList } from "../../components/AdminProductList";

export default function Configuracoes() {
  const [refresh, setRefresh] = useState(false);

  function handleCreated() {
    setRefresh((prev) => !prev); // 🔥 força reload da lista
  }

  return (
    <div className="min-h-screen bg-gray-100 p-6">
      <h1 className="text-3xl font-bold mb-6">
        Configurações
      </h1>

      <AdminProducts onCreated={handleCreated} />
      <AdminProductList refresh={refresh} />
    </div>
  );
}