console.log('correcto');
function mostrarCategorias() {
    console.log('Dentro de la función');

    const xhttp = new XMLHttpRequest();
    xhttp.open('GET', 'categorias.json', true);
    
    xhttp.onreadystatechange = function () {

        if (this.readyState == 4 && this.status == 200) {
            console.log(this.responseText);
            let datos = JSON.parse(this.responseText);
            console.log(datos);
        }

    };

}


