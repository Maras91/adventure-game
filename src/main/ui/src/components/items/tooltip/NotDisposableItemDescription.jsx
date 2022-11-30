import Tooltip from 'react-bootstrap/Tooltip';
function NotDisposableItemDescription({item}){
    return <div>
               {item.itemType}<br/>
               {Object.entries(item.attributes).map(([attribute,value]) => <div key={attribute}>{attribute}:+{value} </div>)}
           </div>
}

export default NotDisposableItemDescription