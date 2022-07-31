function ActionsView ({updateFunction}) {
    const requestFightOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            "fightStats": {
                "strength": 5,
                "hp": 25,
                "hpMax":25,
                "armor": 3
              },
              "resources": {
                "gold": 4,
                "iron": 1,
                "meat": 6
              },
              "experience" : 500
          })
    };
    const attackGoblin = () => {
        fetch('/fight', requestFightOptions).then(response => updateFunction());
    }

    function delay(time) {
      return new Promise(resolve => setTimeout(resolve, time));
    }

    const drinkPotion = () => {
        fetch('/getPotion',
        {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' }
        }).then(response => updateFunction());
    }
    return (
       <div>
           <button type="button" onClick={attackGoblin}>Kill Goblin</button>
           <button type="button" onClick={drinkPotion}>Drink Potion</button>
       </div>
    );

}
export default ActionsView
