function killGoblin() {
    // Creating a XHR object
    let xhr = new XMLHttpRequest();
    let url = "fight";

    // open a connection
    xhr.open("POST", url, true);

    // Set the request header i.e. which type of content you are sending
    xhr.setRequestHeader("Content-Type", "application/json");

    // Create a state change callback
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var response = JSON.parse(this.responseText);
            document.getElementById("hp").innerHTML = response.fightStatsJsonObject.hp;
            document.getElementById("experience").innerHTML = response.fightStatsJsonObject.experience;
            document.getElementById("amountOfGold").innerHTML = response.resourcesJsonObject.gold;
            document.getElementById("amountOfIron").innerHTML = response.resourcesJsonObject.iron;
            document.getElementById("amountOfMeat").innerHTML = response.resourcesJsonObject.meat;
        }
    };
    // Converting JSON data to string
    var data = JSON.stringify({
                              	"fightStats": {
                                		"strength": 5,
                                		"hp": 25,
                                		"armor": 3,
                                		"experience" : 100
                                	  },
                                	  "resources": {
                                		"gold": 4,
                                		"iron": 1,
                                		"meat": 6
                                	  }
                                });

    // Sending data with the request
     xhr.send(data);
}

function getPotion() {
       let xhr = new XMLHttpRequest();
        let url = "getPotion";

        // open a connection
        xhr.open("POST", url, true);

        // Set the request header i.e. which type of content you are sending
        xhr.setRequestHeader("Content-Type", "application/json");

        // Create a state change callback
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var response = JSON.parse(this.responseText);
                document.getElementById("hp").innerHTML = response.fightStatsJsonObject.hp;
                document.getElementById("amountOfGold").innerHTML = response.resourcesJsonObject.gold;
                document.getElementById("amountOfIron").innerHTML = response.resourcesJsonObject.iron;
                document.getElementById("amountOfMeat").innerHTML = response.resourcesJsonObject.meat;
            }
        };

         xhr.send();

}