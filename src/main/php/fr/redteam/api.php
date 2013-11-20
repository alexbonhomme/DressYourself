<?php
/**
 * API usage (examples):
 * 
 *   api.php?model='jeans'
 */

require('dbhelper.php');

if (isset($_GET['model'])) {
	$db = new DBHelper('zara_20131110.sqlite');

	exit($db->findClothesByModel($_GET['model']));
}

?>