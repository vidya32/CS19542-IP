<?php
include 'config.php';

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $empid = $_POST['empid'];
    $ename = $_POST['ename'];
    $desig = $_POST['desig'];
    $dept = $_POST['dept'];
    $doj = $_POST['doj'];
    $salary = $_POST['salary'];

    $check_sql = "SELECT * FROM EMPDETAILS WHERE EMPID = ?";
    $stmt = $conn->prepare($check_sql);
    $stmt->bind_param("i", $empid);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($result->num_rows > 0) {
        $sql = "UPDATE EMPDETAILS SET ENAME = ?, DESIG = ?, DEPT = ?, DOJ = ?, SALARY = ? WHERE EMPID = ?";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param("ssssdi", $ename, $desig, $dept, $doj, $salary, $empid);
    } else {
        $sql = "INSERT INTO EMPDETAILS (EMPID, ENAME, DESIG, DEPT, DOJ, SALARY) VALUES (?, ?, ?, ?, ?, ?)";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param("issssd", $empid, $ename, $desig, $dept, $doj, $salary);
    }

    if ($stmt->execute()) {
        echo "Employee details saved successfully.";
    } else {
        echo "Error: " . $stmt->error;
    }

    $stmt->close();
}

$conn->close();
?>
