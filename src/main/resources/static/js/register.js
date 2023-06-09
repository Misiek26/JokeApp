
function sendRegisterRequest() {

    let firstname = document.getElementById("inputFirstName").value.trim();
    let lastname = document.getElementById("inputLastName").value.trim();
    let email = document.getElementById("inputEmail").value.trim();
    let password = document.getElementById("inputPassword").value;
    let passwordConfirm = document.getElementById("inputPasswordConfirm").value;

    if(firstname === '' || lastname === '' || email === '' || password === '' || passwordConfirm === ''){
            return alert("Enter your information");
        }

    if(!email.includes("@")){
        return alert("Email does not contain '@'")
    }

    if(password!=passwordConfirm){
        return alert("Passwords do not match");
    }
}

document.getElementById("createAccount").addEventListener("click", sendRegisterRequest);