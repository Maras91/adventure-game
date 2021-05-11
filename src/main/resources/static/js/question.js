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
            document.getElementById("strength").innerHTML = response.fightStatsView.strength;
            document.getElementById("hp").innerHTML = response.fightStatsView.hp;
            document.getElementById("hpMax").innerHTML = response.fightStatsView.hpMax;
            document.getElementById("armor").innerHTML = response.fightStatsView.armor;
            document.getElementById("experience").innerHTML = response.experienceView.value;
            document.getElementById("nextLevelExp").innerHTML = response.experienceView.nextLevelExp;
            document.getElementById("level").innerHTML = response.experienceView.level;
            document.getElementById("levelUpPoints").innerHTML = response.levelUpPoints;
            document.getElementById("amountOfGold").innerHTML = response.resourcesView.gold;
            document.getElementById("amountOfIron").innerHTML = response.resourcesView.iron;
            document.getElementById("amountOfMeat").innerHTML = response.resourcesView.meat;
        }
    };
    // Converting JSON data to string
    var data = JSON.stringify({
                              	"fightStats": {
                                		"strength": 5,
                                		"hp": 25,
                                		"hpMax":25,
                                		"armor": 3
                                	  },
                                	  "resources": {
                                		"gold": 4,
                                		"iron": 1,
                                		"meat": 6
                                	  },
                                	  "experience" : 1250
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
                document.getElementById("hp").innerHTML = response.fightStatsView.hp;
                document.getElementById("amountOfGold").innerHTML = response.resourcesView.gold;
                document.getElementById("amountOfIron").innerHTML = response.resourcesView.iron;
                document.getElementById("amountOfMeat").innerHTML = response.resourcesView.meat;
            }
        };

         xhr.send();

}