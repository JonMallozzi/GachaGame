import React, {useState, useEffect} from 'react';
import './dashboard.css';
import {Link} from "react-router-dom";

function DataDashboard() {

    useEffect(() => {
        fetchItems();
    },[]);


    const fetchItems = async () => {
        const data = await fetch('http://localhost:8080/api/v1/person/');
        console.log(data);
    };

    return (
        <div >
            <header className="App-header">
                <p>
                    <div id="title"> Data Dashboard</div>
                    <Link to="/" className="Link">
                        return home
                    </Link>

                </p>
            </header>
        </div>
    );
}

export default DataDashboard;