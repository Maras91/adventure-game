function ItemsView({updateFunction, notDisposableItemsView, disposableItemsView}){
    function usedItem(itemName) {
        let request = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: itemName
        }
        fetch('/useItem',request)
        .then(response => updateFunction())
        console.log("item name", itemName)
    }
    console.log("inventory: ", notDisposableItemsView);
    return(
        <>
          <h3>Inventory:</h3>
                 {
                     Object.entries(notDisposableItemsView).map(([name,item]) =>
                         <p key={name}>
                            <button type="button" onClick ={()=> usedItem(name)}>Put on</button>{name} x {item.amount}
                         </p>
                     )
                 }
                 {
                      Object.entries(disposableItemsView).map(([name,item]) =>
                          <p key={name}>
                             <button type="button" onClick ={()=> usedItem(name)}>use</button>{name} x {item.amount}
                          </p>
                      )
                 }
        </>
    )
}

export default ItemsView