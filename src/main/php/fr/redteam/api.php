<?php
/**
 * API usage (examples):
 * 
 *   api.php?model='jeans'
 */

require('dbhelper.php');

if (isset($_GET['model'])) {
	$db = new DBHelper('zara_20131121.sqlite');

	exit($db->findClothesByModel($_GET['model']));
}

if (isset($_GET['id'])) {
	$db = new DBHelper('zara_20131121.sqlite');

	exit($db->findClotheById($_GET['id']));
}

?>