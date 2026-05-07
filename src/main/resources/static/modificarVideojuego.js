async function obtenerGeneros() {
    const respuesta = await fetch("http://localhost:8080/api/videojuegos/generos");
    const arrayGeneros = await respuesta.json();

    crearCheckboxsGeneros(arrayGeneros);
}

function crearCheckboxsGeneros(arrayGeneros) {
    const divGeneros = document.getElementById("generos");

    for(let x of arrayGeneros) {
        const checkbox = document.createElement("input");
        checkbox.type = "checkbox";
        checkbox.value = x.nombre;
        checkbox.id = "gen-" + x.nombre;

        const label = document.createElement("label");
        label.textContent = x.nombre + "   ";

        divGeneros.appendChild(checkbox);
        divGeneros.appendChild(label);
    }
}

async function obtenerPlataformas() {
    const respuesta = await fetch("http://localhost:8080/api/videojuegos/plataformas");
    const arrayPlataformas = await respuesta.json();

    crearCheckboxsPlataformas(arrayPlataformas);
}

function crearCheckboxsPlataformas(arrayPlataformas) {
    const divGeneros = document.getElementById("plataformas");

    for(let x of arrayPlataformas) {
        const checkbox = document.createElement("input");
        checkbox.type = "checkbox";
        checkbox.value = x.nombre;
        checkbox.id = "pla-" + x.nombre;

        const label = document.createElement("label");
        label.textContent = x.nombre + "   ";

        divGeneros.appendChild(checkbox);
        divGeneros.appendChild(label);
    }
}

const idVideojuego = new URLSearchParams(window.location.search).get("variableTemporal");
async function obtenerDatosVideojuego() {
    const respuesta = await fetch("http://localhost:8080/api/videojuegos/" + idVideojuego)
    const objVideojuego = await respuesta.json()

    rellenarFormulario(objVideojuego)
}

function rellenarFormulario(objVideojuego) {
    document.getElementById("titulo").value = objVideojuego.titulo;
    document.getElementById("desarrolladora").value = objVideojuego.desarrolladora;
    document.getElementById("fechaSalida").value = objVideojuego.fechaSalida;
    document.getElementById("horasJugadas").value = objVideojuego.horasJugadas;
    document.getElementById("completado").checked = objVideojuego.completado;

    for(let x of objVideojuego.generos) {
        const checkbox = document.getElementById("gen-" + x.nombre)
        if(checkbox) {
            checkbox.checked = true
        }
    }

    for(let x of objVideojuego.plataformas) {
        const checkbox = document.getElementById("pla-" + x.nombre)
        if(checkbox) {
            checkbox.checked = true
        }
    }
}

async function init() {
    await obtenerGeneros();
    await obtenerPlataformas();
    await obtenerDatosVideojuego();
}

init();