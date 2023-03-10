<?php 
	//recupera informação
    $username   = urldecode($_POST['username']);  
    $password   = urldecode($_POST['password']);
    // mostra informação
    print " ==== Dados enviados:  username  : $username   Password  : $password"; 
    // conecta a máquina
    $conecta=mysqli_connect("localhost","root","","empresa");
	
	// define sql de inserção
    $sql="insert into contas (username,password) values ('$username','$password')";
	// executar ordem no servidor sql
    $resultado=mysqli_query($conecta,$sql);
	// verificando resultado da ordem
    if (!$resultado) print "/n erro de na tentativa de inserção";
    else
        print "inserido com sucesso";
	// finalizar conexão	
	mysqli_close($conecta);    
?>