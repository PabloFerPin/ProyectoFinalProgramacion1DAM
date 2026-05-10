async function obtenerVideojuegos() {
    const respuesta = await fetch("http://localhost:8080/api/videojuegos");
    const arrayVideojuegos = await respuesta.json();

    crearTabla(arrayVideojuegos)
}

function crearTabla(arrayVideojuegos) {
    const tabla = document.getElementById("cuerpoDeLaTabla")

    for (let x of arrayVideojuegos) {
        const fila = document.createElement("tr");

        const columnaTitulos = document.createElement("td")
        columnaTitulos.textContent = x.titulo;

        const columnaFechaSalida = document.createElement("td")
        columnaFechaSalida.textContent = x.fechaSalida;

        const columnahorasJugadas = document.createElement("td")
        columnahorasJugadas.textContent = x.horasJugadas;

        const columnaCompletado = document.createElement("td")
        columnaCompletado.textContent = x.completado;

        const columnaDesarrolladora = document.createElement("td")
        columnaDesarrolladora.textContent = x.desarrolladora;

        const columnaBotonModificar = document.createElement("td")
        const botonModificar = document.createElement("button")
        botonModificar.textContent = "\u2699\uFE0F"
        botonModificar.onclick = function () {
            window.location.href = "modificarVideojuego.html?variableTemporal=" + x.id_videojuego
        }
        columnaBotonModificar.appendChild(botonModificar)

        const columnaBotonEliminar = document.createElement("td")
        const botonEliminar = document.createElement("button")
        botonEliminar.textContent = "\u274C"
        botonEliminar.onclick = function () {
            fetch("http://localhost:8080/api/videojuegos/" + x.titulo, {
                method: "Delete"
            }).then(function () {
                location.reload()
            })
        }
        columnaBotonEliminar.appendChild(botonEliminar)

        const columnaGeneros = document.createElement("td")
        for (let y of x.generos) {
            columnaGeneros.textContent += y.nombre + ", "
        }
        columnaGeneros.textContent = columnaGeneros.textContent.slice(0, -2);

        const columnaPlataforma = document.createElement("td")
        for (let y of x.plataformas) {
            columnaPlataforma.textContent += y.nombre + ", "
        }
        columnaPlataforma.textContent = columnaPlataforma.textContent.slice(0, -2);

        fila.appendChild(columnaTitulos);
        fila.appendChild(columnaGeneros);
        fila.appendChild(columnaPlataforma);
        fila.appendChild(columnaFechaSalida);
        fila.appendChild(columnahorasJugadas);
        fila.appendChild(columnaCompletado);
        fila.appendChild(columnaDesarrolladora);
        fila.appendChild(columnaBotonModificar)
        fila.appendChild(columnaBotonEliminar)
        tabla.appendChild(fila);
    }
}

obtenerVideojuegos()

function importarCsv() {
    const archivo = document.getElementById("archivoCsv").files[0];

    fetch("http://localhost:8080/api/videojuegos/csv",{
        method: "POST",
        headers: {"Content-Type": "text/csv"},
        body: archivo
    }).then(function temp() {
        alert("CSV importado correctamente");
    });
}