<?php
include "connect.php";
$page = $_POST['page'];
$total = 5;
$pos = ($page-1)*$total;
$loai = $_POST['loai'];

$query = 'SELECT * FROM sanpham WHERE loai='.$loai.' LIMIT '.$page.','.$total.'';
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
print_r(json_encode($arr));
?>
