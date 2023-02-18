const express=require('express');
const app=express();// En app guardamos el servidor
const bodyParser=require('body-parser');

const path=require('path');//Para saber si estamos en windows o linux (/ o \)
const { application } = require('express');

app.set('port',8080);//Guardamos en la variable port del servidor el numero del puerto
app.set('view engine', 'ejs');//Para poder tratar ficheros ejs
app.engine('html',require('ejs').renderFile);//Para tratar los html como ejs
app.set('views',path.join(__dirname,'views'));//Unimos la ruta del fichero con views

//middlewares
app.use(bodyParser.urlencoded({extended: true}));//Para poder pasar datos del ejs al js 

//routes
app.use(require('./routes/server.js'));//Para usar el fichero de rutas 

//static files
app.use(express.static(path.join(__dirname,'publica')));//Cualquier arhivo dentro de esa carpeta va a poder ser accedido desde el navegador

//Le decimos al servidor que escuche en el puerto 8080 (app.get('port'))
app.listen(app.get('port'),()=>{
    console.log("Servidor en el puerto",app.get('port'));
});

