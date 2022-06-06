<style>
#title{
color: black;
}

#subtitle{
color: green;
}
</style>



<%

if(sessione.getRuolo().getDescrizione().equals("user")){
%>
<nav id="navbar" class="navbar navbar-light bg-light" >
	  <a class="navbar-brand" href="Homepage">
		<img src="img/smemoGif.gif" width="20%" alt="">
	  </a>
      <ul>
        <li><a class="nav-link active" href="HomepageUser">Profilo</a></li>
        <li><a class="nav-link" href="Bacheca">Bacheca</a></li>
        <li><a><form action="Homepage" method="POST">
			<button type="submit" class="btn btn-primary mt-2 mt-md-0">Logout</button>
			</form></a> </li>
			
      </ul>
      <i class="bi bi-list mobile-nav-toggle"></i>
    </nav>
 <%} else{
	 %>
 
 <nav id="navbar" class="navbar navbar-light bg-light" >
	  <a class="navbar-brand" href="Homepage">
		<img src="img/smemoGif.gif" width="20%" alt="">
	  </a>
      <ul>
        <li><a title="Sei un Admin" ><i style="font-size:20px;" class="bi bi-key-fill"></i></a></li>
        <li><a class="nav-link active" href="HomepageAdmin">Dashboard</a></li>
        <li><a class="nav-link" href="Bacheca">Bacheca</a></li>
        <li><form action="Homepage" method="POST">
			<button type="submit" class="btn btn-primary mt-2 mt-md-0">Logout</button>
			</form> </li>
			
      </ul>
      <i class="bi bi-list mobile-nav-toggle"></i>
    </nav><!-- .navbar -->
 
 <% 
 }
%>



<div class="container">
<div class = "row mt-3 d-flex justify-content-center">
<div class = "panel" style ="background-color: #fff75e !important;">
<div class = "row mt-4 ">
	<div class="section-title" style="padding-bottom:10px;">
          
          <span id="title">POST IT</span>
			<h2 id="subtitle">Crea</h2>
        </div>
</div> 
<div class = "row mt-3">
	<form class ="form-crea" id="form-creazione" style ="background-color: #fff75e !important" action="AddPost" method='POST'>
	<div class="row">
	<div class="col-12">
			<input class = "text-form form-control" type='text' name='titolo' id ="titolo" placeholder='inserisci titolo'>
		</div>
	</div>
	<div class = "row mt-2">
	<div class="col-12">	
		<textarea form="form-creazione" class = "form-control text-form" name='descrizione' placeholder='inserisci descrizione'
			 cols="54" rows="10"
			></textarea>
	</div>
	</div>
	<div class = "row mt-2">
	<div class="col-12">		
		<label for ="data">Data di scadenza:</label><br>
		<input class = "text-form form-control" type='date' id="data" name='data' placeholder='inserisci data di scadenza'>
	</div>
	</div>
	<div class = "row mt-2">
	<div class="col-12">
		<label for ="cat">Scegli categoria:</label><br>
		<select class = "text-form form-control" name ="categoria" id="cat" style="width:300px;" class="form-control" multiple aria-label="multiple select example">
		
		
		<%
		List<Categoria> categorie = (List<Categoria>)request.getAttribute("categorie");
			for(Categoria c : categorie){
		%>	
		<option value = "<%=c.getId()%>"><%=c.getDescrzione()%></option>
	<% 
	}
	%>
		
		</select>
		</div>
		</div>
		<div class = "row mt-4">
		<div class="col-12">
		<button type="submit" class="btn btn-warning">CREA</button>
		</div>
		</div>
		
		
		<div class = "row mt-5">
			<div class="col-12">
			</div>
		</div>
	
		
	</form>
	<br><br>
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

<div class = "row mt-5">
	<div class="col-12">
	</div>
</div>
	
</div>
