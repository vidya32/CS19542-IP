<?php
include 'db_connection.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $cname = trim($_POST['cname']);

    // Basic validation
    if (empty($cname)) {
        echo "Customer name is required.";
    } else {
        // Insert into CUSTOMER table
        $stmt = $conn->prepare("INSERT INTO CUSTOMER (CNAME) VALUES (?)");
        $stmt->bind_param("s", $cname);

        if ($stmt->execute()) {
            echo "Customer added successfully.";
        } else {
            echo "Error: " . $stmt->error;
        }
        $stmt->close();
    }
}
?>

<!DOCTYPE html>
<html>
<head>
    <title>Insert Customer</title>
</head>
<body>
    <h2>Add New Customer</h2>
    <form method="post" action="insert_customer.php">
        <label for="cname">Customer Name:</label>
        <input type="text" id="cname" name="cname" required>
        <br><br>
        <input type="submit" value="Add Customer">
    </form>
</body>
</html>
