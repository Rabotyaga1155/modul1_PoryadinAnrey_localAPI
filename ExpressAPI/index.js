const express = require("express");
const app = express();
app.use(express.json());
const port = 3000;

const logins = []

app.get("/", (req, res) => {
    res.json({ logins });
});

app.post("/",(req,res) =>{
    console.log(req.body)
    if(req.body.login){
        logins.push(req.body.login)
        res.json({status:`${req.body.login} is your login`} );
    }
    else
    {
        res.json({status: `Some data is missing: ${JSON.stringify({login: ""})}`,
    });
    }
    
});

app.put("/",(req,res) =>{
    const {oldName, newName} = req.body;

    if (oldName && newName){
        const index = logins.findIndex((friendName) => friendName === oldName);
        logins[index] = newName;
        res.json({status: `${oldName} renamed to ${newName}`});
    }
    else{
        res.json({status: `Some data is missing: ${JSON.stringify({
            oldName: oldName || "",
            newName: newName || "",
        })}`,
    });
    }
    res.json({logins});
});

app.delete("/",(req,res) =>{
    res.json({logins});
});

app.listen(port, ()=>{
    console.log('APP listening')
})