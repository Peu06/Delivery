import { useEffect, useState } from "react";
import { ShoppingCart } from "lucide-react";
import { useCart } from "../context/CartContext";
import { ProductModal } from "./ProductModal";

export function Cards() {
  const [products, setProducts] = useState([]);
  const [selectedProduct, setSelectedProduct] = useState(null);

  const { addItem } = useCart();

  useEffect(() => {
    fetch("http://localhost:8080/products")
      .then((res) => res.json())
      .then((data) => setProducts(data))
      .catch((err) => console.error("Erro ao buscar produtos:", err));
  }, []);

  function handleAddToCart(item) {
    addItem({ ...item, id: item.cartKey || item.id });
  }

  return (
    <>
      {/* Cards de produtos */}
      <div className="flex flex-wrap gap-6 justify-center p-6">
        {products.map((product) => (
          <div
            key={product.id}
            className="bg-white rounded-2xl shadow-md p-4 w-72"
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
                R$ {Number(product.preco).toFixed(2)}
              </span>

              <button
                onClick={() => setSelectedProduct(product)}
                className="bg-green-500 p-2 rounded-full hover:bg-green-700 text-white transition-colors"
              >
                <ShoppingCart size={18} />
              </button>
            </div>
          </div>
        ))}
      </div>

      {/* Modal de customização */}
      {selectedProduct && (
        <ProductModal
          product={selectedProduct}
          onClose={() => setSelectedProduct(null)}
          onAddToCart={handleAddToCart}
        />
      )}
    </>
  );
}