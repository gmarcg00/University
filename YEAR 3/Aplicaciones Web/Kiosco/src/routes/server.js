const express = require('express');
const router = express.Router();
const mysqlConnection =require('../config/mysqlConnection');
var sesionUsuario;
var datosCromos;
var datosColeccion;


const conecction=mysqlConnection();

router.get('/',(req,res) => {
    res.render('Kiosco.ejs',{title:'Kiosco'});
});

router.get('/Registrarse',(req,res) => {
    res.render('Registrarse.ejs', { title: 'Pasatiempos'});
});

router.get('/Kiosco-Inicio',(req,res) => {
    res.render('Kiosco-InicioSesion.ejs', { datos: sesionUsuario});
});

router.get('/IniciarSesion',(req,res) => {
    res.render('Iniciar-Sesion.ejs', { title: 'Pasatiempos'});
});

router.get('/Contacto',(req,res) => {
    res.render('Contacto.ejs');
});

router.get('/Contacto-Inicio',(req,res) => {
    res.render('Contacto-InicioSesion.ejs', {datos: sesionUsuario});
});

router.get('/Perfil',(req,res) => {
    res.render('Perfil.ejs', {datos: sesionUsuario});
});

router.get('/Pasatiempo',(req,res) => {
    res.render('Pasatiempo2.ejs', {datos: sesionUsuario});
});


router.get('/ColeccionFutbol',(req,res) => {
    sql="SELECT * FROM coleccion WHERE Nombre=?"
    var nombre="Futbol";
    conecction.query(sql,nombre,(err,rows)=>{
        if(err) throw err;
        idColeccion=rows[0].idColeccion;
        datosColeccion=rows;
        sql2="SELECT * FROM cromo WHERE idColeccion=?"
        conecction.query(sql2,idColeccion,(err,rows2)=>{
            if(err) throw err;
            datosCromos=rows2;
            res.render('ColeccionFutbol.ejs', { dataColeccion: datosColeccion , dataCromos: datosCromos });
        })
        
    })
});

router.get('/ColeccionNBA',(req,res) => {
    sql="SELECT * FROM coleccion WHERE Nombre=?"
    var nombre="NBA";
    conecction.query(sql,nombre,(err,rows)=>{
        if(err) throw err;
        idColeccion=rows[0].idColeccion;
        datosColeccion=rows;
        sql2="SELECT * FROM cromo WHERE idColeccion=?"
        conecction.query(sql2,idColeccion,(err,rows2)=>{
            if(err) throw err;
            datosCromos=rows2;
            res.render('ColeccionNBA.ejs', { dataColeccion: datosColeccion , dataCromos: datosCromos });
        })
        
    })
});

router.get('/ColeccionPokemon',(req,res) => {
    sql="SELECT * FROM coleccion WHERE Nombre=?"
    var nombre="Pokemon";
    conecction.query(sql,nombre,(err,rows)=>{
        if(err) throw err;
        idColeccion=rows[0].idColeccion;
        datosColeccion=rows;
        sql2="SELECT * FROM cromo WHERE idColeccion=?"
        conecction.query(sql2,idColeccion,(err,rows2)=>{
            if(err) throw err;
            datosCromos=rows2;
            res.render('ColeccionPokemon.ejs', { dataColeccion: datosColeccion , dataCromos: datosCromos });
        })
        
    })
});

router.get('/ColeccionAnimales',(req,res) => {
    sql="SELECT * FROM coleccion WHERE Nombre=?"
    var nombre="Animales";
    conecction.query(sql,nombre,(err,rows)=>{
        if(err) throw err;
        idColeccion=rows[0].idColeccion;
        datosColeccion=rows;
        sql2="SELECT * FROM cromo WHERE idColeccion=?"
        conecction.query(sql2,idColeccion,(err,rows2)=>{
            if(err) throw err;
            dataCromos=rows2;
            res.render('ColeccionAnimales.ejs', { dataColeccion: datosColeccion , dataCromos: datosCromos});
        })
        
    })
});


router.post('/Registrarse',(req,res) => {
    conecction.query('SELECT * FROM usuario WHERE nombreUsuario=? AND contraseña=?',[req.body.userName,req.body.password],(err,rows)=>{
        if(err) throw err;
        if((rows==0)){
            var idConsulta="SELECT max(idUsuario) from usuario";
            conecction.query(idConsulta,(err,rows)=>{
                if(err) throw err;
                var id=rows[0].idUsuario+1
                console.log(rows);
                var sql="INSERT INTO usuario (idUsuario,nombreKiosco,Nombre,Apellidos,Puntos,nombreUsuario,contraseña) VALUES (?,?,?,?,?,?,?)";
                var values=[
                    id,"Crome",req.body.userName,req.body.Apellidos,0,req.body.userName,req.body.password,
                ];
                conecction.query(sql,values,(err,rows)=>{
                    if(err) throw err;
                    res.render('Kiosco-InicioSesion.ejs',{datos: values});
                })
            }) 
        }else{
            res.send("Ya existe un usuario con ese nombre");
        }
    })

});

router.post('/IniciarSesion',(req,res)=>{
   conecction.query("SELECT * FROM usuario WHERE nombreUsuario=? AND contraseña=?",[req.body.userName,req.body.password],(err,rows)=>{
    if(err) throw err;
    if(rows==0){
        //El usuario introduce mal los datos
    }else{
        sesionUsuario=rows;
        res.render('Kiosco-InicioSesion.ejs',{datos: rows});
    }
   })
});

router.get('/ColeccionNBA-SesionIniciada',(req,res) => {
    sql="SELECT * FROM coleccion WHERE Nombre=?"
    var nombre="NBA";
    conecction.query(sql,nombre,(err,rows)=>{
        if(err) throw err;
        idColeccion=rows[0].idColeccion;
        datosColeccion=rows;
        sql2="SELECT * FROM cromo WHERE idColeccion=?"
        conecction.query(sql2,idColeccion,(err,rows2)=>{
            if(err) throw err;
            datosCromos=rows2;
            console.log(datosCromos)
            res.render('ColeccionNBA-SesionIniciada.ejs', { datos: sesionUsuario, dataColeccion: datosColeccion , dataCromos: datosCromos });
        })
        
    })
});

router.get('/ColeccionFutbol-SesionIniciada',(req,res) => {
    sql="SELECT * FROM coleccion WHERE Nombre=?"
    var nombre="Futbol";
    conecction.query(sql,nombre,(err,rows)=>{
        if(err) throw err;
        idColeccion=rows[0].idColeccion;
        datosColeccion=rows;
        sql2="SELECT * FROM cromo WHERE idColeccion=?"
        conecction.query(sql2,idColeccion,(err,rows2)=>{
            if(err) throw err;
            datosCromos=rows2;
            console.log(datosCromos)
            res.render('ColeccionFutbol-SesionIniciada.ejs', { datos: sesionUsuario, dataColeccion: datosColeccion , dataCromos: datosCromos });
        })
        
    })
});


router.get('/ColeccionPokemon-SesionIniciada',(req,res) => {
    sql="SELECT * FROM coleccion WHERE Nombre=?"
    var nombre="Pokemon";
    conecction.query(sql,nombre,(err,rows)=>{
        if(err) throw err;
        idColeccion=rows[0].idColeccion;
        datosColeccion=rows;
        sql2="SELECT * FROM cromo WHERE idColeccion=?"
        conecction.query(sql2,idColeccion,(err,rows2)=>{
            if(err) throw err;
            datosCromos=rows2;
            console.log(datosCromos)
            res.render('ColeccionPokemon-SesionIniciada.ejs', { datos: sesionUsuario, dataColeccion: datosColeccion , dataCromos: datosCromos });
        })
        
    })
});

router.get('/ColeccionAnimales-SesionIniciada',(req,res) => {
    sql="SELECT * FROM coleccion WHERE Nombre=?"
    var nombre="Animales";
    conecction.query(sql,nombre,(err,rows)=>{
        if(err) throw err;
        idColeccion=rows[0].idColeccion;
        datosColeccion=rows;
        sql2="SELECT * FROM cromo WHERE idColeccion=?"
        conecction.query(sql2,idColeccion,(err,rows2)=>{
            if(err) throw err;
            datosCromos=rows2;
            console.log(datosCromos)
            res.render('ColeccionAnimales-SesionIniciada.ejs', { datos: sesionUsuario, dataColeccion: datosColeccion , dataCromos: datosCromos });
        })
        
    })
});











router.get('/Animales',(req,res) => {
    res.render('Animales.html', { title: 'Pasatiempos 1'});
});

router.get('/Futbol',(req,res) => {
    res.render('Futbol.html', { title: 'Pasatiempos'});
});

router.get('/Pokemon',(req,res) => {
    res.render('Pokemon.html', { title: 'Pasatiempos'});
});

router.get('/Compra-Cromos',(req,res) => {
    res.render('Compra-Cromos.html', { title: 'Pasatiempos'});
});

router.get('/Compra_Coleccion',(req,res) => {
    res.render('Compra_Coleccion.html', { title: 'Pasatiempos'});
});

router.get('/Cuenta',(req,res) => {
    res.render('Cuenta.html', { title: 'Pasatiempos'});
});

router.get('/fichero',(req,res) => {
    res.render('fichero.html', { title: 'Pasatiempos'});
});

module.exports = router;