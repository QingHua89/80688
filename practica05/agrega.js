function agrega(){
    nodo = document.createElement("li")
    //texto = document.createTextNode("hola")
    texto = document.createTextNode(document.getElementById("msj").value)
    nodo.appendChild(texto)
    nodo.setAttribute("id","jdx")
    document.getElementById("lista").appendChild(nodo)
}