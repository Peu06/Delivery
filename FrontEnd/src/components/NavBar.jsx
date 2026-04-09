import { Link } from 'react-router-dom';

export function Navbar() {
  return (
    <nav>
      <Link to="/">Início</Link>
      <Link to="/login">Login</Link>
    </nav>
  );
}