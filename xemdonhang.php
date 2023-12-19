<?php
include "connect.php";
//include  "donhang.php";
//$id = $_POST['id'];
$id = $_POST['id'];


//$id= 153;
//INNER JOIN products ON products.id = order_detail.product_id
//WHERE order_id = ".$_GET['id'];
$query = "SELECT id FROM `donhang` ORDER BY id DESC LIMIT 1";
//$query = "SELECT * FROM donhang INNER JOIN donhang.id = chitietdonhang.iddonhang WHERE id = ".$_GET['id'];
$da = mysqli_query($conn, $query);
$result = array();

while ($row = mysqli_fetch_assoc($da)){
    //echo ($row['id']);
    $tr = "SELECT * FROM chitietdonhang WHERE iddonhang = '".$row['id']."'";
    $da1 = mysqli_query($conn, $tr);
    $item = array();
    while ($row1 = mysqli_fetch_assoc($da1)){
        $item[]=$row1;
    }

    $row['item'] = $item;
    $result[] = ($row);
    
}
 //var_dump($result);
if(!empty($result)){
    $arr=[
        'success'=>true,
        'message'=>'thanhcong',
        'result'=> $result
    ];
}else{
    $arr=[
        'success'=>false,
        'message'=>'khongthanhcong',
        'result'=> $result
    ];
}
print_r(json_encode($arr));
?>
