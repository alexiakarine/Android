<?php
	$acao = urldecode($_POST["acao"]); //recupera a informação
	$conexao = mysqli_connect("localhost","root","123456","empresa");//conecta no servidor mysql
	$query = "select * from funcionarios";//define a consulta
	$resultados = mysqli_query($conexao,$query);//executa a consulta
	$count = mysqli_num_rows($resultados);
	if($count==0){//caso o total de resultados(registros) seja = 0, não tem nenhum resultado
		$array["dados"][] = array(
			"msg" => "erro",
			"nome" => "",
			"funcao" => ""
		);
	}
	else{//caso tenha resultados
		//pega todos os resultados encontrados pelo o while(repetição)
		while($campo=mysqli_fetch_array($resultados,MYSQLI_ASSOC)){
			//armazena os dados de cada registro encontrado em uma lista (array)
			$array["dados"][] = array(
				"msg" => "sucesso",
				"nome" => $campo["nome"],
				"funcao" => $campo["funcao"]
			);
		}
		mysqli_free_result($resultados);
	}
	//fecha a conexão
	mysqli_close($conexao);
	//retorna um json com os resultados
	echo json_encode($array);
?>