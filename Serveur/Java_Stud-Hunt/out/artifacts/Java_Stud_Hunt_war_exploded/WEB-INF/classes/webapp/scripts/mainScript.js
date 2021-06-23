$('.ui.dropdown')
    .dropdown()
;

function activateStudentForm(){
    let inputs = document.getElementsByTagName("input");
    for (let i=0; i< inputs.length; ++i){
        inputs[i].style.pointerEvents = "auto";
    }
    document.getElementById("change_profil").className += " loading disabled";

    document.getElementById("submitButton").classList.remove("invisibleDiv");
    document.getElementById("cancelButton").classList.remove("invisibleDiv");
}

function desactivateStudentForm(){
    // let inputs = document.getElementsByTagName("input");
    // for (let i=0; i< inputs.length; ++i){
    //     inputs[i].style.pointerEvents = "none";
    // }
    // document.getElementById("change_profil").classList.remove("loading");
    // document.getElementById("change_profil").classList.remove("disabled");
    //
    // document.getElementById("submitButton").className += " invisibleDiv";
    // document.getElementById("cancelButton").className += " invisibleDiv";
    window.location.reload();
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

//---------------------Image Upload-----------------------------

var fileTypes = [
    'image/jpeg',
    'image/pjpeg',
    'image/png'
]

var input = document.getElementById("image_uploads");
var preview = document.querySelector('.preview');
var blobData = null;

input.addEventListener('change', updateImageDisplay);

function updateImageDisplay() {
    while(preview.firstChild) {
        preview.removeChild(preview.firstChild);
    }

    let curFiles = input.files;
    let para = document.createElement('p');
    if(curFiles == null) {
        para.textContent = 'No files currently selected for upload';
        preview.appendChild(para);
    } else {
        var list = document.createElement('ol');
        preview.appendChild(list);

        if(validFileType(curFiles[0])) {
            para.textContent = 'File name ' + curFiles[0].name + ', file size ' + returnFileSize(curFiles[0].size) + '.';
            console.log("curFiles[i]" + curFiles[0]);
            console.log("curFiles" + curFiles);
            blobData = curFiles[0];
            var image = document.createElement('img');
            image.src = window.URL.createObjectURL(curFiles[0]);
            console.log("src" + image.src);
            image.className="ui centered circular image"

            list.appendChild(image);
            list.appendChild(para);

        } else {
            para.textContent = 'File name ' + curFiles[i].name + ': Not a valid file type. Update your selection.';
            list.appendChild(para);
        }

    }
}

function validFileType(file) {
    for(let i = 0; i < fileTypes.length; i++) {
        if(file.type === fileTypes[i]) {
            return true;
        }
    }

    return false;
}

function returnFileSize(number) {
    if(number < 1024) {
        return number + ' octets';
    } else if(number >= 1024 && number < 1048576) {
        return (number/1024).toFixed(1) + ' Ko';
    } else if(number >= 1048576) {
        return (number/1048576).toFixed(1) + ' Mo';
    }
}

function FileUpload(id) {
    console.log(id);
    console.log(blobData);
    jQuery.ajax({
        url: "./image_action",
        type: "POST",
        data: blobData,
        processData: false,
        contentType: false,
        success: function (result) {
            // if all is well
            // play the audio file
        }
    });
    // $.post( "./image_action", { image: blobData, id: id } );

}
