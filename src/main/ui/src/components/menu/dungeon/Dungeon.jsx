//import MonstersView from '../../fight/MonstersView';
import DungeonMap from './DungeonMap'

export default function dungeon( {updateFunction} ) {
    return (
    <div>
        <DungeonMap updateFunction={updateFunction}/>
{/*         <MonstersView updateFunction={updateFunction} /> */}
    </div>
    )
}