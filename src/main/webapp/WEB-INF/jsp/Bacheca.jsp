<% if(sessione.getRuolo().getDescrizione().equals("user")){
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


<div class = "container">
<div class = "row d-flex justify-content-center">
<div class = "row mt-2">


<div class="section-title">
          <h2>Cerca i tuoi post it</h2>
		  <span>BACHECA</span>
</div>


<% 
	String chiavecerca = (String)request.getAttribute("chiavereq");
	System.out.println("chiave: " + chiavecerca);
	if(chiavecerca!=null && chiavecerca.equalsIgnoreCase("titolo")){
	%>
	
	<form action="CercaPostItServlet" method="POST">
	<div class = "row">
	<div class="col-4">
		<select name="key" class="form-select">
			<option value="titolo">Titolo</option>
		</select> 
	</div>
	<div class="col-4">
		<input type="text" name="ricerca" value="Inserisci Titolo">
	</div>	
	<div class="col-4">
	    <input type="submit" value="&#128269; Cerca">
	</div>	 
		
    </div>
    </form>
    
	<%
	}else if(chiavecerca!=null && chiavecerca.equalsIgnoreCase("dataCreazione")){
	%>	
		<form action="CercaPostItServlet" method="POST">
		<div class = "row">
		<div class="col-4">
			<select name="key" class="form-select">
				<option value="data">Titolo</option>
			</select>
		</div>
		 <div class="col-4">
			<input class="form-select" type="date" name="ricerca" value="Inserisci Data"> 
		</div>
		<div class="col-4">
			<input class="btn btn-primary" type="submit" value="&#128269; Cerca">
		</div>
		</div>
		</form>
		
	<%
	}
	else{
	%>
	<% System.out.println("sono nell'else bacheca jsp");%>
		
		<form action="Bacheca" method="GET">
		<div class = "row">
		<div class="col-4">
		 <select name="key" class="form-select">
			<option value="titolo" name="titolo">Cerca per Titolo</option>
			<option value="dataCreazione" name= "titolo">Cerca per Data di creazione</option>
		</select> 
		</div>
		<div class="col-2">
		<input class="btn btn-primary" type="submit" value="&#128269; Cerca">
		</div>
		</div>
		</form>
		<% }
			Object msg = request.getAttribute("messaggio");
		if(msg != null){
			String mess = (String)msg;
		%>
		<br><br>
		<div class="alert alert-info" role="alert">
		  <%=mess%>
		</div>
		
		<%
		}
		%>

<div class="row mt-2 d-flex justify-content-end ">
<div class="col-4 d-flex justify-content-end ">
<form action="AddPost" method="GET">
		<input class="btn btn-info" type="submit" value="+ Aggiungi Post-it" />			
</form>
</div>

</div>

<div class="round-panel" style="background-color:#d4a373;margin-top:10px;border-color:black;border: 2px solid;border-radius:20px;">
<div class="row mt-4">
	<!-- questo dentro al ciclo for -->

<%	
    Postit single_post;
	List<Postit> postit = (List<Postit>)request.getAttribute("postit");
	System.out.println("Post it in lista: " + postit);
	if(postit != null){
		for( int i=0;i<postit.size();i++){
			single_post = postit.get(i);
			if(i%4==0){
%>
	</div>
	<div class="row mt-3">
<%
			}
			
			
%>
	<div class="col-3">
		  <div class="postit">
		    <span class="dataScandenza">Scade il: <i><%=single_post.getDataScadenza()%></i></span>
		    <p style = "font-size: 12px;">Categorie: <i>
		    		<%
						List<Categoria> list = (List<Categoria>)single_post.getCategorie();
						String listaCategorie= "";
						
						if (list.isEmpty() || list == null){
						%>
							nessuna categoria
						<% 
						}else{
							
						listaCategorie=list.get(0).getDescrzione();
						for(int y=1;y<list.size();y++){
							listaCategorie+=","+list.get(y).getDescrzione();
						}
					%>
						<%=listaCategorie %>
						<p class = "cat" style = "display:none"><%=list.get(0).getDescrzione()%></p>
					<%} %>    
		    </i></p>   
		    			
			<h3 id="title"><%=single_post.getTitolo()%></h3>
			<p id="desc"><%=single_post.getDescrizione()%></p>
			
		  </div>
		  <div class="row d-flex justify-content-center">
				<div class="col-6 d-flex justify-content-center">
				  <form action="EliminaPostIt" method="POST">
					<input type="hidden" name="titolo" value="<%=single_post.getTitolo()%>" />
					<button type="submit" class="btn btn-danger btn-sm"><span style="font-size:12px;">X Elimina</span></button>
				</form>
				</div>
				<div class="col-6 d-flex justify-content-center">
				<form action="Modifica" method="GET">
				<input type="hidden" name="titolo" value= "<%=single_post.getTitolo()%>"/>
					<button type="submit" class="btn btn-warning btn-sm"><span style="font-size:12px;">&#9998; Modifica</span></button>
				</form>
				</div>
			</div>
	</div>
<%
		}
	}else{
		%>
		<p><%="Non ci sono ancora post it"%></p>
		<% }
	
%>
</div>	


<div class="row mt-3">
</div>
</div>

<div class="row mt-5">
</div>

</div>		
</div>
</div>
