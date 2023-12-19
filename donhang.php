<?php
include "connect.php";
$sdt = $_POST['sdt'];
$tong = $_POST['tong'];
$ten = $_POST['ten'];
//$iduser = $_POST['iduser'];
$diachi = $_POST['diachi'];
$sl = $_POST['sl'];
$chitiet= $_POST['chitiet'];
$id =$_POST['id'];

$query =  "INSERT INTO `donhang` ( `id`,`ten`, `diachi`, `sdt`, `sl`, `tong`)
 VALUES (NULL,'".$ten."','".$diachi."','".$sdt."','".$sl."','".$tong."' )";
$da = mysqli_query($conn, $query);

if ($da == true){
    $query ="SELECT id AS iddonghang FROM `donhang` WHERE ten = '".$ten."' ORDER BY id DESC LIMIT 1 ";   
    $da = mysqli_query($conn, $query);

    while ($row = mysqli_fetch_assoc($da)){
        $iddonhang =($row);
    }
     
    if(!empty($iddonhang)){
        $chitiet = json_decode($chitiet, true);
        //var_dump ($chitiet);
        foreach ($chitiet as $key => $value) {
            $tr ="INSERT INTO chitietdonhang (iddonhang,id,tensp,hinhsp,soluong,giasp) VALUES
             ('".$iddonhang['iddonghang']."','".$value["id"]."','".$value["tensp"]."','".$value["hinhsp"]."','".$value["soluong"]."','".$value["giasp"]."')";
            $da = mysqli_query($conn, $tr);    
        }
        if ($da == true){
            $arr=[
                'success'=>true,
                'message'=>"thanhcong"
                //'result'=> $result
            ];
        }
        //print_r(json_encode($arr));
    else{
        $arr=[
            'success'=>false,
            'message'=>'khong'
            //'result'=> $result
        ];
        //print_r(json_encode($arr));
    }
}

}else{
        $arr=[
            'success'=>false,
            'message'=>'khongthanhcong'
            //'result'=> $result
        ];
        
    }
    print_r(json_encode($arr));
//  [{"giasp":546,"hinhsp":"https://tse4.mm.bing.net/th?id\u003dOIP.4Bw945OM10JEPve8o8eopAHaJ4\u0026pid\u003dApi\u0026P\u003d0\u0026h\u003d220",
//    "id":44,"soluong":1,"tensp":"đầm mẹ bầu màu xám tiện "}]





?>
