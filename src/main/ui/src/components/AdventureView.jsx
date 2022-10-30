import React, {useState, useEffect} from 'react';
import PlayerStatsView from './PlayerStatsView';
import ExperienceView from './ExperienceView';
import ResourcesView from './ResourcesView';
import IncreaseStats from './IncreaseStatsView';
import ActionsView from './ActionsView';
import ItemsView from './ItemsView'
import _ from 'lodash';

function AdventureView(){
    const [stats,setStats] = useState(
    {
       fightStatsView: {
           strength: null,
           armor: null,
           hp: null,
           currentHp: null
       },
       resourcesView: {
           gold: null,
           iron: null,
           food: null
       },
       experienceView: {
           value: null,
           nextLevelExp: null,
           level: null
       },
       levelUpPoints: null
   });
   const getStatsData = ()=> {
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify()
        };
        fetch('/adventureStats', requestOptions)
            .then(response => response.json())
            .then(data => {
            setStats(_.cloneDeep(data));
            })
            .catch(error => {
                          this.setState({ errorMessage: error.toString() });
                          console.error('There was an error!', error);
                      });

   }
    useEffect(() => {
        getStatsData();
    }, []);
    console.log("render", stats);
    return (
        <div className="row">
        	<ResourcesView className="col-md-3 border" resources={stats.resourcesView} />
            <div className="col-md-3 border">
                <ExperienceView experienceView={stats.experienceView} />
                <PlayerStatsView playerStats={stats.fightStatsView}/>
            </div>
            <div className="col-md-3 border">
                <IncreaseStats updateFunction={getStatsData} levelUpPoints={stats.levelUpPoints} />
            </div>
            <div className="col-md-3 border">
                <ItemsView updateFunction={getStatsData} />
            </div>
            <ActionsView updateFunction={getStatsData} />
        </div>
    );

}

export default AdventureView