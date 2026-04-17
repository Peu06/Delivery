import { useState } from "react";

export function ProductModal({ product, onClose, onAddToCart }) {
  const [selectedVariation, setSelectedVariation] = useState(null);
  const [selectedOptions, setSelectedOptions] = useState({});
  // 🔥 VARIAÇÃO SUBSTITUI O PREÇO BASE
  function getBasePrice() {
    if (selectedVariation) {
      return Number(selectedVariation.preco || 0);
    }
    return Number(product.preco || 0);
  }

  // 🔥 OPÇÕES SOMAM NO FINAL
  function getOptionsPrice() {
    let total = 0;

    Object.values(selectedOptions).forEach((group) => {
      group.forEach((option) => {
        total += Number(option.preco || 0);
      });
    });

    return total;
  }

  function calculatePrice() {
    return getBasePrice() + getOptionsPrice();
  }

  function toggleOption(groupIndex, option) {
    setSelectedOptions((prev) => {
      const group = prev[groupIndex] || [];

      const exists = group.find((o) => o.nome === option.nome);

      let updated;

      if (exists) {
        updated = group.filter((o) => o.nome !== option.nome);
      } else {
        updated = [...group, option];
      }

      return {
        ...prev,
        [groupIndex]: updated,
      };
    });
  }

  function handleAdd() {
    onAddToCart({
      ...product,
      selectedVariation,
      selectedOptions,
    });

    onClose();
  }

  function handleVariationClick(v) {
    setSelectedVariation((prev) =>
        prev?.nome === v.nome ? null : v
    );
    }

  return (
    <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
      <div className="bg-white w-[90%] max-w-lg rounded-2xl p-5 max-h-[90vh] overflow-y-auto">

        {/* HEADER */}
        <div className="flex justify-between items-center mb-4">
          <h2 className="text-xl font-bold">{product.nome}</h2>
          <button onClick={onClose}>X</button>
        </div>

        <img
          src={product.urlImg}
          className="w-full h-40 object-cover rounded-xl mb-4"
        />

        <p className="text-gray-600 mb-4">{product.descricao}</p>

        {product.variation?.length > 0 && (
            <div className="mb-4">
                <h3 className="font-bold mb-2">Escolha o tamanho</h3>

                {product.variation.map((v) => {
                const isSelected = selectedVariation?.nome === v.nome;

                return (
                    <button
                        key={v.nome}
                        onClick={() => handleVariationClick(v)}
                        className={`block w-full text-left p-2 border rounded mb-2 transition ${
                            selectedVariation?.nome === v.nome
                            ? "bg-green-500 text-white"
                            : ""
                        }`}
                        >
                        {v.nome} — R$ {Number(v.preco).toFixed(2)}
                    </button>
                );
                })}
            </div>
            )}

        {product.groups?.map((group, groupIndex) => (
            <div key={groupIndex} className="mb-4">
                <h3 className="font-bold">
                {group.nome} ({group.minEscolhas}-{group.maxEscolhas})
                </h3>

                {group.options?.map((option) => {
                const isSelected = selectedOptions[groupIndex]?.find(
                    (o) => o.nome === option.nome
                );

                return (
                    <label
                    key={option.nome}
                    className={`flex justify-between p-2 border rounded mt-2 cursor-pointer transition ${
                        isSelected ? "bg-green-100 border-green-500" : ""
                    }`}
                    >
                    <span>
                        {option.nome} (+ R$ {Number(option.preco).toFixed(2)})
                    </span>

                    <input
                        type="checkbox"
                        checked={!!isSelected}
                        onChange={() => toggleOption(groupIndex, option)}
                    />
                    </label>
                );
                })}
            </div>
            ))}

        {/* 💰 BOTÃO FINAL */}
        <button
          onClick={handleAdd}
          className="w-full bg-green-500 text-white py-3 rounded-xl mt-4 font-bold"
        >
          Adicionar ao carrinho • R$ {calculatePrice().toFixed(2)}
        </button>
      </div>
    </div>
  );
}