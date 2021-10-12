
const xhttp = new XMLHttpRequest();
xhttp.open('GET', '../scripts/categorias.json', true);

xhttp.send();

xhttp.onreadystatechange = function () {

    if (this.readyState == 4 && this.status == 200) {

        let datos = JSON.parse(this.responseText);

        let tablaCategorias = document.querySelector('#tablaCategorias');
        tablaCategorias.innerHTML = '';

        tablaCategorias.innerHTML += `<tr> <th><a href="/Cliente_Productos" title="Categoria ${datos[0].nombre}"><img src="${datos[0].imagen}" alt="" width="${datos[0].ancho}"/></a></th>`;
        for (let i = 1; i < 4; i++) {
            tablaCategorias.innerHTML += `
                <th><img src="${datos[i].imagen}" alt="" width="${datos[i].ancho}"/></th>
            `
        }
        tablaCategorias.innerHTML += '</tr>';

        tablaCategorias.innerHTML += '<tr style="text-align: center">';
        for (let i = 0; i < 4; i++) {
            tablaCategorias.innerHTML += `
                <td>${datos[i].nombre}</td>
            `
        }
        tablaCategorias.innerHTML += '</tr>';

        //--------------------------------------------------------------------------------------

        for (let i = 4; i < 8; i++) {
            tablaCategorias.innerHTML += `
                <th><img src="${datos[i].imagen}" alt="" width="${datos[i].ancho}"/></th>
            `
        }
        tablaCategorias.innerHTML += '</tr>';

        tablaCategorias.innerHTML += '<tr style="text-align: center">';
        for (let i = 4; i < 8; i++) {
            tablaCategorias.innerHTML += `
                <td>${datos[i].nombre}</td>
            `
        }
        tablaCategorias.innerHTML += '</tr>';

    }

};




