import Tooltip from 'react-bootstrap/Tooltip';

function DisposableItemDescription({item}){
    return <div>
            Potion<br/>
            {Object.entries(item.itemEffects).map(([effect,value]) => <div key={effect}>{effect}:{value} </div>)}
           </div>
}

export default DisposableItemDescription