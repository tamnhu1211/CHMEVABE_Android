<?php
include "connect.php";
$email = $_POST['email'];
$pass = $_POST['pass'];

$query = 'SELECT * FROM user WHERE email = '.$email.'AND pass ='.$pass.'';
$da = mysqli_query($conn, $query);
$result = array();

while ($row = mysqli_fetch_assoc($da)){
    $result[]=$row;
}

if(!empty($result)){
    $arr=[
        'success'=>true,
        'message'=>'thanhcong',
        'result'=> $result
    ];
}

print_r(Json_encode($arr));
?>
