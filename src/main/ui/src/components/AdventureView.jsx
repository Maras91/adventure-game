import React, {useState, useEffect} from 'react';
import PlayerStatsView from './fight/PlayerStatsView';
import ExperienceView from './level/ExperienceView';
import ResourcesView from './fight/ResourcesView';
import IncreaseStats from './level/IncreaseStatsView';
import MonstersView from './fight/MonstersView';
import ItemsView from './items/ItemsView';
import UsingItemsView from './items/UsingItemsView';
import _ from 'lodash';

function AdventureView(){
    const [stats,setStats] = useState(
    {
       fightStatsView: new Map(),
       resourcesView: new Map(),
       disposableItemsView: new Map(),
       notDisposableItemsView: new Map(),
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
    console.log("adventure view stats: ", stats.fightStatsView);
    return (
        <>
            <ResourcesView className="col-md-3" resources={stats.resourcesView} />
            <div className="row">
                <div className="col-md-3 border">
                    <PlayerStatsView playerStats={stats.fightStatsView}/>
                </div>
                <div className="col-md-2 border">
                    <UsingItemsView />
                </div>
                <div className="col-md-3 border">
                    <ExperienceView experienceView={stats.experienceView} />
                    <IncreaseStats updateFunction={getStatsData} levelUpPoints={stats.levelUpPoints} />
                </div>
            </div>
            <div className="col-md-4">
                <ItemsView updateFunction={getStatsData} notDisposableItemsView={stats.notDisposableItemsView} disposableItemsView={stats.disposableItemsView} />
            </div>
            <MonstersView updateFunction={getStatsData} />
        </>
    );

}

export default AdventureView