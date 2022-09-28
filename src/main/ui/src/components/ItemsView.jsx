import React, {useState, useEffect} from 'react';
import _ from 'lodash';

function ItemsView({updateFunction}){
    const [items, setItems] = useState([])
    function getItems() {
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' }
        }
        fetch('/getItems', {
                               method: 'POST',
                               headers: { 'Content-Type': 'application/json' }
                           })
            .then(response => response.json())
            .then(data => setItems(data))
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

    return(
        <>
        <h3>Shop:</h3>
        {
            items.map((name) =>
            <p key={name}>
                <img src="/images/buy_button.png" onClick = {() => displayName(name)} style={{wight: 25}, {height: 25}} alt="images" /> {name}
            </p>)
        }
        </>
    )
}

export default ItemsView