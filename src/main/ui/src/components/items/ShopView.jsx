import React, {useState, useEffect} from 'react';
import _ from 'lodash';
import Tooltip from 'react-bootstrap/Tooltip';
import OverlayTrigger from "react-bootstrap/OverlayTrigger";

function ShopView({updateFunction}){
    const [notDisposableItems, setNotDisposableItems] = useState(new Map())
    const [disposableItems, setDisposableItems] = useState(new Map())

    function getItems() {
        getNotDisposableItems()
        getDisposableItems()
    }

    function getNotDisposableItems() {
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' }
        }
        fetch('/getNotDisposableItems', {
                               method: 'POST',
                               headers: { 'Content-Type': 'application/json' }
                           })
            .then(response => response.json())
            .then(data => setNotDisposableItems(...notDisposableItems,data))
            .then(updateFunction())
    }

    function getDisposableItems() {
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' }
        }
        fetch('/getDisposableItems', {
                               method: 'POST',
                               headers: { 'Content-Type': 'application/json' }
                           })
            .then(response => response.json())
            .then(data => setDisposableItems(...disposableItems,data))
            .then(updateFunction())
    }

    useEffect(getItems,[])
    function buyItem(name :String){
        fetch('/buyItem', {
                             method: 'POST',
                             headers: { 'Content-Type': 'application/json' },
                             body: name
                           }).then(response => updateFunction())
    }
    function displayNotDisposableItemDescription (notDisposableItem) {
        return <Tooltip>
                {notDisposableItem.itemType}<br/>
                {Object.entries(notDisposableItem.attributes).map(([attribute,value]) => <div key={attribute}>{attribute}:+{value} </div>)}
               </Tooltip>
    }
    function displayDisposableItemDescription (disposableItem) {
            return <Tooltip>
                    Potion<br/>
                    {Object.entries(disposableItem.itemEffects).map(([effect,value]) => <div key={effect}>{effect}:+{value} </div>)}
                   </Tooltip>
        }
    return(
        <>
        <h3>Shop:</h3>
        {
            Object.entries(notDisposableItems).map(([name,item]) =>
            <li key={name}>
                <OverlayTrigger placement="top" overlay={displayNotDisposableItemDescription(item)}>
                    <span>
                        <img src="/images/buy_button.png" onClick = {() => buyItem(name)} style={{wight: 25}, {height: 25}} alt="images" />
                         {name} ${item.bayCost} gold
                    </span>
                </OverlayTrigger>
            </li>
            )
        }
        {
            Object.entries(disposableItems).map(([name,item]) =>
            <li key={name}>
                <OverlayTrigger placement="top" overlay={displayDisposableItemDescription(item)}>
                    <span>
                        <img src="/images/buy_button.png" onClick = {() => buyItem(name)} style={{wight: 25}, {height: 25}} alt="images" />
                         {name} ${item.bayCost} gold
                    </span>
                </OverlayTrigger>
            </li>
            )
        }
        </>
    )
}

export default ShopView