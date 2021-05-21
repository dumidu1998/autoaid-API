module.exports = app => {
    const customer = require("../controllers/customer.controller.js");

    //get - for retrive
    //post - for insert
    //put - for update
    //delete - for delete


    app.get("/customer/:custId", customer.findOne);
    app.post("/customer", customer.create);

};