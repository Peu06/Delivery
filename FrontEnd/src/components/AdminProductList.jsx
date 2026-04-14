import { useEffect, useState } from "react";
import { Pencil, Trash2, Save, X } from "lucide-react";

export function AdminProductList({ refresh }) {
  const [products, setProducts] = useState([]);
  const [editingId, setEditingId] = useState(null);
  const [editProduct, setEditProduct] = useState({});

  function loadProducts() {
    fetch("http://localhost:8080/products")
      .then((res) => res.json())
      .then((data) => setProducts(data))
      .catch((err) => console.error(err));
  }

  useEffect(() => {
    loadProducts();
  }, [refresh]);

  function handleDelete(id) {
    if (!confirm("Deseja deletar?")) return;

    fetch(`http://localhost:8080/products/${id}`, {
      method: "DELETE"
    }).then(() => loadProducts());
  }

  function handleEdit(p) {
    setEditingId(p.id);
    setEditProduct(p);
  }

  function cancelEdit() {
    setEditingId(null);
    setEditProduct({});
  }

  function handleSave() {
    fetch(`http://localhost:8080/products/${editingId}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        ...editProduct,
        preco: Number(editProduct.preco)
      })
    }).then(() => {
      setEditingId(null);
      loadProducts();
    });
  }

  return (
    <div className="bg-white rounded-xl shadow p-4">
      <h2 className="text-xl font-bold mb-4">Produtos</h2>

      <div className="space-y-3">
        {products.map((p) => (
          <div key={p.id} className="flex justify-between border p-3 rounded">
            {editingId === p.id ? (
              <div className="flex gap-2">
                <input
                  value={editProduct.nome || ""}
                  onChange={(e) =>
                    setEditProduct({ ...editProduct, nome: e.target.value })
                  }
                  className="input"
                />
                <input
                  value={editProduct.preco || ""}
                  onChange={(e) =>
                    setEditProduct({ ...editProduct, preco: e.target.value })
                  }
                  className="input"
                />
              </div>
            ) : (
              <div>
                <h3 className="font-semibold">{p.nome}</h3>
                <p>R$ {p.preco}</p>
              </div>
            )}

            <div className="flex gap-2">
              {editingId === p.id ? (
                <>
                  <button onClick={handleSave}><Save /></button>
                  <button onClick={cancelEdit}><X /></button>
                </>
              ) : (
                <>
                  <button onClick={() => handleEdit(p)}><Pencil /></button>
                  <button onClick={() => handleDelete(p.id)}><Trash2 /></button>
                </>
              )}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}