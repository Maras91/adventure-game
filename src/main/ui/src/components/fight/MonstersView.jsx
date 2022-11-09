import React, {useState, useEffect} from 'react';
import _ from 'lodash';

function MonstersView ({updateFunction}) {
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

    useEffect(getAllMonstersName, []);

    function attackMonster(name){
        fetch('/fight',
        {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: name
        }
        ).then(response => updateFunction());
    }
    return (
       <div>
            {monsterNames.map((name) => <button  type="button" key={name} onClick={() => attackMonster(name)}>Attack {name}</button>)}
       </div>
    );

}
export default MonstersView
