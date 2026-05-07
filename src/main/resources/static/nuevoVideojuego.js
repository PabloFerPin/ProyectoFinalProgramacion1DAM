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

obtenerGeneros()

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

obtenerPlataformas()

const form = document.getElementById("form-videojuego");

form.addEventListener("submit", function (event) {
    event.preventDefault();

    const generos = [];
    for (let checkbox of document.querySelectorAll("#generos input:checked")) {
        generos.push({nombre: checkbox.value});
    }

    const plataformas = [];
    for (let checkbox of document.querySelectorAll("#plataformas input:checked")) {
        plataformas.push({nombre: checkbox.value});
    }

    if (generos.length === 0) {
        alert("Por favor, selecciona al menos un género.");
        return;
    }

    if (plataformas.length === 0) {
        alert("Por favor, selecciona al menos una plataforma.");
        return;
    }

    const titulo = document.getElementById("titulo").value;
    const desarrolladora = document.getElementById("desarrolladora").value;
    const fechaSalida = document.getElementById("fechaSalida").value;
    const horasJugadas = parseFloat(document.getElementById("horasJugadas").value);
    const completado = document.getElementById("completado").checked;

    const dto = {
        titulo,
        desarrolladora,
        fechaSalida,
        horasJugadas,
        completado,
        generos,
        plataformas
    };

    fetch("http://localhost:8080/api/videojuegos", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(dto)
    }).then(response => {
        if (response.ok) {
            window.location.href = "index.html";
        } else {
            alert("Error al guardar el videojuego");
        }
    }).catch(error => {
        console.error("Error en la petición:", error);
        alert("No se pudo conectar con el servidor.");
    });
});