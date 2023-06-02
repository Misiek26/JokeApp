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
