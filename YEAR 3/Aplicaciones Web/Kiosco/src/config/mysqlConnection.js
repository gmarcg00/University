const mysql=require('mysql');

module.exports= () =>{
    return mysql.createConnection({
        host: 'localhost',
        database: 'kiosco',
        user: 'root',
        password: 'Autoking00',
    });
}