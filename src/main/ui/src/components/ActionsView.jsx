import React, {useState, useEffect} from 'react';
import _ from 'lodash';

function ActionsView ({updateFunction}) {
    const [monsterNames, setMonsterNames] = useState([])

    function getAllMonstersName() {
        fetch('/allMonsters',
        {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' }
        }).then(response => response.json()).then(data => {
            setMonsterNames(data);
        });
    }

    useEffect(() => {
        getAllMonstersName();
    }, []);

    const attackMonster = (name) => {
        fetch('/fight',
        {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: name
        }
        ).then(response => updateFunction());
        console.log("you are attacked ",name);
    }
    const drinkPotion = () => {
        fetch('/getPotion',
        {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' }
        }).then(response => updateFunction());
    }
    console.log("actions render",monsterNames);
    return (
       <div>
            {monsterNames.map((name) => <button  type="button" key={name} onClick={() => attackMonster(name)}>Kill {name}</button>)}
            <br />
           <button type="button" onClick={drinkPotion}>Drink Potion</button>
       </div>
    );

}
export default ActionsView
