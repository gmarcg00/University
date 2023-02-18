
function nuevoUsuario(){
    alert("holaa");
    var nombre=document.getElementById("name").value;
    var email=document.getElementById("email").value;
    var contraseña=document.getElementById("contraseña").value;

    alert(nombre);

    var conexion=Conexion();

    var sentencia="INSERT INTO usuario (idUsuario,nombreKiosco,Nombre,Apellidos,Puntos,nombreUsuario,contraseña) VALUES(?,?,?,?,?,?,?)";
    var valores=[3,"Crome","Nacho","Varane",0,"rafaRM","me voy"];
    var results={};
    var fields={};

    conexion.query(sentencia,valores, function (error, results, fields) {
        if (error)
            throw error;
    });
    
}