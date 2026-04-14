import { useState } from "react";

export function AdminProducts({ onCreated }) {
  const [product, setProduct] = useState({
    nome: "",
    descricao: "",
    preco: "",
    urlImg: "",
    ativo: true,
    variations: [],
    groups: []
  });

  function handleChange(e) {
    const { name, value } = e.target;
    setProduct({ ...product, [name]: value });
  }

  // VARIAÇÕES
  function addVariation() {
    setProduct({
      ...product,
      variations: [...product.variations, { nome: "", preco: "" }]
    });
  }

  function updateVariation(index, field, value) {
    const list = [...product.variations];
    list[index][field] = value;
    setProduct({ ...product, variations: list });
  }

  // GRUPOS
  function addGroup() {
    setProduct({
      ...product,
      groups: [
        ...product.groups,
        { nome: "", minEscolhas: 0, maxEscolhas: 1, options: [] }
      ]
    });
  }

  function updateGroup(index, field, value) {
    const list = [...product.groups];
    list[index][field] = value;
    setProduct({ ...product, groups: list });
  }

  // OPTIONS
  function addOption(groupIndex) {
    const list = [...product.groups];
    list[groupIndex].options.push({ nome: "", preco: "" });
    setProduct({ ...product, groups: list });
  }

  function updateOption(groupIndex, optionIndex, field, value) {
    const list = [...product.groups];
    list[groupIndex].options[optionIndex][field] = value;
    setProduct({ ...product, groups: list });
  }

  function handleSubmit(e) {
    e.preventDefault();

    const formatted = {
      ...product,
      preco: Number(product.preco),
      variations: product.variations.map(v => ({
        ...v,
        preco: Number(v.preco)
      })),
      groups: product.groups.map(g => ({
        ...g,
        minEscolhas: Number(g.minEscolhas),
        maxEscolhas: Number(g.maxEscolhas),
        options: g.options.map(o => ({
          ...o,
          preco: Number(o.preco)
        }))
      }))
    };

    fetch("http://localhost:8080/products", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(formatted)
    })
      .then((res) => res.json())
      .then((data) => {
        alert("Produto criado com sucesso!");

        setProduct({
          nome: "",
          descricao: "",
          preco: "",
          urlImg: "",
          ativo: true,
          variations: [],
          groups: []
        });

        if (onCreated) onCreated(); // 🔥 atualiza lista
      })
      .catch((err) => console.error(err));
  }

  return (
    <div className="flex justify-center mb-8">
      <form
        onSubmit={handleSubmit}
        className="bg-white shadow-lg rounded-2xl p-6 w-full max-w-3xl space-y-6"
      >
        <h1 className="text-2xl font-bold text-center !text-black">
          Criar Produto
        </h1>

        <div className="grid gap-3">
          <input name="nome" placeholder="Nome" onChange={handleChange} className="input" />
          <input name="descricao" placeholder="Descrição" onChange={handleChange} className="input" />
          <input name="preco" placeholder="Preço" onChange={handleChange} className="input" />
          <input name="urlImg" placeholder="URL da imagem" onChange={handleChange} className="input" />
        </div>

        {/* VARIAÇÕES */}
        <div>
          <h2 className="font-semibold">Variações</h2>

          {product.variations.map((v, i) => (
            <div key={i} className="flex gap-2 mb-2">
              <input
                placeholder="Nome"
                onChange={(e) => updateVariation(i, "nome", e.target.value)}
                className="input"
              />
              <input
                placeholder="Preço"
                onChange={(e) => updateVariation(i, "preco", e.target.value)}
                className="input"
              />
            </div>
          ))}

          <button type="button" onClick={addVariation} className="btn-secondary">
            + Variação
          </button>
        </div>

        {/* GRUPOS */}
        <div>
          <h2 className="font-semibold">Grupos</h2>

          {product.groups.map((g, gi) => (
            <div key={gi} className="border p-3 rounded mb-3 bg-gray-50">
              <input
                placeholder="Nome grupo"
                onChange={(e) => updateGroup(gi, "nome", e.target.value)}
                className="input mb-2"
              />

              <div className="flex gap-2 mb-2">
                <input
                  placeholder="Min"
                  onChange={(e) => updateGroup(gi, "minEscolhas", e.target.value)}
                  className="input"
                />
                <input
                  placeholder="Max"
                  onChange={(e) => updateGroup(gi, "maxEscolhas", e.target.value)}
                  className="input"
                />
              </div>

              <h3>Opções</h3>

              {g.options.map((o, oi) => (
                <div key={oi} className="flex gap-2 mb-2">
                  <input
                    placeholder="Nome"
                    onChange={(e) =>
                      updateOption(gi, oi, "nome", e.target.value)
                    }
                    className="input"
                  />
                  <input
                    placeholder="Preço"
                    onChange={(e) =>
                      updateOption(gi, oi, "preco", e.target.value)
                    }
                    className="input"
                  />
                </div>
              ))}

              <button
                type="button"
                onClick={() => addOption(gi)}
                className="btn-secondary"
              >
                + Opção
              </button>
            </div>
          ))}

          <button type="button" onClick={addGroup} className="btn-secondary">
            + Grupo
          </button>
        </div>

        <button className="w-full bg-green-500 text-white py-2 rounded-lg">
          Salvar Produto
        </button>
      </form>
    </div>
  );
}