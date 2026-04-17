import { useCart } from "../context/CartContext";

export default function Carrinho() {
  const { items, total } = useCart();

  return (
    <div>
      <h1>Carrinho</h1>

      {items.length === 0 ? (
        <p>Vazio</p>
      ) : (
        items.map((item) => (
          <div key={item.id}>
            {item.nome} - R$ {item.preco}
          </div>
        ))
      )}

      <h2>Total: R$ {total.toFixed(2)}</h2>
    </div>
  );
}