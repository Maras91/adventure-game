function plus(attribute, multiplier) {
    var availablePoints = document.getElementById('levelUpPoints').innerHTML;
    if (availablePoints > 0) {
        var score = document.getElementById(attribute);
        var attributeValue = parseInt(score.innerHTML);
        score.innerHTML = attributeValue + 1 * multiplier;
        document.getElementById('levelUpPoints').innerHTML = availablePoints -1
    }
}
function minus(attribute , multiplier) {
    var attributeValue = document.getElementById(attribute).innerHTML;
    if (attributeValue > 0) {
        var score = document.getElementById('levelUpPoints');
        var availablePoints = score.innerHTML;
        availablePoints++;
        score.innerHTML = availablePoints;
        document.getElementById(attribute).innerHTML = attributeValue - 1 * multiplier
    }
}

function levelUp() {
        // Creating a XHR object
        let xhr = new XMLHttpRequest();
        let url = "levelUp";

        // open a connection
        xhr.open("POST", url, true);

        // Set the request header i.e. which type of content you are sending
        xhr.setRequestHeader("Content-Type", "application/json");

        // Create a state change callback
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                var response = JSON.parse(this.responseText);
                //output
                document.getElementById("strength").innerHTML = response.strength;
                document.getElementById("hp").innerHTML = response.hp;
                document.getElementById("hpMax").innerHTML = response.hpMax;
                document.getElementById("armor").innerHTML = response.armor;

                document.getElementById("addStrength").innerHTML = 0;
                document.getElementById("addHp").innerHTML = 0;
                document.getElementById("addArmor").innerHTML = 0;
            }
        };
        // Converting JSON data to string
        var data = JSON.stringify({
                          "strength" : getMyElementById("addStrength"),
                                "hp" : getMyElementById("addHp"),
                             "armor" : getMyElementById("addArmor")
                                    });

        // Sending data with the request
         xhr.send(data);
}
 function getMyElementById(id) {
    return document.getElementById(id).innerHTML
 }