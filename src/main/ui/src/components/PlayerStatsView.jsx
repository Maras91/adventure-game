import React from 'react';

class PlayerStatsView extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            playerStatsView: {
                strength: null,
                armor: null,
                hpMax: null,
                hp: null
            },
            resourcesView: {
                gold: null,
                iron: null,
                meat: null
            },
            experienceView: {
                value: null,
                nextLevelExp: null,
                level: null
            }
        };
    }
    componentDidMount() {
    const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ title: 'React POST Request Example' })
        };
        fetch('/adventureStats', requestOptions)
            .then(response => response.json())
            .then(data => this.setState({
                strength: data.fightStatsView.strength,
                armor: data.fightStatsView.armor,
                hpMax: data.fightStatsView.hpMax,
                hp: data.fightStatsView.hp,
                gold: data.resourcesView.gold,
                iron: data.resourcesView.iron,
                meat: data.resourcesView.meat,
                value: data.experienceView.value,
                nextLevelExp: data.experienceView.nextLevelExp,
                level: data.experienceView.level
             }));
    }

    render() {
        const {strength} = this.state
        const {armor} = this.state
        const {hpMax} = this.state
        const {hp} = this.state
        const {gold} = this.state
        const {iron} = this.state
        const {meat} = this.state
        const {value} = this.state
        const {nextLevelExp} = this.state
        const {level} = this.state
        return (
            <div class="row">
                <div class="col-md-3 border">
                    <p>Strength: {strength}</p>
                    <p>      HP: {hp}/{hpMax}</p>
                    <p>   Armor: {armor}</p>
                </div>
                <div class="col-md-3 border">
                    <p>Gold: {gold}</p>
                    <p>Iron: {iron}</p>
                    <p>Meet: {meat}</p>
                </div>
                <div class="col-md-3 border">
                    <p>Value: {value}</p>
                    <p>NextLevelExp: {nextLevelExp}</p>
                    <p>Level: {level}</p>
                </div>
            </div>
        );
    }
}

export default PlayerStatsView