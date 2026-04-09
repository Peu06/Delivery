import { useState } from "react";
import { useNavigate } from "react-router-dom";

export function Login() {
  const [email, setEmail] = useState("");
  const [senha, setSenha] = useState("");
  const navigate = useNavigate();

  const handleLogin = (e) => {
    e.preventDefault(); // evita recarregar a página

    // Aqui você pode adicionar validação de email/senha
    if (email && senha) {
      localStorage.setItem("isLoggedIn", "true"); // marca como logado
      navigate("/dashboard"); // redireciona para o Dashboard
    } else {
      alert("Preencha email e senha!");
    }
  };

  return (
    <div className="bg-zinc-900 h-screen flex items-center justify-center">
      <form
        onSubmit={handleLogin} // chama handleLogin ao enviar
        className="bg-zinc-800 p-8 rounded-2xl shadow-lg w-80 flex flex-col gap-4"
      >
        <h1 className="text-white text-2xl font-bold text-center">Login</h1>

        <input
          type="email"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          className="p-2 rounded bg-zinc-700 text-white placeholder-zinc-400 outline-none focus:ring-2 focus:ring-blue-500"
        />

        <input
          type="password"
          placeholder="Senha"
          value={senha}
          onChange={(e) => setSenha(e.target.value)}
          className="p-2 rounded bg-zinc-700 text-white placeholder-zinc-400 outline-none focus:ring-2 focus:ring-blue-500"
        />

        <button className="bg-blue-500 hover:bg-blue-600 transition p-2 rounded text-white font-semibold">
          Entrar
        </button>
      </form>
    </div>
  );
}