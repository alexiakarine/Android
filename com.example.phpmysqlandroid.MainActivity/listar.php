<?php 
	//recupera informação
    $username   = urldecode($_POST['username']);  
	$password   = urldecode($_POST['password']);  
    // conecta
    $conecta=mysqli_connect("localhost","root","","empresa");
	// define ordem de consulta
    $sql="select * from contas where username ='$username' and password='$password'";
	// executa ordem de consulta
    $registro=mysqli_query($conecta,$sql);
	// conta quantos registro foram retornados após a execução de ordem da consulta
    $cont=mysqli_num_rows($registro);
    // se cont (total de registros da consulta) = 0, não tem registro para mostrar
    if ($cont==0) print "Acesso,Não permitido,";
	// do contrário mostrar informação
    else{
		// percorrer os registros através do while	
		//$row=
		while ($campo=mysqli_fetch_array($registro,MYSQLI_ASSOC)){
			// enviar a respota para o android de cada campo dos registors
			print $campo["username"].",".$campo["password"].",";
		}
		print "último";
		mysqli_free_result($registro);
	}
	// fechar conexão
	mysqli_close($conecta);
?>
 