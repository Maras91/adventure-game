function killGoblin() {
    var strength = $("#strength").html()
    var hp = $("#hp").html()
    var armor = $("#armor").html()


    // Creating a XHR object
    let xhr = new XMLHttpRequest();
    let url = "json";

    // open a connection
    xhr.open("POST", url, true);

    // Set the request header i.e. which type of content you are sending
    xhr.setRequestHeader("Content-Type", "application/json");

    // Create a state change callback
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var response = JSON.parse(this.responseText);
            document.getElementById("amountOfGold").innerHTML = response.gold;
            document.getElementById("amountOfIron").innerHTML = response.iron;
            document.getElementById("amountOfMeat").innerHTML = response.meat;
        }
    };
    // Converting JSON data to string
    var data = JSON.stringify({ "strength": strength, "hp": hp, "armor": armor});

    // Sending data with the request
     xhr.send(data);
}