$('.ui.dropdown')
    .dropdown()
;

function activateStudentForm(){
    let inputs = document.getElementsByTagName("input");
    console.log(inputs);
    for (let i=0; i< inputs.length; ++i){
        inputs[i].style.pointerEvents = "auto";
    }
    document.getElementById("change_profil").className += " loading disabled";

    document.getElementById("submitButton").classList.remove("invisibleDiv");


}
