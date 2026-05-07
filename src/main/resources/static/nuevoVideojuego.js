
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
        return; // Detiene la ejecución aquí
    }

    if (plataformas.length === 0) {
        alert("Por favor, selecciona al menos una plataforma.");
        return; // Detiene la ejecución aquí
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