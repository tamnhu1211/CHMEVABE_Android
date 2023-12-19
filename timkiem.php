<?php
include "connect.php";
$search = $_POST['search'];
if (empty($search)){
    $arr=[
        'success'=> false,
        'message'=>'khongthanhcong'
        //'result'=> $result
    ];
}else{
    $query= "SELECT * FROM sanpham WHERE tensp LIKE '%".$search."%'";
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
    print_r(json_encode($arr));    
}