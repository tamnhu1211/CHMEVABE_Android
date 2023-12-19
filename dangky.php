<?php
include "connect.php";
$email = $_POST['email'];
$pass = $_POST['pass'];
$ten = $_POST['ten'];
$sdt = $_POST['sdt'];

$query = 'SELECT * FROM user WHERE email = '.$email.'';
$da = mysqli_query($conn, $query);
$num = mysqli_num_rows($da);
if ($num > 0 ){
    $arr = [
        'success'=> false,
        'message'=>'Email da ton tai'
    ];
}
//$query = 'SELECT * FROM sanpham WHERE loai='.$loai.' LIMIT '.$page.','.$total.'';

else{
$query = 'INSERT INTO user (email,pass,ten,sdt) VALUES ('.$email.','.$pass.','.$ten.','.$sdt.')';
$da = mysqli_query($conn, $query);
//$result = array();

// while ($row = mysqli_fetch_assoc($da)){
//     $result[]=$row;
//}
// print_r($result);

if($da==true){
    $arr=[
        'success'=>true,
        'message'=>'thanhcong',
        'result'=> $result
    ];
}
}
print_r(Json_encode($arr));
?>
