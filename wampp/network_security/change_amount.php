<?php
  require "conn.php";
  $password = $_POST["password"];
  $id = $_POST["id"];
  $amount = $_POST['amount'];
  $sql = "select password,email,id,amount from card where id='$id' and password='$password'";
  $result = mysqli_query($conn,$sql);
  if(mysqli_num_rows($result)>0){
    $rows = mysqli_fetch_assoc($result);
    $amoun = $rows['amount']-$amount;
    $query = "update card set amount='$amoun' where id='$id'";
    $result = mysqli_query($conn,$query);
    echo json_encode($array = array("otp"=>"failed"));
  }
  else{
    echo json_encode($array = array("otp"=>"failed"));
  }
  ?>
