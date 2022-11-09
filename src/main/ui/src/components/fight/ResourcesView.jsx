import React, {useState} from 'react';

function ResourcesView ({resources}) {
    return (
       <div>
            <p>
                Gold: {resources.gold} |
                Food: {resources.food} |
                Iron: {resources.iron}
            </p>
       </div>
    );

}

export default ResourcesView