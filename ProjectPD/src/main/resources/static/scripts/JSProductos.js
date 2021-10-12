/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


console.log("holQ");
function mostrarProductos() {
    console.log('Dentro de la función');

    const xhttp = new XMLHttpRequest();
    xhttp.open('GET', '../scripts/JsonProductos.json', true);
    
    xhttp.onreadystatechange = function () {

        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);
            let datos = JSON.parse(this.responseText);
            console.log(datos);
        }

    };
}