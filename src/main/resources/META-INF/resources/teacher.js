var dge = id => document.getElementById(id)
var addTeacher = id => fetch('teacher?name=' + dge(id).value, { method: 'POST' }).then(load)
var load = () => fetch('teacher').then(r => r.text()).then(t => dge('t').innerHTML = t)
var del = id => fetch('teacher/' + id, { method: 'DELETE' }).then(load)
var save = id => fetch('teacher/' + id + "/name", { method: 'POST', body: dge('tn' + id).value }).then(load)
var hist = id => fetch('teacher/' + id + "/history").then(r => r.text()).then(alert)
load();