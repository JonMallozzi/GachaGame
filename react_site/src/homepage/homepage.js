import React from 'react';
import '../App.css'
import {Link} from "react-router-dom";
import logo from "./Anime_Girl.svg";

const linkStyle = {
    color : '#09d3ac',
    paddingTop : '10px'
}


function Homepage(){
    return(
        <header className="App-header">
            <img src={logo} className="App-logo" alt="logo" />
            <p>
                Welcome to the Idle Game
            </p>
            <a
                className="App-link"
                href="https://twitter.com/BlazefirerLoL"
                target="_blank"
                rel="noopener noreferrer"
            >
                Follow me on twitter
            </a>

            <a
                className="gitLab"
                href="https://gitlab.com/JonMallozzi"
                target="_blank"
                rel="noopener noreferrer"
            >
                Check out my Gitlab
            </a>

           <Link to="/page2" style={linkStyle}>
                Next Page
           </Link>
        </header>
    );
}

export default Homepage;
