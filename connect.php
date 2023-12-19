<?php
$host = "localhost";
$user = "root";
$pass = "";
$data = "android";

$conn = mysqli_connect($host, $user, $pass, $data);
mysqli_set_charset($conn,"utf8");
if($conn){
    // echo "ok";
}
?>