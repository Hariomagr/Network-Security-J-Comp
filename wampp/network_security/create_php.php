<?php
  require "conn.php";
  $name = $_POST["name"];
  $password = $_POST["password"];
  $email = $_POST["email"];
  $id = $_POST["id"];
  $amount = $_POST["amount"];
  $iid = hash('sha256', $_POST['id']);
  $ppass = hash('sha256', $_POST['password']);
  $phone = $_POST["phone"];
  $sql = "insert into card values('$email','$iid','$ppass','$name','$amount','$phone')";
  $result = mysqli_query($conn,$sql);
  echo $result;
   ?>
