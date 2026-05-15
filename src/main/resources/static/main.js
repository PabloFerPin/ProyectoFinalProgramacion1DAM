async function obtenerVideojuegos() {
    const respuesta = await fetch("http://localhost:8080/api/videojuegos");
    const arrayVideojuegos = await respuesta.json();
    crearTabla(arrayVideojuegos);
}

function crearTabla(arrayVideojuegos) {
    const tabla = document.getElementById("cuerpoDeLaTabla");

    for (let x of arrayVideojuegos) {
        const fila = document.createElement("tr");
        fila.className = "align-middle";

        const columnaTitulos = document.createElement("td");
        columnaTitulos.textContent = x.titulo;

        const columnaGeneros = document.createElement("td");
        for (let y of x.generos) {
            columnaGeneros.textContent += y.nombre + ", ";
        }
        columnaGeneros.textContent = columnaGeneros.textContent.slice(0, -2);

        const columnaPlataforma = document.createElement("td");
        for (let y of x.plataformas) {
            columnaPlataforma.textContent += y.nombre + ", ";
        }
        columnaPlataforma.textContent = columnaPlataforma.textContent.slice(0, -2);

        const columnaFechaSalida = document.createElement("td");
        columnaFechaSalida.textContent = x.fechaSalida;

        const columnaHorasJugadas = document.createElement("td");
        columnaHorasJugadas.textContent = x.horasJugadas;

        const columnaCompletado = document.createElement("td");
        columnaCompletado.innerHTML = x.completado
            ? '<i class="bi bi-check-circle-fill text-success fs-5"></i>'
            : '<i class="bi bi-x-circle-fill text-danger fs-5"></i>';

        const columnaDesarrolladora = document.createElement("td");
        columnaDesarrolladora.textContent = x.desarrolladora;

        const columnaBotonModificar = document.createElement("td");
        const botonModificar = document.createElement("button");
        botonModificar.innerHTML = '<i class="bi bi-pencil-square"></i>';
        botonModificar.onclick = function () {
            window.location.href = "modificarVideojuego.html?variableTemporal=" + x.id_videojuego;
        };
        columnaBotonModificar.appendChild(botonModificar);

        const columnaBotonEliminar = document.createElement("td");
        const botonEliminar = document.createElement("button");
        botonEliminar.textContent = "🗑️";
        botonEliminar.innerHTML = '<i class="bi bi-trash3"></i>';
        botonEliminar.onclick = function () {
            fetch("http://localhost:8080/api/videojuegos/" + x.id_videojuego, {
                method: "DELETE"
            }).then(function () {
                location.reload();
            });
        };
        columnaBotonEliminar.appendChild(botonEliminar);

        fila.appendChild(columnaTitulos);
        fila.appendChild(columnaDesarrolladora);
        fila.appendChild(columnaFechaSalida);
        fila.appendChild(columnaHorasJugadas);
        fila.appendChild(columnaCompletado);
        fila.appendChild(columnaGeneros);
        fila.appendChild(columnaPlataforma);
        fila.appendChild(columnaBotonModificar);
        fila.appendChild(columnaBotonEliminar);
        tabla.appendChild(fila)
    }
}

obtenerVideojuegos();

async function importarCsv() {
    const archivo = document.getElementById("archivoCsv").files[0];
    if (!archivo) return;

    const respuesta = await fetch("http://localhost:8080/api/videojuegos/csv", {
        method: "POST",
        headers: {"Content-Type": "text/csv"},
        body: archivo
    });
    if (respuesta.ok) {
        alert("CSV importado correctamente");
        location.reload();
    }
}

async function exportarCsv() {
    const respuesta = await fetch("http://localhost:8080/api/videojuegos/downloadCSV");
    const blob = await respuesta.blob();
    const url = URL.createObjectURL(blob);
    const a = document.createElement("a");
    a.href = url;
    a.download = "videojuegos.csv";
    a.click();
}