<html>
<body>

</body>
<head>
	<title>Registration Form</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

    <h2>Registration Form</h2>

    <form action="create_php.php" method="POST">
      Name:  <input type="text" name="name"> <br><br>
      Email:   <input type="text" name="email"> <br><br>
      Id:   <input type="text" name="id"> <br><br>
      Password:   <input type="text" name="password"><br> <br>
      Amount:   <input type="number" name="amount"> <br><br>
			Phone:   <input type="text" name="phone"> <br><br>
      <input type="hidden" name="form_submitted" value="1" /><br>
      <input type="submit" value="submit">

    </form>
</body>
</html>
