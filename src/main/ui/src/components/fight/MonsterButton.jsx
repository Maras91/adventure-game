import React, {useState, useEffect} from 'react';
import _ from 'lodash';

function MonstersButton ({updateFunction, monsterName}) {

    function attackMonster(){
        fetch('/fight',
        {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: monsterName
        }
        ).then(response => updateFunction());
    }
    return (
       <>
            <button  type="button" onClick={() => attackMonster()}>Attack {monsterName}</button>
       </>
    );

}
export default MonstersView
