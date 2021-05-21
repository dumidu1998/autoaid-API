const Customer = require("../models/customer.model.js");

// Create and Save a new Customer
exports.create = (req, res) => {
    if (!req.body) {
        res.status(400).send({
            message: "Content can not be empty!"
        });
    }
    // Create a Customer
    const customer = new Customer({
        name: req.body.name,
        age: req.body.age,
        city: req.body.city
    });
    // Save Customer in the database
    Customer.create(customer, (err, data) => {
        if (err)
            res.status(500).send({
                message:
                    err.message || "Some error occurred while creating the tour."
            });
        else res.send(data);
    });
};

// Find a single Customer with a customerId
exports.findOne = (req, res) => {

    Customer.findById(req.params.custId, (err, data) => {
        if (err) {
            if (err.kind === "not_found") {
                res.status(404).send({
                    message: `Not found customer with id ${req.params.custId}.`
                });
            } else {
                res.status(500).send({
                    message: "Error retrieving customer with id " + req.params.custId
                });
            }
        } else res.send(data);
    });
};