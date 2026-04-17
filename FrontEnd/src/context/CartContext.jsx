import { createContext, useContext, useState } from "react";

const CartContext = createContext();

export function CartProvider({ children }) {
  const [items, setItems] = useState([]);

  // 🔑 chave única do item (produto + variação + extras)
  function generateCartKey(product) {
    return (
      product.id +
      "-" +
      (product.selectedVariation?.nome || "default") +
      "-" +
      JSON.stringify(product.selectedOptions || {})
    );
  }

  // ➕ ADICIONAR ITEM
  function addItem(product) {
    const cartKey = generateCartKey(product);

    setItems((prev) => {
      const existing = prev.find((item) => item.cartKey === cartKey);

      if (existing) {
        return prev.map((item) =>
          item.cartKey === cartKey
            ? { ...item, quantity: item.quantity + 1 }
            : item
        );
      }

      return [
        ...prev,
        {
          ...product,
          cartKey,
          quantity: 1,
        },
      ];
    });
  }

  // ❌ REMOVER ITEM
  function removeItem(cartKey) {
    setItems((prev) =>
      prev.filter((item) => item.cartKey !== cartKey)
    );
  }

  // 🔁 ALTERAR QUANTIDADE
  function updateQuantity(cartKey, quantity) {
    if (quantity <= 0) {
      removeItem(cartKey);
      return;
    }

    setItems((prev) =>
      prev.map((item) =>
        item.cartKey === cartKey
          ? { ...item, quantity }
          : item
      )
    );
  }

  function clearCart() {
    setItems([]);
  }

  // 💰 CALCULAR PREÇO DO ITEM (COM VARIAÇÕES + OPTIONS)
  function getItemTotal(item) {
    const base = Number(item.precoBase || item.preco || 0);

    const variation = item.selectedVariation?.preco || 0;

    const options = Object.values(item.selectedOptions || {})
      .flat()
      .reduce((sum, opt) => sum + Number(opt.preco || 0), 0);

    return (base + variation + options) * item.quantity;
  }

  // 💰 TOTAL DO CARRINHO
  const total = items.reduce(
    (sum, item) => sum + getItemTotal(item),
    0
  );

  // 🔢 CONTADOR
  const count = items.reduce(
    (sum, item) => sum + item.quantity,
    0
  );

  return (
    <CartContext.Provider
      value={{
        items,
        addItem,
        removeItem,
        updateQuantity,
        clearCart,
        total,
        count,
        getItemTotal, // opcional (útil no UI)
      }}
    >
      {children}
    </CartContext.Provider>
  );
}

export function useCart() {
  return useContext(CartContext);
}