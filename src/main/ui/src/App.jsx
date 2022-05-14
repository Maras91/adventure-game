import logo from './logo.svg';
import './App.css';
import React from 'react';
import PlayerStatsView from './components/PlayerStatsView'

class App extends React.Component{
    render() {
        return (
        <div className="App">
            <PlayerStatsView />
        </div>
        );
    }
}

export default App;
