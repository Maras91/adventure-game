import City from './City';
import Dungeon from './Dungeon'
import {Route, Routes, Link} from 'react-router-dom'
export default function NavBar() {
    return (
        <>
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <Link className="navbar-brand" to="/city">CITY</Link>
                <br />
                <Link className="navbar-brand" to="/dungeon">DUNGEON</Link>
            </nav>
            <Routes>
                <Route path='/' element={<City />} />
                <Route path='/city' element={<City />} />
                <Route path='/dungeon' element={<Dungeon />} />
            </Routes>
        </>
    )
}