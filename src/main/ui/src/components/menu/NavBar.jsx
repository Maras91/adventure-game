import City from './City';
import Dungeon from './dungeon/Dungeon'
import {Route, Routes, Link} from 'react-router-dom'
export default function NavBar({updateFunction}) {
    return (
        <>
            <nav className="navbar navbar-expand-lg navbar-light bg-light col-md-10">
                <Link className="navbar-brand" to="/city">CITY</Link>
                <br />
                <Link className="navbar-brand" to="/dungeon">DUNGEON</Link>
            </nav>
            <Routes>
                <Route path='/' element={<City updateFunction = {updateFunction} />} />
                <Route path='/city' element={<City updateFunction = {updateFunction} />} />
                <Route path='/dungeon' element={<Dungeon updateFunction = {updateFunction} />} />
            </Routes>
        </>
    )
}