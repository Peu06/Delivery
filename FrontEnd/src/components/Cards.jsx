import { useEffect, useState } from "react";

export function Cards() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/products")
      .then((res) => res.json())
      .then((data) => setProducts(data))
      .catch((err) => console.error("Erro ao buscar produtos:", err));
  }, []);

  return (
    <div className="flex flex-wrap gap-6 justify-center p-6">
      {products.map((product) => (
        <div
          key={product.id}
          className="bg-white rounded-2xl shadow-md p-4 w-72 hover:scale-105 transition"
        >
          <img
            src={product.urlImg}
            alt={product.nome}
            className="w-full h-40 object-cover rounded-xl"
          />

          <h2 className="text-lg font-bold mt-2">{product.nome}</h2>
          <p className="text-gray-600 text-sm">{product.descricao}</p>

          <div className="flex justify-between items-center mt-3">
            <span className="text-green-600 font-bold">
              R$ {product.preco}
            </span>

            <button className="bg-green-500 text-white px-3 py-1 rounded-lg hover:bg-green-600">
              Pedir
            </button>
          </div>
        </div>
      ))}
    </div>
  );
}