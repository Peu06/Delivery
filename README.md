# 🛒 Delivery_v1

Sistema de e-commerce/delivery desenvolvido como projeto acadêmico, com frontend em **React + Vite + Tailwind** e backend em **Java Spring Boot**.

---

## 🚀 Funcionalidades

### 👨‍💻 Admin (Painel de configuração)

- Criar produtos
- Editar produtos
- Excluir produtos
- Gerenciar:
  - Variações (ex: P, M, G)
  - Grupos de opções (ex: extras, molhos)
  - Opções dentro dos grupos

---

### 🛍️ Produtos

- Nome
- Descrição
- Preço base
- Imagem
- Status ativo/inativo

---

## 🧱 Tecnologias

### Frontend

- React
- Vite
- Tailwind CSS
- Lucide Icons
- Fetch API

### Backend

- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL / PostgreSQL

---

## ⚙️ Como rodar o projeto

### ▶️ Backend (Spring Boot)

- Abra o projeto em sua IDE (IntelliJ, Eclipse ou VS Code)
- Execute a classe principal:

ApiDeliveryApplication.java

---

### ▶️ Frontend (React + Vite)

- cd FrontEnd
- pnpm install
- pnpm run dev

---

## 🔗 Endpoints principais

- GET /products → Listar produtos
- POST /products → Criar produto
- PUT /products/{id} → Editar produto
- DELETE /products/{id} → Remover produto

---

## 📤 Exemplo de JSON (Produto)

```bash
{
  "nome": "X-Burger",
  "descricao": "Hamburguer artesanal com queijo e bacon",
  "preco": 20.00,
  "urlImg": "https://img.com/xburger.png",
  "ativo": true,
  "variation": [
    {
      "nome": "Pequeno",
      "preco": 18.00
    },
    {
      "nome": "Grande",
      "preco": 25.00
    }
  ],
  "groups": [
    {
      "nome": "Extras",
      "minEscolhas": 0,
      "maxEscolhas": 2,
      "options": [
        {
          "nome": "Queijo",
          "preco": 2.00
        },
        {
          "nome": "Bacon",
          "preco": 3.50
        }
      ]
    }
  ]
}
```

---

## 🔥 Principais desafios resolvidos

- Integração Frontend + Backend via REST API
- Problemas de CORS
- Serialização de objetos complexos (JPA + JSON)
- MultipleBagFetchException no Hibernate (resolvido com uso de Set)
- Relacionamentos bidirecionais com @JsonManagedReference / @JsonBackReference
- Atualização de entidades com listas aninhadas
- Estrutura de produtos com variações e grupos de opções

---

## 📌 Melhorias futuras

- 🔐 Autenticação JWT
- 🛒 Carrinho de compras
- 💳 Pagamentos (PIX / Mercado Pago)
- 📦 Sistema de pedidos
- 📊 Dashboard com métricas
- 📱 Versão mobile (PWA)

---

## 📄 Licença

- Projeto acadêmico — sem licença comercial
