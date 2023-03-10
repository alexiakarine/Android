<?php 
    $acao   = isset($_POST['acao'])?urldecode($_POST['acao']):"";  
    $query   = isset($_POST['query'])?urldecode($_POST['query']):"";
	if($acao!=""&&$query!="")
	{
		$conecta=mysqli_connect("localhost","root","","loja");
		$resultado=mysqli_query($conecta,$query);
		switch($acao)
		{
			case "insert":
			case "delete":
			case "update":
				if (!$resultado) $retorno="0";
				else $retorno="1";
				echo $retorno;
				break;
			case "select":
				if(mysqli_num_rows($resultado)>0)
				{
					while($linha=mysqli_fetch_array($resultado,MYSQLI_ASSOC))
					{
						$retorno["dados"][] = array(
							"id"=>$linha["id"],
							"nome"=>$linha["nome"],
							"endereco"=>$linha["endereco"],
							"telefone"=>$linha["telefone"],
							"rg"=>$linha["rg"],
							"cpf"=>$linha["cpf"],
							"nascimento"=>$linha["nascimento"]
						);
					}
					mysqli_close($conecta);
					echo json_encode($retorno);
				}
				else {
					mysqli_close($conecta);
					echo "";
				}
				break;
		}
	}
?>