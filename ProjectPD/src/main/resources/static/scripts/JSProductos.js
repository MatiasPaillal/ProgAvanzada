/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

const xhttp = new XMLHttpRequest();
xhttp.open('GET', '../JSON/JsonProductos.json', true);

xhttp.send();

xhttp.onreadystatechange = function () {

    if (this.readyState == 4 && this.status == 200) {

        let datos = JSON.parse(this.responseText);
        var carro= java.values(Carro);
        let tablaCategorias = document.querySelector('#tablaProductos');
        tablaCategorias.innerHTML = '';

        let html = "";

        html += `<tr> <th><a href="/Admin_ProductoSeleccionado" title="Producto ${datos[0].nombre}"><img src="${datos[0].imagen}" alt="" width="${datos[0].ancho}"/></a></th>`;
        for (let i = 1; i < 5; i++) {
            html += `
                <th><img src="${datos[i].imagen}" alt="" width="${datos[i].ancho}"/></th>
            `
        }
        html += `</tr>`;

        html += `<tr style="text-align: center">`;
        for (let i = 0; i < 5; i++) {
            html += `
                <td>${datos[i].nombre}</td>
            `
        }
        html += `</tr>`;

        //--------------------------------------------------------------------------------------

        for (let i = 5; i < 10; i++) {
            html += `
                <th><img src="${datos[i].imagen}" alt="" width="${datos[i].ancho}"/></th>
            `
        }
        html += `</tr>`;

        html += `<tr style="text-align: center">`;
        for (let i = 5; i < 10; i++) {
            html += `
                <td>${datos[i].nombre}</td>
            `
        }
        html += `</tr>`;
        for (let i = 10; i < 15; i++) {
            html += `
                <th><img src="${datos[i].imagen}" alt="" width="${datos[i].ancho}"/></th>
            `
        }
        html += `</tr>`;

        html += `<tr style="text-align: center">`;
        for (let i = 10; i < 15; i++) {
            html += `
                <td>${datos[i].nombre}</td>
            `
        }
        html += `</tr>`;
        
        tablaCategorias.innerHTML = html;

    }

};


