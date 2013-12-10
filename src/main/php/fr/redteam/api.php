<?php
require('dbhelper.php');

if (isset($_GET['q'])) {
	$query = $_GET['q'];
	$db = new DBHelper('zara_20131210.sqlite');
	
	if (isset($_GET['filters']) && !empty($_GET['filters'])) {
		$filters = explode(",", $_GET['filters']);
		$db->addFilters($filters);
	}
	
	$json = $db->findAll($query);

	exit($json);
}

if (isset($_GET['id'])) {
	$db = new DBHelper('zara_20131210.sqlite');
	$json = $db->findClotheById($_GET['id']);
	
	exit($json);
}
?>