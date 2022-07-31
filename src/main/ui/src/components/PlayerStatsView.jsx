
function PlayerStatsView ({playerStats}) {
    return (
       <div>
            <p>Strength: {playerStats.strength}</p>
            <p>      HP: {playerStats.hp}/{playerStats.hpMax}</p>
            <p>   Armor: {playerStats.armor}</p>
       </div>
    );

}

export default PlayerStatsView