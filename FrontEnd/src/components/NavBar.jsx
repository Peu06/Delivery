import { Link } from 'react-router-dom';
import { ShoppingCart } from 'lucide-react';

export function Navbar() {
  return (
    <nav className='bg-zinc-800 flex gap-15 justify-center content-center p-1.5'>
      <Link to="/" className='bg-zinc-100 rounded-3xl p-2.5'>Início</Link>
      <Link to="/login" className='bg-zinc-100 rounded-3xl p-2.5'>Login</Link>
      <Link to="/" className='bg-zinc-100 rounded-3xl p-2.5'> <ShoppingCart/></Link>
    </nav>
  );
}