import React, {useState, useEffect} from 'react';
import ShopView from './ShopView'
import InventoryView from './InventoryView'
import _ from 'lodash';

function ItemsView({updateFunction, inventory}){
    console.log("item view inventory: ", inventory);
    return(
        <div className="row">
            <div className="col-md-6 border">
                <ShopView updateFunction={updateFunction} />
            </div>
            <div className="col-md-6 border">
                <InventoryView updateFunction={updateFunction} inventory={inventory} />
            </div>
        </div>
    )
}

export default ItemsView