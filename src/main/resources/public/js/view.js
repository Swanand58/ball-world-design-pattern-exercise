'use strict';

//app to draw polymorphic shapes on canvas
var app;
let validInterval = -1;
/**
 * Create the ball world app for a canvas
 * @param canvas The canvas to draw balls on
 * @returns {{drawBall: drawBall, clear: clear}}
 */
function createApp(canvas) {
    let c = canvas.getContext("2d");

    /**
     * Draw a circle
     * @param x  The x location coordinate
     * @param y  The y location coordinate
     * @param radius  The circle radius
     * @param color The circl color
     */
    let drawCircle = function(x, y, radius, color) {
        c.fillStyle = color;
        c.beginPath();
        c.arc(x, y, radius, 0, 2 * Math.PI, false);
        c.closePath();
        c.fill();
    };

    let clear = function() {
        c.clearRect(0,0, canvas.width, canvas.height);
    };


    return {
        drawCircle,
        clear,
        dims: {height: canvas.height, width: canvas.width}
    }
}


window.onload = function() {
    app = createApp(document.querySelector("canvas"));
    canvasDims();
    $("#btn-straight").click(function () {loadBall("straight")});
    $("#btn-rotation").click(function () {loadBall("rotation")});
    $("#btn-stop").click(function () {loadBall("stop")});
    $("#btn-teleport").click(function () {loadBall("teleport")});
    $("#btn-zigzag").click(function () {loadBall("zigzag")});
    $("#btn-straightstop").click(function () {loadBall("straightstop")});
    $("#btn-rotatestop").click(function () {loadBall("rotatestop")});
    $("#btn-composite").click(function () {loadBall("composite")});
    $("#btn-zigzagstop").click(function () {loadBall("zigzagstop")});
    $("#btn-drunkrotate").click(function () {loadBall("drunkrotate")});
    $("#btn-unknown").click(function () {loadBall("unknown")});
    $("#btn-remove").click(remove);
    $("#btn-clear").click(clear);
};

function CreateBalls(data) {
    for (var i=0; i<data.length; i++) {
        var a = data[i];
        app.drawCircle(a.loc.x, a.loc.y, a.radius, a.color);
    }
}

/**
 * load ball at a location on the canvas
 */
function loadBall(kind) {
    try {
        let values = kind;

        $.post("/load", { strategies: values, type: "ball"}, function (data) {

            console.log(data);
            if (data === "error") {
                alert("Error while loading the ball");
            }
            CreateBalls(data);
            if (validInterval < 0) {
                validInterval = setInterval(updateBallWorld, 100);
            }
        }, "json");
    }
    catch (error){
        console.error("An error occured:", error)
        alert("Error while loading the ball");
    }
}

/**
 * Switch ball strategies
 */
function switchStrategy() {
    let values = "";

    $.post("/switch", { strategies: values}, function (data) {

    }, "json");
}

function updateBallWorld() {
    try{
        $.get("/update", function(data) {
            if (data === "error") {
                alert("Error Occurred while updating the balls")
            }
            app.clear();
            CreateBalls(data);
        }, "json");
    }
    catch (error){
        console.log(error);
        alert("Error Occurred while updating the balls");
    }
}
function remove(){
    try{
        $.get("/remove", function(data) {
            if (data === "error") {
                alert("Error Occurred while removing the balls")
            }
            app.clear();
            updateBallWorld();
        }, "json");
    }
    catch (error) {
        console.log(error);
        alert("Cannot remove the ball, Error Occurred");
    }
}
/**
 * Pass along the canvas dimensions
 */
function canvasDims() {
    try{
        $.post("/canvas/dims", {height: app.dims.height, width: app.dims.width}, function (data){
            if (data === "error") {
                alert("Error Occurred while setting canvas dimensions")
            }
        }, "json");
    }
    catch(error) {
        console.log(error);
        alert("Cannot set Canvas Dimensions");
    }
}

/**
 * Clear the canvas
 */
function clear() {
    try{
        $.get("/clear");
        clearInterval(validInterval);
        validInterval = -1;
        app.clear();
    }
    catch (error) {
        console.log(error);
        alert("Cannot clear the balls");
    }
}