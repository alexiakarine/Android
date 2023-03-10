<?php
$nome = urldecode($_POST['nome']); 
$cpf = urldecode($_POST['cpf']); 
$rg = urldecode($_POST['rg']); 
$idade = urldecode($_POST['idade']); 
$email = urldecode($_POST['email']); 
$telefone = urldecode($_POST['telefone']); 
$conecta=mysqli_connect("localhost","root","vertrigo","cadastro");
$sql="insert into usuario (nome,cpf,rg,idade,email,telefone) values ('$nome','$cpf','$rg','$idade','$email','$telefone')";
$resultado=mysqli_query($conecta,$sql);
 if (!$resultado) print "\n Erro na inserção";
 else print "Cadastrado com sucesso!";
 mysqli_close($conecta); 
?>



