<style>
#title{
color: black;
}

#subtitle{
color: green;
}
</style>

<nav id="navbar" class="navbar navbar-light bg-light" >
	  <a class="navbar-brand" href="Homepage">
		<img src="img/smemoGif.gif" width="20%" alt="">
	  </a>
      <ul>
        <li><a class="nav-link active" href="HomepageUser">Profilo</a></li>
        <li><a class="nav-link" href="Bacheca">Bacheca</a></li>
        <li><a>
            <form action="Homepage" method="POST">
				<button type="submit" class="btn btn-primary">Logout</button>
			</form> 
			</a>
		</li>
			
      </ul>
      <i class="bi bi-list mobile-nav-toggle"></i>
</nav><!-- .navbar -->
<div class="container">
<div class = "row mt-3 d-flex justify-content-center">
<div class = "panel" style="background-color: #ffafcc !important;">
<div class = "row mt-4 ">
	<div class="section-title" style="padding-bottom:10px;">
          
          <span id="title">Profilo</span>
			<h2 id="subtitle">Modifica</h2>
        </div>
</div> 
<div class = "row mt-3 ">
<form class ="form-crea" id="form-modifica" style="background-color: #ffafcc !important;" action="ModificaCredenziali" method='POST'>
<div class = "row">
	<div class="col-12">
		<label for="nome">Nome</label>
		<input  class = "form-control text-form-modifica-utente" type='text' name="nome" id="nome" value="<%= sessione.getNome() %>">
		<input name="nomeVecchio" type="hidden" id="nome" value="<%= sessione.getNome() %>">
	</div>	
</div>
<div class = "row mt-2">
	<div class="col-12">
		<label for="cognome">Cognome</label>
		<input  name="cognome" type="text"  id="cognome" class = "form-control text-form-modifica-utente" value="<%= sessione.getCognome() %>">
		<input name="CognomeVecchio" type="hidden" id="cognome" value="<%= sessione.getCognome() %>">
	</div>	
</div>
<div class = "row mt-2">
	<div class="col-12">
		<label for="email">Email</label>
		<input name="email" type="text"  id="email" class = "form-control text-form-modifica-utente" value="<%= sessione.getEmail() %>">
		<input name="EmailVecchio" type="hidden"  id="cognome" value="<%= sessione.getEmail() %>">
	</div>	
</div>
<div class = "row mt-2" >
	<div class="col-12">
		<label for="username">Username</label>
		<input name="username" type="text"  id="username" class = "form-control text-form-modifica-utente" value="<%= sessione.getUsername() %>">
		<input name="UsernameVecchio" type="hidden"  id="username" value="<%= sessione.getUsername() %>">
	</div>	
</div>
	<div class = "row mt-3">	
		<button type="submit" style = "background-color:#ff006e;" class="btn btn-success">MODIFICA</button>
	</div>		
</form>
</div>
<br>
	<%
	String error = (String) request.getAttribute("error");
	String esito = (String) request.getAttribute("esito");
	%>
	
	<% 
	if (error != null && esito != null) {
		if (!error.isEmpty()){
	%>
		<h3><%=error%></h3>
		
		<%
		}
		else{
		%>
			<h3><%=esito%></h3>
			
	
		<% 	
		}
		
	}
	%>
</div>	
</div>
</div>