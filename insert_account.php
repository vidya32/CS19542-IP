<?php
include 'db_connection.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $atype = $_POST['atype'];
    $balance = $_POST['balance'];
    $cid = $_POST['cid'];

    // Validate inputs
    if (empty($balance) || !is_numeric($balance) || empty($cid) || !is_numeric($cid)) {
        echo "All fields are required and must be valid.";
    } else {
        // Insert into ACCOUNT table
        $stmt = $conn->prepare("INSERT INTO ACCOUNT (ATYPE, BALANCE, CID) VALUES (?, ?, ?)");
        $stmt->bind_param("sdi", $atype, $balance, $cid);

        if ($stmt->execute()) {
            echo "Account added successfully.";
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
    <title>Insert Account</title>
</head>
<body>
    <h2>Add New Account</h2>
    <form method="post" action="insert_account.php">
        <label for="atype">Account Type (S/C):</label>
        <input type="text" id="atype" name="atype" maxlength="1" required>
        <br><br>
        <label for="balance">Initial Balance:</label>
        <input type="number" id="balance" name="balance" step="0.01" required>
        <br><br>
        <label for="cid">Customer ID:</label>
        <input type="number" id="cid" name="cid" required>
        <br><br>
        <input type="submit" value="Add Account">
    </form>
</body>
</html>
