import React, {useState, useEffect} from 'react';
import _ from 'lodash';
import Tooltip from 'react-bootstrap/Tooltip';
import OverlayTrigger from "react-bootstrap/OverlayTrigger";
import DisposableItemDescription from "./tooltip/DisposableItemDescription";
import NotDisposableItemDescription from "./tooltip/NotDisposableItemDescription";

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
    return(
        <>
        <h3>Shop:</h3>
        {
            Object.entries(notDisposableItems).map(([name,item]) =>
            <li key={name}>
                <OverlayTrigger placement="top" overlay={<Tooltip><NotDisposableItemDescription item = {item} /></Tooltip>}>
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
                <OverlayTrigger placement="top" overlay={<Tooltip><DisposableItemDescription item = {item} /></Tooltip>}>
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