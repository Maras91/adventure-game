import React, {useState} from 'react';

function ResourcesView ({resources}) {
    return (
       <div>
            <p>Gold: {resources.gold}</p>
            <p>Meat: {resources.food}</p>
            <p>Iron: {resources.iron}</p>
       </div>
    );

}

export default ResourcesView