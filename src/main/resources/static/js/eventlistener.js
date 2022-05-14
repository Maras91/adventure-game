//------------------------Adventure-----------------------------------
document.getElementById('adventure').addEventListener('load',adventureStats);
document.getElementById('attack').addEventListener('click',killGoblin);
document.getElementById('potion').addEventListener('click',getPotion);
document.getElementById('levelUp').addEventListener('click',levelUp);

document.getElementById('minArmor').addEventListener('click', () => { minus('addArmor',1);});
document.getElementById('plusArmor').addEventListener('click', () => { plus('addArmor',1);});

document.getElementById('minHp').addEventListener('click', () => { minus('addHp',10);});
document.getElementById('plusHp').addEventListener('click', () => { plus('addHp',10);});

document.getElementById('minStrength').addEventListener('click', () => { minus('addStrength',1);});
document.getElementById('plusStrength').addEventListener('click', () => { plus('addStrength',1);});

//---------------------------------------------------------------------