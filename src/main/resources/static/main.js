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
        tabla.appendChild(fila);
    }
}

obtenerVideojuegos()