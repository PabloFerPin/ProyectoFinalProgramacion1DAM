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