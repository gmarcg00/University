const express = require('express');
const router = express.Router();
const mysqlConnection =require('../config/mysqlConnection');


router.get('/Registrarse',(req,res) => {
    res.render('Registrarse.ejs', { title: 'Pasatiempos'});
});