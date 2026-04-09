import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from './pages/Home';
import Dashboard from './pages/dashboard/Dashboard';
import Pedidos from './pages/dashboard/Pedidos';
import Estoque from './pages/dashboard/Estoque';
import Financeiro from './pages/dashboard/Financeiro';
import Clientes from './pages/dashboard/Clientes';
import Configuracoes from './pages/dashboard/Configuracoes';
import { ProtectedRoute } from './components/ProtectedRoute';
import LoginSistema from './pages/LoginSistema';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<LoginSistema />} />

        <Route 
          path="/dashboard" 
          element={
            <ProtectedRoute>
              <Dashboard />
            </ProtectedRoute>
          }
        >
          <Route index element={<div>Início</div>} />
          <Route path="pedidos" element={<Pedidos />} />
          <Route path="estoque" element={<Estoque />} />
          <Route path="financeiro" element={<Financeiro />} />
          <Route path="clientes" element={<Clientes />} />
          <Route path="configuracoes" element={<Configuracoes />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;