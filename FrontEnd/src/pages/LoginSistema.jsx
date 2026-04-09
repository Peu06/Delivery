import { Login } from '../components/Login';

const handleLogin = () => {
  localStorage.setItem("isLoggedIn", "true"); // marca como logado
  navigate("/dashboard"); // redireciona para dashboard
};

export default function LoginSistema() {
  return (
    <Login />

  )
}