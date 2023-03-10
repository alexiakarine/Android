<?php
$conecta=mysqli_connect("localhost","root","vertrigo","cadastro");
$sql="select * from usuario";
$resultado=mysqli_query($conecta,$sql);
while($linha = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){
	echo $linha["nome"]."\n";
}
mysqli_close($conecta); 
?>



