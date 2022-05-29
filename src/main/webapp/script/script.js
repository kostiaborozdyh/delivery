const t1 = document.getElementById('textCityFrom')
const t2 = document.getElementById('textCityTo')
const tb = document.getElementsByTagName("table")
const pag = document.getElementsByClassName("pagination")
if (pag.length !== 0) {
    pag[0].scrollIntoView(false)
} else {
    if (tb.length !== 0) {
        tb[tb.length - 1].scrollIntoView(false)
    }
}
let a2 = 1;
let a1 = 1;
t2.addEventListener('click', foo2);
t1.addEventListener('click', foo1);

function foo1() {
    a1 = a1 + 1
    const d1 = document.getElementById(`divCityFrom${a1}`)
    d1.innerHTML = `<label class="mb-1"><input type="text" id="idCityFrom${a1}" name="cityFrom${a1}" required></label><br>`
    if (a1 === 5) t1.style.visibility = "hidden"
}

function foo2() {
    a2 = a2 + 1
    const d2 = document.getElementById(`divCityTo${a2}`)
    d2.innerHTML = `<label class="mb-1"><input type="text" id="idCityTo${a2}" name="cityTo${a2}" required></label><br>`
    if (a2 === 5) t2.style.visibility = "hidden"
}