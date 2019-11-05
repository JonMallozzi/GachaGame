extern crate reqwest;


fn http_request_results(uri : &str) {
    match reqwest::get(uri) {
        Ok(mut response) => {
            //checking to see if the response code was 200 ok
            if response.status() == reqwest::StatusCode::OK {
                match response.text() {
                    Ok(text) => println!("Response Text: {}", text),
                    Err(_) => println!("could not read text")
                }
            } else {
                println!("error {} could not print out text", response.status())
            }
        }
        Err(_) => println!("Could not make the request")
    }
}


fn main(){

    let uri = "http://localhost:8080/api/v1/person/89cc4b9f-af47-4c01-9168-8b31f524ac52";
    http_request_results(uri);

}
