
function PlayerStatsView ({playerStats}) {
    return (
       <div className="row">
            <div className="col-md-4">
                <img className ="rounded mx-auto d-block" src="/images/stats/strength.png" style={{wight: 70}, {height: 70}} alt="images" />
                <p className ="text-center display-6">{playerStats.strength}</p>
            </div>
            <div className="col-md-4">
                <img className ="rounded mx-auto d-block" src="/images/stats/hp.png" style={{wight: 70}, {height: 70}} alt="images" />
                <p className ="text-center display-6">{playerStats.currentHp}/{playerStats.hp}</p>
            </div>
            <div className="col-md-4">
                <img className ="rounded mx-auto d-block" src="/images/stats/armor.png" style={{wight: 70}, {height: 70}} alt="images" />
                <p className ="text-center display-6">{playerStats.armor}</p>
            </div>
       </div>
    );

}

export default PlayerStatsView