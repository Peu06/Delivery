import { Link } from 'react-router-dom';
import { ShoppingCart, House, CircleUserRound } from 'lucide-react';
import { useCart } from '../context/CartContext';

export function Navbar() {
  const { count } = useCart();

  return (
    <nav className='bg-zinc-800 flex gap-15 justify-center content-center p-1.5'>
      
      <Link to="/" className='bg-zinc-100 rounded-3xl p-2.5'>
        <House />
      </Link>

      <Link to="/cart" className='bg-zinc-100 rounded-3xl p-2.5 relative'>
        <ShoppingCart />
        
        {count > 0 && (
          <span className="absolute -top-1 -right-1 bg-red-500 text-white text-xs rounded-full w-5 h-5 flex items-center justify-center font-bold">
            {count}
          </span>
        )}
      </Link>

      <Link to="/login" className='bg-zinc-100 rounded-3xl p-2.5'>
        <CircleUserRound />
      </Link>
    
    </nav>
  );
}