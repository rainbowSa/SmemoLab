/**Bacheca */

var colorObj = {
	casa : "#dde5b6",
	tempoLibero: "#bde0fe",
	salute: "#ffe047",
	lavoro: "#e5989b",
	studio: "#f7b267",
	eventi: "#e7ad99",
	shopping: "#ffd6ff"
	
};


//prendo la lista di tutti gli elementi di classe cat nella pagina
var testElements = document.getElementsByClassName('cat');
for(var i=0;i<testElements.length;i++){
	//prendo l'elemento padre dell'elemento categoria
	console.log(testElements[i].innerText);
	//ora cambio lo sfondo
	testElements[i].parentNode.style.backgroundColor=colorObj[testElements[i].innerText];
	
}



/**
 * Login e registrazione
 */
 
const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('panel');

signUpButton.addEventListener('click', () => {
	container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
	container.classList.remove("right-panel-active");
});


