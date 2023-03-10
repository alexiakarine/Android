<?php 
    $matricula   = urldecode($_POST['matricula']);
    $nome   = urldecode($_POST['nome']);
    $departamento   = urldecode($_POST['departamento']);
    $salariototal   = urldecode($_POST['salariototal']);
    $salario   = urldecode($_POST['salario']);
    $faltas   = urldecode($_POST['faltas']);
    $horasextras   = urldecode($_POST['horasextras']);
    $conveniomedico   = urldecode($_POST['conveniomedico']); 
	$rg   = urldecode($_POST['rg']);
    $cpf   = urldecode($_POST['cpf']);

    $conecta=mysqli_connect("localhost","root","","empresa");
    $sql="insert into matricula (matricula,nome,departamento,salario_total,salario_receber,faltas,horas_extras,convenio_medico,rg,cpf) values ($matricula,'$nome','$departamento',$salariototal,$salario,$faltas,$horasextras,'$conveniomedico','$rg','$cpf')";
    
	$resultado=mysqli_query($conecta,$sql);
    if (!$resultado) echo "Erro na inserção";
    else{
		echo "Cadastrado com sucesso!\n";
		echo "Salário a receber:\n";
		echo "R$:".$salario;
	}
	mysqli_close($conecta);    
?>