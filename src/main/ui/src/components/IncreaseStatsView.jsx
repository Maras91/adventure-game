import React, {useState, useEffect} from 'react';
import _ from 'lodash'

function IncreaseStats ({updateFunction, levelUpPoints}) {
    const [stats,setStats] = useState({
        pointsToSpend: 0,
        hp: 0,
        armor: 0,
        strength: 0
    });

    let varStats = _.cloneDeep(stats);
         useEffect(() => {
                varStats.pointsToSpend = levelUpPoints;
                setStats(varStats);
            }, [levelUpPoints]);
    function changeStrength(operation: String) {
        if (operation == "+" && varStats.pointsToSpend>0) {
            varStats.strength++;
            varStats.pointsToSpend--;
        } else if (operation == "-" && varStats.strength>0) {
            varStats.strength--;
            varStats.pointsToSpend++;
        }
        setStats(varStats);
    }
    function changeHp(operation: String) {
        if (operation == "+" && stats.pointsToSpend>0) {
            varStats.hp = varStats.hp+10;
            varStats.pointsToSpend--;
        } else if (operation == "-" && stats.hp>0) {
            varStats.hp = varStats.hp-10;
            varStats.pointsToSpend++;
        }
        setStats(varStats);
    }
    function changeArmor(operation: String) {
        if (operation == "+" && stats.pointsToSpend>0) {
            varStats.armor++;
            varStats.pointsToSpend--;
        } else if (operation == "-" && stats.armor>0) {
             varStats.armor--;
             varStats.pointsToSpend++;
        }
        setStats(varStats);
    }
    function increaseStats(){
        let requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({"strength" : stats.strength,
                                        "hp" : stats.hp,
                                     "armor" : stats.armor})
        };
        fetch('/levelUp', requestOptions)
            .then(response => {
                updateFunction();
                varStats.strength=0;
                varStats.hp=0;
                varStats.armor=0;
                setStats(varStats);
            });
    }
    return (
        <>

            <p>Points: <span id="levelUpPoints">{stats.pointsToSpend}</span></p>
            <p>
                Strength + <span>{stats.strength}</span>
                <button onClick={() => changeStrength("+")}>+</button>
                <button onClick={() => changeStrength("-")}>-</button>
            </p>
            <p>
                &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;HP + <span>{stats.hp}</span>
                <button onClick={() => changeHp("+")}>+</button>
                <button onClick={() => changeHp("-")}>-</button></p>
            <p>
                &#160;&#160;&#160;&#160;Armor + <span>{stats.armor}</span>
                <button onClick={() => changeArmor("+")}>+</button>
                <button onClick={() => changeArmor("-")}>-</button>
            </p>
            <button onClick={() => increaseStats()}>Level Up!</button>
        </>
    );

}
export default IncreaseStats