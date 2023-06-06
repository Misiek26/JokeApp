function displayUserJokes(){
    let path = "http://localhost:8080/api/user-jokes/1";

    $( "#add" ).load(path, function( response, status, xhr ) {
      if ( status == "error" ) {
        var msg = "Sorry but there was an error: ";
        $( "#error" ).html( msg + xhr.status + " " + xhr.statusText );
      }
    });
}

displayUserJokes();

function displaySuccessCommunicat(){
    let urlParams = new URLSearchParams(location.search);
    let communicat = urlParams.get('success');
    if(communicat != null)
        document.getElementById("success").style.visibility = "visible";
}

displaySuccessCommunicat();

function updateJoke(id){
    location.href="http://localhost:8080/manage/update-joke/" + id;
}

function updateJokeForm(){
    let url = location.href;
    let parts = url.split('/');
    let jokeId = parseInt(parts[parts.length - 1]);
    let setupValue = document.getElementById('setup').value;
    let punchlineValue = document.getElementById("punchline").value;
    let categoryNameValue = document.getElementById("category").value;
    let userId = 1;

    const formData = new FormData();
    formData.append("setup", setupValue);
    formData.append("punchline", punchlineValue);
    formData.append("category", categoryNameValue);

    fetch('/api/jokes/'+jokeId,{
       method: "PUT",
       body: formData
    }).then(response => {
          if (response.ok) {
              location.href="http://localhost:8080/manage/update-joke?success";
          } else {
              location.href="http://localhost:8080/error";
          }
      })
}

function deleteJoke(jokeId){
    if(confirm("Are you sure you want to delete this joke?"))
    {
        fetch('/api/jokes/'+jokeId,{
           method: "DELETE"
        }).then(response => {
              if (response.ok) {
                  location.href="http://localhost:8080/manage/delete-joke?success";
              } else {
                  location.href="http://localhost:8080/error";
              }
          })
    }

}