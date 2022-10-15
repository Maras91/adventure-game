import React, {useState, useEffect} from 'react';
import _ from 'lodash';

function ItemsView({updateFunction}){
    const [items, setItems] = useState(new Map())
    function getItems() {
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' }
        }
        fetch('/getAllItems', {
                               method: 'POST',
                               headers: { 'Content-Type': 'application/json' }
                           })
            .then(response => response.json())
            .then(data => setItems(...items,data))
            .then(updateFunction())
    }
    useEffect(getItems,[])
    function displayName(name :String){
        fetch('/buyItem', {
                             method: 'POST',
                             headers: { 'Content-Type': 'application/json' },
                             body: name
                           }).then(response => updateFunction())
    }
    console.log(items)
    return(
        <>
        <h3>Shop:</h3>
        {
            Object.entries(items).map(([name,item]) =>
            <p key={name}>
                <img src="/images/buy_button.png" onClick = {() => displayName(name)} style={{wight: 25}, {height: 25}} alt="images" />
                 {name} ${item.bayCost} gold
            </p>)
        }
        </>
    )
}

export default ItemsView