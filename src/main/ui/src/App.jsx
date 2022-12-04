import './App.css';
import React, {useState, useEffect} from 'react';
import AdventureView from './components/AdventureView';
import {BrowserRouter} from 'react-router-dom';

function App (){
        return (
        <div className="App">
          <BrowserRouter>
            <AdventureView />
          </BrowserRouter>
        </div>
        );
}

export default App;
