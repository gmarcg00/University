var pistas=6;
var diccionario;



function compruebaPalabras(){
    if(!existenLetras()){
        alert("No hay ninguna letra");
    }else{
        for(var i=1;i<=12;i++){
            compruebaFila(i);
        }
    }
}

function compruebaFila(fila){
    if(fila<7){
        switch(fila){
            case 1:
                if(palabraEscrita(1,4)){
                    var palabra=creaPalabra(1,4);
                    if(existePalabra(palabra)){
                        if((document.getElementById("letra1").value.toUpperCase()=="P") && (document.getElementById("letra2").value.toUpperCase()=="A") && (document.getElementById("letra3").value.toUpperCase()=="T") && (document.getElementById("letra4").value.toUpperCase()=="O")){
                            verdePalabra(1,4);
                        }else{
                            rojoPalabra(1,4);
                        }
                    }else{
                        alert("La palabra no está en el diccionario");
                        rojoPalabra(1,4);
                    } 
                }
                break; 
            case 2:
                if(palabraEscrita(5,4)){//Compruebo que hay una palabra escrita
                    var palabra=creaPalabra(5,4);
                    if(existePalabra(palabra)){//Compruebo que la palabra esta en el diccionario
                        if(palabraValidaPista(5,4)){//Compruebo que solo cambia una letra
                            verdePalabra(5,4);
                        }else{
                            alert("Solo puedes cambiar una letra la palabra anterior");
                            rojoPalabra(5,4);
                        }
                    }else{
                        rojoPalabra(5,4);
                        alert("La palabra no está en el diccionario");
                    }
                }
                break;
            case 3:
                if(palabraEscrita(9,4)){//Compruebo que hay una palabra escrita
                    var palabra=creaPalabra(9,4);
                    if(existePalabra(palabra)){//Compruebo que la palabra esta en el diccionario
                        if(palabraValida(9,4)){//Compruebo que la palabra tiene las mismas letras que la anterior
                            verdePalabra(9,4);
                        }else{
                            rojoPalabra(9,4);
                            alert("La palabra no contiene las mismas letras que la palabra anterior");
                        }  
                    }else{
                        rojoPalabra(9,4);
                        alert("La palabra no está en el diccionario");
                    }
                }
                break;
            case 4:
                if(palabraEscrita(13,4)){//Compruebo que hay una palabra escrita
                    var palabra=creaPalabra(13,4);
                    if(existePalabra(palabra)){//Compruebo que la palabra esta en el diccionario
                        if(palabraValidaPista(13,4)){//Compruebo que solo cambia una letra
                            verdePalabra(13,4);
                        }else{
                            alert("Solo puedes cambiar una letra de la palabra anterior");
                            rojoPalabra(13,4);
                        }
                    }else{
                        rojoPalabra(13,4);
                        alert("La palabra no está en el diccionario");
                    }
                }
                break;
            case 5:
                if(palabraEscrita(17,4)){//Compruebo que hay una palabra escrita
                    var palabra=creaPalabra(17,4);
                    if(existePalabra(palabra)){//Compruebo que la palabra esta en el diccionario
                        if(palabraValida(17,4)){//Compruebo que la palabra tiene las mismas letras que la anterior
                            verdePalabra(17,4);
                        }else{
                            rojoPalabra(17,4);
                            alert("La palabra no contiene las mismas letras que la palabra anterior");
                        }
                    }else{
                        rojoPalabra(17,4);
                        alert("La palabra no está en el diccionario");
                    }
                }
                break;
            case 6:
                if(palabraEscrita(21,4)){
                    var palabra=creaPalabra(21,4);
                    if(existePalabra(palabra)){//Compruebo que la palabra esta en el diccionario
                        if((document.getElementById("letra21").value.toUpperCase()=="M") && (document.getElementById("letra22").value.toUpperCase()=="O") && (document.getElementById("letra23").value.toUpperCase()=="T") && (document.getElementById("letra24").value.toUpperCase()=="O")){
                            verdePalabra(21,4);
                        }else{
                            rojoPalabra(21,4);
                        }
                    }else{
                        alert("La palabra no está en el diccionario");
                        rojoPalabra(21,4);
                    }
                }
                break;
            default:
        }  
    }else{
        switch(fila){
            case 7:
                if(palabraEscrita(25,6)){
                    var palabra=creaPalabra(25,6);
                    if(existePalabra(palabra)){//Compruebo que la palabra esta en el diccionario
                        if((document.getElementById("letra25").value.toUpperCase()=="M") && (document.getElementById("letra26").value.toUpperCase()=="A") && (document.getElementById("letra27").value.toUpperCase()=="R") && (document.getElementById("letra28").value.toUpperCase()=="I") && (document.getElementById("letra29").value.toUpperCase()=="N") && (document.getElementById("letra30").value.toUpperCase()=="O")){
                            verdePalabra(25,6);
                        }else{
                            rojoPalabra(25,6);
                        }
                    }else{
                        alert("La palabra no está en el diccionario");
                        rojoPalabra(25,6);
                    }
                }
                break;
            case 8:
                if(palabraEscrita(31,6)){
                    var palabra=creaPalabra(31,6);
                    if(existePalabra(palabra)){//Compruebo que la palabra esta en el diccionario
                        if(palabraValidaPista(31,6)){//Compruebo que solo cambia una letra
                            verdePalabra(31,6);
                        }else{
                            alert("La palabra debe tener 6 letras de la palabra anterior");
                            rojoPalabra(31,6);
                        }
                    }else{
                        alert("La palabra no está en el diccionario");
                        rojoPalabra(31,6);
                    }
                }
                break;
            case 9:
                if(palabraEscrita(37,6)){
                    var palabra=creaPalabra(37,6);
                    if(existePalabra(palabra)){//Compruebo que la palabra esta en el diccionario
                        if(palabraValida(37,6)){//Compruebo que la palabra tiene las mismas letras que la anterior
                            verdePalabra(37,6);
                        }else{
                            alert("La palabra debe tener 6 letras de la palabra anterior");
                            rojoPalabra(37,6);
                        }
                    }else{
                        alert("La palabra no está en el diccionario");
                        rojoPalabra(37,6); 
                    }
                }
                break;
            case 10:
                if(palabraEscrita(43,6)){
                    var palabra=creaPalabra(43,6);
                    if(existePalabra(palabra)){//Compruebo que la palabra esta en el diccionario
                        if(palabraValidaPista(43,6)){//Compruebo que solo cambia una letra
                            verdePalabra(43,6);
                        }else{
                            alert("La palabra no contiene las mismas letras que la palabra anterior");
                            rojoPalabra(43,6);
                        }
                    }else{
                        alert("La palabra no está en el diccionario");
                        rojoPalabra(43,6);
                    }
                }
                break;
            case 11:
                if(palabraEscrita(49,6)){
                    var palabra=creaPalabra(49,6);
                    if(existePalabra(palabra)){//Compruebo que la palabra esta en el diccionario
                        if(palabraValida(49,6)){//Compruebo que la palabra tiene las mismas letras que la anterior
                            verdePalabra(49,6);
                        }else{
                            alert("La palabra no contiene las mismas letras que la palabra anterior");
                            rojoPalabra(49,6);
                        }
                    }else{
                        alert("La palabra no está en el diccionario");
                        rojoPalabra(49,6);
                    }  
                }
                break;
            case 12:
                if(palabraEscrita(55,6)){
                    var palabra=creaPalabra(55,6);
                    if(existePalabra(palabra)){//Compruebo que la palabra esta en el diccionario
                        if((document.getElementById("letra55").value.toUpperCase()=="A") && (document.getElementById("letra56").value.toUpperCase()=="N") && (document.getElementById("letra57").value.toUpperCase()=="I") && (document.getElementById("letra58").value.toUpperCase()=="M") && (document.getElementById("letra59").value.toUpperCase()=="A") && (document.getElementById("letra60").value.toUpperCase()=="R")){
                            verdePalabra(55,6);
                        }else{
                            rojoPalabra(55,6);
                        }
                    }else{
                        alert("La palabra no está en el diccionario");
                        rojoPalabra(55,6);
                    }
                }
                break;
        }
    }
}

/*
*Comprueba que el usuario haya escrito una palabra completa en la fila
*
*/
function palabraEscrita(j,size){
    if(size==4){
        if((document.getElementById("letra"+j).value!="") && (document.getElementById("letra"+(j+1)).value!="") && (document.getElementById("letra"+(j+2)).value!="") && (document.getElementById("letra"+(j+3)).value!="")){
            return true;
        }else{
            return false;
        }
    }else{
        if((document.getElementById("letra"+j).value!="") && (document.getElementById("letra"+(j+1)).value!="") && (document.getElementById("letra"+(j+2)).value!="") && (document.getElementById("letra"+(j+3)).value!="") && (document.getElementById("letra"+(j+4)).value!="") && (document.getElementById("letra"+(j+5)).value!="")){
            return true;
        }
        else{
            return false;
        }
    }
}

function existePalabra(palabra){
    for(var i=0;i<diccionario.length;i++){
        if(palabra==diccionario[i]){
            return true;
        }
    }
    return false;
}



function solucionVerde(){
    for(var i=1;i<=60;i++){
        document.getElementById("2letra"+i).setAttribute("style","color:green ; width:30px ; height: 30px ;text-transform: uppercase ;font-weight: bold");
    }
}



function palabraValida(indiceLetra,tamPalabra){
    var n=0;
    var letraEncontrada=false;
    var tamMaximo=false;
    for(var i=indiceLetra;i<indiceLetra+tamPalabra;i++){
        letraEncontrada=false;
        for(var j=indiceLetra-1;j>((indiceLetra-1)-tamPalabra);j--){
            if(!tamMaximo){
                if(!letraEncontrada && document.getElementById("letra"+i).value==document.getElementById("letra"+j).value){
                    n++;
                    if(n<tamPalabra){
                        letraEncontrada=true;
                    }else{
                        tamMaximo=true;
                    }  
                }
            }else{
                return tamMaximo;
            } 
        }
    }
    return tamMaximo;
}

function palabraValidaPista(indiceLetra,tamPalabra){
    var n=0;
    for(var i=indiceLetra;i<indiceLetra+tamPalabra;i++){
        if(document.getElementById("letra"+i).value==document.getElementById("letra"+(i-tamPalabra)).value){
            n++;
            if(!(n<tamPalabra-1)){
               return true;
            }
        } 
    }
    return false;
}

function existenLetras(){
    for(var i=1;i<61;i++){
        if(document.getElementById("letra"+i).value!=""){
            return true;
        }
    }
    return false;
}

function limpiaPalabras(){
    var x=false;
    for(var i=1;i<61;i++){
        var letra=document.getElementById("letra"+i);
        if(letra.value!=""){
            letra.value="";
            letra.setAttribute("style","color:black ; width:30px ; height: 30px ;text-transform: uppercase")
            x=true;
        }
    }
    if(!x){
        window.alert("Todas las casillas están libres");
    }
}

function buscarPistas(tam1,tam2){
    var palabrasBusquedaOrdenadas="";
    if(document.getElementById("cuadroPista").value!=""){
        palabrasBusquedaOrdenadas+=buscarPista(tam1);
        if(palabrasBusquedaOrdenadas!=""){
            palabrasBusquedaOrdenadas+=buscarPista(tam2);
            document.getElementById("cuadroPista").value=palabrasBusquedaOrdenadas;
        }
        
    }else{
        alert("Introduzca alguna letra");
    }
   
    
}

function buscarPista(tam){
    if(pistas>0){
        var palabrasBusqueda="";
        var n=0;
        var pista=document.getElementById("cuadroPista").value;
        for(var i=0;i<diccionario.length;i++){
            n=0;
            for(var j=0;j<pista.length;j++){
                if(diccionario[i].length==tam){
                    if(diccionario[i].includes(pista[j])){
                        n++;
                    }
                }
            }
            if(n==pista.length){
                palabrasBusqueda+=diccionario[i];
                palabrasBusqueda+=", ";
            }
        }
        pistas--;
        return palabrasBusqueda;

    }else{
        window.alert("Ya has pedido 3 pistas");
        return "";
    }
    
}

function limpiarPista(){
    var x=false;
    if(document.getElementById("cuadroPista").value!=""){
        document.getElementById("cuadroPista").value="";
        x=true;
    }

    if(!x){
        alert("No hay ninguna pista escrita");
    }
}


function guardaDiccionario(data) {
    diccionario = data.split("\n");
    diccionario.push("nace");
    diccionario.push("nací");
    diccionario.push("remato");
    diccionario.push("tolero");
    diccionario.push("cuadre");
    diccionario.push("imanas");
    diccionario.push("animas");
}

function verdePalabra(j,tam){
    for(var i=j;i<j+tam;i++){
        document.getElementById("letra"+i).setAttribute("style","color:green ; width:30px ; height: 30px ;text-transform: uppercase ;font-weight: bold");
    }
}

function rojoPalabra(j,tam){
    for(var i=j;i<j+tam;i++){
        document.getElementById("letra"+i).setAttribute("style","color:red ; width:30px ; height: 30px ;text-transform: uppercase ;font-weight: bold");
    }
}

function creaPalabra(j,tam){
    var palabra="";
    for(var i=j;i<j+tam;i++){
        palabra+=document.getElementById("letra"+i).value.toLowerCase();
    }
    return palabra;
}

function guardarPartida() {
    for(let i=1;i<61;i++){
      localStorage.setItem(("letra"+i),document.getElementById("letra"+ i).value);
    }
}

function cargarPartida(){
    for(let i = 1;i<61;i++){
      document.getElementById(("letra"+i)).value = localStorage.getItem("letra"+ i);
    }
}

function borrarPartida(){
    for(let i = 1;i < 61; i++){
      localStorage.setItem(("letra"+i),"");
    }
}

function muestraSolucion(){
    var elems=document.getElementById("table2").style.display="block";
    document.getElementById("2letra1").value="P";
    document.getElementById("2letra2").value="A";
    document.getElementById("2letra3").value="T";
    document.getElementById("2letra4").value="O";

    document.getElementById("2letra5").value="G";
    document.getElementById("2letra6").value="A";
    document.getElementById("2letra7").value="T";
    document.getElementById("2letra8").value="O";

    document.getElementById("2letra9").value="T";
    document.getElementById("2letra10").value="O";
    document.getElementById("2letra11").value="G";
    document.getElementById("2letra12").value="A";

    document.getElementById("2letra13").value="T";
    document.getElementById("2letra14").value="O";
    document.getElementById("2letra15").value="M";
    document.getElementById("2letra16").value="A";

    document.getElementById("2letra17").value="M";
    document.getElementById("2letra18").value="O";
    document.getElementById("2letra19").value="T";
    document.getElementById("2letra20").value="A";

    document.getElementById("2letra21").value="M";
    document.getElementById("2letra22").value="O";
    document.getElementById("2letra23").value="T";
    document.getElementById("2letra24").value="O";

    document.getElementById("2letra25").value="M";
    document.getElementById("2letra26").value="A";
    document.getElementById("2letra27").value="R";
    document.getElementById("2letra28").value="I";
    document.getElementById("2letra29").value="N";
    document.getElementById("2letra30").value="O";

    document.getElementById("2letra31").value="M";
    document.getElementById("2letra32").value="A";
    document.getElementById("2letra33").value="R";
    document.getElementById("2letra34").value="I";
    document.getElementById("2letra35").value="N";
    document.getElementById("2letra36").value="A";

    document.getElementById("2letra37").value="I";
    document.getElementById("2letra38").value="M";
    document.getElementById("2letra39").value="A";
    document.getElementById("2letra40").value="N";
    document.getElementById("2letra41").value="A";
    document.getElementById("2letra42").value="R";

    document.getElementById("2letra43").value="I";
    document.getElementById("2letra44").value="M";
    document.getElementById("2letra45").value="A";
    document.getElementById("2letra46").value="N";
    document.getElementById("2letra47").value="A";
    document.getElementById("2letra48").value="S";

    document.getElementById("2letra49").value="A";
    document.getElementById("2letra50").value="N";
    document.getElementById("2letra51").value="I";
    document.getElementById("2letra52").value="M";
    document.getElementById("2letra53").value="A";
    document.getElementById("2letra54").value="S";

    document.getElementById("2letra55").value="A";
    document.getElementById("2letra56").value="N";
    document.getElementById("2letra57").value="I";
    document.getElementById("2letra58").value="M";
    document.getElementById("2letra59").value="A";
    document.getElementById("2letra60").value="R";

    solucionVerde();
}

/* ésto comprueba la localStorage si ya tiene la variable guardada */
function compruebaAceptaCookies() {
    if(localStorage.aceptaCookies == 'true'){
      cajacookies.style.display = 'none';
    }
}
  
  /* aquí guardamos la variable de que se ha
  aceptado el uso de cookies así no mostraremos
  el mensaje de nuevo */
  function aceptarCookies() {
    localStorage.aceptaCookies = 'true';
    cajacookies.style.display = 'none';
  }
  

fetch('https://ordenalfabetix.unileon.es/aw/diccionario.txt')
  .then(response => response.text())
  .then(data => {
    guardaDiccionario(data)
  })
  .catch(error => console.log(error))