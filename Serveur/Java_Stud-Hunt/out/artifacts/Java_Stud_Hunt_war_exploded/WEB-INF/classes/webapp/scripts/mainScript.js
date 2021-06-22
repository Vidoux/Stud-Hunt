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

function changePicture(){

}

//---------------------REGISTER-----------------------------

$('#student_checkbox')
    .checkbox({
    onChecked: function() {
        document.getElementById("prenom_register").classList.remove("invisibleDiv");
        console.log("unchecked");
    },
});

$('#company_checkbox')
    .checkbox({
        onChecked: function() {
            document.getElementById("prenom_register").classList.add("invisibleDiv");
            console.log("unchecked");
        },
    });