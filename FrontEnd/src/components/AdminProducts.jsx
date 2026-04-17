import { useState } from "react";

export function AdminProducts({ onCreated }) {
  const [product, setProduct] = useState({
    nome: "",
    descricao: "",
    preco: "",
    urlImg: "",
    ativo: true,
    variation: [],
    groups: []
  });

  function handleChange(e) {
    const { name, value } = e.target;

    setProduct((prev) => ({
      ...prev,
      [name]: value
    }));
  }

  // =========================
  // VARIAÇÕES
  // =========================
  function addVariation() {
    setProduct((prev) => ({
      ...prev,
      variation: [
        ...prev.variation,
        { nome: "", preco: "" }
      ]
    }));
  }

  function updateVariation(index, field, value) {
    setProduct((prev) => {
      const list = [...prev.variation];
      list[index] = {
        ...list[index],
        [field]: value
      };

      return { ...prev, variation: list };
    });
  }

  // =========================
  // GRUPOS
  // =========================
  function addGroup() {
    setProduct((prev) => ({
      ...prev,
      groups: [
        ...prev.groups,
        {
          nome: "",
          minEscolhas: 0,
          maxEscolhas: 1,
          options: []
        }
      ]
    }));
  }

  function updateGroup(index, field, value) {
    setProduct((prev) => {
      const list = [...prev.groups];

      list[index] = {
        ...list[index],
        [field]: value
      };

      return { ...prev, groups: list };
    });
  }

  // =========================
  // OPTIONS
  // =========================
  function addOption(groupIndex) {
    setProduct((prev) => {
      const list = [...prev.groups];

      list[groupIndex].options = [
        ...list[groupIndex].options,
        { nome: "", preco: "" }
      ];

      return { ...prev, groups: list };
    });
  }

  function updateOption(groupIndex, optionIndex, field, value) {
    setProduct((prev) => {
      const list = [...prev.groups];

      list[groupIndex].options[optionIndex] = {
        ...list[groupIndex].options[optionIndex],
        [field]: value
      };

      return { ...prev, groups: list };
    });
  }

  // =========================
  // SUBMIT
  // =========================
  function handleSubmit(e) {
    e.preventDefault();

    const formatted = {
      ...product,
      preco: Number(product.preco),

      variation: product.variation.map((v) => ({
        nome: v.nome,
        preco: Number(v.preco)
      })),

      groups: product.groups.map((g) => ({
        nome: g.nome,
        minEscolhas: Number(g.minEscolhas),
        maxEscolhas: Number(g.maxEscolhas),

        options: g.options.map((o) => ({
          nome: o.nome,
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
      .then(() => {
        alert("Produto criado com sucesso!");

        setProduct({
          nome: "",
          descricao: "",
          preco: "",
          urlImg: "",
          ativo: true,
          variation: [],
          groups: []
        });

        if (onCreated) onCreated();
      })
      .catch((err) => console.error(err));
  }

  // =========================
  // UI
  // =========================
  return (
    <div className="flex justify-center mb-8">
      <form
        onSubmit={handleSubmit}
        className="bg-white shadow-lg rounded-2xl p-6 w-full max-w-3xl space-y-6"
      >
        <h1 className="text-2xl font-bold text-center text-black">
          Criar Produto
        </h1>

        {/* CAMPOS PRINCIPAIS */}
        <div className="grid gap-3">
          <input name="nome" placeholder="Nome" onChange={handleChange} className="input" />
          <input name="descricao" placeholder="Descrição" onChange={handleChange} className="input" />
          <input name="preco" placeholder="Preço" onChange={handleChange} className="input" />
          <input name="urlImg" placeholder="URL da imagem" onChange={handleChange} className="input" />
        </div>

        {/* =========================
            VARIAÇÕES
        ========================= */}
        <div>
          <h2 className="font-semibold">Variações</h2>

          {product.variation.map((v, i) => (
            <div key={i} className="flex gap-2 mb-2">
              <input
                placeholder="Nome"
                value={v.nome}
                onChange={(e) =>
                  updateVariation(i, "nome", e.target.value)
                }
                className="input"
              />

              <input
                placeholder="Preço"
                value={v.preco}
                onChange={(e) =>
                  updateVariation(i, "preco", e.target.value)
                }
                className="input"
              />
            </div>
          ))}

          <button
            type="button"
            onClick={addVariation}
            className="btn-secondary"
          >
            + Variação
          </button>
        </div>

        {/* =========================
            GRUPOS
        ========================= */}
        <div>
          <h2 className="font-semibold">Grupos</h2>

          {product.groups.map((g, gi) => (
            <div key={gi} className="border p-3 rounded mb-3 bg-gray-50">
              <input
                placeholder="Nome grupo"
                value={g.nome}
                onChange={(e) =>
                  updateGroup(gi, "nome", e.target.value)
                }
                className="input mb-2"
              />

              <div className="flex gap-2 mb-2">
                <input
                  placeholder="Min"
                  value={g.minEscolhas}
                  onChange={(e) =>
                    updateGroup(gi, "minEscolhas", e.target.value)
                  }
                  className="input"
                />

                <input
                  placeholder="Max"
                  value={g.maxEscolhas}
                  onChange={(e) =>
                    updateGroup(gi, "maxEscolhas", e.target.value)
                  }
                  className="input"
                />
              </div>

              <h3 className="font-medium">Opções</h3>

              {g.options.map((o, oi) => (
                <div key={oi} className="flex gap-2 mb-2">
                  <input
                    placeholder="Nome"
                    value={o.nome}
                    onChange={(e) =>
                      updateOption(gi, oi, "nome", e.target.value)
                    }
                    className="input"
                  />

                  <input
                    placeholder="Preço"
                    value={o.preco}
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

          <button
            type="button"
            onClick={addGroup}
            className="btn-secondary"
          >
            + Grupo
          </button>
        </div>

        {/* SUBMIT */}
        <button className="w-full bg-green-500 text-white py-2 rounded-lg">
          Salvar Produto
        </button>
      </form>
    </div>
  );
}