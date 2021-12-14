
const registrierenBtn = document.getElementById("registrierenBtn");
const loginBtn = document.getElementById("loginBtn");
const form = document.getElementById("anmeldungForm");
const passwordInput = document.getElementById("password");
const reAnButtons = document.getElementById("ReAnButtons");

function registrieren(){
    let div = document.createElement("div");
    let input = document.createElement("input");
    input.type = "text";
    input.setAttribute("class", "form-control w-25 mt-4");
    input.setAttribute("placeholder", "passwort wiederholen");
    input.setAttribute("required", "true");
    div.appendChild(input);
    div.setAttribute("class", "d-flex justify-content-center");

    form.insertBefore(div, reAnButtons);
    reAnButtons.removeChild(loginBtn);
}