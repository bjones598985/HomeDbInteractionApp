<?php
define('DB_USER', "root"); // db user
define('DB_PASSWORD', ); // db password (mention your db password here)
define('DB_DATABASE', "cardio_log"); // database name
define('DB_SERVER', "www.robertjones.com"); // db server
/**
 * * A class file to connect to database
*/
class DB_CONNECT {
    
    
    function _construct() {
        //connecting to database
        $this->connect();
    }
    
    function _destruct() {
        //closing db connection
        $this->close();
    }
    
    //function to connect with database
    function connect() {
        //import database connection variables
        require_once '/Config.php';
        
        //connecting to mysql db
        //$conn = mysql_connect(DB_SERVER, DB_USER, DB_PASSWORD) or die(mysql_error());
        $conn = new PDO("mysql:host=" . DB_SERVER . ";dbname=DB_DATABASE", DB_USER, DB_PASSWORD);
        $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        //selecting db
        //$db = mysql_select_db(DB_DATABASE) or die(mysql_error()) or die(mysql_error());
        
        //return connection
        return $conn;
    }
    
    //closing the db connection
    function close() {
        mysql_close();
    }
}



?>