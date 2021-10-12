
const xhttp = new XMLHttpRequest();
xhttp.open('GET', '../scripts/categorias.json', true);

xhttp.send();

xhttp.onreadystatechange = function () {

    if (this.readyState == 4 && this.status == 200) {

        let datos = JSON.parse(this.responseText);

        let tablaCategorias = document.querySelector('#tablaCategorias');
        tablaCategorias.innerHTML = '';

        let html = "";

        html += `<tr> <th><a href="/Cliente_Productos" title="Categoria ${datos[0].nombre}"><img src="${datos[0].imagen}" alt="" width="${datos[0].ancho}"/></a></th>`;
        for (let i = 1; i < 4; i++) {
            html += `
                <th><img src="${datos[i].imagen}" alt="" width="${datos[i].ancho}"/></th>
            `
        }
        html += `</tr>`;

        html += `<tr style="text-align: center">`;
        for (let i = 0; i < 4; i++) {
            html += `
                <td>${datos[i].nombre}</td>
            `
        }
        html += `</tr>`;

        //--------------------------------------------------------------------------------------

        for (let i = 4; i < 8; i++) {
            html += `
                <th><img src="${datos[i].imagen}" alt="" width="${datos[i].ancho}"/></th>
            `
        }
        html += `</tr>`;

        html += `<tr style="text-align: center">`;
        for (let i = 4; i < 8; i++) {
            html += `
                <td>${datos[i].nombre}</td>
            `
        }
        html += `</tr>`;
        
        tablaCategorias.innerHTML = html;

    }

};




