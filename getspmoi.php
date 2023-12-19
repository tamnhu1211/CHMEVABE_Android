<?php
include "connect.php";
$query = "SELECT * FROM sanpham ORDER BY id DESC";
$da = mysqli_query($conn, $query);
$result = array();

while ($row = mysqli_fetch_assoc($da)){
    $result[]=$row;
}
// print_r($result);

if(!empty($result)){
    $arr=[
        'success'=>true,
        'message'=>'thanhcong',
        'result'=> $result
    ];
}
print_r(Json_encode($arr));
?>
