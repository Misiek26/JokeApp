function sendLoginRequest() {

    let email = document.getElementById("inputEmail").value.trim();
    let password = document.getElementById("inputPassword").value;

    if(email === '' || password === ''){
            return alert("Enter your email and password");
        }

    if(!email.includes("@")){
        return alert("Email does not contain '@'")
    }
}

document.getElementById("login").addEventListener("click", sendLoginRequest);

