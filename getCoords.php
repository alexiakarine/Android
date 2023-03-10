<?php
	// conexão com banco de dados
	$con = new mysqli("127.0.0.1","root","root","maps");
	// executa sql para obter as informações da tabela do banco de dados
	$result = $con->query("SELECT * FROM coordenadas");
	// cria array para armazenar resultado da consulta Select
	$resultado = array();
	// percorre resultado consulta e armazena valores de coordenadas em um vetor
	while($row = $result->fetch_assoc()){
		$resultado[] = array("aluno" =>$row["aluno"], "x" => $row["x"], "y" => $row["y"], "situacao" => $row["situacao"]);
	}
	// codifica vetor para padrão json e devole para ajax no java
	echo json_encode($resultado);
?>