function ItemsView({updateFunction, inventory}){
    function putItem(itemName) {
        let request = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: itemName
        }
        fetch('/putOnItem',request)
        .then(response => updateFunction())
        console.log("item name", itemName)
    }
    console.log("inventory: ", inventory);
    return(
        <>
          <h3>Inventory:</h3>
                 {
                     Object.entries(inventory).map(([name,item]) =>
                         <p key={name}>
                            <button type="button" onClick ={()=> putItem(name)}>Put on</button>{name} x {item.amount}
                         </p>
                     )
                 }
        </>
    )
}

export default ItemsView