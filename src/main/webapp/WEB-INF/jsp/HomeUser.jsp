 <nav id="navbar" class="navbar navbar-light bg-light" >
	  <a class="navbar-brand" href="Homepage">
		<img src="img/smemoGif.gif" width="20%" alt="">
	  </a>
      <ul>
        <li><a class="nav-link active" href="HomepageUser">Profilo</a></li>
        <li><a class="nav-link" href="Bacheca">Bacheca</a></li>
        <li><form action="Homepage" method="POST">
			<button type="submit" class="btn btn-primary mt-2 mt-md-0">Logout</button>
			</form> </li>
			
      </ul>
      <i class="bi bi-list mobile-nav-toggle"></i>
    </nav><!-- .navbar -->

  <main id="main">

    <!-- ======= Profilo Section ======= -->
    <section id="about" class="about" style="padding:30px">
      <div class="container">
      
        <div class="section-title">
          
          <span>Profilo</span>
			<h2>Le tue informazioni personali</h2>
        </div>
        
        <%
        Utente u = (Utente) request.getAttribute("utente");
        %>
        
        <div class="row">
          <div class="col-lg-12 pt-3 d-flex flex-column align-items-stretch">
            <div class="content ps-lg-4 d-flex flex-column justify-content-center">
              <div class="row d-flex justify-content-center">
                <div class="col-3 d-flex justify-content-center align-items-center">
                  <ul>
                    <li><i class="bi bi-person-fill"></i> <strong>Nome:</strong> <span><%=u.getNome() %> </span></li>
                    <li><i class="bi bi-key-fill"></i> <strong>Username:</strong> <span><%=u.getUsername() %> </span></li>
                  </ul>
                </div>
				<div class="col-3 d-flex justify-content-center align-items-center">
                  <ul>
                    <li><i class="bi bi-person-fill"></i> <strong>Cognome:</strong> <span><%=u.getCognome() %> </span></li>
                    <li><i class="bi bi-envelope-fill"></i> <strong>Email:</strong> <span><%=u.getEmail() %> </span></li>
                  </ul>
                </div>	
              </div>
              
              <!-- Qui inizia la row che dovete copiare -->
			  <div class="row d-flex justify-content-center"> 
				<div class="col-3 d-flex justify-content-center align-items-center">
                  
                  <form action="ModificaCredenziali" method="GET">
						<input type="hidden" value="<%=sessione.getUsername()%>" name="username" /> 
						<button type="submit" class="btn btn-primary btn-md">Modifica Profilo</button>
				  </form>	
                </div>
                	 <%
						String chiaveElimina = (String)request.getAttribute("chiaveElimina");
						System.out.println("Chiave elimina in jsp: " + chiaveElimina);
						if(chiaveElimina != null && chiaveElimina.equalsIgnoreCase("elimina")){
					%>
							
					<div class="col-3 d-flex justify-content-center align-items-center">		
							Sei sicuro di voler eliminare in tuo account?
					</div>
										
					<div class="col-3 d-flex justify-content-center align-items-center">	
					<form action="EliminaProfilo" method="POST"> 	
						
						<select name="keyElimina" value="keyElimina" class="form-select">
							<option value="si" name="si">Si</option>
							<option value="no" name= "no">No</option>
						</select>
						<input type="hidden" name="userId" value="<%=u.getId()%>"> 
						<input class="btn btn-secondary mt-1" type="submit" value="Conferma">
						
						
                	</form>
                	</div>
                 		
                 	<% 
					}else{
					%>
				<div class="col-3 d-flex justify-content-center align-items-center">
					<form action="EliminaProfilo" method="GET">
						<input type="hidden" name="userId" value="<%=u.getId()%>" />
						<button type="submit" class="btn btn-danger">Elimina Profilo</button>
						</form>
				<%	} %>
				</div> 
			  </div> 
	<!--  questo div chiude la row che dovete copiare-->
			  
          </div>
        </div>
	</div>
    </section><!-- End Profilo Section -->
	
	<!-- ======= Post it in scadenza Section ======= -->
	<section id="expiring" class="expiring" style="background-color: #D2B48C;">
		<div class="container">
		  <div class="section-title">	
			<span>In scadenza</span>
			<h2>Post-it</h2>
		  </div>
		  <div class="row">
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
	<div class="row">
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
						for(int y=1;y<list.size()-1;y++){
							listaCategorie+=","+list.get(i).getDescrzione();
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
		
	  </div>
		<div class="row d-flex justify-content-center">
		<div class="col-3 d-flex justify-content-center">
		<a href="Bacheca"><button type="button" class="btn btn-info btn-md">Vai alla tua Bacheca</button></a>
		</div>
		</div>
		</div>
	</section>
  <div class="spacer" style="background-color: white;height:20px;">
  </div>
  <!-- ======= Footer ======= -->
  <a name="footer"><footer id="footer">
    <div class="container">
      <h3>SmemoLab Srl</h3>
      <p>Ti aiutiamo a ricordare</p>

      <a href="mailto:info@smemolab.com"><p>info@smemolab.com</p></a>
      <div class="copyright">
        &copy; 2022 Copyright SmemoLab.com. All Rights Reserved
      </div>
      <div class="credits">
        
      
      </div>
    </div>
  </footer></a><!-- End Footer -->

  <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>