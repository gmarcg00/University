var url = 'https://ordenalfabetix.unileon.es/aw/diccionario.txt';
var array;
var botonResolver = document.getElementById('boton');
var botonPistas = document.getElementById('generarPistas');
var numeroPistas = 3;

fetch(url)
    .then(response => response.text())
    .then(data => {
        guardarPalabras(data);
    })
    .catch(error => console.log(error))

function guardarPalabras(datos) {
    array = datos.split("\n");
    array.push("nace");
    array.push("nací");
    array.push("remato");
    array.push("tolero");
}

function eliminarAcentos(str) {
    return str.normalize("NFD").replace(/[\u0300-\u036f]/g, "");
}

function limpiar() {
    for (let i = 1; i <= 60; i++) {
        document.getElementById("letra" + i).value = "";
        document.getElementById("letra" + i).setAttribute('style', 'background:#FFFFFF');
    }
}

function comprobarFilas() {
    comprobarFilasPrediseñadas();
    comprobarFilasIntermedias();
}

function comprobarFilasIntermedias() {
    filasDeCuatro();
    ultFilaDeCuatro();
    filasDeSeis();
    ultFilaDeSeis();
}

function filasDeCuatro() {
    var contador = 0;
    for (let i = 1; i < 14; i += 4) {
        var palabraArriba = document.getElementById("letra" + i).value.toLowerCase() +
            document.getElementById("letra" + (i + 1)).value.toLowerCase() +
            document.getElementById("letra" + (i + 2)).value.toLowerCase() +
            document.getElementById("letra" + (i + 3)).value.toLowerCase();

        var palabraAbajo = document.getElementById("letra" + (i + 4)).value.toLowerCase() +
            document.getElementById("letra" + (i + 5)).value.toLowerCase() +
            document.getElementById("letra" + (i + 6)).value.toLowerCase() +
            document.getElementById("letra" + (i + 7)).value.toLowerCase();

        if (contador % 2 == 0) {
            if (comprobarExiste(palabraAbajo) && tresLetrasIguales(palabraArriba, palabraAbajo)) {
                document.getElementById("letra" + (i + 4)).setAttribute('style', 'background:#47FF33');
                document.getElementById("letra" + (i + 5)).setAttribute('style', 'background:#47FF33');
                document.getElementById("letra" + (i + 6)).setAttribute('style', 'background:#47FF33');
                document.getElementById("letra" + (i + 7)).setAttribute('style', 'background:#47FF33');
            } else {
                document.getElementById("letra" + (i + 4)).setAttribute('style', 'background:#FF4500');
                document.getElementById("letra" + (i + 5)).setAttribute('style', 'background:#FF4500');
                document.getElementById("letra" + (i + 6)).setAttribute('style', 'background:#FF4500');
                document.getElementById("letra" + (i + 7)).setAttribute('style', 'background:#FF4500');
            }
        } else {
            if (comprobarExiste(palabraAbajo) && cuatroLetrasIguales(palabraArriba, palabraAbajo)) {
                document.getElementById("letra" + (i + 4)).setAttribute('style', 'background:#47FF33');
                document.getElementById("letra" + (i + 5)).setAttribute('style', 'background:#47FF33');
                document.getElementById("letra" + (i + 6)).setAttribute('style', 'background:#47FF33');
                document.getElementById("letra" + (i + 7)).setAttribute('style', 'background:#47FF33');
            } else {
                document.getElementById("letra" + (i + 4)).setAttribute('style', 'background:#FF4500');
                document.getElementById("letra" + (i + 5)).setAttribute('style', 'background:#FF4500');
                document.getElementById("letra" + (i + 6)).setAttribute('style', 'background:#FF4500');
                document.getElementById("letra" + (i + 7)).setAttribute('style', 'background:#FF4500');
            }
        }
        contador++;
    }
}

function ultFilaDeCuatro() {
    var bien = false;
    palabraAbajo = document.getElementById("letra21").value.toUpperCase() +
        document.getElementById("letra22").value.toUpperCase() +
        document.getElementById("letra23").value.toUpperCase() +
        document.getElementById("letra24").value.toUpperCase();
    palabraAbajo = eliminarAcentos(palabraAbajo);
    palabraArriba = document.getElementById("letra13").value.toUpperCase() +
        document.getElementById("letra14").value.toUpperCase() +
        document.getElementById("letra15").value.toUpperCase() +
        document.getElementById("letra16").value.toUpperCase();
    palabraArriba = eliminarAcentos(palabraArriba);
    palabraMedio = document.getElementById("letra17").value.toUpperCase() +
        document.getElementById("letra18").value.toUpperCase() +
        document.getElementById("letra19").value.toUpperCase() +
        document.getElementById("letra20").value.toUpperCase();
    palabraMedio = eliminarAcentos(palabraMedio);

    if (palabraAbajo != "") {
        var contadorAbajo = 0;
        var contadorArriba = 0;
        for (let i = 0; i < palabraMedio.length; i++) {
            if (palabraAbajo.includes(palabraMedio[i])) {
                contadorAbajo++;
                palabraAbajo = palabraAbajo.replace(palabraMedio[i], "");
            }
            if (palabraArriba.includes(palabraMedio[i])) {
                contadorArriba++;
                palabraArriba = palabraArriba.replace(palabraMedio[i], "");
            }
        }
        if (contadorAbajo == 3 && contadorArriba == 4) {
            bien = true;
        } else {
            bien = false;
        }
    }
    if (bien) {
        document.getElementById("letra17").setAttribute('style', 'background:#47FF33');
        document.getElementById("letra18").setAttribute('style', 'background:#47FF33');
        document.getElementById("letra19").setAttribute('style', 'background:#47FF33');
        document.getElementById("letra20").setAttribute('style', 'background:#47FF33');
    } else {
        document.getElementById("letra17").setAttribute('style', 'background:#FF4500');
        document.getElementById("letra18").setAttribute('style', 'background:#FF4500');
        document.getElementById("letra19").setAttribute('style', 'background:#FF4500');
        document.getElementById("letra20").setAttribute('style', 'background:#FF4500');
    }

}

function filasDeSeis() {
    var contador = 0;
    for (let i = 25; i < 44; i += 6) {
        var palabraArriba = document.getElementById("letra" + i).value.toLowerCase() +
            document.getElementById("letra" + (i + 1)).value.toLowerCase() +
            document.getElementById("letra" + (i + 2)).value.toLowerCase() +
            document.getElementById("letra" + (i + 3)).value.toLowerCase() +
            document.getElementById("letra" + (i + 4)).value.toLowerCase() +
            document.getElementById("letra" + (i + 5)).value.toLowerCase();

        var palabraAbajo = document.getElementById("letra" + (i + 6)).value.toLowerCase() +
            document.getElementById("letra" + (i + 7)).value.toLowerCase() +
            document.getElementById("letra" + (i + 8)).value.toLowerCase() +
            document.getElementById("letra" + (i + 9)).value.toLowerCase() +
            document.getElementById("letra" + (i + 10)).value.toLowerCase() +
            document.getElementById("letra" + (i + 11)).value.toLowerCase();

        if (contador % 2 == 0) {
            if (comprobarExiste(palabraAbajo) && cincoLetrasIguales(palabraArriba, palabraAbajo)) {
                document.getElementById("letra" + (i + 6)).setAttribute('style', 'background:#47FF33');
                document.getElementById("letra" + (i + 7)).setAttribute('style', 'background:#47FF33');
                document.getElementById("letra" + (i + 8)).setAttribute('style', 'background:#47FF33');
                document.getElementById("letra" + (i + 9)).setAttribute('style', 'background:#47FF33');
                document.getElementById("letra" + (i + 10)).setAttribute('style', 'background:#47FF33');
                document.getElementById("letra" + (i + 11)).setAttribute('style', 'background:#47FF33');
            } else {
                document.getElementById("letra" + (i + 6)).setAttribute('style', 'background:#FF4500');
                document.getElementById("letra" + (i + 7)).setAttribute('style', 'background:#FF4500');
                document.getElementById("letra" + (i + 8)).setAttribute('style', 'background:#FF4500');
                document.getElementById("letra" + (i + 9)).setAttribute('style', 'background:#FF4500');
                document.getElementById("letra" + (i + 10)).setAttribute('style', 'background:#FF4500');
                document.getElementById("letra" + (i + 11)).setAttribute('style', 'background:#FF4500');
            }
        } else {
            if (comprobarExiste(palabraAbajo) && seisLetrasIguales(palabraArriba, palabraAbajo)) {
                document.getElementById("letra" + (i + 6)).setAttribute('style', 'background:#47FF33');
                document.getElementById("letra" + (i + 7)).setAttribute('style', 'background:#47FF33');
                document.getElementById("letra" + (i + 8)).setAttribute('style', 'background:#47FF33');
                document.getElementById("letra" + (i + 9)).setAttribute('style', 'background:#47FF33');
                document.getElementById("letra" + (i + 10)).setAttribute('style', 'background:#47FF33');
                document.getElementById("letra" + (i + 11)).setAttribute('style', 'background:#47FF33');
            } else {
                document.getElementById("letra" + (i + 6)).setAttribute('style', 'background:#FF4500');
                document.getElementById("letra" + (i + 7)).setAttribute('style', 'background:#FF4500');
                document.getElementById("letra" + (i + 8)).setAttribute('style', 'background:#FF4500');
                document.getElementById("letra" + (i + 9)).setAttribute('style', 'background:#FF4500');
                document.getElementById("letra" + (i + 10)).setAttribute('style', 'background:#FF4500');
                document.getElementById("letra" + (i + 11)).setAttribute('style', 'background:#FF4500');
            }
        }
        contador++;
    }
}

function ultFilaDeSeis() {

    var bien = false;
    palabraAbajo = document.getElementById("letra55").value.toUpperCase() +
        document.getElementById("letra56").value.toUpperCase() +
        document.getElementById("letra57").value.toUpperCase() +
        document.getElementById("letra58").value.toUpperCase() +
        document.getElementById("letra59").value.toUpperCase() +
        document.getElementById("letra60").value.toUpperCase();
    palabraAbajo = eliminarAcentos(palabraAbajo);
    palabraArriba = document.getElementById("letra43").value.toUpperCase() +
        document.getElementById("letra44").value.toUpperCase() +
        document.getElementById("letra45").value.toUpperCase() +
        document.getElementById("letra46").value.toUpperCase() +
        document.getElementById("letra47").value.toUpperCase() +
        document.getElementById("letra48").value.toUpperCase();
    palabraArriba = eliminarAcentos(palabraArriba);
    palabraMedio = document.getElementById("letra49").value.toUpperCase() +
        document.getElementById("letra50").value.toUpperCase() +
        document.getElementById("letra51").value.toUpperCase() +
        document.getElementById("letra52").value.toUpperCase() +
        document.getElementById("letra53").value.toUpperCase() +
        document.getElementById("letra54").value.toUpperCase();
    palabraMedio = eliminarAcentos(palabraMedio);

    if (palabraAbajo != "") {
        var contadorAbajo = 0;
        var contadorArriba = 0;
        for (let i = 0; i < palabraMedio.length; i++) {
            if (palabraAbajo.includes(palabraMedio[i])) {
                contadorAbajo++;
                palabraAbajo = palabraAbajo.replace(palabraMedio[i], "");
            }
            if (palabraArriba.includes(palabraMedio[i])) {
                contadorArriba++;
                palabraArriba = palabraArriba.replace(palabraMedio[i], "");
            }
        }
        if (contadorAbajo == 5 && contadorArriba == 6) {
            bien = true;
        } else {
            bien = false;
        }
    }
    if (bien) {
        document.getElementById("letra49").setAttribute('style', 'background:#47FF33');
        document.getElementById("letra50").setAttribute('style', 'background:#47FF33');
        document.getElementById("letra51").setAttribute('style', 'background:#47FF33');
        document.getElementById("letra52").setAttribute('style', 'background:#47FF33');
        document.getElementById("letra53").setAttribute('style', 'background:#47FF33');
        document.getElementById("letra54").setAttribute('style', 'background:#47FF33');
    } else {
        document.getElementById("letra49").setAttribute('style', 'background:#FF4500');
        document.getElementById("letra50").setAttribute('style', 'background:#FF4500');
        document.getElementById("letra51").setAttribute('style', 'background:#FF4500');
        document.getElementById("letra52").setAttribute('style', 'background:#FF4500');
        document.getElementById("letra53").setAttribute('style', 'background:#FF4500');
        document.getElementById("letra54").setAttribute('style', 'background:#FF4500');
    }
}

function tresLetrasIguales(palabra1, palabra2) {
    var contador = 0;
    palabra2 = eliminarAcentos(palabra2);
    for (let i = 0; i < palabra2.length; i++) {
        if (eliminarAcentos(palabra1).includes(palabra2[i])) {
            contador++;
            palabra1 = palabra1.replace(palabra2[i], "");
        }
    }
    if (contador == 3) {
        return true;
    } else {
        return false;
    }
}

function cuatroLetrasIguales(palabra1, palabra2) {
    var contador = 0;
    palabra2 = eliminarAcentos(palabra2);
    for (let i = 0; i < palabra2.length; i++) {
        if (eliminarAcentos(palabra1).includes(palabra2[i])) {
            contador++;
            palabra1 = palabra1.replace(palabra2[i], "");
        }
    }
    if (contador == 4) {
        return true;
    } else {
        return false;
    }
}

function cincoLetrasIguales(palabra1, palabra2) {
    var contador = 0;
    palabra2 = eliminarAcentos(palabra2);
    for (let i = 0; i < palabra2.length; i++) {
        if (eliminarAcentos(palabra1).includes(palabra2[i])) {
            contador++;
            palabra1 = palabra1.replace(palabra2[i], "");
        }
    }
    if (contador == 5) {
        return true;
    } else {
        return false;
    }
}

function seisLetrasIguales(palabra1, palabra2) {
    var contador = 0;
    palabra2 = eliminarAcentos(palabra2);
    for (let i = 0; i < palabra2.length; i++) {
        if (eliminarAcentos(palabra1).includes(palabra2[i])) {
            contador++;
            palabra1 = palabra1.replace(palabra2[i], "");
        }
    }
    if (contador == 6) {
        return true;
    } else {
        return false;
    }
}

function comprobarExiste(palabra) {
    for (let i = 0; i < array.length; i++) {
        if (palabra == array[i]) {
            return true;
            palabra1 = palabra1.replace(palabra2[i], "");
        }
    }
    return false;
}

function comprobarFilasPrediseñadas() {
    comprobarFila1();
    comprobarFila2();
    comprobarFila3();
    comprobarFila4();
}

function comprobarFila1() {
    var palabra = document.getElementById("letra1").value.toUpperCase() +
        document.getElementById("letra2").value.toUpperCase() +
        document.getElementById("letra3").value.toUpperCase() +
        document.getElementById("letra4").value.toUpperCase();

    if (palabra == "CLAN") {
        document.getElementById("letra1").setAttribute('style', 'background:#47FF33');
        document.getElementById("letra2").setAttribute('style', 'background:#47FF33');
        document.getElementById("letra3").setAttribute('style', 'background:#47FF33');
        document.getElementById("letra4").setAttribute('style', 'background:#47FF33');
    } else {
        document.getElementById("letra1").setAttribute('style', 'background:#FF4500');
        document.getElementById("letra2").setAttribute('style', 'background:#FF4500');
        document.getElementById("letra3").setAttribute('style', 'background:#FF4500');
        document.getElementById("letra4").setAttribute('style', 'background:#FF4500');
    }
}

function comprobarFila2() {
    var palabra = document.getElementById("letra21").value.toUpperCase() +
        document.getElementById("letra22").value.toUpperCase() +
        document.getElementById("letra23").value.toUpperCase() +
        document.getElementById("letra24").value.toUpperCase();

    if (palabra == "PENA") {
        document.getElementById("letra21").setAttribute('style', 'background:#47FF33');
        document.getElementById("letra22").setAttribute('style', 'background:#47FF33');
        document.getElementById("letra23").setAttribute('style', 'background:#47FF33');
        document.getElementById("letra24").setAttribute('style', 'background:#47FF33');
    } else {
        document.getElementById("letra21").setAttribute('style', 'background:#FF4500');
        document.getElementById("letra22").setAttribute('style', 'background:#FF4500');
        document.getElementById("letra23").setAttribute('style', 'background:#FF4500');
        document.getElementById("letra24").setAttribute('style', 'background:#FF4500');
    }
}

function comprobarFila3() {
    var palabra = document.getElementById("letra25").value.toUpperCase() +
        document.getElementById("letra26").value.toUpperCase() +
        document.getElementById("letra27").value.toUpperCase() +
        document.getElementById("letra28").value.toUpperCase() +
        document.getElementById("letra29").value.toUpperCase() +
        document.getElementById("letra30").value.toUpperCase();

    if (palabra == "REMATO") {
        document.getElementById("letra25").setAttribute('style', 'background:#47FF33');
        document.getElementById("letra26").setAttribute('style', 'background:#47FF33');
        document.getElementById("letra27").setAttribute('style', 'background:#47FF33');
        document.getElementById("letra28").setAttribute('style', 'background:#47FF33');
        document.getElementById("letra29").setAttribute('style', 'background:#47FF33');
        document.getElementById("letra30").setAttribute('style', 'background:#47FF33');
    } else {
        document.getElementById("letra25").setAttribute('style', 'background:#FF4500');
        document.getElementById("letra26").setAttribute('style', 'background:#FF4500');
        document.getElementById("letra27").setAttribute('style', 'background:#FF4500');
        document.getElementById("letra28").setAttribute('style', 'background:#FF4500');
        document.getElementById("letra29").setAttribute('style', 'background:#FF4500');
        document.getElementById("letra30").setAttribute('style', 'background:#FF4500');
    }
}

function comprobarFila4() {
    var palabra = document.getElementById("letra55").value.toUpperCase() +
        document.getElementById("letra56").value.toUpperCase() +
        document.getElementById("letra57").value.toUpperCase() +
        document.getElementById("letra58").value.toUpperCase() +
        document.getElementById("letra59").value.toUpperCase() +
        document.getElementById("letra60").value.toUpperCase();

    if (palabra == "TORERO") {
        document.getElementById("letra55").setAttribute('style', 'background:#47FF33');
        document.getElementById("letra56").setAttribute('style', 'background:#47FF33');
        document.getElementById("letra57").setAttribute('style', 'background:#47FF33');
        document.getElementById("letra58").setAttribute('style', 'background:#47FF33');
        document.getElementById("letra59").setAttribute('style', 'background:#47FF33');
        document.getElementById("letra60").setAttribute('style', 'background:#47FF33');
    } else {
        document.getElementById("letra55").setAttribute('style', 'background:#FF4500');
        document.getElementById("letra56").setAttribute('style', 'background:#FF4500');
        document.getElementById("letra57").setAttribute('style', 'background:#FF4500');
        document.getElementById("letra58").setAttribute('style', 'background:#FF4500');
        document.getElementById("letra59").setAttribute('style', 'background:#FF4500');
        document.getElementById("letra60").setAttribute('style', 'background:#FF4500');
    }
}

function buscarPistas() {
    var letras = document.getElementById('pistas').value;
    var palabraDiccionario;
    var palabrasValidas = [];
    var contador = 0;
    var contadorIntroducidas = 0;

    if (numeroPistas == 0) {
        window.alert("No tienes más pistas disponibles");
    } else {
        for (let i = 0; i < array.length; i++) {
            contador = 0
            palabraDiccionario = array[i];
            for (let j = 0; j < letras.length; j++) {
                if (palabraDiccionario.includes(letras[j])) {
                    contador++;
                    palabraDiccionario = palabraDiccionario.replace(letras[j], '');
                }
            }
            if (contador == letras.length && (array[i].length == 4 || array[i].length == 6)) {
                palabrasValidas[contadorIntroducidas] = array[i];
                contadorIntroducidas++;
            }
        }
        if (numeroPistas != 1) {
            numeroPistas--;
            window.alert("Te quedan " + numeroPistas + " pistas");
        } else {
            numeroPistas--;
            window.alert("Acabas de agotar las pistas disponibles");
        }
        document.getElementById('areaPistas').value = palabrasValidas.toString();
    }
}

function guardarTablero() {
    for (let i = 1; i < 61; i++) {
        localStorage.setItem("letra" + i, document.getElementById("letra" + i).value);
    }
}

function cargarTablero() {
    for (let i = 1; i < 61; i++) {
        document.getElementById("letra" + i).value = localStorage.getItem("letra" + i);
    }
}

function acceptarCookies() {
    localStorage.acceptCookies = 'true';
    $('#div-cookies').hide();
}

function borrarAlmacenamiento() {
    for (let i = 1; i < 61; i++) {
        localStorage.setItem("letra" + i, "");
    }
}