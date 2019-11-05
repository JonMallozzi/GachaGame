import React from 'react';
import Homepage from "./homepage/homepage";
import Page2 from './page2/page2';
import DataDashboard from './dataDashboard/dataDashboard';
import './App.css';

import {BrowserRouter as Router, Switch, Route} from "react-router-dom";

function App() {
  return (
   <Router>
    <div className="App">
        <Switch>
            <Route path="/" exact component={Homepage}/>
            <Route path="/page2" component={Page2}/>
            <Route path="/dataDashboard" component={DataDashboard}/>
        </Switch>
    </div>
  </Router>
  );
}

export default App;
