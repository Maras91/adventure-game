import Tooltip from 'react-bootstrap/Tooltip';
import OverlayTrigger from "react-bootstrap/OverlayTrigger";
import DisposableItemDescription from "./tooltip/DisposableItemDescription";
import NotDisposableItemDescription from "./tooltip/NotDisposableItemDescription";

function ItemsView({updateFunction, notDisposableItemsView, disposableItemsView}){
    function usedItem(itemName) {
        let request = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: itemName
        }
        fetch('/useItem',request)
        .then(response => updateFunction())
        console.log("item name", itemName)
    }
    console.log("inventory: ", notDisposableItemsView);
    return(
        <>
            <h3>Inventory:</h3>
            {
                 Object.entries(notDisposableItemsView).map(([name,item]) =>
                     <div key={name}>
                        <button type="button" onClick ={()=> usedItem(name)}>Put on</button>
                        <OverlayTrigger placement="top" overlay={<Tooltip><NotDisposableItemDescription item = {item}/></Tooltip>}>
                            <span>{name} </span>
                        </OverlayTrigger>
                         x {item.amount}
                     </div>
                 )
            }
            {
                  Object.entries(disposableItemsView).map(([name,item]) =>
                      <div key={name}>
                        <button type="button" onClick ={()=> usedItem(name)}>use</button>
                        <OverlayTrigger placement="top" overlay={<Tooltip><DisposableItemDescription item = {item} /></Tooltip>}>
                            <span>{name} </span>
                        </OverlayTrigger>
                        x {item.amount}
                      </div>
                  )
            }
        </>
    )
}

export default ItemsView