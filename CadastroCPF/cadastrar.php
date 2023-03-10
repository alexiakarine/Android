<?php 
    $nome   = urldecode($_POST['nome']);  
    $cpf   = urldecode($_POST['cpf']);
    $rg   = urldecode($_POST['rg']);
    $telefone   = urldecode($_POST['telefone']);
    $nascimento   = urldecode($_POST['nascimento']);
    $conecta=mysqli_connect("localhost","root","","usuarios");
    $sql="insert into contas (nome,cpf,rg,telefone,nascimento) values ('$nome','$cpf','$rg','$telefone','$nascimento')";
    $resultado=mysqli_query($conecta,$sql);
    if (!$resultado) print "\n Erro na inserção";
    else print "Cadastrado com sucesso!";
	mysqli_close($conecta);    
?>