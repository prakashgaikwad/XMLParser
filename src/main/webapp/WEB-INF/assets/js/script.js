function hideOptions () {

    var select = document.getElementById("action"),
        selectedNode = select.options[select.selectedIndex],
        visibility = "hidden";

    if (selectedNode.value === "customers") {
        visibility = "visible";
    }

    document.getElementById("amount").style.visibility = visibility;
}