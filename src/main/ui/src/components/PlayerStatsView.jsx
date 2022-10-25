
function PlayerStatsView ({playerStats}) {
    return (
       <div>
            <p>Strength: {playerStats.strength}</p>
            <p>      HP: {playerStats.currentHp}/{playerStats.hp}</p>
            <p>   Armor: {playerStats.armor}</p>
       </div>
    );

}

export default PlayerStatsView