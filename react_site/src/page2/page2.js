import React from 'react';
import '../App.css';
import logo from "./anime-girl-pink.svg";
import {Link} from "react-router-dom";

//const linkStyle = {
    //color : '#09d3ac'
//}

function Page2() {
    return (
        <div className="App">
            <header className="App-header">
                <img src={logo} className="App-logo" alt="logo" />
                <p>
                    Welcome to page 2 <br/>
                    <Link to="/" className="Link">
                        return home
                    </Link>
                    <Link to="/dataDashboard" className="App-link">
                         Data Dashboard
                    </Link>

                    <Link to="/profile/{id}" className="App-link" id="profileLink">
                        Profile
                    </Link>

                    <Link to="/login" id="login" className="App-link">
                        Login
                    </Link>

                </p>
            </header>
        </div>
    );
}

export default Page2;

