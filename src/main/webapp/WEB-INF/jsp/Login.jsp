<style>
* {
	box-sizing: border-box;
}
</style>
<nav id="navbar" class="navbar navbar-light bg-light" >
	  <a class="navbar-brand" href="Homepage">
		<img src="img/smemoGif.gif" width="20%" alt="">
	  </a>
      <ul>
        <li><a class="nav-link active" href="Homepage">Home</a></li>	
      </ul>
      <i class="bi bi-list mobile-nav-toggle"></i>
    </nav>
<!-- .navbar -->
<div class = "body-login">
 <div class="panel" id="panel">
        <div class="form-container sign-up-container">
            <form class="form-login" action="Registrazione" method="post">
                <h1 class = "h1-login">Registrati</h1>  
                
                <div>
                    <input class = "input-login " type="text" name = "nome" placeholder="Nome" />
                    <input class = "input-login " type="text" name = "cognome" placeholder="Cognome" />
                </div> 
                <input class = "input-login " type="email" name = "email" placeholder="Email" />
                <input class = "input-login " type="text" name = "username" placeholder="Username" />
                <input class = "input-login " type="password" name = "password" placeholder="Password" />
                
                <button class = "button-login" type = "submit">Registrati</button>
              
            </form>
        </div>
        <div class="form-container sign-in-container">
            <form class="form-login" action="Login" method="post">
                <h1 class = "h1-login">Login</h1>
                <input class = "input-login " type="text" name ="username" placeholder="Username" />
                <input class = "input-login " type="password" name = "password" placeholder="Password" />
                <button class = "button-login" type = "submit">Login</button>
                <br>
                  <% 
    				Object messaggio  = request.getAttribute("errore");
					if(messaggio != null){
					String mess = (String)messaggio;
					%>
					<br>
					<h5 class="error"><%= mess%></h5>
					<% 
					}	
				%>
            </form>
        </div>
        <div class="overlay-container">
            <div class="overlay">
                <div class="overlay-panel overlay-left">
                    <!-- <img src ="img/SMEMOLAB.gif" class ="photo" alt = "logo-SMEMOLAB"/> -->
                    <h1 class = "h1-login" id="reg">Welcome Back!</h1>
                    <p class = "p-login">Effettua il login nella tua pagina personale</p>
                    <button class="ghost button-login" id="signIn">login</button>
                </div>
                <div class="overlay-panel overlay-right">
                    <!--<img src ="img/SMEMOLAB.gif" class ="photo" alt = "logo-SMEMOLAB"/>-->
                    <h1 class = "h1-login">Ciao!</h1>
                    <p class = "p-login">Se non sei registrato clicca qui sotto ed inizia a ricordare!</p>
                    <button class="ghost button-login" id="signUp">Registrati</button>
                </div>
            </div>
        </div>
    </div>
 </div>   
    
 








