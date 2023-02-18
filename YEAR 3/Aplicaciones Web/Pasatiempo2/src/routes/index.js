const express=require('express');
const router=express.Router();

//routes
router.get('/',(req,res)=>{
    //res.sendFile(path.join(__dirname,'/views/index.html')); // __dirname da la ruta del arhivo que lo ejecuta, en este caso index.js
    res.render('index.html',{title:'Pasatiempos Online'});
})

router.get('/contact',(req,res)=>{
    //res.sendFile(path.join(__dirname,'/views/index.html')); // __dirname da la ruta del arhivo que lo ejecuta, en este caso index.js
    res.render('contact.html',{title:'Contact Page'});
})

router.get('/pasatiempo1',(req,res)=>{
    //res.sendFile(path.join(__dirname,'/views/index.html')); // __dirname da la ruta del arhivo que lo ejecuta, en este caso index.js
    res.render('pasatiempo1.html');
})

router.get('/pasatiempo2',(req,res)=>{
    //res.sendFile(path.join(__dirname,'/views/index.html')); // __dirname da la ruta del arhivo que lo ejecuta, en este caso index.js
    res.render('pasatiempo2.html');
})

router.get('/pasatiempo3',(req,res)=>{
    //res.sendFile(path.join(__dirname,'/views/index.html')); // __dirname da la ruta del arhivo que lo ejecuta, en este caso index.js
    res.render('pasatiempo3.html');
})

module.exports=router;