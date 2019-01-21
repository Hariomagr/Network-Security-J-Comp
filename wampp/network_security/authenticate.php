<?php
  require "conn.php";
  $password = $_POST["password"];
  $id = $_POST["id"];
  $sql = "select password,email,id,number from card where id='$id' and password='$password'";
  $result = mysqli_query($conn,$sql);
  if(mysqli_num_rows($result)>0){
    $rows = mysqli_fetch_assoc($result);
    $conc ="%";
    $res = $rows['email'].$conc;
    $ress=$res.$rows['number'];
    echo json_encode($array = array("otp"=>$ress));
  }
  else{
    echo json_encode($array = array("otp"=>"failed%xxx"));
  }
  ?>
